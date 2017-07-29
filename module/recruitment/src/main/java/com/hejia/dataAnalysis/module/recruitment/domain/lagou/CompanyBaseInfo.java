package com.hejia.dataAnalysis.module.recruitment.domain.lagou;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * @Description:
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
@Document(collection = "company_baseInfo")
public class CompanyBaseInfo implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8201946413803597745L;

	@Id
	private String _id;
	@Indexed
	private Integer companyId;
	private String id;
	private String companySize;
	@Indexed
	private String industryField;
	@Indexed
//	@TextIndexed // 全文检索
	private String city;
	private String financeStage;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getIndustryField() {
		return industryField;
	}

	public void setIndustryField(String industryField) {
		this.industryField = industryField;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFinanceStage() {
		return financeStage;
	}

	public void setFinanceStage(String financeStage) {
		this.financeStage = financeStage;
	}

}
