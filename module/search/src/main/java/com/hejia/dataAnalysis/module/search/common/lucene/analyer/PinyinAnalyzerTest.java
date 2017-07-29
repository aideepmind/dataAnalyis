package com.hejia.dataAnalysis.module.search.common.lucene.analyer;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;

/**
 * 拼音分词器测试
 * @author Lanxiaowei
 *
 */
public class PinyinAnalyzerTest {
	public static void main(String[] args) throws IOException {
		String text = "2011年3月31日，孙燕姿与相恋5年多的男友纳迪姆在新加坡登记结婚";
		Analyzer analyzer = new CustomIKAnalyer(20);
		AnalyzerUtils.displayTokens(analyzer, text);
	}
}
