package com.hejia.dataAnalysis.module.search.common.lucene;

public class Constant {
	public static final int DEFAULT_MIN_TERM_LRNGTH = 1;//汉字转化成拼音的词语最小长度
	public static final int DEFAULT_MIN_GRAM = 1;//分割最小长度
	public static final int DEFAULT_MAX_GRAM = 20;//分割最大长度
	public static final boolean DEFAULT_NGRAM_CHINESE = true;//是否切割中文
	public static final boolean DEFAULT_FIRST_CHAR = false;//多音字是否使用第一个，否则使用多个
	public static final boolean DEFAULT_OUT_CHINESE = true;//是否转换成拼音后，保留中文
	public static final boolean DEFAULT_IK_USE_SMART = false;//ik分词是否使用智能
}
