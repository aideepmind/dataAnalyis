package com.hejia.dataAnalysis.module.common.socket;

import java.io.UnsupportedEncodingException;

import org.springframework.util.Base64Utils;

import com.hejia.dataAnalysis.module.common.Constant;
import com.hejia.dataAnalysis.module.common.utils.MD5Util;
import com.hejia.dataAnalysis.module.common.utils.SDESUtils;

import net.sf.json.JSONObject;

/**
 * @Description: Socket工具类
 * @author: chenyongqiang
 * @Date: 2015年10月15日
 * @version: 1.0
 */
public class SocketUtils {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年10月15日
	 * @param str
	 * @return
	 */
	public synchronized static SocketRequest parseRequest(String str) {
		SocketRequest sr = null;
		try {
			sr = (SocketRequest) JSONObject.toBean(JSONObject.fromObject(str), SocketRequest.class);
			if (SocketHeader.CONTENT_ENCODING_BASE64.equals(sr.getHeader().getContentEncoding())) {
				sr.setContent(new String(Base64Utils.decodeFromString(sr.getContent()), MD5Util.CHARSET_UTF8));
			} else if (SocketHeader.CONTENT_ENCODING_DES.equals(sr.getHeader().getContentEncoding())) {
				sr.setContent(SDESUtils.decrypt(Constant.COOKIE_DES_KEY, sr.getContent()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sr;
	}
	
	
	
}
