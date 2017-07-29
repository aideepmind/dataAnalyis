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
@Document(collection = "company_history")
public class CompanyHistory implements BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5601075311299751404L;

	@Id
	private String _id;
	@Indexed
	private Integer companyId;
	private String id;
	private String year;
	private String month;
	private String day;
	private String eventdate;
	private String financeStage;
	private String type;
	private String eventtype;
	private String eventname;
	private String eventprofile;
	private String eventurl;

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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getEventdate() {
		return eventdate;
	}

	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}

	public String getFinanceStage() {
		return financeStage;
	}

	public void setFinanceStage(String financeStage) {
		this.financeStage = financeStage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getEventprofile() {
		return eventprofile;
	}

	public void setEventprofile(String eventprofile) {
		this.eventprofile = eventprofile;
	}

	public String getEventurl() {
		return eventurl;
	}

	public void setEventurl(String eventurl) {
		this.eventurl = eventurl;
	}

}
