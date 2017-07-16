package com.hejia.dataAnalysis.module.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * RolePermission entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role_permission", schema = "public")
public class RolePermission implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4129804316048139558L;
	private Integer id;
	private Integer roleId;
	private Integer permsId;

	// Constructors

	/** default constructor */
	public RolePermission() {
	}

	/** minimal constructor */
	public RolePermission(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public RolePermission(Integer id, Integer roleId, Integer permsId) {
		this.id = id;
		this.roleId = roleId;
		this.permsId = permsId;
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

	@Column(name = "role_id")
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "perms_id")
	public Integer getPermsId() {
		return this.permsId;
	}

	public void setPermsId(Integer permsId) {
		this.permsId = permsId;
	}

}