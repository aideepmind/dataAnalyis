package com.hejia.dataAnalysis.module.common.domain.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 序列
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
public class Series {
	
	private String type; // 类型
	private String name; // 名词
	private List<Data> data; // 数据
	
	/**
	 * 类型
	 */
	public static final String TYPE_LINE = "line";
	public static final String TYPE_BAR = "bar";
	public static final String TYPE_PIE = "pie";
	public static final String TYPE_SCATTER = "scatter";
	public static final String TYPE_EFFECTSCATTER = "effectScatter";
	public static final String TYPE_RADAR = "radar";
	public static final String TYPE_TREEMAP = "treemap";
	public static final String TYPE_BOXPLOT = "boxplot";
	public static final String TYPE_CANDLESTICK = "candlestick";
	public static final String TYPE_HEATMAP = "heatmap";
	public static final String TYPE_MAP = "map";
	public static final String TYPE_PARALLEL = "parallel";
	public static final String TYPE_LINES = "lines";
	public static final String TYPE_GRAPH = "graph";
	public static final String TYPE_SANKEY = "sankey";
	public static final String TYPE_FUNNEL = "funnel";
	public static final String TYPE_GAUGE = "gauge";
	public static final String TYPE_PICTORIALBAR = "pictorialBar";
	public static final String TYPE_THEMERIVER = "themeRiver";
	public static final String TYPE_CUSTOM = "custom";
	
	
	public Series() {
		
	}

	public Series(String type) {
		this.type = type;
	}

	public Series(String type, String name) {
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
