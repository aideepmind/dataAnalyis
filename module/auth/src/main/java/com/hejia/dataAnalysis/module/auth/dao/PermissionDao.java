package com.hejia.dataAnalysis.module.auth.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.auth.domain.Permission;
/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2015年4月10日
 * @version: 1.0
 */
public interface PermissionDao extends PagingAndSortingRepository<Permission, Integer> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月10日
	 * @param ids
	 * @return
	 */
	public List<Permission> findByPermsIdIn(Collection<Integer> ids);
	
}
