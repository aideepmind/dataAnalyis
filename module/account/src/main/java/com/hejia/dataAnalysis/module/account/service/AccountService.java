package com.hejia.dataAnalysis.module.account.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.account.domain.AccountProfile;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 账户业务层接口
 * @author: chenyongqiang
 * @Date: 2015年4月6日
 * @version: 1.0
 */
public interface AccountService extends DomainBaseService {
	
	/**
	 * @Definition: 根据手机号或者email查找用户
	 * @author: chenyongqiang
	 * @Date: 2015年4月9日
	 * @param username
	 * @return
	 */
	public Account findByMobileOrEmail(String username) throws ServiceException;
}
