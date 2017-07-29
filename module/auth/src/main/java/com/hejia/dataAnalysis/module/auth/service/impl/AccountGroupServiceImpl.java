package com.hejia.dataAnalysis.module.auth.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.auth.dao.AccountGroupDao;
import com.hejia.dataAnalysis.module.auth.domain.AccountGroup;
import com.hejia.dataAnalysis.module.auth.service.AccountGroupService;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
@Service("accountGroupService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class AccountGroupServiceImpl implements AccountGroupService<AccountGroup> {
	
	@Autowired
	private AccountGroupDao dao;
	
	@Transactional(readOnly = false)
	public AccountGroup add(AccountGroup domain) throws ServiceException {
		return dao.save((AccountGroup) domain);
	}

	@Transactional(readOnly = false)
	public AccountGroup modify(AccountGroup domain) throws ServiceException {
		return dao.save((AccountGroup) domain);
	}

	@Transactional(readOnly = false)
	public AccountGroup delete(AccountGroup domain) throws ServiceException {
		dao.delete((AccountGroup) domain);
		return domain;
	}

	public Page<AccountGroup> find(AccountGroup domain, Pageable p) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<AccountGroup> findByAccId(Integer accId) throws ServiceException {
		return dao.findByAccId(accId);
	}

	@Transactional(readOnly = false)
	public void deleteByAccId(Integer accId) throws ServiceException {
		List<AccountGroup> agList = dao.findByAccId(accId);
		dao.delete(agList);
	}
	
	public List<AccountGroup> findByGroupId(Integer groupId) throws ServiceException {
		return dao.findByGroupId(groupId);
	}
	
	public List<AccountGroup> findByAccIdAndGroupId(Integer accId, Integer groupId) throws ServiceException {
		return dao.findByAccIdAndGroupId(accId, groupId);
	}
	
	public List<AccountGroup> findByAccIdsAndGroupId(Collection<Integer> accIds, Integer groupId) throws ServiceException {
		return dao.findByAccIdInAndGroupId(accIds, groupId);
	}
}
