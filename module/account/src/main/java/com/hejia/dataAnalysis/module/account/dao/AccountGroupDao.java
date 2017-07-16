package com.hejia.dataAnalysis.module.account.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.account.domain.AccountGroup;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
public interface AccountGroupDao extends PagingAndSortingRepository<AccountGroup, Integer> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param accId
	 * @return
	 */
	public List<AccountGroup> findByAccId(Integer accId);
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param groupId
	 * @return
	 */
	public List<AccountGroup> findByGroupId(Integer groupId);
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年5月3日
	 * @param accId
	 * @param groupId
	 * @return
	 */
	public List<AccountGroup> findByAccIdAndGroupId(Integer accId, Integer groupId);
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年6月7日
	 * @param accIds
	 * @param groupId
	 * @return
	 */
	public List<AccountGroup> findByAccIdInAndGroupId(Collection<Integer> accIds, Integer groupId);
}
