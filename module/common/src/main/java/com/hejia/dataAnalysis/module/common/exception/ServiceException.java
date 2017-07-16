package com.hejia.dataAnalysis.module.common.exception;
/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2015年4月6日
 * @version: 1.0
 */
public class ServiceException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2097674322818325630L;
	
	private String output = "";

	public ServiceException() {
		super();
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.output = message;
	}

	public ServiceException(String message) {
		super(message);
		this.output = message;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
