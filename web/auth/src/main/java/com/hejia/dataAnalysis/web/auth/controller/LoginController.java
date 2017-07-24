package com.hejia.dataAnalysis.web.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hejia.dataAnalysis.module.auth.service.LoginService;
import com.hejia.dataAnalysis.module.common.Constant;
import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
/**
 * @Description: 登录（以后这块代码全部变成统一认证方式，即所有的系统集中认证，不需要每个系统各自开发一套认证 - chenyq）
 * @author: chenyongqiang
 * @Date: 2017年7月16日
 * @version: 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	private static final Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("loginService")
	private LoginService loginService;
	
	/**
	 * @Definition: 本平台的同一登录入口
	 * 参数：
	 * 名词：是否必填；解释
	 * platformType：必填；平台类型，相当于告诉我们你从哪里来
	 * redirectUrl：选填；跳转链接，相当于告诉我们你到哪里去，系统提供根据platform参数来提供默认值，如果参数有值，URL必须在权限范围内，否则跳转到默认地址
	 * verifyType：必填；验证方式，前端必须要明确告诉后端改怎么做，当然后端提供可选项，如密码登录、短信登录、邮件登录等
	 * verifyCode：选填；验证码，由后端决定是否需要，由ip、cookie（sessionid）、登录账号、平台类型来决定，理论上用户可以针对一个账号重试最多N次而不要求验证码，N=默认许可输出次数*平台类型数
	 * loginName：必填；登录账号，告诉我们你是谁，可以是邮箱或者电话
	 * loginPwd：选填；后端根据verify_type来确认是否需要
	 * loginCode：选填；后端根据verify_type来确认是否需要
	 * @author: chenyongqiang
	 * @Date: 2017年7月17日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doLogin")
	@ResponseBody
	public ResponsePojo doLoginForStu(HttpServletRequest request, HttpServletResponse response) {
		ResponsePojo rp = null;
		try {
			// 获取参数
			RequestArg ra = RequestArg.gteInstance(request);
			// 登录
			rp = loginService.login(ra);
			System.out.println(rp.toJson());
			if (rp.isSuccess()) {
				// 把访问凭证写到cookie中
				this.setAccessToken(response, ra.getInteger("platformType"), ((Map) rp.getMessage()).get("token").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("登录失败，原因是：", e);
			rp = new ResponsePojo();
			rp.setFailType(ResponsePojo.FAIL_TYPE_UNKNOW_ERROR);
			rp.setFailReason("对不起，登录出错了，请稍候重试");
		}
		return rp;
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月17日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/")
	public ModelAndView login(HttpServletRequest request) {
		//取客户端类型
		ModelAndView mv = null;
		if (BaseController.getClientType(request) == Constant.CLIENT_TYPE_MOBILE) { // 移动端
			mv = new ModelAndView("/login");
		} else {//默认是pc端
			mv = new ModelAndView("/login");
		}
		mv.addObject("redirectUrl", request.getParameter("redirectUrl")); // 则登录完之后，跳转到redirectUrl页面
		return mv;
	};
	
	/**
	 * @Definition: 校验访问凭证
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/checkAccessToken")
	@ResponseBody
	public ResponsePojo checkAccessToken(HttpServletRequest request, HttpServletResponse response) {
		ResponsePojo rp = null;
		//获取参数
    	String token = request.getParameter("token");
    	String ip = request.getParameter("ip");
		try {
			rp = loginService.checkAccessToken(token, ip);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("校验访问凭证失败，原因是：", e);
			rp.setFailReason("校验访问凭证出错");
		}
		return rp;
	}
}
