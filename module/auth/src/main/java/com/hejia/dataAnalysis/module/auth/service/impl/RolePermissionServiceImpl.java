package com.hejia.dataAnalysis.module.auth.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.auth.service.RolePermissionService;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
@Service("rolePermissionService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class RolePermissionServiceImpl implements RolePermissionService {

	public BaseDomain add(BaseDomain domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseDomain modify(BaseDomain domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public BaseDomain delete(BaseDomain domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<BaseDomain> find(BaseDomain domain, PageRequest pageRequest)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
