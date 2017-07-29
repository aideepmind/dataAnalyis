package com.hejia.dataAnalysis.module.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 所有跟domain对应的Service接口的基本接口
 * @author: chenyongqiang
 * @Date: 2017年7月16日
 * @version: 1.0
 */
public interface DomainBaseService<T> extends BaseService {
	
	/**
	 * @Definition: 增加
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param domain
	 * @return
	 * @throws ServiceException
	 */
	public T add(T domain) throws ServiceException;
	
	/**
	 * @Definition: 修改
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param domain
	 * @return
	 * @throws ServiceException
	 */
	public T modify(T domain) throws ServiceException;
	
	/**
	 * @Definition: 删除
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param domain
	 * @return
	 * @throws ServiceException
	 */
	public T delete(T domain) throws ServiceException;
	
	/**
	 * @Definition: 根据domain查找
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param domain
	 * @param p
	 * @return
	 * @throws ServiceException
	 */
	public Page<T> find(T domain, Pageable p) throws ServiceException;


}
