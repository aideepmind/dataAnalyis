package com.hejia.dataAnalysis.module.common.dao;

import java.util.List;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.DaoException;

public interface BaseDBDao extends BaseDao {
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
