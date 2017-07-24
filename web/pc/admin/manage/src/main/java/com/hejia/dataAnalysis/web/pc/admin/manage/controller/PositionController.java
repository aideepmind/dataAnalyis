package com.hejia.dataAnalysis.web.pc.admin.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.recruitment.domain.Position;
import com.hejia.dataAnalysis.module.recruitment.service.PositionSerivce;

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
	private PositionSerivce service;
	
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
		ResponsePojo rp = new ResponsePojo();
		Position position = new Position();
		position.setPositionName("工程师");
		position.setCompanyShortName("外迪");
		position.setCity("北京");
		position.setApprove(1);
		List<Position> pList = service.find(position);
		rp.setMessage(pList);
		System.out.println(rp.toJson());
		return rp;
	}
}
