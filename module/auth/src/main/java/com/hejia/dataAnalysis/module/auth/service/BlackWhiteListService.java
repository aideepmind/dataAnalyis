package com.hejia.dataAnalysis.module.auth.service;

import java.util.List;

import com.hejia.dataAnalysis.module.auth.domain.BlackWhiteList;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.BaseService;

/**
 * @Description: 黑白名单业务层接口
 * @author: chenyongqiang
 * @Date: 2015年12月28日
 * @version: 1.0
 */
public interface BlackWhiteListService extends BaseService {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年12月29日
	 * @param type
	 * @param funtionType
	 * @return
	 * @throws ServiceException
	 */
	public List<BlackWhiteList> findByTypeAndFunctionType(Integer type, Integer funtionType) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年12月28日
	 * @param type
	 * @param funtionType
	 * @param value
	 * @return
	 * @throws ServiceException
	 */
	public List<BlackWhiteList> findByTypeAndFunctionTypeAndValue(Integer type, Integer funtionType, String value) throws ServiceException;
}
