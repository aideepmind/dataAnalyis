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
public interface GroupService extends DomainBaseService {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月13日
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public List<Group> findByName(String name) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月17日
	 * @return
	 * @throws ServiceException
	 */
	public Iterable<Group> findAll() throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月17日
	 * @return
	 * @throws ServiceException
	 */
	public Group findById(Integer id) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param ids
	 * @return
	 */
	public List<Group> findByIds(Collection<Integer> ids) throws ServiceException;
}
