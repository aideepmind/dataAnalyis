package com.hejia.dataAnalysis.web.pc.admin.manage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hejia.dataAnalysis.module.auth.domain.Permission;
import com.hejia.dataAnalysis.module.account.service.AccountService;
import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.report.service.CompanyBaseReportService;

/**
 * @Description: 控制台
 * @author: chenyongqiang
 * @Date: 2017年7月21日
 * @version: 1.0
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	private static final Logger log = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	private CompanyBaseReportService companyBaseReportService;
	
	/**
	 * @Definition: 主要是登录之后跳转用，防止刷新提交表单
	 * @author: chenyongqiang
	 * @Date: 2015年4月21日
	 * @return
	 */
	@RequestMapping(value = "/")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView("/dashboard/dashboard");
		return mv;
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @return
	 */
	@RequestMapping(value = "/getBigDataRequirementDivisionByIndustry", method = RequestMethod.GET)
	@ResponseBody
	public ResponsePojo getBigDataRequirementDivisionByIndustry() {
		ResponsePojo rp = companyBaseReportService.BigDataRequirementDivisionByIndustry();
		return rp;
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @return
	 */
	@RequestMapping(value = "/BigDataRequirementDivisionByCompanySize", method = RequestMethod.GET)
	@ResponseBody
	public ResponsePojo BigDataRequirementDivisionByCompanySize() {
		ResponsePojo rp = companyBaseReportService.BigDataRequirementDivisionByCompanySize();
		return rp;
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @return
	 */
	@RequestMapping(value = "/BigDataRequirementDivisionByCity", method = RequestMethod.GET)
	@ResponseBody
	public ResponsePojo BigDataRequirementDivisionByCity() {
		ResponsePojo rp = companyBaseReportService.BigDataRequirementDivisionByCity();
		return rp;
	}
	
	/**
	 * @Definition: 获取用户的权限
	 * @author: chenyongqiang
	 * @Date: 2015年4月20日
	 * @return
	 */
	@RequestMapping(value = "/findFrameLeft")
	public ModelAndView findFrameLeft() {
		ModelAndView mv = new ModelAndView("frame_left");
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		//获取session中的权限
		List<Permission> pList = (List<Permission>) session.getAttribute("perms");;
		Boolean isFirstPerms = (Boolean) session.getAttribute("isFirstPerms");
		if (isFirstPerms == null) {//如果是第一次
			if (pList != null && pList.size() > 0) {
				//排序
				Collections.sort(pList, new Comparator<Permission>() {
					public int compare(Permission p1, Permission p2) {
						Integer seq1 = p1.getSeq();
						Integer seq2 = p2.getSeq();
						if (seq1 == null) {
							return -1;
						}
						if (seq2 == null) {
							return 1;
						}
						return seq1.compareTo(seq2);
					}
				});
				//分类
				Map<Integer, List<Permission>> pMap = new HashMap<Integer, List<Permission>>();
				for (int i = 0; i < pList.size(); i++) {
					Permission p = pList.get(i);
					Integer parentId = p.getParentId();
					List<Permission> childList = pMap.get(parentId);
					if (childList == null) {
						childList = new ArrayList<Permission>();
					}
					childList.add(p);
				}
			}
			session.setAttribute("isFirstPerms", false);
		}
		mv.addObject("perms", new ArrayList<Permission>());
		return mv;
	}
}
