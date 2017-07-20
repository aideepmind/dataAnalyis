package com.hejia.dataAnalysis.module.auth.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.auth.domain.Role;

/**
 * @Description: 角色dao接口
 * @author: chenyongqiang
 * @Date: 2015年4月10日
 * @version: 1.0
 */
public interface RoleDao extends PagingAndSortingRepository<Role, Integer> {
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月10日
	 * @param id
	 * @return
	 */
	public List<Role> findByRoleIdIn(Collection<Integer> ids);
	
}
