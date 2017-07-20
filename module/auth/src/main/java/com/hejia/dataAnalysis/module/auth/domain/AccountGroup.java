package com.hejia.dataAnalysis.module.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * AccountGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_account_group", schema = "public")
public class AccountGroup implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 307425850215698048L;
	private Integer id;
	private Integer accId;
	private Integer groupId;
	private Integer status;

	// Constructors

	/** default constructor */
	public AccountGroup() {
	}

	/** minimal constructor */
	public AccountGroup(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AccountGroup(Integer id, Integer accId, Integer groupId,
			Integer status) {
		this.id = id;
		this.accId = accId;
		this.groupId = groupId;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "acc_id")
	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	@Column(name = "group_id")
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}