package com.hejia.dataAnalysis.module.search.common.lucene.analyer;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.wltea.analyzer.lucene.IKTokenizer;

import com.ibm.icu.text.Transliterator;
import com.hejia.dataAnalysis.module.search.common.lucene.Constant;

/**
 * @Description: 自定义分析器，只适用于当前业务
 * @author: chenyongqiang
 * @Date: 2015年10月15日
 * @version: 1.0
 */
public class CustomIKAnalyer extends Analyzer {
	
	private int minGram;
	private int maxGram;
	private boolean useSmart;
	
	public CustomIKAnalyer() {
		this(Constant.DEFAULT_IK_USE_SMART);
	}
	
	public CustomIKAnalyer(boolean useSmart) {
		this.maxGram = Constant.DEFAULT_MAX_GRAM;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
		this.useSmart = useSmart;
	}
	
	public CustomIKAnalyer(int maxGram) {
		this.maxGram = maxGram;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
		this.useSmart = Constant.DEFAULT_IK_USE_SMART;
	}

	public CustomIKAnalyer(int maxGram,boolean useSmart) {
		this.maxGram = maxGram;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
		this.useSmart = useSmart;
	}

	public CustomIKAnalyer(int minGram, int maxGram,boolean useSmart) {
		this.minGram = minGram;
		this.maxGram = maxGram;
		this.useSmart = useSmart;
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Reader reader = new BufferedReader(new StringReader(fieldName));
		Tokenizer tokenizer = new IKTokenizer(reader, useSmart);
		
		//转拼音
		TokenStream tokenStream = new PinyinTokenFilter(tokenizer, 
				Constant.DEFAULT_FIRST_CHAR, Constant.DEFAULT_MIN_TERM_LRNGTH);
		//对拼音进行NGram处理
		tokenStream = new PinyinNGramTokenFilter(tokenStream, this.minGram, this.maxGram);
//		Transliterator transforms = Transliterator.createFromRules(null, ":: Han-Latin/Names;[[:space:]][bpmfdtnlgkhjqxzcsryw] { [[:any:]-[:white_space:]] >;::NFD;[[:NonspacingMark:][:Space:]]>;",Transliterator.FORWARD);
//		tokenStream = new PimICUTransformFilter(tokenStream, new Transliterator[] {transforms});
	    tokenStream = new LowerCaseFilter(tokenStream);
		tokenStream = new StopFilter(tokenStream, StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	    return new Analyzer.TokenStreamComponents(tokenizer, tokenStream);
	}
}
