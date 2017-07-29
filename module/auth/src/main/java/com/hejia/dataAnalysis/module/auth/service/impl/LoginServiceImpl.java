package com.hejia.dataAnalysis.module.auth.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.auth.common.ModuleConfig;
import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.account.service.AccountService;
import com.hejia.dataAnalysis.module.auth.service.LoginService;
import com.hejia.dataAnalysis.module.common.Constant;
import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.utils.BeanCopyUtils;
import com.hejia.dataAnalysis.module.common.utils.EhcacheUtils;
import com.hejia.dataAnalysis.module.common.utils.MD5Utils;
import com.hejia.dataAnalysis.module.common.utils.RegexUtils;
import com.hejia.dataAnalysis.module.common.utils.SDESUtils;

/**
 * @Description: 登录接口实现
 * @author: chenyongqiang
 * @Date: 2017年7月17日
 * @version: 1.0
 */
@Service("loginService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class LoginServiceImpl implements LoginService {
	
	private static final Logger log = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private AccountService<Account> accountService;
	
	public ResponsePojo login(RequestArg ra) throws ServiceException {
		// 登录设计：
		// 1.auth系统只负责登录，不负责维护access_token和account
		// 2.access_token是客户端负责维护，而account是每个子系统负责维护，当然可以把account放到公共的地方，例如共享session中去
		// 3.如果每个子系统负责维护account，在集群时，可能account会产生多份，这时可以在nginx中采用分发算法（负载均衡），让同一个ip到固定的系统去，例如使用HashTable算法
		// 4.由于每个系统负责维护account且客户端保存access_token，所以需要快速登录（在过滤器中触发），即访问每个系统时，根据access_token再登录一遍
		// 5.access_token要带有这些信息：登录名（你是谁）、ip（哪里来）、到期时间
		ResponsePojo rp = new ResponsePojo(Boolean.TRUE);
		String verifyType = ra.getString("verifyType");
		// 检查参数
		checkParameter(ra, rp);
		// 检查安全性
		if (rp.isSuccess()) checkSecurity(ra, rp);
		// 检验图片验证码
		if (rp.isSuccess()) checkVerifyCode(ra, rp);
		// 登录
		if (rp.isSuccess()) {
			if (Constant.LOGIN_VERIFY_TYPE_PWD.equalsIgnoreCase((verifyType.trim()))) {
				doLoginByPwd(ra, rp);
			} else if (Constant.LOGIN_VERIFY_TYPE_CODE.equalsIgnoreCase((verifyType.trim()))) {
				doLoginByCode(ra, rp);
			} else {
				rp.setSuccess(Boolean.FALSE);
			}
		}
		// 获取cookie中的访问token
		if (rp.isSuccess()) getAccessToken(ra, rp);
		// 跳转
		if (rp.isSuccess()) getRedirectUrl(ra, rp);
		// 后处理
		postDoLogin(ra, rp);
		return rp;
	}
	
	/**
	 * @Definition: 检查参数
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ra
	 * @param rp
	 */
	private void checkParameter(RequestArg ra, ResponsePojo rp) {
		String platformType = ra.getString("platformType");
		String verifyType = ra.getString("verifyType");
		String loginName = ra.getString("loginName");
		// 必填参数
		if (StringUtils.isBlank(platformType) || StringUtils.isBlank(verifyType)) {
			rp.setSuccess(Boolean.FALSE);
			rp.setFailReason("请求参数错误");
			rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_PARAMETER_ILLEGAL);
			return;
		}
		if (StringUtils.isBlank(loginName)) {
			rp.setSuccess(Boolean.FALSE);
			rp.setFailReason("用户名不能空");
			rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_NAME_CAN_NOT_EMPTY);
			return;
		}
		// 格式
		if (!RegexUtils.isEmail(loginName) && !RegexUtils.isMobile(loginName)) {
			rp.setSuccess(Boolean.FALSE);
			rp.setFailReason("用户名格式错误");
			rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_NAME_FORMAT_ERROR);
			return;
		}
	}
	
	/**
	 * @Definition: 检查安全性（不使用数据库日志作为检查），且不以动态时间来定义时间段，如以当下时间到过去的一个时间段，因为当下时间是动态变化的。以第一个请求时间为开始，以缓存结束时间为结束的时间段
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ra
	 * @param rp
	 */
	private void checkSecurity(RequestArg ra, ResponsePojo rp) {
		// 本质是防止机器暴力破解，机器可以使用的手段包括代理ip、无cookie，机器的目标是破解login_name的密码，所以突破点在于控制login_name的尝试次数 
		// 使用ip可能导致一个局域网内部的所有用户登录不了，即一人捣蛋，大家完蛋
		// 一段时间内单ip登录操作的检查
		String ip = ra.getIp();
		Integer times = (Integer) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_IP_FAIL_TIMES, ip);
		if (times != null) {
			int maxTimes = ModuleConfig.getInt("login_single_ip_fail_max_times");
			if (times >= maxTimes) {// 已经超过阀值
				rp.setSuccess(Boolean.FALSE);
				rp.setFailReason("登录错误次数过多，请稍后再试或重置密码");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_SINGLE_IP_FAIL_TIMES_TOO_MUCH);
				return;
			}
		}
		// 一段时间内单账号登录操作的检查，这里不针对单个ip
		String loginName = ra.getString("loginName").trim();
		times = (Integer) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_ACCOUNT_FAIL_TIMES, loginName);
		if (times != null) {
			int maxTimes = ModuleConfig.getInt("login_single_account_fail_max_times");
			if (times >= maxTimes) {// 已经超过阀值
				rp.setSuccess(Boolean.FALSE);
				rp.setFailReason("登录错误次数过多，请稍后再试或重置密码");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_SINGLE_ACCOUNT_FAIL_TIMES_TOO_MUCH);
				return;
			}
		}
		// 一段时间内单IP+单账号登录操作的检查
		times = (Integer) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES, ip + "#&&#" + loginName);
		if (times != null) {
			int maxTimes = ModuleConfig.getInt("login_single_ip_account_fail_max_times");
			if (times >= maxTimes) {// 已经超过阀值
				rp.setSuccess(Boolean.FALSE);
				rp.setFailReason("登录错误次数过多，请稍后再试或重置密码");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES_TOO_MUCH);
				return;
			}
		}
	}
	
	/**
	 * @Definition: 检查图形验证码
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ra
	 * @param rp
	 */
	private void checkVerifyCode(RequestArg ra, ResponsePojo rp) {
		// 范围从大到小：ip -> cookie(sessionid) -> platform -> login_name
		// 如何识别机器操作？除了单位时间操作次数超过阀值之外
		// 原则：一个平台允许在默认重试的范围内可以不使用图形验证码
		// 那么问题来了，如何判断平台？ip范围内可以有多平台，cookie倒是可以跟平台对应起来，但是cookie可以人为设置为不使用cookie
		// 使用ip作为key，问题是在局域网多个用户在多个平台上操作，只能有一个能成功，其他全失败，即压根就无法定位到一个人，这绝对不允许发生
		// 使用cookie作为key，问题是用户可以不带cookie（sessionid）或者更新cookie（sessionid），这时就不需要认证码了，直接混过关，这也绝对不允许发生
		// 使用平台+login_name，用户换个login_name，便找不到缓存了，这时验证码即使错误，也没办法判断，且有可能给用户错觉，输错验证码也能登录成功
		// 解决方案：这里假设前端配合的非常好，深入理解后端控制的意图，即图形验证与否是完全由后端控制的，前端只需执行该执行的步骤，并且不要遗落任何步骤，否则可能出问题
		// 新原则：一个ip允许在默认重试的范围内可以不使用图形验证码
		// 前端要干的事情如下：
		// 1.每次页面刷新都要想后端发起是否需要图形验证码的请求，如果需要，后端返回一个图形验证码
		// 2.每次登录提交（ajax），若失败，则前端发起是否需要图形验证码的请求，如果需要，后端返回一个图形验证码
		// 后端要干的事情如下：
		// 1.把sessionid当作key来维护图形验证码
		// 2.由ip来决定是否需要验证
		// 3.如果ip决定需要验证，但sessionid（cookie）不存在或不匹配，这时肯定是找不到缓存来比较的，只能默认是操作失败，即验证码错误
		// 4.上一步有个bug，即两个用户的先后是否需要图形验证码的请求，第一个不要（原本不需要，因另一个用户提交失败之后需要，但自己没更新当前页面所致），
		//   第二个因自己重试了一次，且是在第一个用户提交之前，所以这时需要，那么第一个用户这时提交必然是失败的，这时解决方式可以是轮询，也可以是长链接，不过代价太大
		//   另外一种方式是不验证，这样的话，不止两次请求时出现这种情况，N次请求也可能出现，不过随着N的增大，概率越来越小罢了，不单单是两个用户，多个用户也一样。不认证是bug，可能被利用
		// 5.由于存在上一步的bug，还是认证为上，宁可错杀一万，不可放过一个
		// 6.用户可能利用登录成功来让相关缓存清零，这时便可暴力破解了，只不是中间多了多次登录成功操作。所以建议成功不清除缓存，或者只清除部分缓存，但这样可能造成群体累计失败，即多个用户的失败累计。
		//   清除跟单个账户相关的
		// 7.最靠谱的方式是机器学习来判断这些请求的行为特征，会有误差，但是比人为设定阀值好多了，而且机器学习随着数据变多越来越智能
		if (!ModuleConfig.getBoolean("login_fail_show_verify_code")) return;
		String ip = ra.getIp();
		int maxTimes = ModuleConfig.getInt("login_fail_show_verify_code_max_times");
		// 判断是否需要验证码（因为用户可能在多个平台上登录，而所有平台共享一个验证码，所以这个步骤不能忽略，否则可能出现在一个平台上需要验证码，而另外一个平台不需要认证码的情况下，比较验证码）
		boolean isNeedVerifyCode = false;
		Integer times = (Integer) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_IP_FAIL_TIMES, ip);
		if (times != null && times >= maxTimes) isNeedVerifyCode = true;
//		String sessionid = ra.getCookieString("sessionid");
		if (isNeedVerifyCode) { // 虽然前面的步骤确认了需要验证码，但是比较的验证码可能在缓存中不存在
			String verifyCode = ra.getString("verifyCode");
			if (StringUtils.isBlank(verifyCode)) { // 说明请求没把sessionid带过来，或是浏览器禁止cookie，或是机器访问（也禁止cookie），或是平台代码逻辑出错（没生成session）
				rp.setSuccess(Boolean.FALSE);
				rp.setFailReason("图形认证码错误");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_VERIFY_CODE_ERROR);
				return;
			}
			// 判断验证码是否正确
			// 还得配合cookie（sessionid）才行，这样才能具体定位到某个人，而不是某群人（ip，多人在局域网内使用同一ip）
			String sessionId = ra.getCookieString("sessionid");
			if (StringUtils.isBlank(sessionId)) { // 说明请求没把sessionid带过来，或是浏览器禁止cookie，或是机器访问（也禁止cookie），或是平台代码逻辑出错（没生成session）
				rp.setSuccess(Boolean.FALSE);
				rp.setFailReason("图形认证码错误");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_SESSION_ID_EMPTY);
				return;
			}
			String str = (String) EhcacheUtils.get(EhcacheUtils.LOGIN_VERIFY_CODE, sessionId);
			if (StringUtils.isNotBlank(str)) { // 如果缓存中不存在验证码，那就不需要比较了，这时如果账号登录是需要认证码的，则前端用户完胜
				if (!str.equalsIgnoreCase(verifyCode)) {
					rp.setSuccess(Boolean.FALSE);
					rp.setFailReason("图形认证码错误");
					rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_VERIFY_CODE_ERROR);
					return;
				}
			}
		}
	}
	
	/**
	 * @Definition: 密码登录操作
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ra
	 * @param rp
	 */
	private void doLoginByPwd(RequestArg ra, ResponsePojo rp) {
		String loginName = ra.getString("loginName");
		String loginPwd = ra.getString("loginPwd");
		Account a = accountService.findByMobileOrEmail(loginName);
		// 检查账号
		checkAccount(a, ra, rp);
		// 检查密码
		if (rp.isSuccess()) {
			if (!MD5Utils.compare(loginPwd, a.getPwd())) {
				rp.setSuccess(Boolean.FALSE);
				rp.setFailReason("密码错误");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_PWD_ERROR);
			}
		}
		if (rp.isSuccess()) rp.setMessage(a);
	}
	
	/**
	 * @Definition: 检查账号
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param a
	 * @param ra
	 * @param rp
	 */
	private void checkAccount(Account a, RequestArg ra, ResponsePojo rp) {
		if (a == null) {
			String loginName = ra.getString("loginName");
			rp.setSuccess(Boolean.FALSE);
			rp.setFailReason("该" + (RegexUtils.isMobile(loginName) ? "手机" : "邮箱") + "还未注册，请先注册");
			rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_NAME_FORMAT_ERROR);
			return;
		}
		if (a.getStatus() == Account.STATUS_DISABLE || a.getStatus() == Account.STATUS_DELETED) {
			rp.setSuccess(Boolean.FALSE);
			rp.setFailReason("账户状态错误");
			rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_ACCOUNT_STATUS_ERROR);
			return;
		}
	}
	
	/**
	 * @Definition: 验证码登录操作
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ra
	 * @param rp
	 */
	private void doLoginByCode(RequestArg ra, ResponsePojo rp) {
		String loginName = ra.getString("loginName");
		String loginCode = ra.getString("loginCode");
		// 判空
		if (StringUtils.isBlank(loginCode)) {
			rp.setSuccess(Boolean.FALSE);
			rp.setFailReason("验证码错误");
			rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_CODE_ERROR);
			return;
		}
		// 检查验证码，一个账号只有一个验证码，即多个平台同时登录，也只能一个可用，即最新生成的那个验证码
		String str = (String) EhcacheUtils.get(EhcacheUtils.LOGIN_CODE, loginName);
		if (!loginCode.equals(str)) {
			rp.setSuccess(Boolean.FALSE);
			rp.setFailReason("验证码错误");
			rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_CODE_ERROR);
			return;
		}
		Account a = accountService.findByMobileOrEmail(loginName);
		// 检查账号
		checkAccount(a, ra, rp);
		if (rp.isSuccess()) rp.setMessage(a);
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ra
	 * @param rp
	 */
	private void doLoginByUnionid(RequestArg ra, ResponsePojo rp) {
		
	}
	
	/**
	 * @Definition: 获取cookie中的访问token
	 * @author: chenyongqiang
	 * @Date: 2017年7月19日
	 * @param ra
	 * @param rp
	 */
	private void getAccessToken(RequestArg ra, ResponsePojo rp) {
		String loginName = ra.getString("loginName");
		String ip = ra.getIp();
		Map<String, Object> message = new HashMap<String, Object>(5);
		rp.setMessage(message);
		message.put("token", generateAccessToken(loginName, ip));
	}
	
	/**
	 * @Definition: 获取跳转URL（到这一步，说明登录成功了）
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ra
	 * @param rp
	 */
	private void getRedirectUrl(RequestArg ra, ResponsePojo rp) {
		Map<String, Object> message = (Map) rp.getMessage();
		// 登录成功之后，跳到哪里去，客户端可以指定到哪儿，但还是需要到的那个地方的系统做验证，看是否有权限，否则跳到默认页面
		String redirectUrl = ra.getString("redirectUrl");
		if (StringUtils.isNotBlank(redirectUrl)) { // 根据权限范围来决定是否使用该URL，如果不符合，则根据平台使用默认链接
			/*// 判断URL格式是否合法
			if (RegexUtils.isURL(redirectUrl)) {
				// 判断是否属于禾家平台的域名
				String host = HttpUtils.gethost(redirectUrl);
				if (host.endsWith(".hejia.com") || host.startsWith("hejia.com")) {
					// 判断是否具备访问该URL的权限
					String uri = HttpUtils.getUri(redirectUrl);
					
				}
			}*/
			message.put("redirectUrl", redirectUrl);
		}
		if (StringUtils.isBlank(redirectUrl)) { // 根据平台使用默认链接
			int platformType = ra.getInteger("platformType");
			if (platformType == Constant.PLATFORM_ADMIN) {
				message.put("redirectUrl", Constant.systemUrlAdminManage);
			}
		}
	}
	
	/**
	 * @Definition: 登录后续工作，主要记录或者清除登录信息，用于安全性检查，最好别在aop中做，因为跟登录还是非常相关的
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ra
	 * @param rp
	 */
	private void postDoLogin(RequestArg ra, ResponsePojo rp) {
		String ip = ra.getIp();
		String loginName = ra.getStringAndNotNull("loginName").trim();
		if (rp.isSuccess()) { // 清除登录信息
			EhcacheUtils.remove(EhcacheUtils.LOGIN_SINGLE_ACCOUNT_FAIL_TIMES, loginName);
			EhcacheUtils.remove(EhcacheUtils.LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES, ip + "#&&#" + loginName);
		} else { // 记录登录信息
			Integer times = (Integer) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_IP_FAIL_TIMES, ip);
			if (times == null) times = 0;
			EhcacheUtils.put(EhcacheUtils.LOGIN_SINGLE_IP_FAIL_TIMES, ip, ++times);
			
			times = (Integer) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_ACCOUNT_FAIL_TIMES, loginName);
			if (times == null) times = 0;
			EhcacheUtils.put(EhcacheUtils.LOGIN_SINGLE_ACCOUNT_FAIL_TIMES, loginName, ++times);
			times = (Integer) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES, ip + "#&&#" + loginName);
			
			if (times == null) times = 0;
			EhcacheUtils.put(EhcacheUtils.LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES, ip + "#&&#" + loginName, ++times);
		}
	}
	
	/**
	 * 生成访问凭证
	 */
	public String generateAccessToken(String loginName, String ip) throws ServiceException {
		String token = loginName + "|" + ip + '|' + (Constant.COOKIE_TIME_YEAR * 1000 + System.currentTimeMillis());
		try {
			token = SDESUtils.encrypt(Constant.COOKIE_DES_KEY, token);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("生成访问凭证时报错");
		}
		return token;
	}
	
	/**
	 * 校验访问凭证
	 */
	public ResponsePojo checkAccessToken(String token, String ip) throws ServiceException {
		ResponsePojo rp = new ResponsePojo();
		try {
			if (token != null) {//尝试使用cookie登录
				//解密
				log.info("访问凭证解密前：" + token);
				String token2 = SDESUtils.decrypt(Constant.COOKIE_DES_KEY, token);
				log.info("访问凭证解密后：" + token2);
				//判断ip是否 一致
				String strs[] = token2.split("\\|");
				if (strs.length < 2 || !strs[1].equals(ip)) {
					log.info("访问凭证中的ip不一致");
					rp.setMessage("访问凭证中的ip不一致");
				}
				Account a = accountService.findByMobileOrEmail(strs[0]);
				if (a == null) {
					log.info("根据访问凭证找不到用户：" + strs[0]);
					rp.setMessage("根据访问凭证找不到用户");
					return rp;
				}
				rp.setSuccess(Boolean.TRUE);
				a.setAccessToken(token);
				a = getSafeAccount(a);
				rp.setMessage(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rp.setMessage("访问凭证解密时报错");
			log.info("访问凭证解密时报错");
		}
		return rp;
	}
	
	/**
	 * @Definition: 获取安全的用户对象（防止被带到前台去）
	 * @author: chenyongqiang
	 * @Date: 2016年2月24日
	 * @param a
	 * @return
	 * @throws Exception
	 */
	private Account getSafeAccount(Account a) {
		Account a2 = new Account();
		try {
			BeanCopyUtils.copyProperties(a, a2, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		a2.setPwd(null);
		a2.setSalt(null);
		return a2;
	}
}
