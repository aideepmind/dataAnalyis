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
@Document(collection = "company_product")
public class CompanyProduct implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -630951084226825504L;

	@Id
	private String _id;
	@Indexed
	private Integer companyId;
	private String product;
	private String producturl;
	private String productpicurl;
	private String productprofile;
	private String producttype;

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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProducturl() {
		return producturl;
	}

	public void setProducturl(String producturl) {
		this.producturl = producturl;
	}

	public String getProductpicurl() {
		return productpicurl;
	}

	public void setProductpicurl(String productpicurl) {
		this.productpicurl = productpicurl;
	}

	public String getProductprofile() {
		return productprofile;
	}

	public void setProductprofile(String productprofile) {
		this.productprofile = productprofile;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

}
