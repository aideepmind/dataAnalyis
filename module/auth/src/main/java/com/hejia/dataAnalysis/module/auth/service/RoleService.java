package com.hejia.dataAnalysis.module.auth.service;

import java.util.Collection;
import java.util.List;

import com.hejia.dataAnalysis.module.auth.domain.Role;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 角色业务层接口
 * @author: chenyongqiang
 * @Date: 2015年4月10日
 * @version: 1.0
 */
public interface RoleService<T> extends DomainBaseService<T> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月10日
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(Collection<Integer> ids);
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月17日
	 * @return
	 * @throws ServiceException
	 */
	public Iterable<T> findAll() throws ServiceException;
	
	/**
	 * @Definition: 根据账户id查找角色
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param accId
	 * @return
	 * @throws ServiceException
	 */
	public List<T> findByAccId(Integer accId) throws ServiceException;
}
