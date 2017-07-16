package com.hejia.dataAnalysis.module.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * GroupRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_group_role", schema = "public")
public class GroupRole implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5859815476479404799L;
	private Integer id;
	private Integer groupId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public GroupRole() {
	}

	/** minimal constructor */
	public GroupRole(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public GroupRole(Integer id, Integer groupId, Integer roleId) {
		this.id = id;
		this.groupId = groupId;
		this.roleId = roleId;
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

	@Column(name = "group_id")
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "role_id")
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}