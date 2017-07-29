package com.hejia.dataAnalysis.module.common.domain.chart;

/**
 * @Description: 地图序列
 * @author: chenyongqiang
 * @Date: 2017年7月27日
 * @version: 1.0
 */
public class MapSeries extends Series {
	
	private String map;
	
	/**
	 * 地图类型
	 */
	public static final String MAP_CHINA = "china";
	public static final String MAP_WORLD = "world";
	
	public MapSeries() {
		
	}
	
	public MapSeries(String type) {
		super(type);
	}
	
	public MapSeries(String type, String name) {
		super(type, name);
	}
	
	public MapSeries(String type, String name, String map) {
		super(type, name);
		this.map = map;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}
	
}
