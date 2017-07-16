package com.hejia.dataAnalysis.module.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WeixinTemplateUtil {
	
	public static Map<String, String> tMap = new HashMap<String, String>();
	
	static {
		tMap.put("delivery_pass", "delivery_pass");
		tMap.put("delivery_no_pass", "delivery_no_pass");
		tMap.put("invite", "invite");
		tMap.put("activityEnrollPass", "activityEnrollPass");
		tMap.put("activity_invite_stu", "activity_invite_stu");
		tMap.put("stu_receive_invite", "stu_receive_invite");
		tMap.put("stu_refuse_invite", "stu_refuse_invite");
		Iterator<String> kIt = tMap.keySet().iterator();
		ClassLoader cl = WeixinTemplateUtil.class.getClassLoader();
		while (kIt.hasNext()) {
			String key = kIt.next();
			InputStream stream = cl.getResourceAsStream("weixinTemplate/" + tMap.get(key));
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(stream));//使用InputStreamReader，否则出现乱码
				int ch = 0;
				StringBuffer buf = new StringBuffer();
				while ((ch = br.read()) != -1) {
					buf.append((char) ch);
				}
				tMap.put(key, buf.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * @Definition: 获取模板
	 * @author: chenyongqiang
	 * @Date: 2015年6月4日
	 * @param tempName
	 * @param dataMap
	 * @return
	 */
	public static String getTemplate(String tempName, Map<String, String> dataMap) {
		return StringFormatUtil.format(tMap.get(tempName), dataMap);
	}
	
	public static void main(String[] args) {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("openid", "oYz1Lt6249ylSlxDwhS5fO-kHrso");
		dataMap.put("first", "您好：恭喜，又有一位候选人接受您的邀约啦！");
		dataMap.put("keyword1", "小浩");
		dataMap.put("keyword2", "北京科技大学");
		dataMap.put("keyword3", "计算机科学与技术");
		dataMap.put("remark", "详细请登录迷你校。");
		String tmp = WeixinTemplateUtil.getTemplate("stu_receive_invite", dataMap);
		System.out.println(tmp);
	}
}
