package com.hejia.dataAnalysis.module.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @Description: xml工具，包括解析、拼接。
 * @author: chenyongqiang
 * @Date: 2015年9月17日
 * @version: 1.0
 */
public class XmlUtils {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年9月17日
	 * @param xml
	 * @return
	 */
	public static Map parseForMap(String xml) {
		Map m = new HashMap();
		if (xml != null) {//目前支持一层
			try {
				Document doc = DocumentHelper.parseText(xml);
				Element rootEl = doc.getRootElement();//root
				List<Element> l = rootEl.elements();
				for (int i = 0; i < l.size(); i++) {
					Element e = l.get(i);
					System.out.println(e.getName());
					System.out.println(e.getData());
					m.put(e.getName(), e.getData());
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
		}
		return m;
	}
	
	public static void main(String[] args) {
		String xml = "<xml><ToUserName>111<ToUserName>111</ToUserName></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName></xml>";
		parseForMap(xml);
	}
}
