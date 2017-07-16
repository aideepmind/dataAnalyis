package com.hejia.dataAnalysis.module.common;

import java.io.File;

/**
 * @Description: 全局常量类
 * @author: chenyongqiang
 * @Date: 2015年4月6日
 * @version: 1.0
 */
public class Constant {

	//================= 用户信息区域 =================//

	/**
	 * 1:正常
	 */
	public static final int STATUS_NORMAL = 1;

	//用户类型  用户类型：1.学生  2.运营者  3.管理员

	/**
	 * 账号类型
	 */
	public static final int ACCOUNT_TYPE_STUDENT = 1;//学生

	public static final int ACCOUNT_TYPE_ADMIN = 2;//管理员

	public static final int ACCOUNT_TYPE_OPERATOR = 3;//运营人员

	public static final int ACCOUNT_TYPE_COMPANY = 4;//企业

	public static final int ACCOUNT_TYPE_FREE = 5;//游离于各个平台共享某部分信息的用户，不受限于平台的异同-chenyq

	public static final int ACCOUNT_TYPE_SALE = 6;//销售人员

	public static final int ACCOUNT_ID_SYSTEM = -1;//系统id

	/**
	 * 1:mobile
	 */
	public static final int LOST_SFFICACY_TIME = 1;

	/**
	 * 用户状态 1.正常 2.禁用 3.禁言 4.未激活 5.被删除 6.未完善  7.审核中
	 */
	public static final int ACCOUNT_STATUS_NORMAL = 1;//正常   

	public static final int ACCOUNT_STATUS_DISABLE = 2;//禁用

	public static final int ACCOUNT_STATUS_SILENCE = 3;//禁言

	public static final int ACCOUNT_STATUS_INACTIVE = 4;//未激活 

	public static final int ACCOUNT_STATUS_DELETED = 5;//被删除 

	public static final int ACCOUNT_STATUS_NOT_PERFECT = 6;//未完善

	public static final int ACCOUNT_STATUS_AUDITING = 7;//审核中

	/**
	 * 用户激活状态
	 */
	public static final int ACCOUNT_ACTIVATE_STATUS_NO = 0;//未激活

	public static final int ACCOUNT_ACTIVATE_STATUS_YES = 1;//已激活

	/**
	 * 激活码状态
	 */
	public static final int INVITECODE_STATUS_UNUSED = 0;//未使用

	public static final int INVITECODE_STATUS_USED = 1;//已使用

	/**
	 * 消息状态
	 */
	public static final int NOTIFY_STATUS_UNREAD = 0;//未读

	public static final int NOTIFY_STATUS_READ = 1;//已读

	/**
	 * 职位状态
	 */

	public static final int POSITION_STATUS_DELETE = 0;//已删除，对于职位的删除只做逻辑删除  - chenyq注

	public static final int POSITION_STATUS_UN_ONLINE = 1;//未上线

	public static final int POSITION_STATUS_ONLINE = 2;//已上线

	public static final int POSITION_STATUS_EXCESS = 3;//已超额

	public static final int POSITION_STATUS_OFFLINE = 4;//已下线

	public static final int POSITION_STATUS_INVALID = 5;//已过期

	public static final int POSITION_STATUS_SYSTEM_CREATE = 6;//系统创建（用于虚拟职位时使用）

	/**
	 * 模板相关(默认、非默认)
	 */
	public static final int TEMPLATE_STATUS_UN_DEFAULT = 1;//非默认模板

	public static final int TEMPLATE_STATUS_DEFAULT = 2;//默认模板

	/**
	 * 模板相关(合适、不合适)
	 */

	public static final int TEMPLATE_TYPE_SUITABLE = 1;//合适通知模板

	public static final int TEMPLATE_TYPE_UN_SUITABLE = 2;//不合适通知模板

	public static final int TEMPLATE_TYPE_WRITTEN_ON_LINE_TYPE = 5;//在线笔试模板

	/**
	 * 模板文件相关
	 */
	public static String templateNameForWord;//word 模板文件名
	public static String templatePathForWord;//word 模板文件地址

	public static String poiTemplateNameForWord;//word 模板文件名

	/**
	 * 上传相关
	 */

	public static String imgUploadPath;//图片上传路径
	public static String fileUploadPath;//文件上传路径
	public static String tempUploadPath;//文件上传路径

	public static String imgAccessPrefix;//图片访问前缀
	public static String fileAccessPrefix;//文件访问前缀

	public static String imgCorpLogoName;//公司logo 文件夹命名
	public static String imgTempName;//公司logo 文件夹命名
	public static String imgStudentHeadImgName;//学生头像 文件夹命名
	public static String imgCorpIntroduceName;//公司介绍图片 文件夹命名
	public static String imgCorpProductImgName;//公司logo 文件夹命名
	public static String imgCorpLeaderName;//公司领导人 文件夹命名
	public static String imgCorpLicenseName;//公司营业执照 图片文件夹命名
	public static String imgCutSourceName;//剪切图片 原图片 文件夹命名
	public static String imgVerifyCodeName;//图片认证码文件夹
	public static String imgThirdPartVerifyCodeName;//第三方图片认证码文件夹

	public static String fileStudentAttachResumeName;//学生附件简历 文件夹命名  
	public static String fileStudentAttachPdfResumeName;//学生附件简历 文件夹命名
	public static String fileStudentResumeName;//学生在线简历 文件夹命名

	/**
	 * 导出PDF文件  
	 */

	public static String pdfExportPath;//pdf 路径
	/**
	 * 虚拟目录相关
	 */
	public static String virtualImgPath;//图片上传路径
	public static String virtualFilePath;//文件上传路径
	public static String virtualTempPath;//临时文件上传路径

	public static String virtualCorpImgPath;//图片上传路径
	public static String virtualCorpFilePath;//文件上传路径
	public static String virtualCorpTempPath;//临时文件上传路径

	/**
	 * 子系统URL
	 */
	public static String systemUrlWwwStudent;
	public static String systemUrlWwwCompany;
	public static String systemUrlWeiXinStudent;
	public static String systemUrlWeiXinCompany;
	public static String systemUrlAdminManage;
	public static String systemUrlAuthSso;
	public static String systemUrlWwwSearch;
	public static String systemUrlWwwRecruitment;
	public static String systemUrlAdminSale;

	/**
	 * debug模式（主要作用是防止一些误操作和性能上的考虑）
	 */
	public static boolean debug;

	/**
	 * 版本号，暂用-chenyq
	 */
	public static String version;

	/**
	 * 项目部署的环境（主要作用是防止一些误操作和性能上的考虑）
	 */
	public static int runEnv;

	/**
	 * OpenOffice安装路径
	 */
	public static String openOfficeHome;

	/**
	 * 搜索相关的配置
	 */
	public static String searchSudentIndexPath;
	public static String searchCompanyIndexPath;

	public static String ygysUrl;
	public static String ygysNamespace;
	public static String ygysUsername;
	public static String ygysPwd;

	/**
	 * 搜狐云邮
	 */
	public static String sendCloudApiUser;
	public static String sendCloudApiKey;
	public static String sendCloudApiUrl;
	public static String sendCloudApiTemplateUrl;
	public static String sendCloudApiAddAddressListUrl;
	public static String sendCloudApiDeteleAddressListUrl;
	public static String sendCloudApiAddAddressUrl;

	/**
	 * 第三方接口
	 */
	public static String tpWeicewangUrl;
	public static String tpWeicewangUid;
	public static String tpWeicewangKey;

	static {
		debug = ModuleConfig.getBoolean("debug");
		version = ModuleConfig.get("version");
		runEnv = ModuleConfig.getInt("run_env");
		if (System.getProperty("os.name").toUpperCase().indexOf("WINDOW") == 0) {
			pdfExportPath = ModuleConfig.get("pdf_upload_path_win");
			imgUploadPath = ModuleConfig.get("img_upload_path_win");
			fileUploadPath = ModuleConfig.get("file_upload_path_win");
			tempUploadPath = ModuleConfig.get("temp_upload_path_win");
			openOfficeHome = ModuleConfig.get("openoffice_home_win");
			searchSudentIndexPath = ModuleConfig.get("search_student_index_path_win");
			searchCompanyIndexPath = ModuleConfig.get("search_company_index_path_win");
		} else {
			pdfExportPath = ModuleConfig.get("pdf_upload_path_linux");
			imgUploadPath = ModuleConfig.get("img_upload_path_linux");
			fileUploadPath = ModuleConfig.get("file_upload_path_linux");
			tempUploadPath = ModuleConfig.get("temp_upload_path_linux");
			openOfficeHome = ModuleConfig.get("openoffice_home_linux");
			searchSudentIndexPath = ModuleConfig.get("search_student_index_path_linux");
			searchCompanyIndexPath = ModuleConfig.get("search_company_index_path_linux");
		}
		imgAccessPrefix = ModuleConfig.get("img_access_prefix");
		fileAccessPrefix = ModuleConfig.get("file_access_prefix");
		virtualImgPath = ModuleConfig.get("virtual_img_path");
		virtualFilePath = ModuleConfig.get("virtual_file_path");
		virtualTempPath = ModuleConfig.get("virtual_temp_path");

		virtualCorpImgPath = ModuleConfig.get("virtual_corp_img_path");
		virtualCorpFilePath = ModuleConfig.get("virtual_corp_file_path");
		virtualCorpTempPath = ModuleConfig.get("virtual_corp_temp_path");

		if (runEnv == 1) {//生产环境
			systemUrlWwwStudent = ModuleConfig.get("system_url_www_student");
			systemUrlWwwCompany = ModuleConfig.get("system_url_www_company");
			systemUrlWeiXinStudent = ModuleConfig.get("system_url_weixin_student");
			systemUrlWeiXinCompany = ModuleConfig.get("system_url_weixin_company");
			systemUrlAdminManage = ModuleConfig.get("system_url_admin_manage");
			systemUrlAuthSso = ModuleConfig.get("system_url_auth_sso");
			systemUrlWwwSearch = ModuleConfig.get("system_url_www_search");
			systemUrlWwwRecruitment = ModuleConfig.get("system_url_www_recruitment");
			systemUrlAdminSale = ModuleConfig.get("system_url_admin_sale");
		} else if (runEnv == 2) {//测试环境  
			systemUrlWwwStudent = "http://twww.minixiao.com";
			systemUrlWwwCompany = "http://tcorp.minixiao.com";
			systemUrlWeiXinStudent = "http://tmobile.minixiao.com";
			systemUrlWeiXinCompany = "http://tcorpwx.minixiao.com";
			systemUrlAdminManage = "http://tadmin.minixiao.com";
			systemUrlAuthSso = "http://tpassport.minixiao.com";
			systemUrlWwwSearch = "http://tsearch.minixiao.com";
			//  需要张哥买域名 -------------yxl
			systemUrlWwwRecruitment = "http://tcorp.minixiao.com";
			systemUrlAdminSale = "http://tsale.minixiao.com";
		} else if (runEnv == 3) {//个人开发环境
			systemUrlWwwStudent = "http://localhost:8081";
			systemUrlWwwCompany = "http://localhost:8082";
			systemUrlWeiXinStudent = "http://localhost:8083";
			systemUrlWeiXinCompany = "http://localhost:8084";
			systemUrlAdminManage = "http://localhost:8085";
			systemUrlAuthSso = "http://localhost:8080";
			systemUrlWwwSearch = "http://localhost:8086";
			systemUrlWwwRecruitment = "http://localhost:8087";
			systemUrlAdminSale = "http://localhost:8088";
		} else {//集体开发环境
			systemUrlWwwStudent = "http://localhost:8081";
			systemUrlWwwCompany = "http://localhost:8082";
			systemUrlWeiXinStudent = "http://localhost:8083";
			systemUrlWeiXinCompany = "http://localhost:8084";
			systemUrlAdminManage = "http://localhost:8085";
			systemUrlAuthSso = "http://localhost:8080";
			systemUrlWwwSearch = "http://localhost:8086";
			systemUrlWwwRecruitment = "http://localhost:8087";
			systemUrlAdminSale = "http://localhost:8088";
			//systemUrlWwwStudent = systemUrlWwwCompany = systemUrlWeiXinStudent = systemUrlWeiXinCompany = systemUrlAdminManage = systemUrlAuthSso = "http://localhost:8080";
		}

		imgCorpLogoName = ModuleConfig.get("img_corp_logo_name");
		imgStudentHeadImgName = ModuleConfig.get("img_student_headImg_name");
		imgCorpIntroduceName = ModuleConfig.get("img_corp_introduce_imgs_name");
		fileStudentResumeName = ModuleConfig.get("file_student_resume_name");
		imgCorpLeaderName = ModuleConfig.get("img_corp_leader_imgs_name");
		imgTempName = ModuleConfig.get("img_temp_name");
		imgCorpProductImgName = ModuleConfig.get("img_corp_product_imgs_name");
		imgCorpLicenseName = ModuleConfig.get("img_corp_license_imgs_name");
		imgCutSourceName = ModuleConfig.get("img_cut_source_name");
		imgVerifyCodeName = tempUploadPath + File.separator + ModuleConfig.get("img_verify_code_name");
		imgThirdPartVerifyCodeName = ModuleConfig.get("third_pard_img_verify_code_name");

		fileStudentAttachResumeName = ModuleConfig.get("file_student_attach_resume_name");
		fileStudentAttachPdfResumeName = ModuleConfig.get("file_student_attach_pdf_resume_name");

		templateNameForWord = ModuleConfig.get("word_template_name");
		templatePathForWord = ModuleConfig.get("word_template_path");

		poiTemplateNameForWord = ModuleConfig.get("word_poi_template_name");

		ygysUrl = ModuleConfig.get("ygys_url");
		ygysNamespace = ModuleConfig.get("ygys_namespace");
		ygysUsername = ModuleConfig.get("ygys_username");
		ygysPwd = ModuleConfig.get("ygys_pwd");

		tpWeicewangUrl = ModuleConfig.get("tp_weicwang_url");
		tpWeicewangUid = ModuleConfig.get("tp_weicwang_uid");
		tpWeicewangKey = ModuleConfig.get("tp_weicwang_key");

		sendCloudApiUser = ModuleConfig.get("sendcloud_api_user");
		sendCloudApiKey = ModuleConfig.get("sendcloud_api_key");
		sendCloudApiUrl = ModuleConfig.get("sendcloud_api_url");
		sendCloudApiTemplateUrl = ModuleConfig.get("sendcloud_api_template_url");
		sendCloudApiAddAddressListUrl = ModuleConfig.get("sendcloud_api_add_address_list_url");
		sendCloudApiDeteleAddressListUrl = ModuleConfig.get("sendcloud_api_detele_address_list_url");
		sendCloudApiAddAddressUrl = ModuleConfig.get("sendcloud_api_add_address_url");
	}

	/**
	 * cookie相关
	 */
	public final static String COOKIE_PATH = "/";//作用域
	public final static int COOKIE_TIME_MONTH = 30 * 24 * 60 * 60;//一个月
	public final static int COOKIE_TIME_YEAR = 365 * 24 * 60 * 60;//一年
	public final static String COOKIE_LOGIN_TOKEN = "COOKIE_LOGIN_TOKEN";//凭证
	public final static String COOKIE_LOGOUT_SIGN_WX_STU = "COOKIE_LOGOUT_SIGN_WX_STU";//微信学生端登出标记
	public final static String COOKIE_LOGOUT_SIGN_WX_COMP = "COOKIE_LOGOUT_SIGN_WX_COMP";//微信企业端登出标记
	public final static String COOKIE_LOGIN_TOKEN_WX_STU = "COOKIE_LOGIN_TOKEN_WX_STU";//两个公众号，一个微信浏览器，容易导致混乱，所以最好不用一个token标识
	public final static String COOKIE_LOGIN_TOKEN_WX_COMP = "COOKIE_LOGIN_TOKEN_WX_COMP";//两个公众号，一个微信浏览器，容易导致混乱，所以最好不用一个toke标识
	public final static String COOKIE_DOMAIN = ".minixiao.com";//域名
	public final static String COOKIE_DES_KEY = "minixiao123!";//加密key，可以放在在配置文件中
	public final static String COOKIE_WEIXIN_USER_OPEN_ID = "cookie_weixin_user_open_id";//微信用户openid
	public final static String COOKIE_LOGIN_ERROR_COUNT = "COOKIE_LOGIN_ERROR_COUNT";//登录错误次数
	public final static String COOKIE_LOGIN_IS_NEED_VERIFY = "COOKIE_LOGIN_IS_NEED_VERIFY";//是否需要验证码

	/**
	 * session相关
	 */
	public final static String SESSION_IS_TRY_LOGIN = "SESSION_IS_TRY_LOGIN";//是否尝试过登录
	public final static String SESSION_WEIXIN_USER_ACCESS_TOKEN_JSON = "SESSION_WEIXIN_USER_ACCESS_TOKEN_JSON";//微信用户访问凭证json对象
	public final static String SESSION_SEND_LOGIN_CODE_TOKEN = "SESSION_SEND_LOGIN_CODE_TOKEN";//登录凭证
	public final static String SESSION_REGISTER_VERIFY_CODE = "SESSION_REGISTER_VERIFY_CODE";//注册验证码
	public final static String SESSION_LOGIN_VERIFY_CODE = "SESSION_LOGIN_VERIFY_CODE";//登录验证码
	public final static String SESSION_FORGETPWD_VERIFY_CODE = "SESSION_FORGETPWD_VERIFY_CODE";//忘记密码验证码
	public final static String SESSION_MODIFYPWD_VERIFY_CODE = "SESSION_MODIFYPWD_VERIFY_CODE";//修改密码验证码
	public final static String SESSION_BIND_VERIFY_CODE = "SESSION_BIND_VERIFY_CODE";//绑定验证码
	public final static String SESSION_CLIENT_IP = "SESSION_CLIENT_IP";//客户端ip
	public final static String SESSION_WEIXIN_UNIONID = "SESSION_WEIXIN_UNIONID";//微信用户的unionid
	public final static String SESSION_WEIXIN_PLATFORM = "SESSION_WEIXIN_PLATFORM";//微信用户的平台标识
	public final static String SESSION_LOG_REG_ERR_TIMES = "SESSION_LOG_REG_ERR_TIMES";//登录注册试错次数
	public final static int SESSION_LOG_REG_MAX_ERR_TIMES = 3;//登录注册的最大试错次数
	public final static String SESSION_DELIVERY_NOTICE_CORRECT = "SESSION_DELIVERY_NOTICE_CORRECT";//投递通知信息验证（db与redis同步信息）

	/**
	 * 个人中心--第三方账号绑定页面标识（用于区别注册时的第三方绑定）
	 */
	public static final String SESSION_PERSONAL_THIRD_PART_BIND = "SESSION_PERSONAL_THIRD_PART_BIND";

	/**
	 * 新主站个人中心--第三方账号绑定请求标识
	 */
	public static final String PERSONSETTING_THIRD_PART_BIND = "PERSONSETTING_THIRD_PART_BIND";

	/**
	 * shiro常量
	 */
	public static final String SESSION_SHIRO_CURRENT_ACCOUNT = "SESSION_SHIRO_CURRENT_ACCOUNT";

	/**
	 * openOffice 打开命令
	 */
	public static final String OPENOFFICE_RUN_COMMAND = "soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";

	public final static String SESSION_PREV_ACCESS_URL = "SESSION_PREV_ACCESS_URL";//用户上一次访问的url，用于404错误。

	public final static String SESSION_THIRD_TRANSFORM_INFO = "SESSION_THIRD_TRANSFORM_INFO";//第三方导入信息

	public final static String SESSION_FILE_TRANSFORM_INFO = "SESSION_FILE_TRANSFORM_INFO";//上传文件导入信息

	/**
	 * 编码方式
	 */
	public final static String ENCODING_ISO = "ISO-8859-1";

	public final static String ENCODING_UTF = "UTF-8";

	public final static String ENCODING_GBK = "GBK";

	/**
	 * 客户端类型，支撑个性化页面
	 */
	public final static int CLIENT_TYPE_PC = 1;
	public final static int CLIENT_TYPE_MOBILE = 2;

	/**
	 * 平台
	 */
	public static final int PLATFORM_FREE = 0;//游离于各个平台共享某部分信息的用户，不受限于平台的异同-chenyq

	public static final int PLATFORM_ADMIN = 1;//后台

	public static final int PLATFORM_WEB_STUDENT = 2;//学生端

	public static final int PLATFORM_WEB_COMPANY = 3;//企业端

	public static final int PLATFORM_MOBILE_STUDENT = 4;//学生移动端

	public static final int PLATFORM_MOBILE_COMPANY = 5;//企业移动端

	public static final int PLATFORM_WEB_EVALUATION = 6;//测评子系统

	public static final int PLATFORM_WEB_SEARCH = 7;//搜索子系统

	/**
	 * 头部导航标示 学生端
	 */
	public static final int NAV_STU_DASHBOARD = 10;//首页

	public static final int NAV_STU_RESUME = 11;//我的简历

	public static final int NAV_STU_DELIVERY = 12;//我的投递

	public static final int NAV_STU_ACTIVITY = 13;//活动

	public static final int NAV_STU_WEEK_RECOMMEND = 14;//每周精英职位推荐

	public static final int NAV_STU_POSITION_RECOMMEND = 15;//每周精英职位推荐

	//新版学生主站
	public static final int NAV_NEW_STU_INDEX = 16;//首页

	public static final int NAV_NEW_STU_POSITION = 17;//职位

	public static final int NAV_NEW_STU_COMPANY = 18;//公司

	public static final int NAV_NEW_STU_ACTIVITY = 19;//活动

	/**
	 * 头部导航标示 企业端
	 */
	public static final int NAV_CORP_DASHBOARD = 20;//首页

	public static final int NAV_CORP_POSITION = 21;//职位管理

	public static final int NAV_CORP_RESUME = 22;//简历管理

	public static final int NAV_CORP_HOME = 23;//公司主页

	public static final int NAV_CORP_WEEK_RECOMMEND = 24;//每周精英推荐

	public static final int NAV_CORP_SETUP = 25;//公司设置

	/**
	 * 默认图片
	 */
	public static final String DEFAULT_IMG_URL_STU_HEAD = "http://www.minixiao.com/st/images/cutSource/default_headpic.png";
	public static final String DEFAULT_IMG_URL_COMP_HEAD = "http://www.minixiao.com/st/images/cutSource/default_headpic.png";
	public static final String DEFAULT_IMG_URL_COMP_LOGO = "http://www.minixiao.com/st/images/cutSource/default_headpic.png";
}
