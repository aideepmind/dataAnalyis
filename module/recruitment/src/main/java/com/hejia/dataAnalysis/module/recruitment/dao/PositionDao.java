package com.hejia.dataAnalysis.module.recruitment.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hejia.dataAnalysis.module.recruitment.domain.Position;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月22日
 * @version: 1.0
 */
public interface PositionDao extends MongoRepository<Position, ObjectId> {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param positionName
	 * @return
	 */
	public List<Position> findByPositionNameLike(String positionName);
	
}
