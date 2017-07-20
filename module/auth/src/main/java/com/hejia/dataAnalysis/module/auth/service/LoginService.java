package com.hejia.dataAnalysis.module.auth.service;

import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.BaseService;

/**
 * @Description: 登录接口
 * @author: chenyongqiang
 * @Date: 2017年7月17日
 * @version: 1.0
 */
public interface LoginService extends BaseService {
	
	/**
	 * @Definition: 登录操作
	 * @author: chenyongqiang
	 * @Date: 2017年7月17日
	 * @param ra
	 * @return
	 * @throws ServiceException
	 */
	public ResponsePojo login(RequestArg ra) throws ServiceException;
	
	/**
	 * @Definition: 生成访问凭证
	 * @author: chenyongqiang
	 * @Date: 2017年7月19日
	 * @param loginName
	 * @param ip
	 * @return
	 * @throws ServiceException
	 */
	public String generateAccessToken(String loginName, String ip) throws ServiceException;
	
	/**
	 * @Definition: 校验访问凭证
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param token
	 * @param ip
	 * @return
	 * @throws ServiceException
	 */
	public ResponsePojo checkAccessToken(String token, String ip) throws ServiceException;
	
}
