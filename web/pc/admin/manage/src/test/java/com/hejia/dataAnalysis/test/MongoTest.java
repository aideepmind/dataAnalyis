package com.hejia.dataAnalysis.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sourceforge.jwebunit.exception.TestingEngineResponseException;
import net.sourceforge.jwebunit.junit.WebTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2016年1月19日
 * @version: 1.0
 */
public class MongoTest extends WebTestCase {

	@Before
	public void setUp() throws Exception {
		setBaseUrl("http://localhost:8081/");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		this.beginAt("position/find");
		System.out.println("-------------");
	}
	
}
