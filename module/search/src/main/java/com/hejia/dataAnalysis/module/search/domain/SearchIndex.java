package com.hejia.dataAnalysis.module.search.domain;

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

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * SearchIndex entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_search_index", schema = "public")
public class SearchIndex implements BaseDomain {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 261261616611942977L;
	private Integer siId;
	private Integer type;
	private String name;
	private String content;
	private String subType;
	private Integer status;
	private String other;
	private Integer boost;
	private Date createDate;
	private Date modifyDate;
	
	/**
	 * 类型
	 */
	public static final int TYPE_POSITION = 1;//职位相关的索引类型
	public static final int TYPE_COMPANY = 2;//公司相关的索引类型
	
	
	// Constructors

	/** default constructor */
	public SearchIndex() {
	}

	/** minimal constructor */
	public SearchIndex(Integer siId) {
		this.siId = siId;
	}

	/** full constructor */
	public SearchIndex(Integer siId, Integer type, String name, String content,
			Date createDate, Date modifyDate) {
		this.siId = siId;
		this.type = type;
		this.name = name;
		this.content = content;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public SearchIndex(Integer type, String name, String content,
			String subType, Date createDate, Date modifyDate) {
		super();
		this.type = type;
		this.name = name;
		this.content = content;
		this.subType = subType;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public SearchIndex(Integer type, String name, String content,
			String subType, Integer status, Date createDate, Date modifyDate) {
		super();
		this.type = type;
		this.name = name;
		this.content = content;
		this.subType = subType;
		this.status = status;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	// Property accessors
	@Id
	@Column(name = "si_id", unique = true, nullable = false)
	@SequenceGenerator(name="searchIndexSeqGenerator", sequenceName="t_search_index_seq_id", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="searchIndexSeqGenerator")
	public Integer getSiId() {
		return this.siId;
	}

	public void setSiId(Integer siId) {
		this.siId = siId;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "sub_type")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "other")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Column(name = "boost")
	public Integer getBoost() {
		return boost;
	}

	public void setBoost(Integer boost) {
		this.boost = boost;
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