package com.hejia.dataAnalysis.module.common;

/**
 * @Description: 全局常量类
 * @author: chenyongqiang
 * @Date: 2015年4月6日
 * @version: 1.0
 */
public class Constant {

	/**
	 * cookie相关
	 */
	public final static String COOKIE_PATH = "/";// 作用域
	public final static int COOKIE_TIME_MONTH = 30 * 24 * 60 * 60;// 一个月
	public final static int COOKIE_TIME_YEAR = 365 * 24 * 60 * 60;// 一年
	public final static String COOKIE_ACCESS_TOKEN = "ACCESS_TOKEN";// 访问凭证
	public final static String COOKIE_LOGOUT_SIGN_WX_STU = "LOGOUT_SIGN_WX_STU";// 微信学生端登出标记
	public final static String COOKIE_LOGOUT_SIGN_WX_COMP = "LOGOUT_SIGN_WX_COMP";// 微信企业端登出标记
	public final static String COOKIE_ACCESS_TOKEN_WX_STU = "ACCESS_TOKEN_WX_STU";// 两个公众号，一个微信浏览器，容易导致混乱，所以最好不用一个token标识
	public final static String COOKIE_ACCESS_TOKEN_WX_COMP = "ACCESS_TOKEN_WX_COMP";// 两个公众号，一个微信浏览器，容易导致混乱，所以最好不用一个toke标识
	public final static String COOKIE_DOMAIN = ".hejia.com";// 域名
	public final static String COOKIE_DES_KEY = "hejia123!";// 加密key，可以放在在配置文件中
	public final static String COOKIE_WEIXIN_USER_OPEN_ID = "WEIXIN_USER_OPEN_ID";// 微信用户openid
	public final static String COOKIE_LOGIN_ERROR_COUNT = "LOGIN_ERROR_COUNT";// 登录错误次数
	public final static String COOKIE_LOGIN_IS_NEED_VERIFY = "LOGIN_IS_NEED_VERIFY";// 是否需要验证码

	/**
	 * session相关
	 */
	public final static String SESSION_IS_TRY_LOGIN = "IS_TRY_LOGIN";// 是否尝试过登录
	public final static String SESSION_WEIXIN_USER_ACCESS_TOKEN_JSON = "WEIXIN_USER_ACCESS_TOKEN_JSON";// 微信用户访问凭证json对象
	public final static String SESSION_SEND_LOGIN_CODE_TOKEN = "SEND_LOGIN_CODE_TOKEN";// 登录凭证
	public final static String SESSION_REGISTER_VERIFY_CODE = "REGISTER_VERIFY_CODE";// 注册验证码
	public final static String SESSION_LOGIN_VERIFY_CODE = "LOGIN_VERIFY_CODE";// 登录验证码
	public final static String SESSION_FORGETPWD_VERIFY_CODE = "FORGETPWD_VERIFY_CODE";// 忘记密码验证码
	public final static String SESSION_MODIFYPWD_VERIFY_CODE = "MODIFYPWD_VERIFY_CODE";// 修改密码验证码
	public final static String SESSION_BIND_VERIFY_CODE = "BIND_VERIFY_CODE";// 绑定验证码
	public final static String SESSION_CLIENT_IP = "CLIENT_IP";// 客户端ip
	public final static String SESSION_WEIXIN_UNIONID = "WEIXIN_UNIONID";// 微信用户的unionid
	public final static String SESSION_WEIXIN_PLATFORM = "WEIXIN_PLATFORM";// 微信用户的平台标识
	public final static String SESSION_LOG_REG_ERR_TIMES = "LOG_REG_ERR_TIMES";// 登录注册试错次数
	public final static int SESSION_LOG_REG_MAX_ERR_TIMES = 3;// 登录注册的最大试错次数
	public final static String SESSION_DELIVERY_NOTICE_CORRECT = "DELIVERY_NOTICE_CORRECT";// 投递通知信息验证（db与redis同步信息）

	/**
	 * 平台
	 */
	public static final int PLATFORM_FREE = 0;// 游离于各个平台共享某部分信息的用户，不受限于平台的异同-chenyq
	public static final int PLATFORM_ADMIN = 1;// 后台
	public static final int PLATFORM_AUTH = 2;// 验证子系统
//	public static final int PLATFORM_WEB_STUDENT = 2;// 学生端
//	public static final int PLATFORM_WEB_COMPANY = 3;// 企业端
//	public static final int PLATFORM_MOBILE_STUDENT = 4;// 学生移动端
//	public static final int PLATFORM_MOBILE_COMPANY = 5;// 企业移动端
//	public static final int PLATFORM_WEB_EVALUATION = 6;// 测评子系统
//	public static final int PLATFORM_WEB_SEARCH = 7;// 搜索子系统
	
	/**
	 * 客户端类型，支撑个性化页面
	 */
	public final static int CLIENT_TYPE_PC = 1;
	public final static int CLIENT_TYPE_MOBILE = 2;
	
	/**
	 * 项目部署的环境（主要作用是防止一些误操作和性能上的考虑）
	 */
	public static int runEnv;
	
	/**
	 * 子系统URL
	 */
	public static String systemUrlAdminManage;
	public static String systemUrlAuth;
	
	static {
		if (runEnv == 1) {//生产环境
			systemUrlAdminManage = ModuleConfig.get("system_url_admin_manage");
			systemUrlAuth = ModuleConfig.get("system_url_auth");
		} else if (runEnv == 2) {//测试环境  
			systemUrlAdminManage = "http://tadmin.hejia.com";
			systemUrlAuth = "http://tsearch.hejia.com";
		} else if (runEnv == 3) {//个人开发环境
			systemUrlAdminManage = "http://localhost:8081";
			systemUrlAuth = "http://localhost:8080";
		} else {//集体开发环境
			systemUrlAdminManage = "http://localhost:8081";
			systemUrlAuth = "http://localhost:8080";
		}
	}
}
