package com.hejia.dataAnalysis.module.auth.dao.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.hejia.dataAnalysis.module.auth.domain.Permission;
import com.hejia.dataAnalysis.module.common.dao.AbstractBaseDao;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.DaoException;

public class PermissionDaoImpl extends AbstractBaseDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public Page<Permission> find(BaseDomain domain, PageRequest pr) throws DaoException {
		try {
			StringBuilder hql = new StringBuilder("FROM Permission WHERE 1 = 1");
			Class cls = domain.getClass();
			Method ms[] = cls.getMethods();
			Map<String, Object> params = new HashMap<String, Object>(ms.length/2);
			
			for (Method m : ms) {
				Class mcls[] = m.getParameterTypes();
				//有返回值
				if (mcls != null && mcls.length > 0) continue;
				String name = m.getName();
				//只要get方法
				if (name.startsWith("get")) {
					
					Object value = m.invoke(domain);
					if (value == null) continue;
					if (value instanceof String && "".equals(value)) {
						continue;
					}
					String field = name.substring(3, 4).toLowerCase() + name.substring(4);
					if("class".equals(field)) continue;
					if (m.getReturnType() == String.class) {
						hql.append(" AND ").append(field).append(" like :").append(field);
						params.put(field, "%" + value + "%");
					} else {
						hql.append(" AND ").append(field).append(" = :").append(field);
						params.put(field, value);
					}
				}					
			}
			String hqlStr = hql.toString();
			long count = findCount(hqlStr, params);
			if (count == 0) {
				return new PageImpl<Permission>(new ArrayList<Permission>(0), pr, count);
			}
			Query query = em.createQuery(hqlStr);
			setParams(query, params);
			query.setFirstResult(pr.getPageNumber() * pr.getPageSize());
			query.setMaxResults(pr.getPageSize());
			Page<Permission> page = new PageImpl<Permission>(query.getResultList(), pr, count);
			return page;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	};
}
