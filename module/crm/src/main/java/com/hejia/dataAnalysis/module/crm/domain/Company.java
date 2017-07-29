package com.hejia.dataAnalysis.module.crm.domain;

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
 * Company entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_company", schema = "public")
public class Company implements BaseDomain {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7770379808515396052L;
	private Integer compId;
	private Integer status;
	private String outterCompId;
	private Integer sourceType;
	private String companyFullName;
	private String companyIntegerName;
	private String companyLogo;
	private String companyUrl;
	private String companyIntegerIntroduce;
	private String companySize;
	private String city;
	private String financeStage;
	private Integer importantLevel;
	private Date createDate;
	private Date modifyDate;
	private Integer createId;
	private Integer modifyId;

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Integer compId) {
		this.compId = compId;
	}

	/** full constructor */
	public Company(Integer compId, Integer status, String outterCompId,
			Integer sourceType, String companyFullName, String companyIntegerName,
			String companyLogo, String companyUrl,
			String companyIntegerIntroduce, String companySize, String city,
			String financeStage, Integer importantLevel, Date createDate,
			Date modifyDate, Integer createId, Integer modifyId) {
		this.compId = compId;
		this.status = status;
		this.outterCompId = outterCompId;
		this.sourceType = sourceType;
		this.companyFullName = companyFullName;
		this.companyIntegerName = companyIntegerName;
		this.companyLogo = companyLogo;
		this.companyUrl = companyUrl;
		this.companyIntegerIntroduce = companyIntegerIntroduce;
		this.companySize = companySize;
		this.city = city;
		this.financeStage = financeStage;
		this.importantLevel = importantLevel;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.createId = createId;
		this.modifyId = modifyId;
	}

	// Property accessors
	@Id
	@Column(name = "comp_id", unique = true, nullable = false)
	@SequenceGenerator(name = "companySeqGenerator", sequenceName = "t_company_seq_id", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companySeqGenerator")
	public Integer getCompId() {
		return this.compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "outter_comp_id")
	public String getOutterCompId() {
		return this.outterCompId;
	}

	public void setOutterCompId(String outterCompId) {
		this.outterCompId = outterCompId;
	}

	@Column(name = "source_type")
	public Integer getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "company_full_name")
	public String getCompanyFullName() {
		return this.companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	@Column(name = "company_short_name")
	public String getCompanyIntegerName() {
		return this.companyIntegerName;
	}

	public void setCompanyIntegerName(String companyIntegerName) {
		this.companyIntegerName = companyIntegerName;
	}

	@Column(name = "company_logo")
	public String getCompanyLogo() {
		return this.companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	@Column(name = "company_url")
	public String getCompanyUrl() {
		return this.companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	@Column(name = "company_short_introduce")
	public String getCompanyIntegerIntroduce() {
		return this.companyIntegerIntroduce;
	}

	public void setCompanyIntegerIntroduce(String companyIntegerIntroduce) {
		this.companyIntegerIntroduce = companyIntegerIntroduce;
	}

	@Column(name = "company_size")
	public String getCompanySize() {
		return this.companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "finance_stage")
	public String getFinanceStage() {
		return this.financeStage;
	}

	public void setFinanceStage(String financeStage) {
		this.financeStage = financeStage;
	}

	@Column(name = "important_level")
	public Integer getImportantLevel() {
		return this.importantLevel;
	}

	public void setImportantLevel(Integer importantLevel) {
		this.importantLevel = importantLevel;
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

}