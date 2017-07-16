package com.hejia.dataAnalysis.module.common.log;

import java.util.HashMap;
import java.util.Map;

import com.hejia.dataAnalysis.module.common.Constant;

/**
 * @Description: 日志操作平台
 * @author: chenyongqiang
 * @Date: 2015年6月23日
 * @version: 1.0
 */
public class LogOperPlatform {
	/**
	 * ID对应中文名的映射
	 */
	private static Map<Integer, String> map = new HashMap<Integer, String>(10);
	
	static {
		map.put(Constant.PLATFORM_ADMIN, "后台");
		map.put(Constant.PLATFORM_WEB_STUDENT, "学生PC端");
		map.put(Constant.PLATFORM_WEB_COMPANY, "企业PC端");
		map.put(Constant.PLATFORM_MOBILE_STUDENT, "学生移动端");
		map.put(Constant.PLATFORM_MOBILE_COMPANY, "企业移动端");
		map.put(Constant.PLATFORM_WEB_EVALUATION, "测评系统");
	}
	
	/**
	 * @Definition: 通过平台ID获取平台中文名
	 * @author: chenyongqiang
	 * @Date: 2015年6月23日
	 * @param platformId
	 * @return
	 */
	public static String getName(Integer platformId) {
		return map.get(platformId);
	}
	
	public  static  Map<Integer,String>    getMapOperPlatform(){
		return    map;
	}
}
