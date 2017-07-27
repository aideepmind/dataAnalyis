package com.hejia.dataAnalysis.module.recruitment.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hejia.dataAnalysis.module.recruitment.domain.CompanyBaseInfo;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
public interface CompanyBaseInfoDao extends MongoRepository<CompanyBaseInfo, ObjectId> {

}
