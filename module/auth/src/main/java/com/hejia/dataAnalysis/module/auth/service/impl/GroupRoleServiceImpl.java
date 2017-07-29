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

import com.hejia.dataAnalysis.module.auth.dao.GroupRoleDao;
import com.hejia.dataAnalysis.module.auth.domain.GroupRole;
import com.hejia.dataAnalysis.module.auth.service.GroupRoleService;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
@Service("groupRoleService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class GroupRoleServiceImpl implements GroupRoleService<GroupRole> {
	
	@Autowired
	private GroupRoleDao dao;
	
	@Transactional(readOnly = false)
	public GroupRole add(GroupRole domain) throws ServiceException {
		return dao.save((GroupRole) domain);
	}

	@Transactional(readOnly = false)
	public GroupRole modify(GroupRole domain) throws ServiceException {
		return dao.save((GroupRole) domain);
	}

	@Transactional(readOnly = false)
	public GroupRole delete(GroupRole domain) throws ServiceException {
		GroupRole gr = (GroupRole) domain;
		dao.delete(gr);
		return null;
	}

	public Page find(GroupRole domain, Pageable p) throws ServiceException {
		return null;
	}
	
	public List<GroupRole> findByGroupId(Integer gId) throws ServiceException {
		return dao.findByGroupId(gId);
	}
	
	public List<GroupRole> findByRoleId(Integer rId) throws ServiceException {
		return dao.findByRoleId(rId);
	}
	
	public List<GroupRole> findByGroupIds(Collection<Integer> ids) throws ServiceException {
		return dao.findByGroupIdIn(ids);
	}
	
	public List<GroupRole> findByRoleIds(Collection<Integer> ids) throws ServiceException {
		return dao.findByRoleIdIn(ids);
	}
}
