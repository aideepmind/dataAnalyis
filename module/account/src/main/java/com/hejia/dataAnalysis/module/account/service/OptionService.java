package com.hejia.dataAnalysis.module.account.service;

import java.util.List;

import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
public interface OptionService<T> extends DomainBaseService<T> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param type
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findByTypeAndName(int type, String name) throws ServiceException;
}
