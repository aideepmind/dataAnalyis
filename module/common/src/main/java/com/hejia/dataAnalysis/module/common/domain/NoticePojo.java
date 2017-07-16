package com.hejia.dataAnalysis.module.common.domain;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2015年8月21日
 * @version: 1.0
 */
public class NoticePojo implements Pojo {
	private int count = 0;//数量
	private int type;//通知类型
	private int showType;//展示方式
	private int getType;//获取数据类型
	private Object data;//数据
	
	public static final int TYPE_STU_DELIVERY = 1;//学生投递相关
	public static final int TYPE_COMP_OPEN_SERVICE = 2;//公司开通服务相关
	public static final int TYPE_COMP_INFO_COMPLETE = 3;//公司信息完善相关
	public static final int TYPE_STU_INFO_COMPLETE = 4;//学生信息完善相关
	public static final int TYPE_COMP_OPEN_SERVICE_SUB = 5;//公司开通服务相关（子账号）
	
	public NoticePojo() {
		
	}

	public NoticePojo(int type) {
		this.type = type;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public int getGetType() {
		return getType;
	}
	public void setGetType(int getType) {
		this.getType = getType;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
