package com.hejia.dataAnalysis.module.auth.common;

import java.io.FileOutputStream;
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
			pro.load(ModuleConfig.class.getClassLoader().getResourceAsStream("module-auth.properties"));
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

	public static boolean getBoolean(String key) {
		String val = pro.getProperty(key);
		if (val != null) {
			return Boolean.parseBoolean(val.trim());
		}
		return true;
	}
	
	/**
	 * @Definition: 弃用
	 * @author: chenyongqiang
	 * @Date: 2015年7月6日
	 * @param key
	 * @param value
	 * @return
	 */
	@Deprecated
	public static boolean set(String key, String value) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(ModuleConfig.class.getClassLoader().getResource("module-common.properties").getPath());
			Properties pro = new Properties();
			pro.setProperty(key, value);
			pro.store(fos, ""); 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos != null) 
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
