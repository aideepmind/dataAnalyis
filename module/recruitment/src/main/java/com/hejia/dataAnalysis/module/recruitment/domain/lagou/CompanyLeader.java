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
@Document(collection = "company_leader")
public class CompanyLeader implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5651518520754451462L;

	@Id
	private String _id;
	@Indexed
	private Integer companyId;
	private String id;
	private String name;
	@Indexed
	private String position;
	private String photo;
	private String weibonickname;
	private String cyclopediaUrl;
	private String remark;
	private String weibo;
	private String createtime;
	private Integer isenable;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getWeibonickname() {
		return weibonickname;
	}

	public void setWeibonickname(String weibonickname) {
		this.weibonickname = weibonickname;
	}

	public String getCyclopediaUrl() {
		return cyclopediaUrl;
	}

	public void setCyclopediaUrl(String cyclopediaUrl) {
		this.cyclopediaUrl = cyclopediaUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getIsenable() {
		return isenable;
	}

	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
	}
	
}
