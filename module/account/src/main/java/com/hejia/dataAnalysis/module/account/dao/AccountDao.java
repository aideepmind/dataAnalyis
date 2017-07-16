package com.hejia.dataAnalysis.module.account.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.account.domain.Account;

/**
 * @Description: 账户dao接口，可以使用@query注解和方法名的方式来查找，如果两者无法满足需求，则需要自定义sql查找。
 * @author: chenyongqiang
 * @Date: 2015年4月8日
 * @version: 1.0
 */
public interface AccountDao extends PagingAndSortingRepository<Account, Integer> {
	
}
