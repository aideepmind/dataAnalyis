package com.hejia.dataAnalysis.module.auth.domain;

import java.util.Date;
import java.util.List;

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
	private static final long serialVersionUID = -1762708522192425777L;
	private Integer roleId; // 主键
	private String name; // 名称
	private String value; // shiro中使用，类似Permission中的URL，访问比较大的权限控制，更细的权限使用Permission中URL的前缀匹配模式。可以应用在用户注册需要完成某些步骤之后才能访问其他资源
	private Integer type; // 功能1、数据2
	private Integer status; // 禁用0、正常1
	private String description; // 描述
	private Integer createId; // 创建人id
	private Integer modifyId; // 修改人id
	private Date createDate; // 创建时间
	private Date modifyDate; // 修改时间

	@Transient
	private List<Group> gList; // 组集合
	@Transient
	private List<Permission> pList; // 权限集合

	/**
	 * 一些预设的角色值
	 */
	public static final String VALUE_ADMIN = "admin"; // 后台角色-超级管理员

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public Role(Integer roleId, String name, Integer type, Integer status,
			String description, Date createDate, Date modifyDate) {
		this.roleId = roleId;
		this.name = name;
		this.type = type;
		this.status = status;
		this.description = description;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	// Property accessors
	@Id
	@Column(name = "role_id", unique = true, nullable = false)
	@SequenceGenerator(name = "roleSeqGenerator", sequenceName = "t_role_seq_id", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeqGenerator")
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
		return value;
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
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "create_id")
	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	@Column(name = "modify_id")
	public Integer getModifyId() {
		return modifyId;
	}

	public void setModifyId(Integer modifyId) {
		this.modifyId = modifyId;
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

	/*
	 * @ManyToMany(fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "t_group_role", schema = "public", joinColumns =
	 * {@JoinColumn(name = "role_id", nullable = false, updatable = false)},
	 * inverseJoinColumns = {@JoinColumn(name = "group_id", nullable = false,
	 * updatable = false)})
	 * 
	 * @Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	 */
	@Transient
	public List<Group> getgList() {
		return gList;
	}

	public void setgList(List<Group> gList) {
		this.gList = gList;
	}

	/*
	 * @ManyToMany(fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "t_role_permission", schema = "public", joinColumns =
	 * {@JoinColumn(name = "role_id", nullable = false, updatable = false)},
	 * inverseJoinColumns = {@JoinColumn(name = "perms_id", nullable = false,
	 * updatable = false)})
	 * 
	 * @Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	 */
	@Transient
	public List<Permission> getpList() {
		return pList;
	}

	public void setpList(List<Permission> pList) {
		this.pList = pList;
	}

}