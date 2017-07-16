package com.hejia.dataAnalysis.module.common.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: Service基本接口
 * @author: chenyongqiang
 * @Date: 2015年4月6日
 * @version: 1.0
 */
public interface BaseService {
	
	/**
	 * @Definition: 增加
	 * @author: chenyongqiang
	 * @Date: 2015年4月6日
	 * @param domain
	 * @return
	 * @throws ServiceException
	 */
	public BaseDomain add(BaseDomain domain) throws ServiceException;
	
	/**
	 * @Definition: 修改
	 * @author: chenyongqiang
	 * @Date: 2015年4月6日
	 * @param domain
	 * @return
	 * @throws ServiceException
	 */
	public BaseDomain modify(BaseDomain domain) throws ServiceException;
	
	/**
	 * @Definition: 删除
	 * @author: chenyongqiang
	 * @Date: 2015年4月6日
	 * @param domain
	 * @return
	 * @throws ServiceException
	 */
	public BaseDomain delete(BaseDomain domain) throws ServiceException;
	
	/**
	 * @Definition: 根据domain查找
	 * @author: chenyongqiang
	 * @Date: 2015年4月6日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Page find(BaseDomain domain, PageRequest pageRequest) throws ServiceException;

}
