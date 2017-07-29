package com.hejia.dataAnalysis.module.auth.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.auth.dao.PermissionDao;
import com.hejia.dataAnalysis.module.auth.dao.impl.PermissionDaoImpl;
import com.hejia.dataAnalysis.module.auth.domain.Permission;
import com.hejia.dataAnalysis.module.auth.domain.Role;
import com.hejia.dataAnalysis.module.auth.service.PermissionService;
import com.hejia.dataAnalysis.module.auth.service.RolePermissionService;
import com.hejia.dataAnalysis.module.auth.service.RoleService;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月20日
 * @version: 1.0
 */
@Service("permissionService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class PermissionServiceImpl implements PermissionService<Permission> {
	
	@Autowired
	private PermissionDao dao;
	@Autowired
	private PermissionDaoImpl daoImpl;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePermissionService rolePermissionService;
	
	public Permission add(Permission domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Permission modify(Permission domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Permission delete(Permission domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Permission> find(Permission domain, Pageable p) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Permission> findByIds(Collection<Integer> ids) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Permission> findAll() throws ServiceException {
		return dao.findAll();
	}

	public List<Permission> findByAccId(Integer accId) throws ServiceException {
		List<Role> rList = roleService.findByAccId(accId);
		if (!rList.isEmpty()) {
			Set<Integer> ids = new HashSet<Integer>();
			for (int i = 0; i < rList.size(); i++) {
				ids.add(rList.get(i).getRoleId());
			}
			return dao.findByPermsIdIn(ids);
		}
		return Collections.EMPTY_LIST;
	}
	
}
