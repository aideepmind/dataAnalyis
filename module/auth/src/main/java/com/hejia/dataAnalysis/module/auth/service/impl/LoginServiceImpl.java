package com.hejia.dataAnalysis.module.auth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.auth.service.LoginService;
import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.utils.EhcacheUtils;

/**
 * @Description: 登录接口实现
 * @author: chenyongqiang
 * @Date: 2017年7月17日
 * @version: 1.0
 */
@Service("loginService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class LoginServiceImpl implements LoginService {
	
	public ResponsePojo doLogin(RequestArg ra) throws ServiceException {
		ResponsePojo rp = null;
		String platformType = ra.getString("platformType");
		String redirectUrl = ra.getString("redirectUrl");
		String verifyType = ra.getString("verifyType");
		String loginName = ra.getString("loginName");
		String loginPwd = ra.getString("loginPwd");
		String loginCode = ra.getString("loginCode");
		// 检查安全性
		rp = checkSecurity(ra);
		// 检验图片验证码
		if (rp == null || rp.isSuccess()) rp = checkVerifyCode(ra);
		// 登录
		if (rp == null || rp.isSuccess()) {
			if (verifyType != null && "pwd".equals(verifyType)) {
				rp = doLoginByPwd(loginName, loginPwd);
			} else if(verifyType != null && "code".equals(verifyType)) {
				rp = doLoginByCode(loginName, loginCode);
			}
		}
		// 跳转
		
		return postDoLogin(rp, ra);
	}
	
	/**
	 * @Definition: 登录后续工作，主要记录或者清除登录信息，用于安全性检查，最好别在aop中做，因为跟登录还是非常相关的
	 * @author: chenyongqiang
	 * @Date: 2017年7月17日
	 * @param rp
	 * @param ra
	 * @return
	 */
	private ResponsePojo postDoLogin(ResponsePojo rp, RequestArg ra) {
		
		return rp;
	}
	
	/**
	 * @Definition: 检查安全性（不使用数据库日志作为检查）
	 * @author: chenyongqiang
	 * @Date: 2017年7月17日
	 * @param ra
	 * @return
	 */
	private ResponsePojo checkSecurity(RequestArg ra) {
		ResponsePojo rp = null;
		// 一段时间内单ip登录操作的检查
		String ip = ra.getString("ip");
		String str = (String) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_IP_FAIL_TIMES, ip);
		if (str != null && !"".equals(str)) {
			int maxTimes = 50;
			int times = Integer.parseInt(str);
			if (times > maxTimes) {// 已经超过阀值
				rp = new ResponsePojo();
				rp.setFailReason("操作失败过多，请稍候再试！");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_SINGLE_IP_FAIL_TIMES_TOO_MUCH);
				return rp;
			}
		}
		// 一段时间内单账号登录操作的检查，这里不针对单个ip
		String loginName = ra.getString("loginName");
		str = (String) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_ACCOUNT_FAIL_TIMES, loginName);
		if (str != null && !"".equals(str)) {
			int maxTimes = 50;
			int times = Integer.parseInt(str);
			if (times > maxTimes) {// 已经超过阀值
				rp = new ResponsePojo();
				rp.setFailReason("操作失败过多，请稍候再试！");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_SINGLE_ACCOUNT_FAIL_TIMES_TOO_MUCH);
				return rp;
			}
		}
		// 一段时间内单IP+单账号登录操作的检查
		str = (String) EhcacheUtils.get(EhcacheUtils.LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES, ip + "#&&#" + loginName);
		if (str != null && !"".equals(str)) {
			int maxTimes = 25;
			int times = Integer.parseInt(str);
			if (times > maxTimes) {// 已经超过阀值
				rp = new ResponsePojo();
				rp.setFailReason("操作失败过多，请稍候再试！");
				rp.setFailType(ResponsePojo.FAIL_TYPE_LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES_TOO_MUCH);
				return rp;
			}
		}
		return null;
	}
	
	/**
	 * @Definition: 检测验证码
	 * @author: chenyongqiang
	 * @Date: 2017年7月17日
	 * @param ra
	 * @return
	 */
	private ResponsePojo checkVerifyCode(RequestArg ra) {
		String ip = ra.getString("ip");
		String loginName = ra.getString("loginName");
		// 判断是否需要验证码
		
		String verifyCode = ra.getString("verifyCode");
		// 判断验证码是否正确
		
		return null;
	}
	
	/**
	 * @Definition: 密码登录操作
	 * @author: chenyongqiang
	 * @Date: 2017年7月17日
	 * @param loginName
	 * @param loginPwd
	 * @return
	 */
	private ResponsePojo doLoginByPwd(String loginName, String loginPwd) {
		return null;
	}
	
	/**
	 * @Definition: 验证码登录操作
	 * @author: chenyongqiang
	 * @Date: 2017年7月17日
	 * @param loginName
	 * @param loginCode
	 * @return
	 */
	private ResponsePojo doLoginByCode(String loginName, String loginCode) {
		return null;
	}
	
	private ResponsePojo doLoginByUnionid(String unionid) {
		return null;
	}
	
}
