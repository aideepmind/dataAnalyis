package com.hejia.dataAnalysis.module.auth.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.auth.domain.Group;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2015年4月10日
 * @version: 1.0
 */
public interface GroupDao extends PagingAndSortingRepository<Group, Integer> {
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月13日
	 * @param name
	 * @return
	 */
	public List<Group> findByName(String name);

	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param ids
	 * @return
	 */
	public List<Group> findByGroupIdIn(Collection<Integer> ids);
		
}
