package com.qa.test;

import org.api.test.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import clientmethods.RestClient;

public class TestGetAPI extends TestBase {
	TestBase base;
	String url;
	String api;
	String actualUrl;
	RestClient rest;

	@BeforeMethod
	public void testsetup() {
		base = new TestBase();
		url = prp.getProperty("URL");
		api = prp.getProperty("api");
		actualUrl = url + api;
		System.out.println(actualUrl);

	}

	@Test
	public void apiGetTest() {
		rest = new RestClient();
		rest.getMethod(actualUrl);
	}
}
