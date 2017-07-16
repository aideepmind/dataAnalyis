package com.hejia.dataAnalysis.module.common.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @Description: Socket工厂，用于注册服务和注册客户端
 * @author: chenyongqiang
 * @Date: 2015年10月16日
 * @version: 1.0
 */
public class SocketFactory {
	
	private static final Logger log = Logger.getLogger(SocketFactory.class);
	
	public final static String WWW_STUDENT = "www_student";

	public final static String WWW_COMPANY = "www_company";

	public final static String WEIXIN_STUDENT = "weixin_student";

	public final static String WEIXIN_COMPANY = "weixin_company";

	public final static String WEB_EVALUATION = "web_evaluation";

	public final static String AUTH_SSO = "auth_sso";

	public final static String WWW_IMG = "www_img";

	public final static String WWW_SEARCH = "www_search";
	
	public final static String WWW_RECRUITMENT = "www_recruitment";
	
	private static List<Map<String, String>> sList = new ArrayList<Map<String,String>>(5);

	private static List<Map<String, String>> cList = new ArrayList<Map<String,String>>(5);
	
	private static Map<String, SocketClient> scMap = new HashMap<String, SocketClient>(5);
	
	static {
		try {
			Document doc = new SAXReader().read(SocketFactory.class.getClassLoader().getResourceAsStream("socket.xml"));
			Element rootEl = doc.getRootElement();//root
			Element ss = rootEl.element("servers");
			List<Element> l = ss.elements();
			for (int i = 0; i < l.size(); i++) {
				Element s = l.get(i);
				Map<String, String> m = new HashMap<String, String>(5);
				List<Element> l2 = s.elements();
				for (int j = 0; j < l2.size(); j++) {
					s = l2.get(j);
					m.put(s.getName(), s.getText());
				}
				sList.add(m);
			}
			Element cs = rootEl.element("clients");
			l = cs.elements();
			for (int i = 0; i < l.size(); i++) {
				Element c = l.get(i);
				Map<String, String> m = new HashMap<String, String>(5);
				List<Element> l2 = c.elements();
				for (int j = 0; j < l2.size(); j++) {
					c = l2.get(j);
					m.put(c.getName(), c.getText());
				}
				cList.add(m);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			log.debug("初始化socket配置工具类时出错，原因：", e);
		}
	}
	
	/**
	 * @Definition: 开通socket服务器
	 * @author: chenyongqiang
	 * @Date: 2015年10月16日
	 * @param type
	 * @param sha
	 * @throws Exception
	 */
	public static void openServer(String type, final SocketHandlerAbstract sha) throws Exception {
		if (type == null) {
			throw new Exception("注册socket服务器失败，参数type不能为空！");
		}
		Map<String, String> m = getMap(type, sList);
		if (m == null) {
			throw new Exception("注册socket服务器失败，参数type不正确，没找到相应的标识！");
		}
		final String ip = m.get("ip");
		final int port = Integer.parseInt(m.get("port"));
		final int backlog = Integer.parseInt(m.get("backlog"));
		new Thread(new Runnable() {
			@Override
			public void run() {
				SocketServer.open(ip, port, backlog, sha);
			}
		}).start();
	}
	
	/**
	 * @Definition: 获取socket客户端
	 * @author: chenyongqiang
	 * @Date: 2015年10月16日
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public static SocketClient getClient(String type) throws Exception {
		if (type == null) {
			throw new Exception("获取socket客户端失败，参数type不能为空！");
		}
		SocketClient sc = scMap.get(type);
		if (sc == null) {//初始化
			Map<String, String> sMap = getMap(type, sList);
			if (sMap == null) {
				throw new Exception("获取socket客户端失败，参数type不正确，没找到相应的标识！");
			}
			Map<String, String> cMap = getMap(type, cList);
			if (cMap == null) {
				throw new Exception("获取socket客户端失败，参数type不正确，没找到相应的标识！");
			}
			sc = new SocketClient();
			int poolSize = Integer.parseInt(cMap.get("pool-size"));
			int maxActive = Integer.parseInt(cMap.get("max-active"));
			int minIdle = Integer.parseInt(cMap.get("min-idle"));
			int maxIdle = Integer.parseInt(cMap.get("max-idle"));
			String ip = sMap.get("ip");
			int port = Integer.parseInt(sMap.get("port"));
			sc.init(poolSize, maxActive, minIdle, maxIdle, ip, port);
			scMap.put(type, sc);
		}
		return sc;
	}
	
	
	private static Map<String, String> getMap(String type, List<Map<String, String>> mList) {
		for (int i = 0; i < mList.size(); i++) {
			Map<String, String> m = mList.get(i);
			if (type.equals(m.get("token"))) {
				return m;
			}
		}
		return null;
	}
}
