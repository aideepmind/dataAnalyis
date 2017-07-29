package com.hejia.dataAnalysis.module.search.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月28日
 * @version: 1.0
 */
public interface CompanySearchService extends SearchService {
	
	/**
	 * @Definition: 搜索职位
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param ra
	 * @param pr
	 * @return
	 */
	public Page<Map> searchPosition(RequestArg ra, Pageable pr) throws ServiceException;

	/**
	 * @Definition: 搜索公司
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param ra
	 * @param pr
	 * @return
	 */
	public Page<Map> searchCompany(RequestArg ra, Pageable pr) throws ServiceException;
	
}
