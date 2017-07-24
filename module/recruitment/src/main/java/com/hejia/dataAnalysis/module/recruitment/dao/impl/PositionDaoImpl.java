package com.hejia.dataAnalysis.module.recruitment.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.hejia.dataAnalysis.module.common.dao.AbstractMongoBaseDao;
import com.hejia.dataAnalysis.module.recruitment.domain.Position;

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
	
}
