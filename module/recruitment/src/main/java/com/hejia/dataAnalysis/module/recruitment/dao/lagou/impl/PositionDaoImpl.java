package com.hejia.dataAnalysis.module.recruitment.dao.lagou.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.hejia.dataAnalysis.module.common.dao.AbstractMongoBaseDao;
import com.hejia.dataAnalysis.module.recruitment.domain.lagou.CompanyCoreInfo;
import com.hejia.dataAnalysis.module.recruitment.domain.lagou.Position;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月24日
 * @version: 1.0
 */
public class PositionDaoImpl extends AbstractMongoBaseDao<Position> {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	protected MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	
	public List<BasicDBObject> findByAllCollection(List<String> idList) {
		long startTime = System.currentTimeMillis();
		final DBObject baseInfoDBObject = new BasicDBObject("$lookup",
				new BasicDBObject("from", "company_baseInfo").append("localField", "companyId")
						.append("foreignField", "companyId").append("as", "baseInfo"));
		final DBObject addressDBObject = new BasicDBObject("$lookup",
				new BasicDBObject("from", "company_address").append("localField", "companyId")
						.append("foreignField", "companyId").append("as", "address"));
		final DBObject introductionDBObject = new BasicDBObject("$lookup",
				new BasicDBObject("from", "company_introduction").append("localField", "companyId")
						.append("foreignField", "companyId").append("as", "introduction"));
		final DBObject leaderDBObject = new BasicDBObject("$lookup",
				new BasicDBObject("from", "company_leader").append("localField", "companyId")
						.append("foreignField", "companyId").append("as", "leader"));
		final DBObject productDBObject = new BasicDBObject("$lookup",
				new BasicDBObject("from", "company_product").append("localField", "companyId")
						.append("foreignField", "companyId").append("as", "product"));
		Aggregation aggregation = Aggregation.newAggregation(
				new AggregationOperation() {
					public DBObject toDBObject(
							AggregationOperationContext context) {
						return context.getMappedObject(baseInfoDBObject);
					}
				},new AggregationOperation() {
					public DBObject toDBObject(
							AggregationOperationContext context) {
						return context.getMappedObject(addressDBObject);
					}
				},new AggregationOperation() {
					public DBObject toDBObject(
							AggregationOperationContext context) {
						return context.getMappedObject(introductionDBObject);
					}
				},new AggregationOperation() {
					public DBObject toDBObject(
							AggregationOperationContext context) {
						return context.getMappedObject(leaderDBObject);
					}
				},new AggregationOperation() {
					public DBObject toDBObject(
							AggregationOperationContext context) {
						return context.getMappedObject(productDBObject);
					}
				}, Aggregation.match(Criteria.where("_id").in(idList)));

		AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(
				aggregation, CompanyCoreInfo.class, BasicDBObject.class);
		List<BasicDBObject> basicDBObjects = results.getMappedResults();
		System.out.println(((System.currentTimeMillis() - startTime)) + "毫秒");
		return basicDBObjects;
	}
}
