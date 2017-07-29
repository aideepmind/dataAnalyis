package com.hejia.dataAnalysis.module.recruitment.domain.lagou;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hejia.dataAnalysis.module.common.domain.BaseDomain;

/**
 * @Description:
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
@Document(collection = "company_coreInfo")
public class CompanyCoreInfo implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5262551311407653210L;

	@Id
	private String _id;
	@Indexed
	private Integer companyId;
	private String id;
	@Indexed
	private String companyName;
	@Indexed
	private String companyShortName;
	private String companyIntroduce;
	private String logo;
	private String companyUrl;
	private Integer approve;
	private Boolean isFirst;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyShortName() {
		return companyShortName;
	}

	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}

	public String getCompanyIntroduce() {
		return companyIntroduce;
	}

	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public Integer getApprove() {
		return approve;
	}

	public void setApprove(Integer approve) {
		this.approve = approve;
	}

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}
}
