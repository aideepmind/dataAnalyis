package com.hejia.dataAnalysis.module.common.varnish;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hejia.dataAnalysis.module.common.socket.SocketFactory;
import com.hejia.dataAnalysis.module.common.utils.HttpUtil;
import com.hejia.dataAnalysis.module.common.utils.StringFormatUtil;
import com.hejia.dataAnalysis.module.common.utils.thread.ThreadManagerPool;

/**
 * @Description: varnish工具类
 * @author: chenyongqiang
 * @Date: 2015年11月12日
 * @version: 1.0
 */
public class VarnishUtil {
	
	private static final Logger log = Logger.getLogger(VarnishUtil.class);
	
	private static final Map<String, Map<String, String>> m = new HashMap<String, Map<String, String>>();
	
	static {
		try {
			Document doc = new SAXReader().read(SocketFactory.class.getClassLoader().getResourceAsStream("varnish.xml"));
			Element rootEl = doc.getRootElement();//root
			Element ss = rootEl.element("list");
			List<Element> l = ss.elements();
			for (int i = 0; i < l.size(); i++) {
				Element s = l.get(i);
				String function = s.attributeValue("function-type");
				if (function == null || "".equals(function.trim())) {
					function = s.attributeValue("file-type");
				}
				if (function != null) {
					Map<String, String> item = new HashMap<String, String>();
					Element ttlE = s.element("ttl");
					if (ttlE != null) {
						item.put("ttl", ttlE.getText());
					}
					Element urlE = s.element("url");
					if (urlE != null) {
						item.put("url", urlE.getText());
					}
					m.put(function, item);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			log.debug("初始化varnish配置工具类时出错，原因：", e);
		}
	}
	
	/**
	 * @Definition: 更新
	 * @author: chenyongqiang
	 * @Date: 2015年11月12日
	 * @param function
	 * @param vMap
	 */
	public static void updateByFunction(String function, Map<String, String> vMap) {
		//获取item
		Map<String, String> item = m.get(function);
		if (item != null) {
			String url = item.get("url");
			if (vMap != null) {
				url = StringFormatUtil.format(url, vMap);
			}
			System.out.println(url);
			purge(url);
		}
	}
	
	/**
	 * @Definition: 清除
	 * @author: chenyongqiang
	 * @Date: 2015年11月12日
	 * @param url
	 */
	private static void purge(final String url) {
		ThreadManagerPool.addThread(new Runnable() {
			@Override
			public void run() {
				String rep = HttpUtil.purge(url);
				System.out.println(rep);
			}
		});
	}
	
	/**
	 * @Definition: 获取url的后缀
	 * @author: chenyongqiang
	 * @Date: 2015年11月12日
	 * @param url
	 * @return
	 */
	private static String getSuffix(String url) {
		//截取url后缀
		int index = url.lastIndexOf(".");
		if (index != -1) {
			return url.substring(index + 1);
		}
		return url;
	}
	
	/**
	 * @Definition: 更新
	 * @author: chenyongqiang
	 * @Date: 2015年11月12日
	 * @param url
	 */
	public static void updateByUrl(String url) {
		purge(url);
	}
	
	/**
	 * @Definition: 设置响应头部
	 * @author: chenyongqiang
	 * @Date: 2015年11月12日
	 * @param response
	 * @param function
	 */
	public static void setResponseHeader(HttpServletResponse response, String function) {
		//获取item
		Map<String, String> item = m.get(function);
		if (item != null) {
			String ttl = item.get("ttl");
			if (ttl == null) {
				String url = item.get("url");
				item = m.get(getSuffix(url));
				if (item != null) {
					ttl = item.get("ttl");
				} else {
					item = m.get("default");
					ttl = item.get("ttl");
				}
			}
			response.setHeader("Cache-Control", "max-age=" + getSecond(ttl));
		}
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年11月15日
	 * @param ttl
	 * @return
	 */
	private static long getSecond(String ttl) {
		long second = 0;
		if (ttl.contains("m")) {
			second = Integer.parseInt(ttl.substring(0, ttl.length() - 1)) * 60;
		} else if (ttl.contains("s")) {
			second = Integer.parseInt(ttl.substring(0, ttl.length() - 1));
		} else if (ttl.contains("h")) {
			second = Integer.parseInt(ttl.substring(0, ttl.length() - 1)) * 60 * 60;
		} else if (ttl.contains("d")) {
			second = Integer.parseInt(ttl.substring(0, ttl.length() - 1)) * 24 * 60 * 60;
		}
		return second;
	}
	
	public static void main(String[] args) {
//		Map<String, String> vMap = new HashMap<String, String>();
//		vMap.put("id", "588");
//		VarnishUtil.updateByFunction("company", vMap);
		
		String url = "http://corp.minixiao.com/static/css/common/common.css";
		VarnishUtil.updateByUrl(url);
	}
}
