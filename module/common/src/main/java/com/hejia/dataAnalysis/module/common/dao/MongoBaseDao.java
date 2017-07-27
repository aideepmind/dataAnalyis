package com.hejia.dataAnalysis.module.common.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;

import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.exception.DaoException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月22日
 * @version: 1.0
 */
public interface MongoBaseDao<T> extends BaseDao {
	
	/**
	 * @Definition: 增加
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public T add(T domain) throws DaoException;
	
	/**
	 * @Definition: 修改
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public T modify(T domain) throws DaoException;
	
	/**
	 * @Definition: 部分修改，只修改第一条记录
	 * @author: chenyongqiang
	 * @Date: 2017年7月25日
	 * @param queryDomain
	 * @param updateDomain
	 * @return
	 * @throws DaoException
	 */
	public Object modifyPartFirst(T queryDomain, T updateDomain) throws DaoException;
	
	/**
	 * @Definition: 部分修改，修改所有符合条件的记录
	 * @author: chenyongqiang
	 * @Date: 2017年7月25日
	 * @param queryDomain
	 * @param updateDomain
	 * @return
	 * @throws DaoException
	 */
	public Object modifyPartMulti(T queryDomain, T updateDomain) throws DaoException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public boolean delete(T domain) throws DaoException;
	
	/**
	 * @Definition: 根据id查找
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public T findById(ObjectId id) throws DaoException;
	
	/**
	 * @Definition: 查找集合
	 * @author: chenyongqiang
	 * @Date: 2017年7月24日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public List<T> find(T domain) throws DaoException;
	
	/**
	 * @Definition: 分页查找
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param domain
	 * @param pr
	 * @return
	 * @throws DaoException
	 */
	public Page<T> findPage(T domain, PageRequest pr) throws DaoException;

	/**
	 * @Definition: 查找总数
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param domain
	 * @return
	 * @throws DaoException
	 */
	public long count(T domain) throws DaoException;

	/**
	 * @Definition: 查找总数
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public long count(Query query) throws DaoException;
}
