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
		try {
			this.beginAt("position/findByPositionName?positionName=" + URLEncoder.encode("大数据", "utf-8"));
		} catch (TestingEngineResponseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("-------------");
	}
	
	@Test
	public void test2() {
		this.beginAt("position/find");
		System.out.println("-------------");
	}
	
}
