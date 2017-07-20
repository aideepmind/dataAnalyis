package com.hejia.dataAnalysis.module.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.account.dao.AccountDao;
import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.account.service.AccountService;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 账户业务层接口实现
 * @author: chenyongqiang
 * @Date: 2017年7月18日
 * @version: 1.0
 */
@Service("accountService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao dao;
	
	@Transactional(readOnly = false)
	public BaseDomain add(BaseDomain domain) throws ServiceException {
		return dao.save((Account) domain);
	}
	
	@Transactional(readOnly = false)
	public BaseDomain modify(BaseDomain domain) throws ServiceException {
		return dao.save((Account) domain);
	}
	
	/**
	 * 可以只做逻辑删除
	 */
	@Transactional(readOnly = false)
	public BaseDomain delete(BaseDomain domain) throws ServiceException {
		// 逻辑删除
		// a.setStatus(Constant.ACCOUNT_STATUS_DELETED);
		// 删除账号之前应该清理账号相关的数据
		
		Account a = (Account) domain;
		a = dao.findOne(a.getAccId());
		dao.delete(a);
		return a;
	}

	public Page<BaseDomain> find(BaseDomain domain, PageRequest pageRequest)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 不能有重，在注册时应该防止信息重复
	 */
	public Account findByMobileOrEmail(String username) throws ServiceException {
		List<Account> aList = dao.findByMobileOrEmail(username, username);
		return aList.size() > 0 ? aList.get(0) : null;
	}

}
