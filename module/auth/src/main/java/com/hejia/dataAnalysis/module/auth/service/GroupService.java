package com.hejia.dataAnalysis.module.auth.service;

import java.util.Collection;
import java.util.List;

import com.hejia.dataAnalysis.module.auth.domain.Group;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2015年4月10日
 * @version: 1.0
 */
public interface GroupService<T> extends DomainBaseService<T> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月13日
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findByName(String name) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月17日
	 * @return
	 * @throws ServiceException
	 */
	public Iterable<T> findAll() throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月17日
	 * @return
	 * @throws ServiceException
	 */
	public T findById(Integer id) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(Collection<Integer> ids) throws ServiceException;
}
