package com.hejia.dataAnalysis.module.common.log;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 日志操作值
 * @author: chenyongqiang
 * @Date: 2015年6月23日
 * @version: 1.0
 */
public class LogOperValue {

	/**
	 * 操作ID和操作名映射
	 */
	public static Map<Integer, String> map = new HashMap<Integer, String>();
	 
	/**
	 * 未知类型
	 */
	public static final int UNKNOWN_OPER_VALUE = 0;
	
    /**
     * 用户登录系统
     */
    public static final int LOGON_VALUE = 101; 
	
	/**
     * 用户退出系统
     */
    public static final int LOGOUT_VALUE = 102;

	/**
     * 登录发送短信
     */
    public static final int LOGIN_SEND_SMS = 103;
    
	/**
     * 注册发送短信
     */
    public static final int RIGISTER_SEND_SMS = 104;

	/**
     * 登录发送邮件
     */
    public static final int LOGIN_SEND_EMAIL = 105;

	/**
     * 注册发送邮件
     */
    public static final int RIGISTER_SEND_EMAIL = 106;
    
    /**
     * 增加用户
     */
    public static final int ADD_ACCOUNT_VALUE = 201;
    
    /**
     * 删除用户
     */
    public static final int DELETE_ACCOUNT_VALUE = 202;

    /**
     * 修改用户
     */
    public static final int UPDATE_ACCOUNT_VALUE = 203;

    /**
     * 发送完善简历邮件
     */
    public static final int SEND_RESUME_COMPLETENESS_EMAIL_VALUE = 204;
    
    
    
    /**
     * 企业用户信息填写第1步
     */
    public static final int COMPANY_INFO_STEP1= 231;
    /**
     * 企业用户信息填写第2步
     */
    public static final int COMPANY_INFO_STEP2= 232;
    /**
     * 企业用户信息填写第3步
     */
    public static final int COMPANY_INFO_STEP3= 233;
    /**
     * 企业用户信息填写第4步
     */
    public static final int COMPANY_INFO_STEP4= 234;
    /**
     * 企业用户信息填写第5步
     */
    public static final int COMPANY_INFO_STEP5= 235;
    
    
    
    /**
     * 更新公司主页顶部
     */
    public static final int UPDATE_COMPANY_TOP_VALUE= 238;
    
    /**
     * 更新公司主页基本信息
     */
    public static final int UPDATE_COMPANY_BASE_VALUE= 239;
    /**
     * 更新公司主页产品
     */
    public static final int UPDATE_COMPANY_PRODUCT_VALUE= 240;
    /**
     * 更新公司主页介绍
     */
    public static final int UPDATE_COMPANY_INTRO_VALUE= 241;
    /**
     * 更新公司主页标签
     */
    public static final int UPDATE_COMPANY_TAG_VALUE= 242;
    /**
     * 更新公司主页领导者
     */
    public static final int UPDATE_COMPANY_LEADER_VALUE= 243;
    /**
     * 删除公司主页产品
     */
    public static final int DELETE_COMPANY_PRODUCT_VALUE= 244;
    /**
     * 删除公司主页领导者
     */
    public static final int DELETE_COMPANY_LEADER_VALUE= 245;
    
    /**
     * 企业用户简历初筛-未处理-通过
     */
    public static final int COMPANY_DELIVERY_INITIAL_UNTREATED_PASS= 261;
    /**
     * 企业用户简历初筛-未处理-不通过
     */
    public static final int COMPANY_DELIVERY_INITIAL_UNTREATED_NOPASS= 262;
    /**
     * 企业用户简历初筛-未处理-待定
     */
    public static final int COMPANY_DELIVERY_INITIAL_UNTREATED_UNDETERMINED= 263;
    /**
     * 企业用户简历初筛-未处理-已读
     */
    public static final int COMPANY_DELIVERY_INITIAL_UNTREATED_READ= 264;
    
    
    /**
     * 企业用户简历笔试-未处理-通过
     */
    public static final int COMPANY_DELIVERY_WRITTEN_UNTREATED_PASS= 271;
    /**
     * 企业用户简历笔试-未处理-不通过
     */
    public static final int COMPANY_DELIVERY_WRITTEN_UNTREATED_NOPASS= 272;
    /**
     * 企业用户简历笔试-未处理-待定
     */
    public static final int COMPANY_DELIVERY_WRITTEN_UNTREATED_UNDETERMINED= 273;
    /**
     * 企业用户简历笔试-未处理-驳回
     */
    public static final int COMPANY_DELIVERY_WRITTEN_UNTREATED_BACK= 274;
    
    
    /**
     * 企业用户简历面试-未处理-通过
     */
    public static final int COMPANY_DELIVERY_INTERVIEW_UNTREATED_PASS= 281;
    /**
     * 企业用户简历面试-未处理-不通过
     */
    public static final int COMPANY_DELIVERY_INTERVIEW_UNTREATED_NOPASS= 282;
    /**
     * 企业用户简历面试-未处理-待定
     */
    public static final int COMPANY_DELIVERY_INTERVIEW_UNTREATED_UNDETERMINED= 283;
    /**
     * 企业用户简历面试-未处理-驳回
     */
    public static final int COMPANY_DELIVERY_INTERVIEW_UNTREATED_BACK= 284;
    
    /**
     * 学生用户投递简历到某职位
     */
    public static final int STUDENT_DELIVERY_POSITION = 291;
    /**
     * 学生用户投递点击投递动作到第三方职位链接
     */
    public static final int STUDENT_CLICK_THIRD_PARTY_POSITION= 292;

    /**
     * 增加活动
     */
    public static final int ADD_ACTIVITY_VALUE = 301;
    
    /**
     * 删除活动
     */
    public static final int DELETE_ACTIVITY_VALUE = 302;

    /**
     * 修改活动
     */
    public static final int UPDATE_ACTIVITY_VALUE = 303; 

    /**
     * 修改密码
     */
    public static final int UPDATE_PWD_VALUE = 401;
    
    /**
     * 忘记密码
     */
    public static final int FORGET_PWD_VALUE = 402;

    /**
     * 重设密码
     */
    public static final int RESET_PWD_VALUE = 403; 

    /**
     * 修改邮箱
     */
    public static final int  UPDATE_EMAIL_VALUE = 404;
    
    /**
     * 修改电话
     */
    public static final int UPDATE_MOBILE_VALUE = 405;

    /**
     * 激活邮箱
     */
    public static final int ACTIVE_EMAIL_VALUE = 406;

    /**
     * 激活电话
     */
    public static final int ACTIVE_MOBILE_VALUE = 407;
    
    /**
     * 增加邀请码
     */
    public static final int ADD_INVITE_CODE_VALUE = 501;

    /**
     * 删除邀请码
     */
    public static final int DELETE_INVITE_CODE_VALUE = 502;
    
    /**
     * 修改邀请码
     */
    public static final int UPDATE_INVITE_CODE_VALUE = 503;

    /**
     * 增加简历基本信息
     */
    public static final int ADD_RESUME_BASE_INFO_VALUE = 601;
    
    /**
     * 修改简历基本信息
     */
    public static final int UPDATE_RESUME_BASE_INFO_VALUE = 602;
    
    /**
     * 增加基本教育信息
     */
    public static final int ADD_RESUME_BASEEDU_INFO_VALUE = 603;

    /**
     * 修改基本教育信息
     */
    public static final int UPDATE_RESUME_BASEEDU_INFO_VALUE = 604;
    
    /**
     * 增加简历教育信息
     */
    public static final int ADD_RESUME_EDUCATION_INFO_VALUE = 605;
    
    /**
     * 删除简历教育信息
     */
    public static final int DELETE_RESUME_EDUCATION_VALUE = 606;

    /**
     * 修改简历教育信息
     */
    public static final int UPDATE_RESUME_EDUCATION_INFO_VALUE = 607;

    /**
     * 增加简历奖励
     */
    public static final int ADD_RESUME_REWARD_VALUE = 608;

    /**
     * 删除简历奖励
     */
    public static final int DELETE_RESUME_REWARD_VALUE = 609;
    
    /**
     * 修改简历奖励
     */
    public static final int UPDATE_RESUME_REWARD_VALUE = 610;

    /**
     * 增加简历证书
     */
    public static final int ADD_RESUME_CERTIFICATE_VALUE = 611;

    /**
     * 删除简历证书
     */
    public static final int DELETE_RESUME_CERTIFICATE_VALUE = 612;
    
    /**
     * 修改简历证书
     */
    public static final int UPDATE_RESUME_CERTIFICATE_VALUE = 613;

    /**
     * 增加简历实习/工作经验
     */
    public static final int ADD_RESUME_WORK_VALUE = 614;

    /**
     * 删除简历实习/工作经验
     */
    public static final int DELETE_RESUME_WORK_VALUE = 615;
    
    /**
     * 修改简历实习/工作经验
     */
    public static final int UPDATE_RESUME_WORK_VALUE = 616;

    /**
     * 增加简历项目经验
     */
    public static final int ADD_RESUME_PRACTICES_VALUE = 617;

    /**
     * 删除简历项目经验
     */
    public static final int DELETE_RESUME_PRACTICES_VALUE = 618;
    
    /**
     * 修改简历项目经验
     */
    public static final int UPDATE_RESUME_PRACTICES_VALUE = 619;

    /**
     * 增加简历专业技能
     */
    public static final int ADD_RESUME_SKILL_VALUE = 620;

    /**
     * 删除简历专业技能
     */
    public static final int DELETE_RESUME_SKILL_VALUE = 621;
    
    /**
     * 修改简历专业技能
     */
    public static final int UPDATE_RESUME_SKILL_VALUE = 622;

    /**
     * 增加简历语言技能
     */
    public static final int ADD_RESUME_LANGUAGE_VALUE = 623;

    /**
     * 删除简历语言技能
     */
    public static final int DELETE_RESUME_LANGUAGE_VALUE = 624;
    
    /**
     * 修改简历语言技能
     */
    public static final int UPDATE_RESUME_LANGUAGE_VALUE = 625;

    /**
     * 增加简历发表论文
     */
    public static final int ADD_RESUME_PAPERS_VALUE = 626;

    /**
     * 删除简历发表论文
     */
    public static final int DELETE_RESUME_PAPERS_VALUE = 627;
    
    /**
     * 修改简历发表论文
     */
    public static final int UPDATE_RESUME_PAPERS_VALUE = 628;

    /**
     * 增加简历所编书
     */
    public static final int ADD_RESUME_BOOK_VALUE = 629;

    /**
     * 删除简历所编书
     */
    public static final int DELETE_RESUME_BOOK_VALUE = 630;
    
    /**
     * 修改简历所编书
     */
    public static final int UPDATE_RESUME_BOOK_VALUE = 631;

    /**
     * 增加简历社团经历
     */
    public static final int ADD_RESUME_CLUB_VALUE = 632;

    /**
     * 删除简历社团经历
     */
    public static final int DELETE_RESUME_CLUB_VALUE = 633;
    
    /**
     * 修改简历社团经历
     */
    public static final int UPDATE_RESUME_CLUB_VALUE = 634;

    /**
     * 增加简历活动大赛
     */
    public static final int ADD_RESUME_EXERCISE_VALUE = 635;

    /**
     * 删除简历活动大赛
     */
    public static final int DELETE_RESUME_EXERCISE_VALUE = 636;
    
    /**
     * 修改简历活动大赛
     */
    public static final int UPDATE_RESUME_EXERCISE_VALUE = 637;

    /**
     * 增加简历实验室
     */
    public static final int ADD_RESUME_LABORATORYE_VALUE = 638;

    /**
     * 删除简历实验室
     */
    public static final int DELETE_RESUME_LABORATORY_VALUE = 639;
    
    /**
     * 修改简历实验室
     */
    public static final int UPDATE_RESUME_LABORATORY_VALUE = 640;

    /**
     * 增加简历意向企业
     */
    public static final int ADD_RESUME_INTENTION_VALUE = 641;

    /**
     * 删除简历意向企业
     */
    public static final int DELETE_RESUME_INTENTION_VALUE = 642;
    
    /**
     * 修改简历意向企业
     */
    public static final int UPDATE_RESUME_INTENTION_VALUE = 643;

    /**
     * 增加简历附件简历
     */
    public static final int ADD_RESUME_LOAD_VALUE = 644;

    /**
     * 删除简历附件简历
     */
    public static final int DELETE_RESUME_UPLOAD_VALUE = 645;
    
    /**
     * 修改简历附件简历
     */
    public static final int UPDATE_RESUME_UPLOAD_VALUE = 646;
    
    /**
     * 增加标签
     */
    public static final int ADD_LABEL_VALUE = 701;

    /**
     * 修改标签
     */
    public static final int UPDATE_LABEL_VALUE = 702;

    /**
     * 删除标签
     */
    public static final int DELETE_LABEL_VALUE = 703;
    
    /**
     * 搜索综合
     */
    public static final int SEARCH_COMPLEX_VALUE = 901;

    /**
     * 搜索职位
     */
    public static final int SEARCH_POSITION_VALUE = 902;

    /**
     * 搜索公司
     */
    public static final int SEARCH_COMPANY_VALUE = 903;

    /**
     * 搜索学生
     */
    public static final int SEARCH_STUDENT_VALUE = 904;

    /**
     * 搜索学校
     */
    public static final int SEARCH_SCHOOL_VALUE = 905;
    
    /**
     * 添加职位信息 
     */
    public static final int ADD_POSITION_VALUE= 1001;
    /**
     * 更新职位信息 
     */
    public static final int UPDATE_POSITION_VALUE= 1002;
    /**
     * 下线职位信息
     */
    public static final int OFFLINE_POSITION_VALUE= 1003;
    /**
     * 删除职位信息
     */
    public static final int DELETE_POSITION_VALUE= 1004;
    
    /**
     * 增加匹配模板
     */
    public static final int ADD_MATCH_TEMPLATE_VALUE = 1101;

    /**
     * 更新匹配模板
     */
    public static final int UPDATE_MATCH_TEMPLATE_VALUE = 1102;

    /**
     * 删除匹配模板
     */
    public static final int DELETE_MATCH_TEMPLATE_VALUE = 1103;

    /**
     * 增加匹配模板职位关系详细
     */
    public static final int ADD_MATCH_TEMPLATE_POSITION_DETAIL_VALUE = 1121;
    
    /**
     * 更新匹配模板职位关系详细
     */
    public static final int UPDATE_MATCH_TEMPLATE_POSITION_DETAIL_VALUE = 1122;

    /**
     * 删除匹配模板职位关系详细
     */
    public static final int DELETE_MATCH_TEMPLATE_POSITION_DETAIL_VALUE = 1123;
    
    /**
     * 增加匹配标准
     */
    public static final int ADD_MATCH_STANDAR_VALUE = 1201;
    
    /**
     * 更新匹配标准
     */
    public static final int UPDATE_MATCH_STANDAR_VALUE = 1202;

    /**
     * 删除匹配标准
     */
    public static final int DELETE_MATCH_STANDAR_VALUE = 1203;
    
    
    /**
     * 企业用户简历初筛-通过
     */
    public static final int RECUITMENT_DELIVERY_INITIAL_PASS = 1301;
    /**
     * 企业用户简历初筛-不通过
     */
    public static final int RECUITMENT_DELIVERY_INITIAL_NOPASS = 1302;
    /**
     * 企业用户简历初筛-待定
     */
    public static final int RECUITMENT_DELIVERY_INITIAL_UNDETERMINED = 1303;
    /**
     * 企业用户简历初筛-未处理-已读
     */
    public static final int RECUITMENT_DELIVERY_INITIAL_UNTREATED_READ = 1304;
    
    
    /**
     * 企业用户简历通过到笔试
     */
    public static final int RECUITMENT_DELIVERY_TO_WRITTEN = 1310;
    /**
     * 企业用户简历笔试-通过
     */
    public static final int RECUITMENT_DELIVERY_WRITTEN_PASS = 1311;
    /**
     * 企业用户简历笔试-不通过
     */
    public static final int RECUITMENT_DELIVERY_WRITTEN_NOPASS = 1312;
    /**
     * 企业用户简历笔试-待定
     */
    public static final int RECUITMENT_DELIVERY_WRITTEN_UNDETERMINED= 1313;
    /**
     * 企业用户简历笔试-安排笔试
     */
    public static final int RECUITMENT_DELIVERY_WRITTEN_ARRANGE = 1314;
    /**
     * 企业用户简历笔试-安排笔试取消
     */
    public static final int RECUITMENT_DELIVERY_WRITTEN_ARRANGE_CANCEL = 1315;
    
    
    /**
     * 企业用户简历通过到面试
     */
    public static final int RECUITMENT_DELIVERY_TO_INTERVIEW = 1320;
    /**
     * 企业用户简历面试-通过
     */
    public static final int RECUITMENT_DELIVERY_INTERVIEW_PASS = 1321;
    /**
     * 企业用户简历面试-不通过
     */
    public static final int RECUITMENT_DELIVERY_INTERVIEW_NOPASS = 1322;
    /**
     * 企业用户简历面试-待定
     */
    public static final int RECUITMENT_DELIVERY_INTERVIEW_UNDETERMINED = 1323;
    /**
     * 企业用户简历笔试-安排面试
     */
    public static final int RECUITMENT_DELIVERY_INTERVIEW_ARRANGE = 1324;
    /**
     * 企业用户简历笔试-安排笔试取消
     */
    public static final int RECUITMENT_DELIVERY_INTERVIEW_ARRANGE_CANCEL = 1325;
    
    /**
     * 企业用户简历通过到OFFER
     */
    public static final int RECUITMENT_DELIVERY_TO_OFFER = 1330;
    /**
     * 企业用户简历面试-已发录用函
     */
    public static final int RECUITMENT_DELIVERY_OFFER_SEND = 1331;
    /**
     * 企业用户简历面试-免发录用函
     */
    public static final int RECUITMENT_DELIVERY_OFFER_NONEED_SEND= 1332;
    /**
     * 企业用户简历面试-取消发送录用函
     */
    public static final int RECUITMENT_DELIVERY_OFFER_SEND_CANCEL= 1333;
    
    /**
     * 扫描表并生成任务调度
     */
    public static final int OTHER_SCAN_AND_CREATE_TASK_SCHEDULE = 10001;
    
    
	static {
		map.put(UNKNOWN_OPER_VALUE, "未知类型");
		
		map.put(LOGON_VALUE, "用户登录系统");
		map.put(LOGOUT_VALUE, "用户退出系统");
		map.put(LOGIN_SEND_SMS, "登录发送短信");
		map.put(RIGISTER_SEND_SMS, "注册发送短信");
		map.put(LOGIN_SEND_EMAIL, "登录发送邮件");
		map.put(RIGISTER_SEND_EMAIL, "注册发送邮件");
		
		map.put(ADD_ACCOUNT_VALUE, "增加用户");
		map.put(DELETE_ACCOUNT_VALUE, "删除用户");
		map.put(UPDATE_ACCOUNT_VALUE, "修改用户");
		map.put(SEND_RESUME_COMPLETENESS_EMAIL_VALUE, "发送完善简历邮件");
		
		map.put(COMPANY_INFO_STEP1, "企业信息填写第1步");
		map.put(COMPANY_INFO_STEP2, "企业信息填写第2步");
		map.put(COMPANY_INFO_STEP3, "企业信息填写第3步");
		map.put(COMPANY_INFO_STEP4, "企业信息填写第4步");
		map.put(COMPANY_INFO_STEP5, "企业信息填写第5步");

		map.put(UPDATE_COMPANY_TOP_VALUE, "更新公司主页顶部");
		map.put(UPDATE_COMPANY_BASE_VALUE, "更新公司主页基本信息");
		map.put(UPDATE_COMPANY_PRODUCT_VALUE, "更新公司主页产品");
		map.put(UPDATE_COMPANY_INTRO_VALUE, "更新公司主页介绍");
		map.put(UPDATE_COMPANY_TAG_VALUE, "更新公司主页标签");
		map.put(UPDATE_COMPANY_LEADER_VALUE, "更新公司主页领导者");
		
		map.put(COMPANY_DELIVERY_INITIAL_UNTREATED_PASS, "企业用户简历初筛-未处理-通过");
		map.put(COMPANY_DELIVERY_INITIAL_UNTREATED_NOPASS, "企业用户简历初筛-未处理-不通过");
		map.put(COMPANY_DELIVERY_INITIAL_UNTREATED_UNDETERMINED, "企业用户简历初筛-未处理-待定");
		
		map.put(COMPANY_DELIVERY_WRITTEN_UNTREATED_PASS, "企业用户简历笔试-未处理-通过");
		map.put(COMPANY_DELIVERY_WRITTEN_UNTREATED_NOPASS, "企业用户简历笔试-未处理-不通过");
		map.put(COMPANY_DELIVERY_WRITTEN_UNTREATED_UNDETERMINED, "企业用户简历笔试-未处理-待定");
		map.put(COMPANY_DELIVERY_WRITTEN_UNTREATED_BACK, "企业用户简历笔试-未处理-驳回");
		
		map.put(COMPANY_DELIVERY_INTERVIEW_UNTREATED_PASS, "企业用户简历面试-未处理-通过");
		map.put(COMPANY_DELIVERY_INTERVIEW_UNTREATED_NOPASS, "企业用户简历面试-未处理-不通过");
		map.put(COMPANY_DELIVERY_INTERVIEW_UNTREATED_UNDETERMINED, "企业用户简历面试-未处理-待定");
		map.put(COMPANY_DELIVERY_INTERVIEW_UNTREATED_BACK, "企业用户简历笔试-未处理-驳回");
		
		map.put(STUDENT_DELIVERY_POSITION, "学生用户投递简历到某职位");
		map.put(STUDENT_CLICK_THIRD_PARTY_POSITION, "学生用户投递点击投递动作到第三方职位链接");
		
		map.put(ADD_ACTIVITY_VALUE, "增加活动");
		map.put(DELETE_ACTIVITY_VALUE, "删除活动");
		map.put(UPDATE_ACTIVITY_VALUE, "修改活动");
		
		map.put(ADD_POSITION_VALUE, "添加职位信息");
		map.put(UPDATE_POSITION_VALUE, "更新职位信息");
		map.put(OFFLINE_POSITION_VALUE, "下线职位");
		map.put(DELETE_POSITION_VALUE, "删除职位");
		
		map.put(UPDATE_PWD_VALUE, "修改密码");
		map.put(FORGET_PWD_VALUE, "忘记密码");
		map.put(RESET_PWD_VALUE, "重设密码");
		map.put(UPDATE_EMAIL_VALUE, "修改邮箱");
		map.put(UPDATE_MOBILE_VALUE, "修改电话");
		map.put(ACTIVE_EMAIL_VALUE, "激活邮箱");
		map.put(ACTIVE_MOBILE_VALUE, "激活电话");

		map.put(ADD_INVITE_CODE_VALUE, "增加邀请码");
		map.put(DELETE_INVITE_CODE_VALUE, "删除邀请码");
		map.put(UPDATE_INVITE_CODE_VALUE, "修改邀请码");

		map.put(ADD_RESUME_BASE_INFO_VALUE, "增加简历基本信息");
		map.put(UPDATE_RESUME_BASE_INFO_VALUE, "修改简历基本信息");
		map.put(ADD_RESUME_BASEEDU_INFO_VALUE, "增加基本教育信息");
		map.put(UPDATE_RESUME_BASEEDU_INFO_VALUE, "更新基本教育信息");
		map.put(ADD_RESUME_EDUCATION_INFO_VALUE, "增加简历教育信息");
		map.put(UPDATE_RESUME_EDUCATION_INFO_VALUE, "修改简历教育信息");
		map.put(DELETE_RESUME_EDUCATION_VALUE, "删除简历教育信息");
		map.put(ADD_RESUME_REWARD_VALUE, "增加简历奖励");
		map.put(DELETE_RESUME_REWARD_VALUE, "删除简历奖励");
		map.put(UPDATE_RESUME_REWARD_VALUE, "修改简历奖励");
		map.put(ADD_RESUME_CERTIFICATE_VALUE, "增加简历证书");
		map.put(DELETE_RESUME_CERTIFICATE_VALUE, "删除简历证书");
		map.put(UPDATE_RESUME_CERTIFICATE_VALUE, "修改简历证书");
		map.put(ADD_RESUME_WORK_VALUE, "增加简历实习/工作经验");
		map.put(DELETE_RESUME_WORK_VALUE, "删除简历实习/工作经验");
		map.put(UPDATE_RESUME_WORK_VALUE, "修改简历实习/工作经验");
		map.put(ADD_RESUME_PRACTICES_VALUE, "增加简历项目经验");
		map.put(DELETE_RESUME_PRACTICES_VALUE, "删除简历项目经验");
		map.put(UPDATE_RESUME_PRACTICES_VALUE, "修改简历项目经验");
		map.put(ADD_RESUME_SKILL_VALUE, "增加简历专业技能");
		map.put(DELETE_RESUME_SKILL_VALUE, "删除简历专业技能");
		map.put(UPDATE_RESUME_SKILL_VALUE, "修改简历专业技能");
		map.put(ADD_RESUME_LANGUAGE_VALUE, "增加简历语言技能");
		map.put(DELETE_RESUME_LANGUAGE_VALUE, "删除简历语言技能");
		map.put(UPDATE_RESUME_LANGUAGE_VALUE, "修改简历语言技能");
		map.put(ADD_RESUME_PAPERS_VALUE, "增加简历发表论文");
		map.put(DELETE_RESUME_PAPERS_VALUE, "删除简历发表论文");
		map.put(UPDATE_RESUME_PAPERS_VALUE, "修改简历发表论文");
		map.put(ADD_RESUME_BOOK_VALUE, "增加简历所编书");
		map.put(DELETE_RESUME_BOOK_VALUE, "删除简历所编书");
		map.put(UPDATE_RESUME_BOOK_VALUE, "修改简历所编书");
		map.put(ADD_RESUME_CLUB_VALUE, " 增加简历社团经历");
		map.put(DELETE_RESUME_CLUB_VALUE, "删除简历社团经历");
		map.put(UPDATE_RESUME_CLUB_VALUE, "修改简历社团经历");
		map.put(ADD_RESUME_EXERCISE_VALUE, "增加简历活动大赛");
		map.put(DELETE_RESUME_EXERCISE_VALUE, "删除简历活动大赛");
		map.put(UPDATE_RESUME_EXERCISE_VALUE, "修改简历活动大赛");
		map.put(ADD_RESUME_LABORATORYE_VALUE, "增加简历实验室");
		map.put(DELETE_RESUME_LABORATORY_VALUE, "删除简历实验室");
		map.put(UPDATE_RESUME_LABORATORY_VALUE, "修改简历实验室");
		map.put(ADD_RESUME_INTENTION_VALUE, "增加简历意向企业");
		map.put(DELETE_RESUME_INTENTION_VALUE, "删除简历意向企业");
		map.put(UPDATE_RESUME_INTENTION_VALUE, "修改简历意向企业");
		map.put(ADD_RESUME_LOAD_VALUE, "增加简历附件简历");
		map.put(DELETE_RESUME_UPLOAD_VALUE, "删除简历附件简历");
		map.put(UPDATE_RESUME_UPLOAD_VALUE, "修改简历附件简历");
		
		map.put(ADD_LABEL_VALUE, "增加标签");
		map.put(UPDATE_LABEL_VALUE, "修改标签");
		map.put(DELETE_LABEL_VALUE, "删除标签");

		map.put(SEARCH_COMPLEX_VALUE, "搜索综合");
		map.put(SEARCH_POSITION_VALUE, "搜索职位");
		map.put(SEARCH_COMPANY_VALUE, "搜索公司");
		map.put(SEARCH_STUDENT_VALUE, "搜索学生");
		map.put(SEARCH_SCHOOL_VALUE, "搜索学校");

		map.put(ADD_MATCH_TEMPLATE_VALUE, "增加匹配模板");
		map.put(UPDATE_MATCH_TEMPLATE_VALUE, "更新匹配模板");
		map.put(DELETE_MATCH_TEMPLATE_VALUE, "删除匹配模板");
		map.put(ADD_MATCH_TEMPLATE_POSITION_DETAIL_VALUE, "增加匹配模板职位关系详细");
		map.put(UPDATE_MATCH_TEMPLATE_POSITION_DETAIL_VALUE, "更新匹配模板职位关系详细");
		map.put(DELETE_MATCH_TEMPLATE_POSITION_DETAIL_VALUE, "删除匹配模板职位关系详细");

		map.put(ADD_MATCH_STANDAR_VALUE, "增加匹配标准");
		map.put(UPDATE_MATCH_STANDAR_VALUE, "更新匹配标准");
		map.put(DELETE_MATCH_STANDAR_VALUE, "删除匹配标准");
		
		map.put(RECUITMENT_DELIVERY_INITIAL_PASS, "企业用户简历初筛-通过");
		map.put(RECUITMENT_DELIVERY_INITIAL_NOPASS, "企业用户简历初筛-不通过");
		map.put(RECUITMENT_DELIVERY_INITIAL_UNDETERMINED, "企业用户简历初筛-待定");
		map.put(RECUITMENT_DELIVERY_INITIAL_UNTREATED_READ, "企业用户简历初筛-已读");
		
		map.put(RECUITMENT_DELIVERY_TO_WRITTEN, "企业用户将简历通过到笔试");
		map.put(RECUITMENT_DELIVERY_WRITTEN_PASS, "企业用户简历笔试-通过");
		map.put(RECUITMENT_DELIVERY_WRITTEN_NOPASS, "企业用户简历笔试-不通过");
		map.put(RECUITMENT_DELIVERY_WRITTEN_UNDETERMINED, "企业用户简历笔试-待定");
		map.put(RECUITMENT_DELIVERY_WRITTEN_ARRANGE, "企业用户简历安排笔试");
		map.put(RECUITMENT_DELIVERY_WRITTEN_ARRANGE_CANCEL, "企业用户简历安排笔试取消");
		
		map.put(RECUITMENT_DELIVERY_TO_INTERVIEW, "企业用户将简历通过到面试");
		map.put(RECUITMENT_DELIVERY_INTERVIEW_PASS, "企业用户简历面试-通过");
		map.put(RECUITMENT_DELIVERY_INTERVIEW_NOPASS, "企业用户简历面试-未处理-不通过");
		map.put(RECUITMENT_DELIVERY_INTERVIEW_UNDETERMINED, "企业用户简历面试-待定");
		map.put(RECUITMENT_DELIVERY_INTERVIEW_ARRANGE, "企业用户简历安排面试");
		map.put(RECUITMENT_DELIVERY_WRITTEN_ARRANGE_CANCEL, "企业用户简历安排笔试取消");
		
		map.put(RECUITMENT_DELIVERY_TO_OFFER, "企业用户将简历通过到OFFER");
		map.put(RECUITMENT_DELIVERY_OFFER_SEND, "企业用户将简历发送录用函");
		map.put(RECUITMENT_DELIVERY_OFFER_NONEED_SEND, "企业用户将简历免发录用函");
		map.put(RECUITMENT_DELIVERY_OFFER_SEND_CANCEL, "企业用户将简历发送录用函取消");
		
		map.put(OTHER_SCAN_AND_CREATE_TASK_SCHEDULE, "扫描表并生成任务调度");
	}
	
	/**
	 * @Definition: 通过操作value获取操作中文名
	 * @author: chenyongqiang
	 * @Date: 2015年6月23日
	 * @param operValue
	 * @return
	 */
	public static String getName(Integer operValue) {
		return map.get(operValue);
	}
	
	public   static  Map<Integer,String>    getAllOperValue(){
		return  map;
	}
}
