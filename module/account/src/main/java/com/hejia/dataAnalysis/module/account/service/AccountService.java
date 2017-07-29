package com.hejia.dataAnalysis.module.account.service;

import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.DomainBaseService;

/**
 * @Description: 账户业务层接口
 * @author: chenyongqiang
 * @Date: 2015年4月6日
 * @version: 1.0
 */
public interface AccountService<T> extends DomainBaseService<T> {
	
	/**
	 * @Definition: 根据手机号或者email查找用户
	 * @author: chenyongqiang
	 * @Date: 2015年4月9日
	 * @param username
	 * @return
	 */
	public T findByMobileOrEmail(String username) throws ServiceException;
}
