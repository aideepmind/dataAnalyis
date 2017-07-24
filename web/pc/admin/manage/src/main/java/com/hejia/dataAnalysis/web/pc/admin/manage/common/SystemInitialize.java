package com.hejia.dataAnalysis.web.pc.admin.manage.common;

import javax.annotation.PostConstruct;

import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hejia.dataAnalysis.module.common.utils.EhcacheUtils;
import com.hejia.dataAnalysis.web.pc.admin.manage.common.interceptor.AuthenticationInterceptor;

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

	@Autowired
	@Qualifier("ehCacheManagerFactory")
	private CacheManager manager;
	
	@PostConstruct
	private void init() {
//		//初始化认证拦截器的参数
		authenticationInterceptor.init();
		// 初始化缓存工具类
		EhcacheUtils.init(manager);
	}
}
