package com.pro.pages;

import java.awt.Desktop;
import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pro.utilities.BrowserFactory;
import com.pro.utilities.ConfigDataProvider;
import com.pro.utilities.ExcelDataProvider;
import com.pro.utilities.Util;

public class TestBase {
	public WebDriver driver; 
	public ConfigDataProvider config;
	public ExcelDataProvider excel;  
	public static ExtentReports extentReports; 
	public static ExtentTest extentTest;
	
	@BeforeSuite
	public void setupSuite() {
		config = new ConfigDataProvider();
		excel = new ExcelDataProvider();
		extentReports = new ExtentReports(); 
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/report.html");
		sparkReporter.config().setReportName("Bank Test");
		extentReports.attachReporter(sparkReporter); 
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}
	@AfterSuite
	public void genrateReport() throws Exception {
		extentReports.flush();
		Desktop.getDesktop().open(new File("Reports/report.html"));
	}
	
	@Parameters("browserName")
	@BeforeTest
	public void setup(String browserName, ITestContext context) {
		driver = BrowserFactory.launchApplication(driver, browserName, 
				config.getUrl());	
		Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
		String device = capabilities.getBrowserName() + "_" + capabilities.getBrowserVersion();
		String author = context.getCurrentXmlTest().getParameter("author");
		extentTest = extentReports.createTest(context.getName());
		
		extentTest.assignAuthor(author);
		extentTest.assignDevice(device);
	}
	
	@AfterTest
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result, Method m) {		
		if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(m.getName() + " failed");
			String screenshotPath = Util.captureScreenshot(driver, m.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(result.getThrowable());
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName() + " passed");
		}else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.skip(m.getName() + " skipped");
		}
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}
	

}




