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
 * Permission entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_permission", schema = "public")
public class Permission implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4372150757548888584L;
	private Integer permsId;
	private String url;
	private Integer dataType;
	private String data;
	private String name;
	private String imgUrl;
	private Integer parentId;
	private Integer seq;
	private Integer status;
	private String descr;
	private Integer createId;
	private Integer modifyId;
	private Date createDate;
	private Date modifyDate;

	// Constructors

	/** default constructor */
	public Permission() {
	}

	/** minimal constructor */
	public Permission(Integer permsId) {
		this.permsId = permsId;
	}

	/** full constructor */
	public Permission(Integer permsId, String url, Integer dataType,
			String data, String name, String imgUrl, Integer parentId,
			Integer seq, Integer status, String descr, Integer createId,
			Integer modifyId, Date createDate, Date modifyDate) {
		this.permsId = permsId;
		this.url = url;
		this.dataType = dataType;
		this.data = data;
		this.name = name;
		this.imgUrl = imgUrl;
		this.parentId = parentId;
		this.seq = seq;
		this.status = status;
		this.descr = descr;
		this.createId = createId;
		this.modifyId = modifyId;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	// Property accessors
	@Id
	@Column(name = "perms_id", unique = true, nullable = false)
	public Integer getPermsId() {
		return this.permsId;
	}

	public void setPermsId(Integer permsId) {
		this.permsId = permsId;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "data_type")
	public Integer getDataType() {
		return this.dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	@Column(name = "data")
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "img_url")
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "seq")
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "descr")
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
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

}