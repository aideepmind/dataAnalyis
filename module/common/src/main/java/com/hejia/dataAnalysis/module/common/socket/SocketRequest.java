package com.hejia.dataAnalysis.module.common.socket;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.util.Base64Utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.hejia.dataAnalysis.module.common.Constant;
import com.hejia.dataAnalysis.module.common.utils.DateUtils;
import com.hejia.dataAnalysis.module.common.utils.MD5Utils;
import com.hejia.dataAnalysis.module.common.utils.SDESUtils;

/**
 * @Description: socket请求
 * @author: chenyongqiang
 * @Date: 2015年10月15日
 * @version: 1.0
 */
public class SocketRequest {
	private SocketHeader header;
	private String content;
	
	public SocketRequest() {
		
	}
	
	public SocketRequest(SocketHeader header, String content) {
		this.header = header;
		this.content = content;
	}
	
	public SocketHeader getHeader() {
		return header;
	}

	public void setHeader(SocketHeader header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public synchronized String toString() {
		StringBuilder b = new StringBuilder();
		b.append("{");
		//头部
		b.append("'header':{");
		b.append("'url':").append("'").append(header.getUrl()).append("'");
		if (header.getAddress() != null) {
			b.append(",'address':").append("'").append(header.getAddress()).append("'");
		} else {//取当前ip地址
			try {
				InetAddress address = InetAddress.getLocalHost();
				b.append(",'address':").append("'").append(address.getHostAddress()).append("'");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		b.append(",'contentType':").append("'").append(header.getContentType()).append("'");
		b.append(",'contentEncoding':").append("'").append(header.getContentEncoding()).append("'");
		//时间
		b.append(",'date':").append("'").append(DateUtils.toString(new Date(), DateUtils.FORMAT_COMMON_LONG)).append("'");
		b.append("}");
		//内容
		try {
			b.append(",'content':").append("'");
			if (SocketHeader.CONTENT_ENCODING_BASE64.equals(header.getContentEncoding())) {//base64
				b.append(Base64Utils.encodeToString(content.getBytes(MD5Utils.CHARSET_UTF8)));
			} else if (SocketHeader.CONTENT_ENCODING_DES.equals(header.getContentEncoding())) {//des
				b.append(SDESUtils.encrypt(Constant.COOKIE_DES_KEY, content));
			}
			b.append("'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		b.append("}");
		//加上结束标示
		b.append("\r\n<![end]>\r\n");
		return b.toString();
	}
}
