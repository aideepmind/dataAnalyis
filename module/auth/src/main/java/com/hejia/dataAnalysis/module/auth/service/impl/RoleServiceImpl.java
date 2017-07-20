package com.hejia.dataAnalysis.module.auth.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.auth.dao.RoleDao;
import com.hejia.dataAnalysis.module.auth.domain.AccountGroup;
import com.hejia.dataAnalysis.module.auth.domain.GroupRole;
import com.hejia.dataAnalysis.module.auth.domain.Role;
import com.hejia.dataAnalysis.module.auth.service.AccountGroupService;
import com.hejia.dataAnalysis.module.auth.service.GroupRoleService;
import com.hejia.dataAnalysis.module.auth.service.RoleService;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 角色业务层实现
 * @author: chenyongqiang
 * @Date: 2015年4月10日
 * @version: 1.0
 */
@Service("roleService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao dao;
	@Autowired
	private AccountGroupService accountGroupService;
	@Autowired
	private GroupRoleService groupRoleService;
	
	public BaseDomain add(BaseDomain domain) throws ServiceException {
		return dao.save((Role) domain);
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

	public List<Role> findByIds(Collection<Integer> ids) {
		return dao.findByRoleIdIn(ids);
	}

	public Iterable<Role> findAll() throws ServiceException {
		return dao.findAll();
	}

	public List<Role> findByAccId(Integer accId) throws ServiceException {
		List<AccountGroup> agList = null;
		try {
			agList = accountGroupService.findByAccId(accId);
			if (!agList.isEmpty()) {
				Set<Integer> ids = new HashSet<Integer>();
				for (int i = 0; i < agList.size(); i++) {
					ids.add(agList.get(i).getGroupId());
				}
				List<GroupRole> grList = groupRoleService.findByGroupIds(ids);
				if (!grList.isEmpty()) {
					ids = new HashSet<Integer>();
					for (int i = 0; i < grList.size(); i++) {
						ids.add(grList.get(i).getRoleId());
					}
					return this.findByIds(ids);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("根据用户id查找角色集合出错，原因是：" + e.getMessage(), e);
		}
		return Collections.EMPTY_LIST;
	}

}
