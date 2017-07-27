package com.hejia.dataAnalysis.module.report.service;

import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.BaseService;

/**
 * @Description: 公司基本报表业务接口
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
public interface CompanyBaseReportService extends BaseService {
	
	/**
	 * @Definition: 按行业划分有大数据需求的公司报表
	 * @author: chenyongqiang
	 * @Date: 2017年7月25日
	 * @return
	 * @throws ServiceException
	 */
	public ResponsePojo BigDataRequirementDivisionByIndustry() throws ServiceException;
	
	/**
	 * @Definition: 按公司规模划分有大数据需求的公司报表
	 * @author: chenyongqiang
	 * @Date: 2017年7月25日
	 * @return
	 * @throws ServiceException
	 */
	public ResponsePojo BigDataRequirementDivisionByCompanySize() throws ServiceException;
	
}
