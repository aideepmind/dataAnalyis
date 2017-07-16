package com.hejia.dataAnalysis.module.common.dao;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.DaoException;

public interface BaseRedisDao extends BaseDao {
	
	/**
	 * @Definition: 查询
	 * @author: chenyongqiang
	 * @Date: 2015年8月10日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public Object find(BaseDomain domain) throws DaoException;
}
