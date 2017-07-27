package com.hejia.dataAnalysis.module.recruitment.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.recruitment.service.CompanyBaseInfoService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
@Service("companyBaseInfoService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class CompanyBaseInfoServiceImpl implements CompanyBaseInfoService {

}
