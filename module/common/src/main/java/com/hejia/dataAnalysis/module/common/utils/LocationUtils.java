package com.hejia.dataAnalysis.module.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @Description: 地理工具类
 * @author: chenyongqiang
 * @Date: 2017年7月27日
 * @version: 1.0
 */
public class LocationUtils {
	
	private final static Map<String, Object> map = new HashMap<String, Object>();
	
	static {
		try {
			Document doc = new SAXReader().read(LocationUtils.class.getClassLoader().getResourceAsStream("location-list.xml"));
			Element rootEle = doc.getRootElement();//root
			List<Element> CountryRegionEleList = rootEle.elements("country-region");
			// 因目前只用到中国的，故只加载中国
			Element first = CountryRegionEleList.get(0);
			List<Element> stateEleList = first.elements();
			List<Map> stateMapList = new ArrayList<Map>();
			for (Element stateEle : stateEleList) {
				Map<String, Object> stateMap = new HashMap<String, Object>();
				List<Element> cityEleList = stateEle.elements();
				List<Map> cityMapList = new ArrayList<Map>();
				for (Element cityEle : cityEleList) {
					Map<String, Object> cityMap = new HashMap<String, Object>();
					cityMap.put("name", cityEle.attributeValue("name"));
					cityMap.put("shortName", removeLastWord(cityEle.attributeValue("name")));
					cityMap.put("list", null);
					cityMap.put(removeLastWord(cityEle.attributeValue("name")), null);
					cityMapList.add(cityMap);
					// 还有一层，具体区域，但这里用不上故不加载
					
				}
				stateMap.put("name", stateEle.attributeValue("name"));
				stateMap.put("shortName", removeLastWord(stateEle.attributeValue("name")));
				stateMap.put("list", cityMapList);
				stateMap.put(removeLastWord(stateEle.attributeValue("name")), cityMapList);
				stateMapList.add(stateMap);
			}
			// 注意，这里因为只加载一个，所以使用这种方式写，如果加载多个国家，可以使用List代替Map，或者去掉两个key： name和list
			map.put("name", first.attributeValue("name"));
			map.put("shortName", removeLastWord(first.attributeValue("name")));
			map.put("list", stateMapList);
			map.put(removeLastWord(first.attributeValue("name")), stateMapList);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Definition: 删除最后一个字，如省、市、县、区等，便于查找，但是有些业务在返回时又需要加上，所以需要重新设计
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @param str
	 * @return
	 */
	private static String removeLastWord(String str) {
		if (str.length() > 2 && (str.lastIndexOf("省") != -1 || str.lastIndexOf("市") != -1 
				|| str.lastIndexOf("县") != -1 || str.lastIndexOf("区") != -1)) {
			return str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	/**
	 * @Definition: 根据国家名获取该国所有省份
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @param countryName
	 * @return
	 */
	public static List<String> getProvinces(String countryName) {
		List<String> list = new ArrayList<String>();
		List<Map> mapList = (List<Map>) map.get(countryName);
		if (mapList != null) {
			for (int i = 0; i < mapList.size(); i++) {
				Map<String, Object> map = mapList.get(i);
				list.add((String) map.get("name"));
			}
		}
		return list;
	}
	
	/**
	 * @Definition: 根据城市获取省份
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @param city
	 * @return
	 */
	public static String getProvince(String city) {
		if (StringUtils.isBlank(city)) {
			return city;
		}
		city = removeLastWord(city);
		List<Map> mapList = (List<Map>) map.get("list");
		if (mapList != null) {
			for (int i = 0; i < mapList.size(); i++) {
				Map<String, Object> map = mapList.get(i);
				List<Map> mapList2 = (List<Map>) map.get("list");
				if (mapList2 != null) {
					for (int j = 0; j < mapList2.size(); j++) {
						Map<String, Object> map2 = mapList2.get(j);
						if (city.equals(map2.get("shortName"))) {
							return map.get("shortName").toString();
						}
					}
				}
			}
		}
		return city;
	}
	
	public static void main(String[] args) {
		System.out.println(LocationUtils.getProvince("深圳"));;
	}
	
}
