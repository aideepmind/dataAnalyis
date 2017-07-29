package com.hejia.dataAnalysis.module.search.common.lucene.analyer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import com.hejia.dataAnalysis.module.search.common.lucene.Constant;

public class CustomStandardAnalyzer extends Analyzer {
	
	private int minGram;
	private int maxGram;
	
	public CustomStandardAnalyzer() {
		super();
		this.maxGram = Constant.DEFAULT_MAX_GRAM;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
	}
	
	public CustomStandardAnalyzer(boolean useSmart) {
		super();
		this.maxGram = Constant.DEFAULT_MAX_GRAM;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
	}
	
	public CustomStandardAnalyzer(int maxGram) {
		super();
		this.maxGram = maxGram;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
	}

	public CustomStandardAnalyzer(int maxGram,boolean useSmart) {
		super();
		this.maxGram = maxGram;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
	}

	public CustomStandardAnalyzer(int minGram, int maxGram,boolean useSmart) {
		super();
		this.minGram = minGram;
		this.maxGram = maxGram;
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		StandardTokenizer tokenizer = new StandardTokenizer();
		tokenizer.setMaxTokenLength(StandardAnalyzer.DEFAULT_MAX_TOKEN_LENGTH);
		//转拼音
		TokenStream tokenStream = new PinyinTokenFilter(tokenizer, 
				Constant.DEFAULT_FIRST_CHAR, Constant.DEFAULT_MIN_TERM_LRNGTH);
		//对拼音进行NGram处理
		tokenStream = new PinyinNGramTokenFilter(tokenStream, this.minGram, this.maxGram);
	    tokenStream = new LowerCaseFilter(tokenStream);
		tokenStream = new StopFilter(tokenStream, StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	    return new Analyzer.TokenStreamComponents(tokenizer, tokenStream);
	}
}
