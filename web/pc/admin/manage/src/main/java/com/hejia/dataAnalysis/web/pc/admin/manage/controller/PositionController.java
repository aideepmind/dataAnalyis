package com.hejia.dataAnalysis.web.pc.admin.manage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.recruitment.service.lagou.PositionService;
import com.hejia.dataAnalysis.module.search.service.CompanySearchService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月22日
 * @version: 1.0
 */
@Controller
@RequestMapping("/position")
public class PositionController extends BaseController {
	
	@Autowired
	private PositionService positionSerivce;
	@Autowired
	private CompanySearchService companySearchService;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/find")
	@ResponseBody
	public ResponsePojo find(HttpServletRequest request, HttpServletResponse response) {
		RequestArg ra = new RequestArg();
		ra.set("kw", "dashuju");
		Pageable p = new PageRequest(0, 15);
		Page<Map> page = companySearchService.searchCompany(ra, p);
		System.out.println(page.getContent().size());
		System.out.println(page.getContent());
		return null;
	}
}
