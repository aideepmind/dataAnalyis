package com.hejia.dataAnalysis.module.search.service;

import java.util.Collection;
import java.util.List;

import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
public interface SearchIndexService<T> extends DomainBaseService<T> {
	 
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findByTypes(Collection<Integer> types) throws ServiceException;
	
}
