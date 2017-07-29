package com.hejia.dataAnalysis.module.search.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.BaseService;

public interface SearchService extends BaseService {

	/**
	 * @Definition: 获取关键字
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param kw
	 * @param p
	 * @return
	 * @throws ServiceException
	 */
	public Page<Map> findKeyword(String kw, Pageable p) throws ServiceException;
	
	/**
	 * @Definition: 初始化lucene索引
	 * @author: chenyongqiang
	 * @Date: 2015年11月2日
	 * @throws ServiceException
	 */
	public void init() throws ServiceException;
	
	/**
	 * @Definition: 分词
	 * @author: chenyongqiang
	 * @Date: 2016年4月27日
	 * @param kw
	 * @return
	 * @throws ServiceException
	 */
	public String analyzer(String kw) throws ServiceException;
}
