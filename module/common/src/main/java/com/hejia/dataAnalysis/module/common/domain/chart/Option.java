package com.hejia.dataAnalysis.module.common.domain.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 设计原则：1.动态数据在后台生成，如x轴和y轴；2.结构化数据在后台生成，如series；
 * @author: chenyongqiang
 * @Date: 2017年7月25日
 * @version: 1.0
 */
public class Option {

	private List<Axis> xAixs; // x轴集合
	private List<Axis> yAxis; // y轴集合
	private List<Series> series; // 序列集合
	
	public Option() {
		
	}
	
	public Option(List<Axis> xAixs, List<Axis> yAxis, List<Series> series) {
		this.xAixs = xAixs;
		this.yAxis = yAxis;
		this.series = series;
	}
	
	/**
	 * @Definition: 添加X轴
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @param aixs
	 */
	public void addXAixs(Axis aixs) {
		if (xAixs == null) {
			xAixs = new ArrayList<Axis>();
		}
		xAixs.add(aixs);
	}
	
	/**
	 * @Definition: 添加Y轴
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @param aixs
	 */
	public void addYAixs(Axis aixs) {
		if (yAxis == null) {
			yAxis = new ArrayList<Axis>();
		}
		yAxis.add(aixs);
	}
	
	/**
	 * @Definition: 添加序列
	 * @author: chenyongqiang
	 * @Date: 2017年7月27日
	 * @param series
	 */
	public void addSeries(Series series) {
		if (this.series == null) {
			this.series = new ArrayList<Series>();
		}
		this.series.add(series);
	}
	
	public List<Axis> getxAixs() {
		return xAixs;
	}

	public void setxAixs(List<Axis> xAixs) {
		this.xAixs = xAixs;
	}

	public List<Axis> getyAxis() {
		return yAxis;
	}

	public void setyAxis(List<Axis> yAxis) {
		this.yAxis = yAxis;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

}
