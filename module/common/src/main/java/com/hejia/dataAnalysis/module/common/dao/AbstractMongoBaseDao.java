package com.hejia.dataAnalysis.module.common.dao;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.exception.DaoException;
import com.hejia.dataAnalysis.module.common.utils.ReflectionUtils;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月22日
 * @version: 1.0
 * @param <T>
 */
public abstract class AbstractMongoBaseDao<T> implements MongoBaseDao<T> {
	/**
	 * 要弄明白这些概念：Query、BasicQuery（重要）、Criteria、Aggregate（重要）（条件以及条件链CriteriaChain）
	 * 1.Criteria类中有一个key属性，对应于表中的一个字段；有一个isValue属性，表示可以的值；有一个criteriaChain属性，List类型，表示条件链；有criteria属性，Map类型，表示非isValue
	 * 2.Criteria中的where和and方法，where是静态方法，返回一个new Criteria()；而and是实例方法，同样返回一个new Criteria(criteriaChain)，但是把当前实例的criteriaChain传了进入，表示是在同一个链上
	 * 3.Criteria中的orOperator、norOperator和andOperator方法可以组合成复杂的查询语句
	 * 4.Query中的skip、limit、sort方法可以用于分页以及排序，里面还有一些别的有用的方法
	 * 5.利用com.mongodb.AggregationOutput提高效率
	 * 6.操作符：$eq、$gt、$gte、$lt、$lte、$ne、$in、$nin、$or、$and、$not、$nor、$exists、$type、$mod、$regex、$text、$where、$all、$$elemMatch（query）、$size、$(projection)、$elemMatch(projection)、$meta、$slice(projection)、$inc、$mul、$rename、$setOnInsert、$set、$unset、$min、$max、$currentDate、$（update）、$addToSet、$pop、$pullAll、$pull、$pushAll、$push、$each、$sort、$position
	 */
	
	protected abstract MongoTemplate getMongoTemplate();
	
	/**
	 * @Definition: 获取实体class类
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @return
	 */
	private Class<T> getEntityClass() {
		return ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	/**
	 * @Definition: 该方法稍微慢一些，但更加方便一些，因为经过 spring的封装
	 * @author: chenyongqiang
	 * @Date: 2017年7月24日
	 * @param domain
	 * @return
	 */
	protected Query domainToQuery(T domain) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		try {
			Class cls = domain.getClass();
			Method ms[] = cls.getMethods();
			Map<String, Object> params = new HashMap<String, Object>(ms.length);
			for (Method m : ms) {
				Class mcls[] = m.getParameterTypes();
				if (mcls != null && mcls.length > 0) continue; // 有返回值
				String name = m.getName();
				if (name.startsWith("get")) { // 只要get方法
					Object value = m.invoke(domain);
					if (value == null) continue;
					if (value instanceof String && "".equals(value)) continue;
					String field = name.substring(3, 4).toLowerCase() + name.substring(4);
					if("class".equals(field)) continue;
					if (m.getReturnType() == String.class) {
						criteria.and(field).regex((String) value); // like
					} else {
						criteria.and(field).is(value); // equal
					}
				}					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		query.addCriteria(criteria);
//		System.out.println(query.toString());
		return query;
	}
	
	/**
	 * @Definition: 该方法更加快，因为更加底层
	 * @author: chenyongqiang
	 * @Date: 2017年7月24日
	 * @param domain
	 * @return
	 */
	protected Query domainToQueryMoreQuick(T domain) {
//		BasicDBList basicDBList = new BasicDBList();
		QueryBuilder queryBuilder = new QueryBuilder();
		try {
			Class cls = domain.getClass();
			Method ms[] = cls.getMethods();
			Map<String, Object> params = new HashMap<String, Object>(ms.length);
			for (Method m : ms) {
				Class mcls[] = m.getParameterTypes();
				if (mcls != null && mcls.length > 0) continue; // 有返回值
				String name = m.getName();
				if (name.startsWith("get")) { // 只要get方法
					Object value = m.invoke(domain);
					if (value == null) continue;
					if (value instanceof String && "".equals(value)) continue;
					String field = name.substring(3, 4).toLowerCase() + name.substring(4);
					if("class".equals(field)) continue;
					if (m.getReturnType() == String.class) {
//						BasicDBObject basicDBObject = new BasicDBObject(field, new BasicDBObject("$regex", Pattern.compile("^.*" + value +  ".*$", Pattern.CASE_INSENSITIVE)));
//						basicDBList.add(basicDBObject);
						queryBuilder.and(field).regex(Pattern.compile("^.*" + value +  ".*$", Pattern.CASE_INSENSITIVE)); // like
					} else {
//						BasicDBObject basicDBObject = new BasicDBObject(field, value);
//						basicDBList.add(basicDBObject);
						queryBuilder.and(field).is(value); // equal
					}
				}					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Query query = new BasicQuery(queryBuilder.get());
		return query;
	}
	
	@Override
	public T add(T domain) throws DaoException {
		getMongoTemplate().insert(domain);
		return domain;
	}
	
	@Override
	public T modify(T domain) throws DaoException {
		getMongoTemplate().save(domain);
		return domain;
	}
	
	@Override
	public boolean delete(T domain) throws DaoException {
		WriteResult wr = getMongoTemplate().remove(domain);
		return wr.wasAcknowledged();
	}
	
	@Override
	public T findById(ObjectId id) throws DaoException {
		return getMongoTemplate().findById(id, getEntityClass());
	}
	
	@Override
	public List<T> find(T domain) throws DaoException {
		return getMongoTemplate().find(domainToQueryMoreQuick(domain), getEntityClass());
	}
	
	@Override
	public Page<T> findPage(T domain, PageRequest pr) throws DaoException {
		return null;
	}
	
	@Override
	public Page<T> findPageByCondition(RequestArg ra, PageRequest pr) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long count(T domain) throws DaoException {
		return getMongoTemplate().count(domainToQuery(domain), getEntityClass());
	}
}
