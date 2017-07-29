package com.hejia.dataAnalysis.module.recruitment.domain.lagou;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月22日
 * @version: 1.0
 */
@Document(collection="position")
public class Position implements BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7679626772320059236L;
	
	@Id
	private String _id;
	private String financeStage;
	private Integer publisherId;
	private String id;
	private String industryField;
	private String companyLogo;
	private String imState;
	private String district;
	private Integer companyId;
	private String[] industryLables;
	private String secondType;
	@Indexed
	private String companyShortName;
	private String explain;
	private String[] companyLabelList;
	private Integer score;
	private String jobNature;
	private String salary;
	private String firstType;
	private String companyFullName;
	private String workYear;
	private Integer positionId;
	private String gradeDescription;
	private String[] positionLables;
	private Integer deliver;
	@Indexed
	private String positionName;
	private String companySize;
	private Integer adWord;
	private String keyword;
	private String businessZones;
	private String education;
	private String plus;
	private Integer appShow;
	private String formatCreateTime;
	private String lastLogin;
	private String positionAdvantage;
	private String city;
	private String createTime;
	private String promotionScoreExplain;
	private Integer approve;
	private Integer pcShow;
	
	
	/** default constructor */
	public Position() {
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getFinanceStage() {
		return financeStage;
	}
	public void setFinanceStage(String financeStage) {
		this.financeStage = financeStage;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndustryField() {
		return industryField;
	}
	public void setIndustryField(String industryField) {
		this.industryField = industryField;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public String getImState() {
		return imState;
	}
	public void setImState(String imState) {
		this.imState = imState;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String[] getIndustryLables() {
		return industryLables;
	}
	public void setIndustryLables(String[] industryLables) {
		this.industryLables = industryLables;
	}
	public String getSecondType() {
		return secondType;
	}
	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}
	public String getCompanyShortName() {
		return companyShortName;
	}
	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String[] getCompanyLabelList() {
		return companyLabelList;
	}
	public void setCompanyLabelList(String[] companyLabelList) {
		this.companyLabelList = companyLabelList;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getJobNature() {
		return jobNature;
	}
	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getFirstType() {
		return firstType;
	}
	public void setFirstType(String firstType) {
		this.firstType = firstType;
	}
	public String getCompanyFullName() {
		return companyFullName;
	}
	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public String getGradeDescription() {
		return gradeDescription;
	}
	public void setGradeDescription(String gradeDescription) {
		this.gradeDescription = gradeDescription;
	}
	public String[] getPositionLables() {
		return positionLables;
	}
	public void setPositionLables(String[] positionLables) {
		this.positionLables = positionLables;
	}
	public Integer getDeliver() {
		return deliver;
	}
	public void setDeliver(Integer deliver) {
		this.deliver = deliver;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getCompanySize() {
		return companySize;
	}
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	public Integer getAdWord() {
		return adWord;
	}
	public void setAdWord(Integer adWord) {
		this.adWord = adWord;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getBusinessZones() {
		return businessZones;
	}
	public void setBusinessZones(String businessZones) {
		this.businessZones = businessZones;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getPlus() {
		return plus;
	}
	public void setPlus(String plus) {
		this.plus = plus;
	}
	public Integer getAppShow() {
		return appShow;
	}
	public void setAppShow(Integer appShow) {
		this.appShow = appShow;
	}
	public String getFormatCreateTime() {
		return formatCreateTime;
	}
	public void setFormatCreateTime(String formatCreateTime) {
		this.formatCreateTime = formatCreateTime;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getPositionAdvantage() {
		return positionAdvantage;
	}
	public void setPositionAdvantage(String positionAdvantage) {
		this.positionAdvantage = positionAdvantage;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPromotionScoreExplain() {
		return promotionScoreExplain;
	}
	public void setPromotionScoreExplain(String promotionScoreExplain) {
		this.promotionScoreExplain = promotionScoreExplain;
	}
	public Integer getApprove() {
		return approve;
	}
	public void setApprove(Integer approve) {
		this.approve = approve;
	}
	public Integer getPcShow() {
		return pcShow;
	}
	public void setPcShow(Integer pcShow) {
		this.pcShow = pcShow;
	}
	
}
