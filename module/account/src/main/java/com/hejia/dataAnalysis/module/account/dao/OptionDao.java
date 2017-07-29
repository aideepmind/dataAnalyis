package com.hejia.dataAnalysis.module.account.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.account.domain.Option;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
public interface OptionDao extends PagingAndSortingRepository<Option, Integer> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param type
	 * @param name
	 * @return
	 */
	public List<Option> findByTypeAndName(int type, String name);
	
}
