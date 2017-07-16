package com.hejia.dataAnalysis.module.auth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.auth.service.AuthService;
import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;

/**
 * @Description: 认证接口实现
 * @author: chenyongqiang
 * @Date: 2016年4月28日
 * @version: 1.0
 */
@Service("authService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class AuthServiceImpl implements AuthService {

	public ResponsePojo getAllUrlPerms(RequestArg ra) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo getCompSearchToken(RequestArg ra)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo verifyCompSearchToken(RequestArg ra)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo getCompOpenServiceToken(RequestArg ra)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo verifyCompOpenServiceToken(RequestArg ra)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo getCompInfoCompleteToken(RequestArg ra)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo verifyCompInfoCompleteToken(RequestArg ra)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo getStuInfoCompleteToken(RequestArg ra)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo verifyStuInfoCompleteToken(RequestArg ra)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponsePojo refreshToken(RequestArg ra) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
