package com.hejia.dataAnalysis.module.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 字符串格式化（简单版）
 * @author: chenyongqiang
 * @Date: 2015年5月28日
 * @version: 1.0
 */
public class StringFormatUtils {
	
	public static final Pattern P = Pattern.compile("\\{.+?\\}");
	
	/**
	 * 如果p1不能满足，使用p2
	 */
	public static final Pattern P2 = Pattern.compile("\\{\\{.+?\\}\\}");
	
	/**
	 * @Definition: 使用默认的正则匹配
	 * @author: chenyongqiang
	 * @Date: 2015年5月28日
	 * @param orl
	 * @param vMap
	 * @return
	 */
	public static String format(String orl, Map<String, String> vMap) {
		return format(P, orl, vMap);
	}

	/**
	 * @Definition: 返回跟json字符串类型的字符串
	 * @author: chenyongqiang
	 * @Date: 2015年11月19日
	 * @param orl
	 * @param vMap
	 * @return
	 */
	public static String formatForJsonString(String orl, Map<String, String> vMap) {
		return format(P, orl, vMap).replaceAll("\"", "\\\\\"");
	}
	
	/**
	 * @Definition: 使用自己的正则匹配
	 * @author: chenyongqiang
	 * @Date: 2015年11月17日
	 * @param regex
	 * @param orl
	 * @param vMap
	 * @return
	 */
	public static String format(String regex, String orl, Map<String, String> vMap) {
		return format(Pattern.compile(regex), orl, vMap);
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年11月19日
	 * @param regex
	 * @param orl
	 * @param vMap
	 * @return
	 */
	public static String formatForJsonString(String regex, String orl, Map<String, String> vMap) {
		return format(Pattern.compile(regex), orl, vMap).replaceAll("\"", "\\\\\"");
	}
	
	/**
	 * @Definition: 使用自己的正则匹配
	 * @author: chenyongqiang
	 * @Date: 2015年11月17日
	 * @param P
	 * @param orl
	 * @param vMap
	 * @return
	 */
	public static String format(Pattern P, String orl, Map<String, String> vMap) {
		if (orl == null) return orl;
		//实现：方式1
//		Set<String> kSet = vMap.keySet();
//		for (String key : kSet) {
//			String val = vMap.get(key);
//			orl = orl.replaceAll("\\{" + key + "\\}", val);
//		}
		//实现：方式2
		StringBuilder b = new StringBuilder();
		Matcher m = P.matcher(orl);
		int start = 0;
		while (m.find()) {
			String g = m.group(0);
			//加头
			b.append(orl.substring(start, m.start()));
			//加中
			b.append(vMap.get(g.substring(1, g.length() - 1)));
			start = m.end();
		}
		//加尾
		b.append(orl.substring(start));
		return b.toString();
	}
	
	public static void main(String[] args) {
		String str = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";
		Map<String, String> m = new HashMap<String, String>();
		m.put("appid", "11");
		m.put("secret", "2323");
		System.out.println(format(str, m));
	}
}
