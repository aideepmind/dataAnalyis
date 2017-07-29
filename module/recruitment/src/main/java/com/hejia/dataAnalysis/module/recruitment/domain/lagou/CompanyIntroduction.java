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
@Document(collection = "company_introduction")
public class CompanyIntroduction implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4931342604062723663L;

	@Id
	private String _id;
	@Indexed
	private Integer companyId;
	private String id;
	private String companyProfile;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

}
