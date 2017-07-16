package com.hejia.dataAnalysis.module.common.utils;

import java.text.DecimalFormat;

/**
 * @Description: 数学工具
 * @author: chenyongqiang
 * @Date: 2016年1月24日
 * @version: 1.0
 */
public final class MathUtils {

	public final static DecimalFormat decimalFormat0 = new DecimalFormat("0");
	public final static DecimalFormat decimalFormat1 = new DecimalFormat("0.0");
	public final static DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
	public final static DecimalFormat decimalFormat3 = new DecimalFormat("0.000");

	/**
	 * @Definition: 四舍五入
	 * @author: chenyongqiang
	 * @Date: 2016年1月24日
	 * @param num
	 * @return
	 */
	public static int roundForInt(double num) {
		return Integer.parseInt(decimalFormat0.format(num));
	}

	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2016年1月24日
	 * @param num
	 * @param decimals
	 * @return
	 */
	public static double roundForDouble(double num, int decimals) {
		String newNum = null;
		if (decimals == 1) {
			newNum = decimalFormat1.format(num);
		} else if (decimals == 2) {
			newNum = decimalFormat2.format(num);
		} else if (decimals == 3) {
			newNum = decimalFormat3.format(num);
		}
		//去掉小数点后面无用的零
		while (true) {
			String last = newNum.substring(newNum.length() - 1);
			if ("0".equals(last)) {
				newNum = newNum.substring(0, newNum.length() - 1);
				continue;
			}
			if (".".equals(last)) {
				newNum = newNum.substring(0, newNum.length() - 1);
			}
			return Double.parseDouble(newNum);
		}
	}

	/**
	 * 对数学工具类的简单测试
	 *@author:David admin@dxscx.com
	 *@date: 2016年10月14日
	 *@param args
	 */

	public static void main(String[] args) {
		//保留小数位测试输出
		System.out.println("1212.0231323保留三位小数：" + roundForDouble(1212.0231323, 3));
		//四舍五入测试输出
		System.out.println("5.4四舍五入：" + roundForInt(5.4));
		System.out.println("5.5四舍五入：" + roundForInt(5.5));
	}
}
