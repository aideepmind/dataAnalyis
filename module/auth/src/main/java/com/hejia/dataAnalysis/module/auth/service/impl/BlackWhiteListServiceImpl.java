package com.hejia.dataAnalysis.module.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.auth.dao.BlackWhiteListDao;
import com.hejia.dataAnalysis.module.auth.domain.BlackWhiteList;
import com.hejia.dataAnalysis.module.auth.service.BlackWhiteListService;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: BlackWhiteListService业务层实现
 * @author: chenyongqiang
 * @Date: 2015年12月28日
 * @version: 1.0
 */
@Service("blackWhiteListService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class BlackWhiteListServiceImpl implements BlackWhiteListService {
	
	@Autowired
	private BlackWhiteListDao dao;
	
	@Transactional(readOnly = false)
	public BaseDomain add(BaseDomain domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = false)
	public BaseDomain modify(BaseDomain domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = false)
	public BaseDomain delete(BaseDomain domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Page find(BaseDomain domain, PageRequest pageRequest)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BlackWhiteList> findByTypeAndFunctionType(Integer type, Integer funtionType) throws ServiceException {
		return dao.findByTypeAndFunctionType(type, funtionType);
	}
	
	public List<BlackWhiteList> findByTypeAndFunctionTypeAndValue(Integer type, Integer funtionType, String value) throws ServiceException {
		return dao.findByTypeAndFunctionTypeAndValue(type, funtionType, value);
	}


}
