package com.hejia.dataAnalysis.module.common.utils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @Description: 缓存工具类
 * @author: chenyongqiang
 * @Date: 2016年2月26日
 * @version: 1.0
 */
public class EhcacheUtils {

	public static final String CACHE_NAME_TOKEN = "tokenCache";//凭证
	public static final String CACHE_NAME_UNIONID = "unionidCache";//unionid
	public static final String CACHE_NAME_CODE = "codeCache";//认证码
	
	
	private static CacheManager manager;
	
	public static void init(CacheManager manager) {
		EhcacheUtils.manager = manager;
	}
	
	/**
	 * @Definition: 存储对象
	 * @author: chenyongqiang
	 * @Date: 2016年2月26日
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		manager.getCache(cacheName).put(new Element(key, value));
	}
	
	/**
	 * @Definition: 获取对象
	 * @author: chenyongqiang
	 * @Date: 2016年2月26日
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
		Element e = manager.getCache(cacheName).get(key);
		return e == null ? e : e.getObjectValue();
	}
	
	/**
	 * @Definition: 删除对象
	 * @author: chenyongqiang
	 * @Date: 2016年2月26日
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		 manager.getCache(cacheName).remove(key);
	}
}
