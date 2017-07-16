package com.hejia.dataAnalysis.module.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 所有跟domain对应的Service接口的基本接口
 * @author: chenyongqiang
 * @Date: 2017年7月16日
 * @version: 1.0
 */
public interface DomainBaseService extends BaseService {
	
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
	public Page<BaseDomain> find(BaseDomain domain, PageRequest pageRequest) throws ServiceException;


}
