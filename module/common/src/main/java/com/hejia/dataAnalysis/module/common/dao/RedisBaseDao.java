package com.hejia.dataAnalysis.module.common.dao;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.DaoException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月16日
 * @version: 1.0
 */
public interface RedisBaseDao extends BaseDao {
	
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
