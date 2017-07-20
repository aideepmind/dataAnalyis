package com.hejia.dataAnalysis.module.common.domain;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.hejia.dataAnalysis.module.common.utils.HttpUtils;

/**
 * @Description: 请求参数封装（以后谁要加上方法时，请注意hasEmptyArg属性）
 * @author: chenyongqiang
 * @Date: 2015年7月20日
 * @version: 1.0
 */
public class RequestArg {
	
	private Map argMap;
	private Map<String, String> cookieMap; // 存放cookie的集合
	private Map attributeMap; // 目前业务用不着，用到时再添加
	private String ip;
	private boolean hasNotEmptyArg; // 是否有空属性，在全部调用之后，可通过该属性来判断参数中是否含有非空值-chenyq
	
	public RequestArg() {
		argMap = new HashMap();
		cookieMap = new HashMap();
	}

	public RequestArg(HttpServletRequest request) {
		this.setArg(request);
	}
	
	public static RequestArg gteInstance(HttpServletRequest request) {
		return new RequestArg(request);
	}
	
	/**
	 * @Definition: 设置参数
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param request
	 */
	public void setArg(HttpServletRequest request) {
		argMap = new HashMap();
		cookieMap = new HashMap();
		argMap.putAll(request.getParameterMap());
		Cookie cookies[] = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				cookieMap.put(cookie.getName(), cookie.getValue());
			}
		}
		// 设置ip
		this.setIp(HttpUtils.getIpAddr(request));
	};
	
	/**
	 * @Definition: 设置参数
	 * @author: chenyongqiang
	 * @Date: 2015年8月11日
	 * @param name
	 * @param value
	 */
	public void set(String name, String value) {
		argMap.put(name, new String[] {value});
	};
	
	/**
	 * @Definition: 获取值
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param name
	 * @return
	 */
	public String getString(String name) {
		String value = null;
		if (isNotEmpty(name)) {
			String values[] = (String[]) argMap.get(name);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					if (i == 0) {
						value = values[i];
						continue;
					}
					value += "," + values[i];
				}
			}
		}
		if (value != null) {
			hasNotEmptyArg = true;
		}
		return value;
	};
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年10月21日
	 * @param name
	 * @return
	 */
	public String getStringAndNotNull(String name) {
		String value = getString(name);
		if (value == null) {
			value = "";
		}
		return value;
	};
	
	/**
	 * @Definition: 编码
	 * @author: chenyongqiang
	 * @Date: 2016年6月7日
	 * @param name
	 * @return
	 */
	public String getStringAndDecode(String name) {
		String value = getString(name);
		if (value != null) {
			URLDecoder.decode(value);
		}
		return value;
	};
	
	/**
	 * @Definition: 获取字符串数组
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param name
	 * @return
	 */
	public String[] getStrings(String name) {
		String values[] = null;
		if (isNotEmpty(name)) {
			values = (String[]) argMap.get(name);
		}
		if (values != null) {
			hasNotEmptyArg = true;
		}
		return values;
	};
	
	/**
	 * @Definition: 获取集合
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param name
	 * @return
	 */
	public List<String> getList(String name) {
		List<String> l = null;
		if (isNotEmpty(name)) {
			String values[] = (String[]) argMap.get(name);
			if (values != null) {
				l = Arrays.asList(values);
			}
		}
		if (l != null) {
			hasNotEmptyArg = true;
		}
		return l;
	};
	
	/**
	 * @Definition: 获取整形
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param name
	 * @return
	 */
	public Integer getInteger(String name) {
		Integer value = null;
		if (isNotEmpty(name)) {
			String values[] = (String[]) argMap.get(name);
			if (values != null) {
				value = Integer.parseInt(values[0]);
			}
		}
		if (value != null) {
			hasNotEmptyArg = true;
		}
		return value;
	};

	/**
	 * @Definition: 获取布尔值
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param name
	 * @return
	 */
	public Boolean getBoolean(String name) {
		Boolean value = null;
		if (isNotEmpty(name)) {
			String values[] = (String[]) argMap.get(name);
			if (values != null) {
				value = Boolean.parseBoolean(values[0]);
			}
		}
		if (value != null) {
			hasNotEmptyArg = true;
		}
		return value;
	};
	
	/**
	 * @Definition: 自定义判空，暂时不用工具类中的静态方法
	 * @author: chenyongqiang
	 * @Date: 2015年5月18日
	 * @param value
	 * @return
	 */
	private boolean isNotEmpty(String value) {
		return value != null && !"".equals(value.trim());
	}

	/**
	 * @Definition: 是否有非空值，一般跟getXxx方法连着用
	 * @author: chenyongqiang
	 * @Date: 2016年5月10日
	 * @return
	 */
	public boolean isHasNotEmptyArg() {
		return hasNotEmptyArg;
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param hasNotEmptyArg
	 */
	public void setHasNotEmptyArg(boolean hasNotEmptyArg) {
		this.hasNotEmptyArg = hasNotEmptyArg;
	}
	
	/**
	 * @Definition: 获取cookie值
	 * @author: chenyongqiang
	 * @Date: 2017年7月18日
	 * @param name
	 * @return
	 */
	public String getCookieString(String name) {
		return cookieMap.get(name);
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
