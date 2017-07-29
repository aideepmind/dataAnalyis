package com.hejia.dataAnalysis.module.auth.service;

import java.util.Collection;
import java.util.List;

import com.hejia.dataAnalysis.module.auth.domain.Permission;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月20日
 * @version: 1.0
 */
public interface PermissionService<T> extends DomainBaseService<T> {
	
	/**
	 * @Definition: 根据id的集合查找
	 * @author: chenyongqiang
	 * @Date: 2015年4月10日
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(Collection<Integer> ids) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月17日
	 * @return
	 */
	public Iterable<T> findAll() throws ServiceException;
	
	/**
	 * @Definition: 根据账户id查找权限
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param accId
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findByAccId(Integer accId) throws ServiceException;
}
