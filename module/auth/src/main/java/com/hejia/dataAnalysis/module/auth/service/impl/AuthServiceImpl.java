package com.hejia.dataAnalysis.module.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.auth.domain.Permission;
import com.hejia.dataAnalysis.module.auth.domain.Role;
import com.hejia.dataAnalysis.module.auth.service.AuthService;
import com.hejia.dataAnalysis.module.auth.service.GroupService;
import com.hejia.dataAnalysis.module.auth.service.LoginService;
import com.hejia.dataAnalysis.module.auth.service.PermissionService;
import com.hejia.dataAnalysis.module.auth.service.RoleService;
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
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	public ResponsePojo getAllUrlPerms(RequestArg ra) throws ServiceException {
		ResponsePojo rp = new ResponsePojo();
		//获取访问凭证
		String accessToken = ra.getString("accessToken");
		String ip = ra.getString("ip");
		try {
			//先判断访问凭证是否有效
			ResponsePojo rp2 = loginService.checkAccessToken(accessToken, ip);
			if (rp2.isSuccess()) {
				Account a = (Account) rp2.getMessage();
				//获取用户相关的权限
				List<Permission> pList = permissionService.findByAccId(a.getAccId());
				for (int i = pList.size() - 1; i > -1; i--) {
					if (pList.get(i).getUrl() == null) {
						pList.remove(i);
					}
				}
				rp.setSuccess(true);
				rp.setMessage(pList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取所有URL的权限出错，原因是："  + e.getMessage(), e);
		}
		return rp;
	}

	public ResponsePojo getCompSearchToken(RequestArg ra) throws ServiceException {
		ResponsePojo rp = new ResponsePojo();
		//获取访问凭证
		String accessToken = ra.getString("accessToken");
		String ip = ra.getString("ip");
		try {
			//先判断访问凭证是否有效
			ResponsePojo rp2 = loginService.checkAccessToken(accessToken, ip);
			if (rp2.isSuccess()) {
				Account a = (Account) rp2.getMessage();
				//获取用户相关的权限（这里只需要获取搜索服务权限即可）
				List<Role> rList = roleService.findByAccId(a.getAccId());
				for (int i = 0; i < rList.size(); i++) {
					Role r = rList.get(i);
//					if (Role.VALUE_COMP_OPEN_SEARCH.equals(r.getValue())) {
//						// 服务的限制：时间、数量、数据不全、功能不全
//						String t = (a.getEmail() != null ? a.getEmail() : a.getMobile()) + "|" + ip;
//						t = SDESUtils.encrypt(Constant.COOKIE_DES_KEY, t);
//						EhcacheUtils.put(EhcacheUtils.CACHE_NAME_TOKEN, t, t);
//						rp.setSuccess(true);
//						rp.setMessage(t);
//						break;
//					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("获取公司搜索凭证出错，原因是："  + e.getMessage(), e);
		}
		return rp;
	}

	public ResponsePojo verifyCompSearchToken(RequestArg ra) throws ServiceException {
		ResponsePojo rp = new ResponsePojo();
		//获取访问凭证
		String st = ra.getString("token");
		try {
//			if (st != null) {
//				String val = (String) EhcacheUtils.get(EhcacheUtils.CACHE_NAME_TOKEN, st);
//				if (val != null) {//主要查看缓存的失效时间
//					rp.setSuccess(true);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("验证公司搜索凭证出错，原因是："  + e.getMessage(), e);
		}
		return rp;
	}

	public ResponsePojo refreshToken(RequestArg ra) throws ServiceException {
		ResponsePojo rp = new ResponsePojo();
		//获取访问凭证
		String st = ra.getString("token");
		try {
//			if (st != null) {
//				String val = (String) EhcacheUtils.get(EhcacheUtils.CACHE_NAME_TOKEN, st);
//				if (val != null) {//主要查看缓存的失效时间
//					rp.setSuccess(true);
//					rp.setMessage(st);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("验证搜索凭证出错，原因是："  + e.getMessage(), e);
		}
		return rp;
	}

}
