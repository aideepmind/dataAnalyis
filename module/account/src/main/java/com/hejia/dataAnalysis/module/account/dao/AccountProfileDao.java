package com.hejia.dataAnalysis.module.account.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.account.domain.Account;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月16日
 * @version: 1.0
 */
public interface AccountProfileDao extends PagingAndSortingRepository<Account, Integer> {

}
