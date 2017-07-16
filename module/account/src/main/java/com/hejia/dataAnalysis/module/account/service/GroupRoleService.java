package com.hejia.dataAnalysis.module.account.service;

import java.util.Collection;
import java.util.List;

import com.hejia.dataAnalysis.module.account.domain.GroupRole;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
public interface GroupRoleService extends DomainBaseService {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param gId
	 * @return
	 * @throws ServiceException
	 */
	public List<GroupRole> findByGroupId(Integer gId) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param rId
	 * @return
	 * @throws ServiceException
	 */
	public List<GroupRole> findByRoleId(Integer rId) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	public List<GroupRole> findByGroupIds(Collection<Integer> ids) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	public List<GroupRole> findByRoleIds(Collection<Integer> ids) throws ServiceException;
	
}
