package com.hejia.dataAnalysis.module.common.domain.cookie;

/**
 * @Description: 认证cookie
 * @author: chenyongqiang
 * @Date: 2016年4月1日
 * @version: 1.0
 */
public class AuthenticationCookie implements BaseCookie {
	
	public static final String KEY_DEFAULT = "matc";//minixiao authen cookie
	
	private String token;//凭证
	private String invalidDate;//失效时间
	
	public String getKey() {
		return KEY_DEFAULT;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}
	
}
