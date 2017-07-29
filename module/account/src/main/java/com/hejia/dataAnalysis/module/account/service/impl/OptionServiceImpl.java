package com.hejia.dataAnalysis.module.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.account.dao.OptionDao;
import com.hejia.dataAnalysis.module.account.domain.Option;
import com.hejia.dataAnalysis.module.account.service.OptionService;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
@Service("optionService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class OptionServiceImpl implements OptionService<Option> {
	
	@Autowired
	private OptionDao dao;
	
	public Option add(Option domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Option modify(Option domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Option delete(Option domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Option> find(Option domain, PageRequest pageRequest)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Option> findByTypeAndName(int type, String name) throws ServiceException {
		return dao.findByTypeAndName(type, name);
	}
}
