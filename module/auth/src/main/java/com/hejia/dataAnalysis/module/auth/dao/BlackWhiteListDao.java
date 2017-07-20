package com.hejia.dataAnalysis.module.auth.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.auth.domain.BlackWhiteList;

/**
 * @Description:  黑名单dao接口，可以使用@query注解和方法名的方式来查找，如果两者无法满足需求，则需要自定义sql查找。
 * @author: chenyongqiang
 * @Date: 2015年12月28日
 * @version: 1.0
 */
public interface BlackWhiteListDao extends PagingAndSortingRepository<BlackWhiteList, Integer> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年12月29日
	 * @param type
	 * @param funtionType
	 * @return
	 */
	public List<BlackWhiteList> findByTypeAndFunctionType(Integer type, Integer funtionType);
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年12月28日
	 * @param type
	 * @param funtionType
	 * @param value
	 * @return
	 */
	public List<BlackWhiteList> findByTypeAndFunctionTypeAndValue(Integer type, Integer funtionType, String value);
}
