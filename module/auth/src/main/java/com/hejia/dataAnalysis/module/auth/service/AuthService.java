package com.hejia.dataAnalysis.module.auth.service;

import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.BaseService;

/**
 * @Description: 认证接口
 * @author: chenyongqiang
 * @Date: 2016年4月28日
 * @version: 1.0
 */
public interface AuthService extends BaseService {

	/**
	 * @Definition: 获取所有URL的权限
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param ra
	 * @return
	 * @throws ServiceException
	 */
	public ResponsePojo getAllUrlPerms(RequestArg ra) throws ServiceException;
	
	/**
	 * @Definition: 获取公司搜索凭证
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param ra
	 * @return
	 * @throws ServiceException
	 */
	public ResponsePojo getCompSearchToken(RequestArg ra) throws ServiceException;
	
	/**
	 * @Definition: 刷新凭证
	 * @author: chenyongqiang
	 * @Date: 2016年4月29日
	 * @param ra
	 * @return
	 * @throws ServiceException
	 */
	public ResponsePojo refreshToken(RequestArg ra) throws ServiceException;
	
}
