package com.hejia.dataAnalysis.module.auth.domain;

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
 * BlackWhiteList entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_black_white_list", schema = "public")
public class BlackWhiteList implements BaseDomain, java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6299162850002304475L;
	private Integer id;
	private Integer type;
	private Integer functionType;
	private String value;
	/**
	 * 简单版的规则：
	 * s:开始时间，后面加具体时间，例如s:2015-02-01 10:12:11
	 * e:结束时间，后面加具体时间，例如e:2015-03-01
	 * a:所有时间，不需要加任何时间
	 * 时间之间要因为逗号隔开","
	 * 总例子：s:2015-02-01 10:12:11&e:2015-03-01
	 */
	private String limitDate;
	private Date createDate;
	private Date modifyDate;

	public static final int TYPE_BLACK = 0;//黑
	public static final int TYPE_WHITE = 1;//白
	
	public static final int FUNTION_TYPE_GLOBAL_IP = 1;//全局性ip禁用
	public static final int FUNTION_TYPE_SMS = 2;//发送短信
	
	// Constructors

	/** default constructor */
	public BlackWhiteList() {
	}

	/** minimal constructor */
	public BlackWhiteList(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public BlackWhiteList(Integer id, Integer type, Integer functionType,
			String value, String limitDate, Date createDate, Date modifyDate) {
		this.id = id;
		this.type = type;
		this.functionType = functionType;
		this.value = value;
		this.limitDate = limitDate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name="blackWhiteListSeqGenerator", sequenceName="t_black_white_list_seq_id", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="blackWhiteListSeqGenerator")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "function_type")
	public Integer getFunctionType() {
		return this.functionType;
	}

	public void setFunctionType(Integer functionType) {
		this.functionType = functionType;
	}

	@Column(name = "value", length = 30)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "limit_date")
	public String getLimitDate() {
		return this.limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
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