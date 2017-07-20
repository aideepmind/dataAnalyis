package com.hejia.dataAnalysis.module.account.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_account", schema = "public")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Account implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7222174099536804202L;
	private Integer accId;
	private String loginName;
	private String pwd;
	private String salt;
	private String nickname;
	private String email;
	private String mobile;
	private String headImg;
	private Integer status;
	private Integer source;
	private Integer type;
	private Date createDate;
	private Date modifyDate;
	private Integer activateStatus; // 激活状态 0未激活 1已激活
	private String realName;
	private Integer parentId;
	private Integer unionId; // 所有的主从账号共同使用的id
	private Integer createId;
	private Integer modifyId;
	private Date startDate; // 账号有效期开始时间
	private Date endDate; // 账号有效期结束时间

	@Transient
	private String accessToken;

	/**
	 * 用户状态
	 */
	public static final int STATUS_NORMAL = 1; // 正常
	public static final int STATUS_DISABLE = 2; // 禁用
	public static final int STATUS_SILENCE = 3; // 禁言
	public static final int STATUS_INACTIVE = 4; // 未激活
	public static final int STATUS_DELETED = 5; // 被删除
	public static final int STATUS_NOT_PERFECT = 6; // 未完善
	public static final int STATUS_AUDITING = 7; // 审核中

	/**
	 * 账号类型
	 */
	public static final int TYPE_ADMIN = 1; // 管理员
	public static final int TYPE_FREE = 2; // 游离于各个平台共享某部分信息的用户，不受限于平台的异同-chenyq
	public static final int ID_SYSTEM = -1; // 系统id

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(Integer accId) {
		this.accId = accId;
	}

	/** full constructor */
	public Account(Integer accId, String loginName, String pwd, String salt,
			String nickname, String email, String mobile, String headImg,
			Integer status, Integer source, Integer type, Date createDate,
			Date modifyDate, Integer activateStatus, String realName,
			Integer parentId, Integer unionId, Integer createId,
			Integer modifyId, Date startDate, Date endDate) {
		this.accId = accId;
		this.loginName = loginName;
		this.pwd = pwd;
		this.salt = salt;
		this.nickname = nickname;
		this.email = email;
		this.mobile = mobile;
		this.headImg = headImg;
		this.status = status;
		this.source = source;
		this.type = type;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.activateStatus = activateStatus;
		this.realName = realName;
		this.parentId = parentId;
		this.unionId = unionId;
		this.createId = createId;
		this.modifyId = modifyId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// Property accessors
	@Id
	@Column(name = "acc_id", unique = true, nullable = false)
	@SequenceGenerator(name = "accountSeqGenerator", sequenceName = "t_account_seq_id", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSeqGenerator")
	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	@Column(name = "login_name", length = 100)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "pwd", length = 100)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "salt", length = 50)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "nickname", length = 100)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile", length = 30)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "head_img")
	public String getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "source")
	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 29)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date", length = 29)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "activate_status")
	public Integer getActivateStatus() {
		return this.activateStatus;
	}

	public void setActivateStatus(Integer activateStatus) {
		this.activateStatus = activateStatus;
	}

	@Column(name = "real_name", length = 100)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "union_id")
	public Integer getUnionId() {
		return this.unionId;
	}

	public void setUnionId(Integer unionId) {
		this.unionId = unionId;
	}

	@Column(name = "create_id")
	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	@Column(name = "modify_id")
	public Integer getModifyId() {
		return this.modifyId;
	}

	public void setModifyId(Integer modifyId) {
		this.modifyId = modifyId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", length = 29)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", length = 29)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Transient
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}