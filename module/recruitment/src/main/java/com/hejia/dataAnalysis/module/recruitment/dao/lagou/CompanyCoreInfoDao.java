package com.hejia.dataAnalysis.module.recruitment.dao.lagou;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hejia.dataAnalysis.module.recruitment.domain.lagou.CompanyCoreInfo;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
public interface CompanyCoreInfoDao extends MongoRepository<CompanyCoreInfo, ObjectId> {
	
//	public Page<CompanyCoreInfo> find(Pageable pr);
	
}
