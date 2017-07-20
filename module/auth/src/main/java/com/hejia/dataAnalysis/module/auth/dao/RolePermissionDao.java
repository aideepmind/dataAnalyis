package com.hejia.dataAnalysis.module.auth.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.hejia.dataAnalysis.module.auth.domain.RolePermission;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年4月29日
 * @version: 1.0
 */
public interface RolePermissionDao extends PagingAndSortingRepository<RolePermission, Integer> {
	
}
