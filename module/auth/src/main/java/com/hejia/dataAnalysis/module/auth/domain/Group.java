package com.hejia.dataAnalysis.module.auth.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.hejia.dataAnalysis.module.account.domain.Account;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * Group entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_group", schema = "public")
public class Group implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840362055215181747L;
	private Integer groupId; // 主键
	private String name; // 名称
	private Integer status; // 禁用0、正常1
	private String description; // 描述
	private Integer createId; // 创建人id
	private Integer modifyId; // 修改人id
	private Date createDate; // 创建时间
	private Date modifyDate; // 修改时间

	@Transient
	private List<Account> aList; // 账户集合
	
	/**
	 * 组对于的数据库id
	 */
	public static final int ID_ = 0; // 
	
	// Constructors

	/** default constructor */
	public Group() {
	}

	/** minimal constructor */
	public Group(Integer groupId) {
		this.groupId = groupId;
	}

	/** full constructor */
	public Group(Integer groupId, String name, Integer status,
			String description, Date createDate, Date modifyDate,
			Integer createId, Integer modifyId) {
		this.groupId = groupId;
		this.name = name;
		this.status = status;
		this.description = description;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.createId = createId;
		this.modifyId = modifyId;
	}

	// Property accessors
	@Id
	@Column(name = "group_id", unique = true, nullable = false)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	@Transient
	public List<Account> getaList() {
		return aList;
	}

	public void setaList(List<Account> aList) {
		this.aList = aList;
	}
}