package com.hejia.dataAnalysis.module.report.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.common.domain.ResponsePojo;
import com.hejia.dataAnalysis.module.common.domain.chart.Option;
import com.hejia.dataAnalysis.module.common.domain.chart.Series;
import com.hejia.dataAnalysis.module.common.domain.chart.SeriesData;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.recruitment.dao.impl.CompanyBaseInfoDaoImpl;
import com.hejia.dataAnalysis.module.report.service.CompanyBaseReportService;
import com.mongodb.BasicDBObject;

/**
 * @Description: 公司基本报表业务接口实现
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
@Service("companyBaseReportService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class CompanyBaseReportServiceImpl implements CompanyBaseReportService {
	
	@Autowired
	private CompanyBaseInfoDaoImpl companyBaseInfoDaoImpl;
	
	public ResponsePojo BigDataRequirementDivisionByIndustry() throws ServiceException {
		ResponsePojo rp = new ResponsePojo(Boolean.TRUE);
		int showSize = 10;
		try {
			List<BasicDBObject> basicDBObjects = companyBaseInfoDaoImpl.groupByIndustryField2();
//			System.out.println(basicDBObjects);
			basicDBObjects = secondHandle(basicDBObjects);
			Series series = new Series(Series.TYPE_PIE, "公司数量");
			SeriesData otherSeriesData = new SeriesData("其他", 0);
			for (int i = 0; i < basicDBObjects.size(); i++) {
				BasicDBObject basicDBObject = basicDBObjects.get(i);
				if (i < showSize) {
					series.add(new SeriesData(basicDBObject.getString("_id"), basicDBObject.get("count")));
				} else {
					otherSeriesData.setValue(basicDBObject.getInt("count") + ((Integer) otherSeriesData.getValue()));
				}
			}
			series.add(otherSeriesData);
			Option option = new Option();
			option.addSeries(series);
			rp.setMessage(option);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return rp;
	}
	
	/**
	 * @Definition: 二次处理
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @param basicDBObjects
	 * @return
	 */
	private List<BasicDBObject> secondHandle(List<BasicDBObject> basicDBObjects) {
		Map<String, BasicDBObject> temp = new HashMap<String, BasicDBObject>();
		for (int i = 0; i < basicDBObjects.size(); i++) {
			BasicDBObject basicDBObject = basicDBObjects.get(i);
			// 去掉“数据服务”
			String key = basicDBObject.getString("_id");
			String newKey = key;
			if (!"数据服务".equals(key)) {
				newKey = key.replace("数据服务", "").replace("、", "").replace(",", "");
			}
			// 合并
			BasicDBObject value = temp.get(newKey);
			if (value == null) {
				basicDBObject.put("_id", newKey);
				value = basicDBObject;
			} else {
				value.put("count", value.getInt("count") + basicDBObject.getInt("count"));
			}
			temp.put(newKey, value);
		}
		// 转换成List
		List<BasicDBObject> basicDBObjects2 = new ArrayList<BasicDBObject>();
		Iterator<String> it = temp.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			basicDBObjects2.add(temp.get(key));
		}
		// 排序，也可以在页面排序
		Collections.sort(basicDBObjects2, new Comparator<BasicDBObject>() {
			public int compare(BasicDBObject o1, BasicDBObject o2) {
				return o2.getInt("count") - o1.getInt("count");
			}
		});
		return basicDBObjects2;
	}
	
	public ResponsePojo BigDataRequirementDivisionByCompanySize() throws ServiceException {
		ResponsePojo rp = new ResponsePojo(Boolean.TRUE);
		try {
			List<BasicDBObject> basicDBObjects = companyBaseInfoDaoImpl.groupByCompanySize();
			basicDBObjects = secondHandle(basicDBObjects);
			Series series = new Series(Series.TYPE_PIE, "公司数量");
			for (int i = 0; i < basicDBObjects.size(); i++) {
				BasicDBObject basicDBObject = basicDBObjects.get(i);
				series.add(new SeriesData(basicDBObject.getString("_id"), basicDBObject.get("count")));
			}
			Option option = new Option();
			option.addSeries(series);
			rp.setMessage(option);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return rp;
	}
}
