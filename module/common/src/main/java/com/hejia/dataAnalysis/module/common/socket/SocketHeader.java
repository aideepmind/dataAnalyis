package com.hejia.dataAnalysis.module.common.socket;

/**
 * @Description: 请求头部
 * @author: chenyongqiang
 * @Date: 2015年10月15日
 * @version: 1.0
 */
public class SocketHeader {

	public static final String CONTENT_TYPE_TEXT = "text";// 默认
	public static final String CONTENT_TYPE_XML = "xml";
	public static final String CONTENT_TYPE_JSON = "json";

	public static final String CONTENT_ENCODING_BASE64 = "base64";// 默认
	public static final String CONTENT_ENCODING_DES = "des";
	
	private String url;
	private String contentType;
	private String contentEncoding;
	private String address;
	private String date;
	
	public SocketHeader() {
		
	}
	
	public SocketHeader(String url) {
		this(url, null);
	}

	public SocketHeader(String url, String address) {
		this(url, address, CONTENT_TYPE_TEXT);
	}

	public SocketHeader(String url, String address, String contentType) {
		this(url, address, contentType, CONTENT_ENCODING_BASE64);
	}

	public SocketHeader(String url, String address, String contentType, String contentEncoding) {
		this.url = url;
		this.address = address;
		this.contentType = contentType;
		this.contentEncoding = contentEncoding;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
