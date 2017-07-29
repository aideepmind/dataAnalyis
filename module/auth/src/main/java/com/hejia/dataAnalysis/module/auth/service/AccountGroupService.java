package com.hejia.dataAnalysis.module.auth.service;

import java.util.Collection;
import java.util.List;

import com.hejia.dataAnalysis.module.auth.domain.AccountGroup;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
public interface AccountGroupService<T> extends DomainBaseService<T> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param accId
	 * @throws ServiceException
	 */
	public void deleteByAccId(Integer accId) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param accId
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findByAccId(Integer accId) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年5月3日
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findByGroupId(Integer groupId) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年5月3日
	 * @param accId
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findByAccIdAndGroupId(Integer accId, Integer groupId) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年6月7日
	 * @param accIds
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	public List<AccountGroup> findByAccIdsAndGroupId(Collection<Integer> accIds, Integer groupId) throws ServiceException;
}
