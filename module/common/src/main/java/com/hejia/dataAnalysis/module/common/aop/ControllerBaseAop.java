package com.hejia.dataAnalysis.module.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * @Description: controller拦截器，主要用于记日志
 * @author: chenyongqiang
 * @Date: 2015年6月24日
 * @version: 1.0
 */
public interface ControllerBaseAop {

	public final static Logger log = Logger.getLogger(ControllerBaseAop.class);
	
	/**
	 * @Definition: 没有异常时进入的方法
	 * @author: chenyongqiang
	 * @Date: 2015年6月23日
	 * @param jp
	 * @param r
	 */
	public void logAfterReturn(JoinPoint jp, Object r);
	
	/**
	 * @Definition: 抛出异常时进入的方法
	 * @author: chenyongqiang
	 * @Date: 2015年6月23日
	 * @param jp
	 * @param e
	 */
	public void logAfterThrowing(JoinPoint jp, Exception e);
}
