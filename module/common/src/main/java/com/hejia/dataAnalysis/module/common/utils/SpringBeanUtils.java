package com.hejia.dataAnalysis.module.common.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Description: spring容器中对bean的操作工具
 * @author: chenyongqiang
 * @Date: 2016年2月26日
 * @version: 1.0
 */
public class SpringBeanUtils {
	
	private static ApplicationContext ac;
	
	/**
	 * @Definition: 需要初始化
	 * @author: chenyongqiang
	 * @Date: 2016年2月26日
	 * @param sc
	 */
	public static void init(ServletContext sc) {
		ac = WebApplicationContextUtils.getWebApplicationContext(sc);
	}

	/**
	 * @Definition: 需要初始化
	 * @author: chenyongqiang
	 * @Date: 2016年2月26日
	 * @param ac
	 */
	public static void init(ApplicationContext ac) {
		SpringBeanUtils.ac = ac;
	}
	
	/**
	 * @Definition: 获取bean
	 * @author: chenyongqiang
	 * @Date: 2016年2月26日
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		if (ac == null) return null;
		return ac.getBean(beanName);
	}
}
