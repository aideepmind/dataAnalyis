package com.hejia.dataAnalysis.module.account.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.account.domain.GroupRole;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
public interface GroupRoleDao extends PagingAndSortingRepository<GroupRole, Integer> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param gId
	 * @return
	 */
	public List<GroupRole> findByGroupId(Integer gId);
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param gId
	 * @return
	 */
	public List<GroupRole> findByRoleId(Integer gId);
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param ids
	 * @return
	 */
	public List<GroupRole> findByGroupIdIn(Collection<Integer> ids);
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param ids
	 * @return
	 */
	public List<GroupRole> findByRoleIdIn(Collection<Integer> ids);
	
}
