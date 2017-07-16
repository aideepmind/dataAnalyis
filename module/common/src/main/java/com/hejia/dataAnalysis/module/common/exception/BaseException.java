package com.hejia.dataAnalysis.module.common.exception;

/**
 * @Description: 基本异常类
 * @author: chenyongqiang
 * @Date: 2015年4月6日
 * @version: 1.0
 */
public class BaseException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9019885855600574073L;

	public BaseException() {
	    super();
	  }

	  public BaseException(String s) {
	    super(s);
	  }

	  public BaseException(String s, Throwable e) {
	    super(s, e);
	  }

	  public BaseException(Throwable e) {
	    super(e);
	  }


}
