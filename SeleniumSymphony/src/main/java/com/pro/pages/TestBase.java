package com.pro.pages;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.pro.utilities.BrowserFactory;
import com.pro.utilities.ConfigDataProvider;
import com.pro.utilities.ExcelDataProvider;
import com.pro.utilities.Util;

public class TestBase {
	public WebDriver driver; 
	public ConfigDataProvider config;
	public ExcelDataProvider excel;  
	
	@BeforeSuite
	public void setupSuite() {
		config = new ConfigDataProvider();
		excel = new ExcelDataProvider();
	}
	
	@BeforeTest
	public void setup() {
		driver = BrowserFactory.launchApplication(driver, config.getBrowser(), 
				config.getUrl());		
	}
	
	@AfterTest
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result, Method m) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			Util.captureScreenshot(driver, m.getName());			
		}
		
	}
	

}
