package com.hejia.dataAnalysis.module.common.domain.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 轴
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
public class Axis {

	private String type; // 类型: value（数值轴）/category（类目轴）/time（时间轴）/log（对数轴）
	private String name; // 坐标轴名称
	private List<Data> data; // 数据
	
	public Axis() {
		
	}
	
	public Axis(String type) {
		this.type = type;
	}
	
	public Axis(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	/**
	 * @Definition: 添加数据
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @param data
	 */
	public void add(Data data) {
		if (this.data == null) {
			this.data = new ArrayList<Data>();
		}
		this.data.add(data);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

}
