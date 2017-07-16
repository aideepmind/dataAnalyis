package com.hejia.dataAnalysis.module.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @Description: uuid生成器
 * @author: chenyongqiang
 * @Date: 2016年2月23日
 * @version: 1.0
 */
public class UUIDUtils {
	
	public static final String ALPS[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

	public static final String NUMS[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	//混合
	public static final String MIX[] = {"2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
		, "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	
	public final static Random r = new Random();
	
	/**
	 * @Definition: 获取uuid的值
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * @Definition: 获取uuid的值
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param name
	 * @return
	 */
	public static String getUUID(String name) {
		UUID uuid = UUID.fromString(name);
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * @Definition: 如果length太短的话，有可能重复
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param length
	 * @return
	 */
	public static String getUUIDHashCode(int length) {
		String uuid = UUID.randomUUID().toString();
		int code = uuid.hashCode();
		if (code < 0) {
			code = -code;
		}
//		return String.format("%015d", code);
		String c = "" + code;
		if (c.length() > length) {
			return c.substring(c.length() - length);//取后面length位数
		}
		return String.format("%0" + length + "d", code);//十位数字，不够往前补零
	}
	
	/**
	 * @Definition: 获取指定长度的随机数字字符串
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param length
	 * @return
	 */
	public static String getNumRandomCode(int length) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < length; i++) {
			b.append(NUMS[r.nextInt(NUMS.length)]);
		}
		return b.toString();
	}
	
	/**
	 * @Definition: 获取指定长度的随机纯字母字符串
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param length
	 * @return
	 */
	public static String getAlpRandomCode(int length) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < length; i++) {
			b.append(ALPS[r.nextInt(ALPS.length)]);
		}
		return b.toString();
	}
	
	/**
	 * @Definition: 获取指定长度的随机混合型字符串
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param length
	 * @return
	 */
	public static String getMixRandomCode(int length) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < length; i++) {
			b.append(MIX[r.nextInt(MIX.length)]);
		}
		return b.toString();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getMixRandomCode(6));
		}
	}
}
