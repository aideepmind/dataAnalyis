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
 * AccountProfile entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_account_profile", schema = "public")
public class AccountProfile implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7451459339452480261L;
	// Fields

	private Integer accProId;
	private Integer accId;
	private Integer type;
	private String data;
	private Date createDate;
	private Date modifyDate;

	// Constructors

	/** default constructor */
	public AccountProfile() {
	}

	/** minimal constructor */
	public AccountProfile(Integer accProId) {
		this.accProId = accProId;
	}

	/** full constructor */
	public AccountProfile(Integer accProId, Integer accId, Integer type,
			String data, Date createDate, Date modifyDate) {
		this.accProId = accProId;
		this.accId = accId;
		this.type = type;
		this.data = data;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	// Property accessors
	@Id
	@Column(name = "acc_pro_id", unique = true, nullable = false)
	public Integer getAccProId() {
		return this.accProId;
	}

	public void setAccProId(Integer accProId) {
		this.accProId = accProId;
	}

	@Column(name = "acc_id")
	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "data")
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 35)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date", length = 35)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}