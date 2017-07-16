package com.hejia.dataAnalysis.module.common.log;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 日志操作结果
 * @author: chenyongqiang
 * @Date: 2015年6月23日
 * @version: 1.0
 */
public class LogOperResult {
	/**
	 * ID对应中文名的映射
	 */
	private static Map<Integer, String> map = new HashMap<Integer, String>(2);
	
	/**
	 * 成功
	 */
	public static final int SUCCESS = 1;

	/**
	 * 失败
	 */
	public static final int FAILED = 0;
	
	
	static {
		map.put(SUCCESS, "成功");
		map.put(FAILED, "失败");
	}
	
	/**
	 * @Definition: 通过操作结果类型ID获取操作结果中文名
	 * @author: chenyongqiang
	 * @Date: 2015年6月23日
	 * @param resultId
	 * @return
	 */
	public static String getName(Integer resultId) {
		return map.get(resultId);
	}
}
