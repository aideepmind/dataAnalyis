package com.hejia.dataAnalysis.module.recruitment.service.lagou.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.recruitment.dao.lagou.CompanyCoreInfoDao;
import com.hejia.dataAnalysis.module.recruitment.dao.lagou.PositionDao;
import com.hejia.dataAnalysis.module.recruitment.dao.lagou.impl.PositionDaoImpl;
import com.hejia.dataAnalysis.module.recruitment.domain.lagou.CompanyCoreInfo;
import com.hejia.dataAnalysis.module.recruitment.domain.lagou.Position;
import com.hejia.dataAnalysis.module.recruitment.service.lagou.PositionService;
import com.mongodb.BasicDBObject;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月22日
 * @version: 1.0
 */
@Service("positionSerivce")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	private PositionDao dao;
	@Autowired
	private PositionDaoImpl daoImpl;
	@Autowired
	private CompanyCoreInfoDao companyCoreInfoDao;
	
//	@Autowired
//	private CompanyJoinDaoImpl companyJoinDaoImpl;
	
	public List<Position> findByPositionName(String positionName) throws ServiceException {
		long startTime = System.currentTimeMillis();
		int pages = 0;
		while (pages < 10) {
			PageRequest pr = new PageRequest(pages, 100);
			List<CompanyCoreInfo> companyCoreInfos = companyCoreInfoDao.findAll(pr).getContent();
			if (!companyCoreInfos.isEmpty()) {
				List<String> idList = new ArrayList<String>();
				for (int i = 0; i < companyCoreInfos.size(); i++) {
					idList.add(companyCoreInfos.get(i).get_id());
				}
				List<BasicDBObject> basicDBObjects = daoImpl.findByAllCollection(idList);
				if (pages == 0) {
					System.out.println(basicDBObjects);
				}
			}
			pages++;
		}
		System.out.println("总共" + ((System.currentTimeMillis() - startTime) / 100) + "秒");
		return null;
	}
	
	public List<Position> find(Position position) throws ServiceException {
		PageRequest pr = new PageRequest(0, 10);
		return daoImpl.findPage(position, pr).getContent();
	}
}
