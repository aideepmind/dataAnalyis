package com.hejia.dataAnalysis.web.auth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.common.Constant;
import com.hejia.dataAnalysis.module.common.CurrentAccount;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.domain.JsonpCallback;
import com.hejia.dataAnalysis.module.common.domain.RequestArg;


public class BaseController {
	
	private static Logger log = Logger.getLogger(BaseController.class);
	
	protected void initJsonObj(JSONObject o ,int status, String reason) {
		o.put("status", status);
		o.put("reason", reason);
	}

	/**
	 * @Definition: 获取分页信息，前端是datatables插件的属性，后台是spring data中定义的分页属性，将两者转换
	 * @author: chenyongqiang
	 * @Date: 2015年4月21日
	 * @param request
	 * @return
	 */
	protected PageRequest getPageRequest(HttpServletRequest request) {
		String pn = request.getParameter("pn"), pageSize = request.getParameter("pageSize");
		int page = 0, size = 10;//默认
		if (pn != null && !"".equals(pn)) {
			page = Integer.parseInt(pn) - 1;//spring page从0开始，页面从1开始
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		return new PageRequest(page, size == -1 ? 100000 : size);
	}

	/**
	 * @Definition: 获取参数
	 * @author: chenyongqiang
	 * @Date: 2015年7月20日
	 * @param request
	 * @return
	 */
	public RequestArg getArg(HttpServletRequest request) {
		RequestArg a = new RequestArg();
		a.setArg(request);
		return a;
	}
	
	/**
	 * @Definition: 把后台的分页信息转换成前台的分页信息
	 * @author: chenyongqiang
	 * @Date: 2015年4月21日
	 * @param p
	 * @return
	 */
	protected Map<String, Object> pageToMap(HttpServletRequest request, Page p) {
		if (p == null) {
			return null;
		}
		Map<String, Object> m = new HashMap<String, Object>(10);
		m.put("pageNo", p.getNumber() + 1);
		m.put("totalCount", p.getTotalElements());
		m.put("totalPage", p.getTotalPages());
		m.put("pageSize", p.getSize());
		m.put("data", p.getContent());
		return m;
	}
	
	protected Map<String, Object> domainToMap(BaseDomain d) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Object> m = null;
		if (d != null) {
			m = new HashMap<String, Object>();
			Class cls = d.getClass();
//			Field fs[] = cls.getDeclaredFields();
//			for (Field f : fs) {
//				m.put(f.getName(), f.get(d));
//			}
			Method ms[] = cls.getMethods();
			for (Method mt : ms) {
				Class mcls[] = mt.getParameterTypes();
				if (mcls != null && mcls.length > 0) continue;
				String name = mt.getName();
				if (name.startsWith("get")) {
					Object value = mt.invoke(d);
					String field = name.substring(3, 4).toLowerCase() + name.substring(4);
					if("class".equals(field)) continue;
					m.put(field, value);
				}
			}
		}
		return m;
	}

	/**
	 * @Definition: 增加cookie
	 * @author: chenyongqiang
	 * @Date: 2015年5月22日
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 * @param time
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		addCookie(response, name, value, Constant.COOKIE_PATH, Constant.COOKIE_TIME_YEAR);
	}
	
	/**
	 * @Definition: 增加cookie
	 * @author: chenyongqiang
	 * @Date: 2015年5月22日
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 * @param time
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, String path, int time) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(time);//30天
		cookie.setPath(path);
		if (Constant.runEnv == 1 || Constant.runEnv == 2) {//非debug模式
			cookie.setDomain(Constant.COOKIE_DOMAIN);
		}
		response.addCookie(cookie);
	}

	/**
	 * @Definition: 增加cookie
	 * @author: chenyongqiang
	 * @Date: 2015年7月29日
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 * @param time
	 * @param domain
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, String path, int time, String domain){
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(time);//30天
		cookie.setPath(path);
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}
	
	/**
	 * @Definition: 删除cookie
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param response
	 * @param name
	 * @return
	 */
	public static boolean delCookie(HttpServletResponse response, String... name) {
		try {
			for (int i = 0; i < name.length; i++) {
				Cookie cookie = new Cookie(name[i], null);
				cookie.setMaxAge(0);//关闭浏览器之后删除cookie
				cookie.setPath(Constant.COOKIE_PATH);
				if (Constant.runEnv == 1 || Constant.runEnv == 2) {//非debug模式
					cookie.setDomain(Constant.COOKIE_DOMAIN);
				}
				response.addCookie(cookie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * @Definition: 获取cookie
	 * @author: chenyongqiang
	 * @Date: 2015年5月22日
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String name){
		Cookie cookies[] = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(name)) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * @Definition: 设置访问凭证
	 * @author: chenyongqiang
	 * @Date: 2017年7月21日
	 * @param response
	 * @param platform
	 * @param token
	 */
	public void setAccessToken(HttpServletResponse response, int platform, String token) {
		try {
			//在cookie中加入凭证
			String cookieName = Constant.COOKIE_ACCESS_TOKEN;
			addCookie(response, cookieName, token);
			//删除登出标记
//			if (platform == Constant.PLATFORM_MOBILE_STUDENT) {
//				delCookie(response, Constant.COOKIE_LOGOUT_SIGN_WX_STU);
//			} else if (platform == Constant.PLATFORM_MOBILE_COMPANY) {
//				delCookie(response, Constant.COOKIE_LOGOUT_SIGN_WX_COMP);
//			} else {
//				delCookie(response, Constant.COOKIE_LOGOUT_SIGN_WX_STU);
//				delCookie(response, Constant.COOKIE_LOGOUT_SIGN_WX_COMP);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("设置访问凭证出错，原因：", e);
		}
	}
	
	/**
	 * @Definition: 获取当前用户
	 * @author: chenyongqiang
	 * @Date: 2016年2月18日
	 * @param request
	 * @return
	 */
	public Account getCurAccount(HttpServletRequest request) {
		Account a = null;
		CurrentAccount ca = CurrentAccount.getCurrentAccount(request);
		if (ca != null) {
			a = (Account) ca.getAccount();
		}
		return a;
	}
	
	/**
	 * @Definition: 获取客户端类型
	 * @author: chenyongqiang
	 * @Date: 2016年2月23日
	 * @param request
	 * @return
	 */
	public static int getClientType(HttpServletRequest request) {
		String cli = request.getParameter("cli");
		String ua=request.getHeader("User-Agent").toLowerCase();
		if ("wx".equals(cli)) {//微信端
			return Constant.CLIENT_TYPE_MOBILE;
		} else if (ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
			return Constant.CLIENT_TYPE_MOBILE;
		} else {//默认是pc端
			return Constant.CLIENT_TYPE_PC;
		}
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年2月29日
	 * @param request
	 * @param name
	 * @param value
	 */
	public static void addSession(HttpServletRequest request, String name, String value) {
		HttpSession s = request.getSession(true);
		s.setAttribute(name, value);
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年2月29日
	 * @param request
	 * @param name
	 */
	public static Object getSession(HttpServletRequest request, String name) {
		HttpSession s = request.getSession(false);
		if (s != null) {
			return s.getAttribute(name);
		}
		return null;
	}

	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年2月29日
	 * @param request
	 * @param name
	 */
	public static void deleteSession(HttpServletRequest request, String name) {
		HttpSession s = request.getSession(false);
		if (s != null) {
			s.removeAttribute(name);
		}
	}

	/**
	 * @Definition: 跨域响应
	 * @author: chenyongqiang
	 * @Date: 2016年3月6日
	 * @param response
	 */
	public void responseByJsonp(HttpServletResponse response, JsonpCallback jc) {
		response.setContentType("text/html");
	    response.setCharacterEncoding("utf-8");
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(jc.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
