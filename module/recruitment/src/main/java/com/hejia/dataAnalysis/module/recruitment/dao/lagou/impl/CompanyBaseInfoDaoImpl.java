package com.hejia.dataAnalysis.module.recruitment.dao.lagou.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.domain.Sort.*;

import com.hejia.dataAnalysis.module.common.dao.AbstractMongoBaseDao;
import com.hejia.dataAnalysis.module.common.exception.DaoException;
import com.hejia.dataAnalysis.module.recruitment.domain.lagou.CompanyBaseInfo;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @Description:
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
public class CompanyBaseInfoDaoImpl extends AbstractMongoBaseDao<CompanyBaseInfo> {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	protected MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	
	/**
	 * @Definition: 第一种写法
	 * @author: chenyongqiang
	 * @Date: 2017年7月25日
	 * @return
	 * @throws DaoException
	 */
	public BasicDBList groupByIndustryField1() throws DaoException {
		// $count/$sum/$avg/$min/$max/$push/$addTose/$first/$last
		DBObject dbObject = new BasicDBObject();
		dbObject.put("count", 0);
		GroupBy groupBy = GroupBy.key("industryField")
				.initialDocument(dbObject)
				.reduceFunction("function(doc, out){out.count++}");
		GroupByResults<CompanyBaseInfo> groupByResults = this.getMongoTemplate()
				.group("company_baseInfo", groupBy, super.getDomainClass());
		dbObject = groupByResults.getRawResults();
		BasicDBList basicDBList = (BasicDBList) dbObject.get("retval");
		return basicDBList;
	}
	
	/**
	 * @Definition: 第二种写法
	 * @author: chenyongqiang
	 * @Date: 2017年7月25日
	 * @return
	 */
	public List<BasicDBObject> groupByIndustryField2() {
//		Aggregation aggregation = Aggregation.newAggregation(
//				Aggregation.group("industryField").count().as("count"), // group
//				Aggregation.sort(new Sort(new Sort.Order(Sort.Direction.DESC,"count"))) // sort
//				);
		
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("industryField").regex("数据服务")), // query
				Aggregation.group("industryField").count().as("count"), // group
				Aggregation.sort(new Sort(new Order(Direction.DESC,"count"))) // sort
				);
		AggregationResults<BasicDBObject> results = this.getMongoTemplate()
				.aggregate(aggregation, this.getDomainClass(), BasicDBObject.class);
		List<BasicDBObject> basicDBObjects = results.getMappedResults();
		return basicDBObjects;
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @return
	 */
	public List<BasicDBObject> groupByCompanySize() {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("industryField").regex("数据服务")), // query
				Aggregation.group("companySize").count().as("count"), // group
				Aggregation.sort(new Sort(new Order(Direction.DESC,"count"))) // sort
				);
		AggregationResults<BasicDBObject> results = this.getMongoTemplate()
				.aggregate(aggregation, this.getDomainClass(), BasicDBObject.class);
		List<BasicDBObject> basicDBObjects = results.getMappedResults();
		return basicDBObjects;
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @return
	 */
	public List<BasicDBObject> groupBycity() {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("industryField").regex("数据服务")), // query
				Aggregation.group("city").count().as("count"), // group
				Aggregation.sort(new Sort(new Order(Direction.DESC,"count"))) // sort
				);
		AggregationResults<BasicDBObject> results = this.getMongoTemplate()
				.aggregate(aggregation, this.getDomainClass(), BasicDBObject.class);
		List<BasicDBObject> basicDBObjects = results.getMappedResults();
		return basicDBObjects;
	}

}
