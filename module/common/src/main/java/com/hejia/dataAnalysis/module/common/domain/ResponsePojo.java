package com.hejia.dataAnalysis.module.common.domain;

import net.sf.json.JSONObject;

/**
 * @Description: 与页面交互时用
 * @author: chenyongqiang
 * @Date: 2015年4月22日
 * @version: 1.0
 */
public class ResponsePojo implements Pojo {
	
	private boolean isSuccess;
	private String failType;
	private String failReason;
	private Object message;
	
	public ResponsePojo() {
		
	}

	public ResponsePojo(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getFailType() {
		return failType;
	}
	public void setFailType(String failType) {
		this.failType = failType;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	public String toJson() {
		return JSONObject.fromObject(this).toString();
	}
}
