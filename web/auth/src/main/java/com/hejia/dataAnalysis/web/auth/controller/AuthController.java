package com.hejia.dataAnalysis.web.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hejia.dataAnalysis.module.auth.service.AuthService;
import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;

/**
 * @Description: 认证（1.按需、先询问、自己管理凭证；2.直接获取所有的权限，然后自己做决定）
 * @author: chenyongqiang
 * @Date: 2016年4月5日
 * @version: 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	private static final Logger log = Logger.getLogger(AuthController.class);
	
	@Autowired
	private AuthService service;
	
	/**
	 * @Definition: 获取所有URL的权限
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllUrlPerms")
	@ResponseBody
	public ResponsePojo getAllUrlPerms(HttpServletRequest request) {
		ResponsePojo rp = null;
		try {
			//获取参数
			RequestArg ra = RequestArg.gteInstance(request);
			rp = service.getCompSearchToken(ra);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("获取所有URL的权限失败，原因是：", e);
			rp = new ResponsePojo();
			rp.setFailReason("获取所有URL的权限出错！");
		}
		return rp;
	}
	
	/**
	 * @Definition: 获取公司搜索凭证
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCompSearchToken")
	@ResponseBody
	public ResponsePojo getCompSearchToken(HttpServletRequest request) {
		ResponsePojo rp = null;
		try {
			//获取参数
			RequestArg ra = RequestArg.gteInstance(request);
			rp = service.getCompSearchToken(ra);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("获取公司搜索凭证失败，原因是：", e);
			rp = new ResponsePojo();
			rp.setFailReason("获取公司凭证出错！");
		}
		return rp;
	}
	
	/**
	 * @Definition: 刷新凭证
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/refreshToken")
	@ResponseBody
	public ResponsePojo refreshToken(HttpServletRequest request) {
		ResponsePojo rp = null;
		try {
			//获取参数
			RequestArg ra = RequestArg.gteInstance(request);
			rp = service.refreshToken(ra);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("刷新搜索凭证失败，原因是：", e);
			rp = new ResponsePojo();
			rp.setFailReason("刷新搜索凭证出错！");
		}
		return rp;
	}
}
