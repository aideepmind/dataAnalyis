package com.hejia.dataAnalysis.web.auth.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hejia.dataAnalysis.web.auth.common.interceptor.AuthenticationInterceptor;

/**
 * @Description: 系统启动时，初始化服务
 * @author: chenyongqiang
 * @Date: 2016年1月25日
 * @version: 1.0
 */
@Component
public class SystemInitialize {
	
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;
	
	@PostConstruct
	private void init() {
//		//初始化认证拦截器的参数
		authenticationInterceptor.init();
	}
}
