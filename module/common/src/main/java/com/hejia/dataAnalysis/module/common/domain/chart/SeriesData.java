package com.hejia.dataAnalysis.module.common.domain.chart;

/**
 * @Description: 序列的数据结构
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
public class SeriesData extends Data {

	private String name; // 名词
	
	public SeriesData() {
		
	}
	
	public SeriesData(String name, Object value) {
		this.name = name;
		this.setValue(value);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
