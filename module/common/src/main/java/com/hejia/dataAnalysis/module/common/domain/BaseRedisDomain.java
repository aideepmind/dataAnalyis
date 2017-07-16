package com.hejia.dataAnalysis.module.common.domain;

/**
 * @Description: redis对象基本接口
 * @author: chenyongqiang
 * @Date: 2015年8月10日
 * @version: 1.0
 */
public interface BaseRedisDomain extends BaseDomain {
	
	/**
	 * @Definition: 获取对象的key
	 * @author: chenyongqiang
	 * @Date: 2015年8月9日
	 * @return
	 */
	public String getKey();
	
	/**
	 * @Definition: 获取值
	 * @author: chenyongqiang
	 * @Date: 2015年8月10日
	 * @return
	 */
	public Object getValue();
}
