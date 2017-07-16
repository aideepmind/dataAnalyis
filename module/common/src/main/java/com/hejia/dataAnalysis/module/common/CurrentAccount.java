package com.hejia.dataAnalysis.module.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * @Description: 当前登录账号
 * @author: chenyongqiang
 * @Date: 2015年5月22日
 * @version: 1.0
 */
public class CurrentAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3097478503711260093L;
	
	private BaseDomain account;
	
	/**
	 * 主要存用户微信信息
	 */
	private Map<String, Object> wInfo;
	
	/**
	 * 平台类型
	 */
	private int platform;
	
	/**
	 * 主要存放权限信息
	 */
	private Map<String, Object> authInfo;
	
	public Object getAccount() {
		return account;
	}

	public void setAccount(BaseDomain account) {
		this.account = account;
	}

	public Map<String, Object> getwInfo() {
		return wInfo;
	}

	public void setwInfo(Map<String, Object> wInfo) {
		this.wInfo = wInfo;
	}
	
	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	/**
	 * @Definition: 从session获取当前用户
	 * @author: chenyongqiang
	 * @Date: 2015年5月22日
	 * @param request
	 * @return
	 */
	public static CurrentAccount getCurrentAccount(HttpServletRequest request) {
		if (request == null) return null;
		HttpSession session = request.getSession(false);
		if (session == null) return null;
		return (CurrentAccount) session.getAttribute(CurrentAccount.class.getName());
	}
	
	/**
	 * @Definition: 把当前用户放入session中
	 * @author: chenyongqiang
	 * @Date: 2015年5月22日
	 * @param account
	 * @param request
	 */
	public static void setCurrentAccount(BaseDomain a, HttpServletRequest request, int platform) {
		if (request == null) return;
		HttpSession session = request.getSession(false);
		if (session == null) return;
		CurrentAccount ca = (CurrentAccount) session.getAttribute(CurrentAccount.class.getName());
		if (ca == null) {
			ca = new CurrentAccount();
		}
		//copy，避免代码中使用a查找对象之后又再更新出现Batch update returned unexpected row count from update [0]; actual row count: 2; expected: 1 - chenyq
		/*try {
//			BaseDomain a2 = (BaseDomain) Class.forName(a.getClass().getName()).newInstance();
			BaseDomain a2 = a.getClass().newInstance();
			BeanCopyUtils.copyProperties(a, a2);
			a = a2;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		ca.setAccount(a);
		ca.setPlatform(platform);
		session.setAttribute(CurrentAccount.class.getName(), ca);
	}
	
	/**
	 * @Definition: 更新微信信息
	 * @author: chenyongqiang
	 * @Date: 2015年6月9日
	 * @param json
	 */
	public static void refreshW(HttpServletRequest request, JSONObject... jsons) {
		if (request == null) return;
		HttpSession session = request.getSession(true);
		CurrentAccount ca = (CurrentAccount) session.getAttribute(CurrentAccount.class.getName());
		if (ca == null) {
			ca = new CurrentAccount();
		}
		Map<String, Object> wInfo = ca.getwInfo();
		if (wInfo == null) {
			wInfo = new HashMap<String, Object>();
		}
		for (int i = 0; i < jsons.length; i++) {
			JSONObject json = jsons[i];
			Iterator<String> it = json.keys();
			while (it.hasNext()) {
				String key = it.next();
				wInfo.put(key, json.get(key));
			}
		}
		ca.setwInfo(wInfo);
		session.setAttribute(CurrentAccount.class.getName(), ca);
	}

	/**
	 * @Definition: 设置权限集合
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param authInfo
	 */
	public void setAuthInfo(Map<String, Object> authInfo) {
		this.authInfo = authInfo;
	}
	
	/**
	 * @Definition: 增加权限
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param role
	 * @param value
	 */
	public void addAuth(String role, Object value) {
		if (authInfo == null) {
			authInfo = new HashMap<String, Object>();
		}
		authInfo.put(role, value);
	}
	
	/**
	 * @Definition: 增加权限
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param request
	 * @param role
	 * @param value
	 */
	public static void addAuth(HttpServletRequest request, String role, Object value) {
		CurrentAccount ca = getCurrentAccount(request);
		if (ca == null) {
			return;
		}
		ca.addAuth(role, value);
	}
	
	/**
	 * @Definition: 获取权限
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param role
	 * @return
	 */
	public Object getAuth(String role) {
		if (authInfo == null) {
			return null;
		}
		return authInfo.get(role);
	}
	
	/**
	 * @Definition: 获取全部权限集合
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @return
	 */
	public Map<String, Object> getAllAuth() {
		if (authInfo == null) {
			authInfo = new HashMap<String, Object>();
		}
		return authInfo;
	}
	
	/**
	 * @Definition: 获取权限
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param role
	 * @return
	 */
	public static Object getAuth(HttpServletRequest request, String role) {
		CurrentAccount ca = getCurrentAccount(request);
		if (ca == null) {
			return null;
		}
		return ca.getAuth(role);
	}
	
	/**
	 * @Definition: 获取全部权限集合
	 * @author: chenyongqiang
	 * @Date: 2016年4月28日
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getAllAuth(HttpServletRequest request) {
		CurrentAccount ca = getCurrentAccount(request);
		if (ca == null) {
			return null;
		}
		return ca.getAllAuth();
	}
}
