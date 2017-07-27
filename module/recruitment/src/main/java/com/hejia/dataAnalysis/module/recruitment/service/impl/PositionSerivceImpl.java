package com.hejia.dataAnalysis.module.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.recruitment.dao.PositionDao;
import com.hejia.dataAnalysis.module.recruitment.dao.impl.PositionDaoImpl;
import com.hejia.dataAnalysis.module.recruitment.domain.Position;
import com.hejia.dataAnalysis.module.recruitment.service.PositionSerivce;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月22日
 * @version: 1.0
 */
@Service("positionSerivce")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class PositionSerivceImpl implements PositionSerivce {
	
	@Autowired
	private PositionDao dao;
	@Autowired
	private PositionDaoImpl daoImpl;
	
	public List<Position> findByPositionName(String positionName) throws ServiceException {
		return dao.findByPositionNameLike(positionName);
	}
	
	public List<Position> find(Position position) throws ServiceException {
		PageRequest pr = new PageRequest(0, 10);
		return daoImpl.findPage(position, pr).getContent();
	}
}
