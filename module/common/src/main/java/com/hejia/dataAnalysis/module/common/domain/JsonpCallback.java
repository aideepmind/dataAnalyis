package com.hejia.dataAnalysis.module.common.domain;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年3月6日
 * @version: 1.0
 */
public class JsonpCallback {
	
	private String callback;
	
	private ResponsePojo rp;
	
	public JsonpCallback(ResponsePojo rp) {
		this.rp = rp;
	}
	
	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public void setSuccess(boolean isSuccess) {
		this.rp.setSuccess(isSuccess);
	}
	
	public void setFailType(String failType) {
		this.rp.setFailType(failType);
	}
	
	public void setFailReason(String failReason) {
		this.rp.setFailReason(failReason);
	}
	
	public void setMessage(Object message) {
		this.rp.setMessage(message);
	}
	
	public String toJson() {
		return this.callback + "(" + rp.toJson() + ")";
	}
}
