package com.hejia.dataAnalysis.module.account.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * Role entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role", schema = "public")
public class Role implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5948039757744265820L;
	private Integer roleId;
	private String name;
	private String value;
	private Integer type;
	private Integer status;
	private String description;
	private Date createDate;
	private Date modifyDate;
	private Integer createId;
	private Integer modifyId;

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public Role(Integer roleId, String name, String value, Integer type,
			Integer status, String description, Date createDate, Date modifyDate,
			Integer createId, Integer modifyId) {
		this.roleId = roleId;
		this.name = name;
		this.value = value;
		this.type = type;
		this.status = status;
		this.description = description;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.createId = createId;
		this.modifyId = modifyId;
	}

	// Property accessors
	@Id
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value", length = 100)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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

}