package com.hejia.dataAnalysis.module.common.utils;

import java.util.regex.Pattern;

/**
 * @Description: 正则工具类
 * @author: chenyongqiang
 * @Date: 2017年7月18日
 * @version: 1.0
 */
public class RegexUtils {

	/**
	 * @Definition: 验证Email
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		return Pattern.matches(regex, email);
	}

	/**
	 * @Definition: 验证手机号码（支持国际格式，如中国内地+86）
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		String regex = "(\\+\\d+)?1[34578]\\d{9}$";
		return Pattern.matches(regex, mobile);
	}

	/**
	 * @Definition: 验证身份证号码
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param idCard
	 * @return
	 */
	public static boolean isIdCard(String idCard) {
		String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
		return Pattern.matches(regex, idCard);
	}

	/**
	 * @Definition: 验证固定电话号码
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
		return Pattern.matches(regex, phone);
	}

	/**
	 * @Definition: 验证中文
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param chinese
	 * @return
	 */
	public static boolean isChinese(String chinese) {
		String regex = "^[\u4E00-\u9FA5]+$";
		return Pattern.matches(regex, chinese);
	}

	/**
	 * @Definition: 验证URL地址
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param url
	 * @return
	 */
	public static boolean isURL(String url) {
		String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
		return Pattern.matches(regex, url);
	}

	/**
	 * @Definition: 验证IP地址
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param ipAddress
	 * @return
	 */
	public static boolean isIpAddress(String ipAddress) {
		String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
		return Pattern.matches(regex, ipAddress);
	}
}
