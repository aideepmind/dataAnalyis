package com.hejia.dataAnalysis.module.account.service;

import java.util.Collection;
import java.util.List;

import com.hejia.dataAnalysis.module.account.domain.AccountGroup;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
public interface AccountGroupService extends DomainBaseService {
	
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
	public List<AccountGroup> findByAccId(Integer accId) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年5月3日
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	public List<AccountGroup> findByGroupId(Integer groupId) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年5月3日
	 * @param accId
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	public List<AccountGroup> findByAccIdAndGroupId(Integer accId, Integer groupId) throws ServiceException;
	
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
