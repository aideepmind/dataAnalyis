package com.hejia.dataAnalysis.module.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 线程局部变量工具
 * @author: chenyongqiang
 * @Date: 2015年6月23日
 * @version: 1.0
 */
public class CurrentThreadVar {

	/**
	 * 系统当前用户标识
	 */
	public static final String CURRENT_USER = CurrentAccount.class.getName();
	
	/**
	 * 系统当前请求标识
	 */
	public static final String CURRENT_REQUEST = HttpServletRequest.class.getName();
	
	
	/**
	 * 线程局部变量
	 */
	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();
	
	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param obj
	 */
	public static void set(String key, Object obj) {
		Map<String, Object> map = threadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, obj);
		threadLocal.set(map);
	}
	
	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Map<String, Object> map = threadLocal.get();
		if (map == null) {
			return null;
		} else {
			return map.get(key);
		}
	}
	
	/**
	 * 清除数据
	 * 
	 */
	public static void clear() {
		threadLocal.set(null);
	}


}
