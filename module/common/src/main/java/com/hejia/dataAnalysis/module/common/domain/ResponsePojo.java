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
	
	/**
	 * 以下是登录相关的
	 */
	public final static String FAIL_TYPE_LOGIN_SINGLE_IP_FAIL_TIMES_TOO_MUCH = "LOGIN_SINGLE_IP_FAIL_TIMES_TOO_MUCH"; // 时间段内单ip重试次数太多
	public final static String FAIL_TYPE_LOGIN_SINGLE_ACCOUNT_FAIL_TIMES_TOO_MUCH = "LOGIN_SINGLE_ACCOUNT_FAIL_TIMES_TOO_MUCH";
	public final static String FAIL_TYPE_LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES_TOO_MUCH = "LOGIN_SINGLE_IP_ACCOUNT_FAIL_TIMES_TOO_MUCH";
	public final static String FAIL_TYPE_LOGIN_PARAMETER_ILLEGAL = "LOGIN_PARAMETER_ILLEGAL"; // 参数非法
	public final static String FAIL_TYPE_LOGIN_NAME_CAN_NOT_EMPTY = "LOGIN_NAME_CAN_NOT_EMPTY"; // 用户名不能为空
	public final static String FAIL_TYPE_LOGIN_NAME_FORMAT_ERROR = "LOGIN_NAME_FORMAT_ERROR"; // 用户名不能为空
	public final static String FAIL_TYPE_LOGIN_ACCOUNT_NOT_EXIST = "LOGIN_NAME_NOT_EXIST"; // 账户不存在
	public final static String FAIL_TYPE_LOGIN_ACCOUNT_STATUS_ERROR = "LOGIN_ACCOUNT_STATUS_ERROR"; // 账户状态错误：被禁止、被删除等
	public final static String FAIL_TYPE_LOGIN_PWD_ERROR = "LOGIN_PWD_ERROR"; // 密码错误
	public final static String FAIL_TYPE_LOGIN_CODE_ERROR = "LOGIN_CODE_ERROR"; // 验证码错误
	public final static String FAIL_TYPE_LOGIN_VERIFY_CODE_ERROR = "LOGIN_VERIFY_CODE_ERROR"; // 图形验证码错误
	public final static String FAIL_TYPE_LOGIN_SESSION_ID_EMPTY = "LOGIN_SESSION_ID_EMPTY"; // sessionid为空
	
	
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
