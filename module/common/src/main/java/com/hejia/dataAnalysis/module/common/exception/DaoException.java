package com.hejia.dataAnalysis.module.common.exception;
/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2015年4月6日
 * @version: 1.0
 */
public class DaoException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7952827878151001407L;
	
	public DaoException() {
		super();
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
	
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}
	
}
