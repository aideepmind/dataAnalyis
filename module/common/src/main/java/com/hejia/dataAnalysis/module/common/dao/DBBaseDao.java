package com.hejia.dataAnalysis.module.common.dao;

import java.util.List;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.DaoException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月16日
 * @version: 1.0
 */
public interface DBBaseDao extends BaseDao {
	
	/**
	 * @Definition: 增加
	 * @author: chenyongqiang
	 * @Date: 2015年4月7日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public BaseDomain add(BaseDomain domain) throws DaoException;
	
	/**
	 * @Definition: 修改
	 * @author: chenyongqiang
	 * @Date: 2015年4月7日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public BaseDomain modify(BaseDomain domain) throws DaoException;
	
	/**
	 * @Definition: 删除
	 * @author: chenyongqiang
	 * @Date: 2015年4月7日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public BaseDomain delete(BaseDomain domain) throws DaoException;
	
	
	/**
	 * @Definition: 根据id查找
	 * @author: chenyongqiang
	 * @Date: 2015年4月7日
	 * @param id
	 * @return
	 */
	public BaseDomain findById(Object id) throws DaoException;
	
	/**
	 * @Definition:  根据domain查找
	 * @author: chenyongqiang
	 * @Date: 2015年4月7日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public List<BaseDomain> findByDomain(BaseDomain domain) throws DaoException;
}
