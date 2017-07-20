package com.hejia.dataAnalysis.module.auth.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.account.dao.AccountDao;
import com.hejia.dataAnalysis.module.auth.dao.GroupDao;
import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.auth.domain.AccountGroup;
import com.hejia.dataAnalysis.module.auth.domain.Group;
import com.hejia.dataAnalysis.module.auth.service.AccountGroupService;
import com.hejia.dataAnalysis.module.auth.service.GroupService;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 用户组接口实现
 * @author: chenyongqiang
 * @Date: 2016年4月28日
 * @version: 1.0
 */
@Service("groupService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupDao dao;
	@Autowired
	private AccountDao accountDao;
	
	@Transactional(readOnly = false)
	public BaseDomain add(BaseDomain domain) throws ServiceException {
		return dao.save((Group) domain);
	}

	@Transactional(readOnly = false)
	public BaseDomain modify(BaseDomain domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly = false)
	public BaseDomain delete(BaseDomain domain) throws ServiceException {
		dao.delete((Group) domain);
		return domain;
	}

	public Page<BaseDomain> find(BaseDomain domain, PageRequest pageRequest) throws ServiceException {

		return null;
	}

	public List<Group> findByName(String name) throws ServiceException {
		return dao.findByName(name);
	}

	public Iterable<Group> findAll() throws ServiceException {
		return dao.findAll();
	}

	public Group findById(Integer id) throws ServiceException {
		return dao.findOne(id);
	}

	public List<Group> findByIds(Collection<Integer> ids) throws ServiceException {
		return dao.findByGroupIdIn(ids);
	}
}
