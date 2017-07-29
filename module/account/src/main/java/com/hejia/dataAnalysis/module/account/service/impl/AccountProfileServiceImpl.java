package com.hejia.dataAnalysis.module.account.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.account.domain.AccountProfile;
import com.hejia.dataAnalysis.module.account.service.AccountProfileService;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
@Service("accountProfileService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class AccountProfileServiceImpl implements AccountProfileService<AccountProfile> {

	public AccountProfile add(AccountProfile domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public AccountProfile modify(AccountProfile domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public AccountProfile delete(AccountProfile domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<AccountProfile> find(AccountProfile domain,
			PageRequest pageRequest) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
