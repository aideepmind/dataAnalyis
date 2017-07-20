package com.hejia.dataAnalysis.web.pc.admin.manage.common;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description: 模块配置
 * @author: chenyongqiang
 * @Date: 2015年5月6日
 * @version: 1.0
 */
public class ModuleConfig {
	
	private static Properties pro = new Properties();
	
	static {
		try {
			pro.load(ModuleConfig.class.getClassLoader().getResourceAsStream("weChat.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		String val = pro.getProperty(key);
		if (val != null) {
			val = val.trim();
		}
		return val;
	}
	
	public static int getInt(String key) {
		String val = pro.getProperty(key);
		if (val != null) {
			return Integer.parseInt(val.trim());
		}
		return 0;
	}

	public static long getLong(String key) {
		String val = pro.getProperty(key);
		if (val != null) {
			return Long.parseLong(val.trim());
		}
		return 0;
	}
	
	public static double getDouble(String key) {
		String val = pro.getProperty(key);
		if (val != null) {
			return Double.parseDouble(val.trim());
		}
		return 0;
	}
	public static void main(String[] args) {
		System.out.println(get("weixin_AppID"));
	}
}
