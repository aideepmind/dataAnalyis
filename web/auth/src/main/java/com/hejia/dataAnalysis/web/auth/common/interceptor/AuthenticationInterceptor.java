package com.hejia.dataAnalysis.web.auth.common.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.auth.domain.BlackWhiteList;
import com.hejia.dataAnalysis.module.auth.service.BlackWhiteListService;
import com.hejia.dataAnalysis.module.auth.service.LoginService;
import com.hejia.dataAnalysis.module.common.Constant;
import com.hejia.dataAnalysis.module.common.CurrentAccount;
import com.hejia.dataAnalysis.module.common.CurrentThreadVar;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.utils.HttpUtils;
import com.hejia.dataAnalysis.web.auth.controller.BaseController;

/**
 * @Description: 认证拦截器，主要拦截用户是否登陆、session回话是否超时、登陆URL是否在权限范围内等
 * @author: chenyongqiang
 * @Date: 2015年5月22日
 * @version: 1.0
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = Logger.getLogger(AuthenticationInterceptor.class);
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private BlackWhiteListService blackWhiteListService;
	
	public static Map<String, Object> accessMap; // 可访问的URL集合
	public static String[] needAuthcUrls; // 需要认证的URL集合
	public static Map<String, Object> ipBlackListMap; // 黑名单
	public static Map<String, Account> accountMap; // 压力测试用户集合-临时解决方案
	
	/**
	 * @Definition: 初始化
	 * @author: chenyongqiang
	 * @Date: 2015年12月29日
	 */
	public void init() {
		//不需要登录就可以访问的URL
		accessMap = new HashMap<String, Object>();
		byte value = 0;
//		accessMap.put("/", value);
		
		//需要登录才能访问的URL
		needAuthcUrls = new String[] {"/account"};
		
		//初始化ip黑名单集合
//		ipBlackListMap = new HashMap<String, Object>();
//		List<BlackWhiteList> bwlList = blackWhiteListService.findByTypeAndFunctionType(BlackWhiteList.TYPE_BLACK, BlackWhiteList.FUNTION_TYPE_GLOBAL_IP);
//		for (int i = 0; i < bwlList.size(); i++) {
//			ipBlackListMap.put(bwlList.get(i).getValue(), value);
//		}
	}
	
	/**
	 * 认证步骤（尽量减少判断次数和降低判断的复杂度）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		//判断是否ip属于黑名单
//		if (isBelongGlobalBlackList(request)) {
//			return false;
//		}
		//判断操作
		if (isStatic(request, response) || isAccessibleUrl(url) || !isNeedAuthcUrl(url) || isAlreadyLogin(request, response)) return true;
//		if (isPortal(url)) return true;
//		if (isAlreadyLogin(request)) if (isAccessibleButNeedAlreadyLoginUrl(url) || isUrlLegal(request)) return true;
//		if (isAccessibleUrl(url)) return true;
		//会话过期操作
		String requestType = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(requestType)) {//ajax（异步）请求
			//如果session超时
			response.setHeader("isTimeOut", "true");
		} else {//同步请求
			if (Constant.runEnv == 1 || Constant.runEnv == 2) {
				response.sendRedirect(Constant.systemUrlAuth + "/login");
			} else {
				response.sendRedirect(request.getContextPath() + "/login/");
			}
		}
		return false;
	}

	/**
	 * @Definition: 检查token
	 * @author: chenyongqiang
	 * @Date: 2016年3月6日
	 * @param request
	 * @return
	 */
	private String checkToken(HttpServletRequest request, HttpServletResponse response) {
		String token = BaseController.getCookie(request, Constant.COOKIE_ACCESS_TOKEN);
		if (StringUtils.isBlank(token)) {
			CurrentAccount.setCurrentAccount(null, request, Constant.PLATFORM_AUTH);
		}
		CurrentAccount ca = CurrentAccount.getCurrentAccount(request);
		if (ca != null && ca.getAccount() != null && !token.equals(((Account) ca.getAccount()).getAccessToken())) {
			CurrentAccount.setCurrentAccount(null, request, Constant.PLATFORM_AUTH);
		}
		// 尝试一次且仅做一次登录
		Object isTryLogin = request.getSession(true).getAttribute(Constant.SESSION_IS_TRY_LOGIN);
		if (isTryLogin == null) { // 说明已经尝试过登录尝试过登录
			checkAccessToken(request, response, token);
			request.getSession(true).setAttribute(Constant.SESSION_IS_TRY_LOGIN, "1");
		}
		return token;
	}
	
	/**
	 * @Definition: 是否为开放的URL
	 * @author: chenyongqiang
	 * @Date: 2016年3月10日
	 * @param request
	 * @param response
	 * @param token
	 * @return
	 */
	private boolean isOpenUrl(HttpServletRequest request, HttpServletResponse response, String token) {
		String url = request.getServletPath();
		if (url.contains("/st/")) {
			//如果用户没登陆，尝试登录
			CurrentAccount ca = CurrentAccount.getCurrentAccount(request);
			if (ca == null || ca.getAccount() == null) {
				checkAccessToken(request, response, token);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * @Definition: 是否静态文件
	 * @author: chenyongqiang
	 * @Date: 2016年1月4日
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean isStatic(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getServletPath();
		if ((url.endsWith(".html") || url.endsWith(".htm") || url.endsWith(".html/") || url.endsWith(".htm/")) && url.contains("/st/")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是前后台
	 * @param request
	 * @return
	 */
	private boolean isPortal(String url) {
		if (url.startsWith("/center")) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否允许访问的url
	 * 表明是:1.请求登陆页面2.或者进行登陆认证3.退出
	 * @param request
	 * @return
	 */
	private boolean isNeedAuthcUrl(String url) {
		for (int i = 0; i < needAuthcUrls.length; i++) {
			if (url.startsWith(needAuthcUrls[i]) || url.startsWith("/" + needAuthcUrls[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @Definition: 判断是否需要登录才能访问的URL
	 * @author: chenyongqiang
	 * @Date: 2015年5月22日
	 * @param url
	 * @return
	 */
	private boolean isAccessibleUrl(String url) {
		if (accessMap.get(url) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否符合已经登陆就允许通过的url
	 * @param request
	 * @return
	 */
	private boolean isAccessibleButNeedAlreadyLoginUrl(String url) {
		if (accessMap.get(url) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Definition: 判断用户是否已经登陆
	 * @author: chenyongqiang
	 * @Date: 2016年3月6日
	 * @param request
	 * @param response
	 * @param token
	 * @return
	 */
	private boolean isAlreadyLogin(HttpServletRequest request, HttpServletResponse response) {
		//查看cookie中的COOKIE_ACCESS_TOKEN是否还存在，不存在则认为已经退出了
		String token = BaseController.getCookie(request, Constant.COOKIE_ACCESS_TOKEN);
		if (StringUtils.isBlank(token)) {
			return false;
		}
		boolean flag = true;
		CurrentAccount ca = CurrentAccount.getCurrentAccount(request);
		if (ca == null || ca.getAccount() == null) {
			ResponsePojo rp = checkAccessToken(request, response, token);
			flag = rp != null && rp.isSuccess();
		}
		CurrentThreadVar.set(CurrentThreadVar.CURRENT_USER, CurrentAccount.getCurrentAccount(request));
		//判断在线用户表中是否存在该用户,如果相同用户不能同时在线,则需要在线用户表
		return flag;
	}
	
	/**
	 * 判断用户访问的url是否合法或者属于合法权限范围
	 * @param request
	 * @return
	 */
	private boolean isUrlLegal(HttpServletRequest request) {
		String url = request.getServletPath().split("/")[2];
		if ("public".equals(url)) return true;//判断该url是否为全局的
//		return CurrentUser.getCurrentAccount(request).isUrlLegal(url);
		return true;
	}

	/**
	 * @Definition: 判断是否为当前系统的用户
	 * @author: chenyongqiang
	 * @Date: 2016年1月4日
	 * @param request
	 * @return
	 */
	private boolean isCurrentSystemUser(HttpServletRequest request) {
		CurrentAccount ca = CurrentAccount.getCurrentAccount(request);
		Account a = (Account) ca.getAccount();
		if (a.getType() == Account.TYPE_ADMIN) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Definition: 是否属于全局黑名单中
	 * @author: chenyongqiang
	 * @Date: 2015年12月28日
	 * @param request
	 * @return
	 */
	private boolean isBelongGlobalBlackList(HttpServletRequest request) {
		//取ip
		HttpSession s = request.getSession();
		String ip = null;
		if (s != null) {
			ip = (String) s.getAttribute(Constant.SESSION_CLIENT_IP);
		}
		if (ip == null) {
			ip = HttpUtils.getIpAddr(request);
			//如果ip不是正确的格式，则加入黑名单，session？还是当前ip的特征？
			if (ip == null || (!HttpUtils.IP_PATTERN.matcher(ip).matches() && !"127.0.0.1".equals(ip) && !"0:0:0:0:0:0:0:1".equals(ip))) {
				ipBlackListMap.put(ip, (byte) 0);
			} else {//加入session
				request.getSession(true).setAttribute(Constant.SESSION_CLIENT_IP, ip);
			}
		}
//		System.out.println("ip:" + ip + ",flag:" + ipBlackListMap.get(ip));
		return ipBlackListMap.get(ip) != null;
	}
	
	/**
	 * @Definition: 校验访问凭证
	 * @author: chenyongqiang
	 * @Date: 2017年7月19日
	 * @param request
	 * @param response
	 * @param token
	 * @return
	 */
	private ResponsePojo checkAccessToken(HttpServletRequest request, HttpServletResponse response, String token) {
		ResponsePojo rp = null;
		if (StringUtils.isNotBlank(token)) {
			String content = HttpUtils.sendGetRequest(Constant.systemUrlAuth + "/login/checkAccessToken?token=" + token + "&ip=" + HttpUtils.getIpAddr(request));
			if (content != null && !"".equals(content)) {
				rp = (ResponsePojo) JSONObject.toBean(JSONObject.fromObject(content), ResponsePojo.class);
				if (rp.isSuccess()) {
					Account a = (Account) JSONObject.toBean(JSONObject.fromObject(rp.getMessage()), Account.class);
					CurrentAccount.setCurrentAccount(a, request, Constant.PLATFORM_AUTH);
				} else {
					BaseController.delCookie(response, Constant.COOKIE_ACCESS_TOKEN);
				}
			}
		}
		return rp;
	}
}
