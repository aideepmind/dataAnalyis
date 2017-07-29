package com.hejia.dataAnalysis.module.account.domain;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;


/**
 * Option entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_option", schema = "public")
public class Option implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1059736553073900633L;
	private Integer optId;
	private Integer type;
	private String name;
	private String value;
	private String majorSub;
	private String minorSub;
	private String otherSub;
	private Integer useTime;
	private Date createDate;
	private Integer parentId;
	private Integer sequence;
	
	private List<Option> children;
	/**
	 * 类型
	 */
	public static final int TYPE_SCHOOL = 1; // 学校类型 
	public static final int TYPE_INTENTION_ENTERPRISE = 2; // 意向企业类型
	public static final int TYPE_PROVINCE = 3; // 生源地类型
	public static final int TYPE_MAJOR = 4; // 专业类型 
	public static final int TYPE_EMAIL_SUFFIX = 5; // 邮箱后缀类型 
	public static final int TYPE_POSITION = 6; // 职位类型 
	public static final int TYPE_POSITION_FOR_SEARCH = 7; // 搜索职位类型 
	public static final int TYPE_POSITION_FOR_SEARCH_LAST_ID = 8; // 搜索职位增量截止id类型 
	public static final int TYPE_COMPANY_FOR_SEARCH = 9; // 搜索公司类型
	public static final int TYPE_COMPANY_FOR_SEARCH_LAST_ID = 10; // 搜索公司增量截止id类型 
	public static final int TYPE_CITY_BY_LETTER = 11; // 搜索城市类型（按字母） 
	
	/**
	 * 院校类型other_sub（仅对type为1的数据适用）
	 */
	public static final int OTHER_SUB_SCHOOL_TYPE_NOT_211_NOT_985 = 0; //既不是211又不是985的院校
	
	public static final int OTHER_SUB_SCHOOL_TYPE_211 = 1; //只是211的院校
	
	public static final int OTHER_SUB_SCHOOL_TYPE_211_985 = 2; //既是211又是985的院校(985院校都是211院校，所以无需单独分一类)
	
	
	// Constructors

	/** default constructor */
	public Option() {
	}

	/** minimal constructor */
	public Option(Integer optId) {
		this.optId = optId;
	}

	/** full constructor */
	public Option(Integer optId, Integer type, String name, String value,
			String majorSub, String minorSub, String otherSub, Integer useTime,
			Date createDate) {
		this.optId = optId;
		this.type = type;
		this.name = name;
		this.value = value;
		this.majorSub = majorSub;
		this.minorSub = minorSub;
		this.otherSub = otherSub;
		this.useTime = useTime;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@Column(name = "opt_id", unique = true, nullable = false)
	@SequenceGenerator(name="optionSeqGenerator", sequenceName="t_option_seq_id", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="optionSeqGenerator")
	public Integer getOptId() {
		return this.optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
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

	@Column(name = "value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "major_sub")
	public String getMajorSub() {
		return this.majorSub;
	}

	public void setMajorSub(String majorSub) {
		this.majorSub = majorSub;
	}

	@Column(name = "minor_sub")
	public String getMinorSub() {
		return this.minorSub;
	}

	public void setMinorSub(String minorSub) {
		this.minorSub = minorSub;
	}

	@Column(name = "other_sub")
	public String getOtherSub() {
		return this.otherSub;
	}

	public void setOtherSub(String otherSub) {
		this.otherSub = otherSub;
	}

	@Column(name = "use_time")
	public Integer getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 29)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "sequence")
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	@Transient
	public List<Option> getChildren() {
		return children;
	}

	public void setChildren(List<Option> children) {
		this.children = children;
	}

}