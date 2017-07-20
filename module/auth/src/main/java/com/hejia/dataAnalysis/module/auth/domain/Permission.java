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
	private Integer permsId;//主键
	private String name;//名称
	private String url;//URL，依照前缀来判断访问权限，所以controller定义时要规范
	private Integer dataType;//数据类型
	private String data;//数据（shiro中使用），跟URL不能共存，只能选其一，可能是任何数据，最好是json串，例如某些服务的id或者key，总之是特征之类的，跟平台的代码能够对应上的
	private String imgUrl;//图片URL，可能没有，因网站及功能而异
	private Integer parentId;//父类id，平行关系可能没有
	private Integer status;//禁用0、正常1
	private Integer seq;//排序，可能没有，因网站及功能布局（依赖可能在前端，而不在后端生成顺序）而已
	private String descr;//描述
	private Integer createId;//创建人id
	private Integer modifyId;//修改人id
	private Date createDate;//创建时间
	private Date modifyDate;//修改时间
	
	@Transient
	private List<Role> rList;
	
	@Transient
	private List<Permission> children;
	// Constructors

	/** default constructor */
	public Permission() {
	}

	/** minimal constructor */
	public Permission(Integer permsId) {
		this.permsId = permsId;
	}

	/** full constructor */
	public Permission(Integer permsId, String name, Integer status, String descr, Date createDate,
			Date modifyDate) {
		this.permsId = permsId;
		this.name = name;
		this.status = status;
		this.descr = descr;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	// Property accessors
	@Id
	@Column(name = "perms_id", unique = true, nullable = false)
	@SequenceGenerator(name="permissionSeqGenerator", sequenceName="t_permission_seq_id", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permissionSeqGenerator")
	public Integer getPermsId() {
		return this.permsId;
	}

	public void setPermsId(Integer permsId) {
		this.permsId = permsId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "data_type")
	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	@Column(name = "data")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Column(name = "img_url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Column(name = "parent_id")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "descr")
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "seq")
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pList")
	@Cascade({CascadeType.SAVE_UPDATE})*/
	@Transient
	public List<Role> getrList() {
		return rList;
	}

	public void setrList(List<Role> rList) {
		this.rList = rList;
	}
	
	@Transient
	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

}