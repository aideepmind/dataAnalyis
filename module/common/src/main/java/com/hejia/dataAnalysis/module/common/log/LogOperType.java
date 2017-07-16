package com.hejia.dataAnalysis.module.common.log;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 日志操作类型
 * @author: chenyongqiang
 * @Date: 2015年6月23日
 * @version: 1.0
 */
public class LogOperType {
	
	/**
	 * 类型ID和类型名映射
	 */
	public static Map<Integer, String> map = new HashMap<Integer, String>(15);

	/**
	 * 未知类型
	 */
	public static final int UNKNOWN_OPER_TYPE = 0;
	
	/**
	 * 系统操作（类似登录、登出操作）
	 */
	public static final int SYSTEM_OPER_TYPE = 1;
	
	/**
	 * 用户操作
	 */
	public static final int ACCOUNT_OPER_TYPE = 2;

	/**
	 * 活动操作
	 */
	public static final int ACTIVITY_OPER_TYPE = 3;

	/**
	 * 安全操作
	 */
	public static final int SECURITY_OPER_TYPE = 4;

	/**
	 * 邀请码操作
	 */
	public static final int INVITE_CODE_OPER_TYPE = 5;

	/**
	 * 简历操作
	 */
	public static final int RESUME_OPER_TYPE = 6;
	
	/**
	 * 标签操作
	 */
	public static final int LABEL_OPER_TYPE = 7;
	
	/**
	 * 反馈操作
	 */
	public static final int FEEDBACK_OPER_TYPE = 8;

	/**
	 * 搜索操作
	 */
	public static final int SEARCH_OPER_TYPE = 9;

	/**
	 * 职位操作
	 */
	public static final int POSITION_OPER_TYPE = 10;

	/**
	 * 匹配模板操作
	 */
	public static final int MATCH_TEMPLATE_OPER_TYPE = 11;

	/**
	 * 匹配标准操作
	 */
	public static final int MATCH_STANDAR_OPER_TYPE = 12;
	
	/**
	 * 校招系统简历投递操作
	 */
	public static final int DELIVERY_OPER_TYPE = 13;
	
	/**
	 * 其他操作
	 */
	public static final int OTHER_OPER_TYPE = 100;
	
	
	static {
		map.put(UNKNOWN_OPER_TYPE, "未知类型");
		map.put(SYSTEM_OPER_TYPE, "系统操作");
		map.put(ACCOUNT_OPER_TYPE, "用户操作");
		map.put(ACTIVITY_OPER_TYPE, "活动操作");
		map.put(SECURITY_OPER_TYPE, "安全操作");
		map.put(INVITE_CODE_OPER_TYPE, "邀请码操作");
		map.put(RESUME_OPER_TYPE, "简历操作");
		map.put(LABEL_OPER_TYPE, "标签操作");
		map.put(FEEDBACK_OPER_TYPE, "反馈操作");
		map.put(SEARCH_OPER_TYPE, "搜索操作");
		map.put(POSITION_OPER_TYPE, "职位操作");
		map.put(MATCH_TEMPLATE_OPER_TYPE, "匹配模板操作");
		map.put(OTHER_OPER_TYPE, "其他操作");
	}
	
	/**
	 * @Definition: 通过类型ID获取类型中文名
	 * @author: chenyongqiang
	 * @Date: 2015年6月23日
	 * @param logTypeId
	 * @return
	 */
	public static String getName(Integer logTypeId) {
		return map.get(logTypeId);
	}
	
	public  static  Map<Integer,String>    getAllOperType(){
		
		return  map;
	}
}
