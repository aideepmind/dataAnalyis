package com.hejia.dataAnalysis.module.search.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.search.domain.SearchIndex;


/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
public interface SearchIndexDao extends PagingAndSortingRepository<SearchIndex, Integer> {
	
	/**
	 * @Definition: 根据类型查找
	 * @author: chenyongqiang
	 * @Date: 2015年11月3日
	 * @param type
	 * @return
	 */
	public List<SearchIndex> findByType(Integer type);
	
	/**
	 * @Definition: 根据类型查找
	 * @author: chenyongqiang
	 * @Date: 2015年11月3日
	 * @param types
	 * @return
	 */
	public List<SearchIndex> findByTypeIn(Collection<Integer> types);
	
}
