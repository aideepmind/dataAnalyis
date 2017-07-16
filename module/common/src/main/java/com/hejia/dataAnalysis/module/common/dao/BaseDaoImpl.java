package com.hejia.dataAnalysis.module.common.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public abstract class BaseDaoImpl {

	/**
	 * sql符号
	 */
	public static final String SIGN_AND = " AND ";
	public static final String SIGN_OR = " OR ";
	public static final String SIGN_EQ = " = ";
	public static final String SIGN_NOT_EQ = " != ";
	public static final String SIGN_LIKE = " LIKE ";
	public static final String SIGN_GT = " > ";
	public static final String SIGN_LT = " < ";
	public static final String SIGN_JSON_RIGHT_CONTAIN = " @> ";
	public static final String SIGN_JSON_LEFT_CONTAIN = " <@ ";
	public static final String SIGN_JSON_TXT_CONTAIN = " ? ";
	public static final String SIGN_JSON_TXT_ANY_CONTAIN = " ?| ";
	public static final String SIGN_JSON_TXT_ALL_CONTAIN = " ?& ";
	public static final String SIGN_JSON_RIGHT = "->";
	public static final String SIGN_JSON_RIGHT_TXT = "->>";
	public static final String SIGN_JSON_RIGHT_PATH = "#>";
	public static final String SIGN_JSON_RIGHT_PATH_TXT = "#>>";

	protected abstract EntityManager getEntityManager();

	/**
	 * @Definition: 设置参数
	 * @author: chenyongqiang
	 * @Date: 2015年4月8日
	 * @param query
	 * @param params
	 */
	protected void setParams(Query query, Map<String, Object> params) {
		if (params != null) {
			Iterator<String> iter = params.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				query.setParameter(key, params.get(key));
			}
		}
	}

	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月8日
	 * @param hql
	 * @return
	 */
	protected long findCount(String hql, Map<String, Object> params) {
		int index = hql.toUpperCase().indexOf("FROM");
		if (index > -1) {
			hql = "SELECT COUNT(*) " + hql.substring(index);
		}
		index = hql.toUpperCase().lastIndexOf("ORDER BY");
		if (index > -1) {
			hql = hql.substring(0, index);
		}
		Query query = getEntityManager().createQuery(hql.toString());
		setParams(query, params);
		return (Long) query.getSingleResult();
	}

	/**
	 * @Definition: 使用原生sql的总数查询
	 * @author: chenyongqiang
	 * @Date: 2015年4月8日
	 * @param sql
	 * @return
	 */
	protected long findCountForNative(String sql, Map<String, Object> params) {
		int index = sql.toUpperCase().indexOf("FROM");
		if (index > -1) {
			sql = "SELECT COUNT(*) " + sql.substring(index);
		}
		index = sql.toUpperCase().lastIndexOf("ORDER BY");
		if (index > -1) {
			sql = sql.substring(0, index);
		}
		Query query = getEntityManager().createNativeQuery(sql.toString());
		setParams(query, params);
		return ((BigInteger) query.getSingleResult()).longValue();
	}

	/**
	 * @Definition: 使用原生sql的查总数(使用group by 或者 order by 进行复杂查询时可调用)
	 * @author: xiachao
	 * @Date: 2016年10月21日
	 * @param sql
	 * @param params
	 * @return long
	 */
	protected long findPositionCount(String sql, Map<String, Object> params) {
		Query query = getEntityManager().createNativeQuery(sql.toString());
		setParams(query, params);
		return ((BigInteger) query.getSingleResult()).longValue();
	}

	/**
	 * @Definition: 自定义判空，暂时不用工具类中的静态方法
	 * @author: chenyongqiang
	 * @Date: 2015年5月18日
	 * @param value
	 * @return
	 */
	protected boolean isNotEmpty(String value) {
		return value != null && !"".equals(value.trim());
	}

	/**
	 * @Definition: 增加like的前缀和后缀
	 * @author: chenyongqiang
	 * @Date: 2015年5月18日
	 * @param value
	 * @return
	 */
	protected String addLikeFix(String value) {
		return "%" + value + "%";
	}

	/**
	 * @Description:拼接逗号分隔的like 
	 * @author: liming
	 * @Date: 2016年9月14日
	 *	@param filterName
	 *	@param filterKey
	 *	@param value
	 *	@param params
	 *	@return
	 */
	protected String addLikeSplite(String filterName, String filterKey, String value, Map<String, Object> params) {
		String likeStr = "";
		if (isNotEmpty(value)) {
			String[] values = value.split(",");
			for (int i = 0; i < values.length; i++) {
				if (isNotEmpty(values[i])) {
					String tempFilterKey = filterKey + i;
					likeStr += "or " + filterName + " like :" + tempFilterKey + " ";
					params.put(tempFilterKey, addLikeFix(values[i]));
				}
			}
		}
		likeStr = likeStr.length() > 0 ? " and (" + likeStr.substring(2) + ")" : "";
		return likeStr;
	}

	/**
	 * @Definition: 增加like的前缀
	 * @author: chenyongqiang
	 * @Date: 2015年5月18日
	 * @param value
	 * @return
	 */
	protected String addLikePrefix(String value) {
		return "%" + value;
	}

	/**
	 * @Definition: 增加like的后缀
	 * @author: chenyongqiang
	 * @Date: 2015年5月18日
	 * @param value
	 * @return
	 */
	protected String addLikePostfix(String value) {
		return value + "%";
	}

	/**
	 * @Definition: 加上双引号
	 * @author: chenyongqiang
	 * @Date: 2016年5月10日
	 * @param name
	 * @return
	 */
	protected String addDoubleQuote(String name) {
		return "\"" + name + "\"";
	}

	/**
	 * @Definition: 加上单引号
	 * @author: chenyongqiang
	 * @Date: 2016年5月10日
	 * @param name
	 * @return
	 */
	protected String addSingleQuote(String name) {
		return "'" + name + "'";
	}

	/**
	 * @Definition: 组合json串
	 * @author: chenyongqiang
	 * @Date: 2016年5月10日
	 * @param key
	 * @param value
	 * @return
	 */
	protected String combineJson(String key, String value) {
		return "'{\"" + key + "\":\"" + value + "\"}'";
	}

	/**
	 * @Definition: 组合json串
	 * @author: chenyongqiang
	 * @Date: 2016年5月10日
	 * @param keys
	 * @param values
	 * @return
	 */
	protected String combineJson(String[] keys, String[] values) {
		String json = "'{\"";
		for (int i = 0; i < keys.length; i++) {
			json += keys[i] + "\":\"" + values[i];
		}
		return json + "\"}'";
	}

	/**
	 * @Definition: 组合json串
	 * @author: chenyongqiang
	 * @Date: 2016年5月10日
	 * @param keys
	 * @param values
	 * @return
	 */
	protected String combineJson(List<String> keys, List<String> values) {
		String json = "'{";
		for (int i = 0; i < keys.size(); i++) {
			json += "\"" + keys.get(i) + "\":\"" + values.get(i) + "\"" + ",";
		}
		return json.substring(0, json.length() - 1) + "}'";
	}

	/**
	 * @Definition: 组合json数组串
	 * @author: chenyongqiang
	 * @Date: 2016年5月10日
	 * @param key
	 * @param value
	 * @return
	 */
	protected String combineJsonArray(String key, String value) {
		return "'[{\"" + key + "\":\"" + value + "\"}]'";
	}

}
