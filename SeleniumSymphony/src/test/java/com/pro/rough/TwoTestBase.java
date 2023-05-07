package com.pro.rough;

import java.awt.Desktop;
import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TwoTestBase {

	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;

	@BeforeSuite
	public void setupSuite() {
		extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/Report.html");
		sparkReporter.config().setReportName("All Test Reports");
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	@AfterSuite
	public void generateReport() throws Exception {
		extentReports.flush();
		Desktop.getDesktop().open(new File("Reports/Report.html"));
	}

	@Parameters("browserName")
	@BeforeTest
	public void setup(ITestContext context, String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("This browser is not supported");
		}
		driver.manage().window().maximize();
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String device = capabilities.getBrowserName() + "_" 
		+ capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
		String author = context.getCurrentXmlTest().getParameter("author");
		extentTest = extentReports.createTest(context.getName());
		extentTest.assignAuthor(author);
		extentTest.assignDevice(device);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@AfterMethod
	public void checkStatus(ITestResult result, ITestContext context, Method method) {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(method.getName() + " failed");
			String screenshotPath = captureScreenshot(context.getName() + "_" + method.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(method.getName() + " passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.skip(method.getName() + " skipped");
		}
		extentTest.assignCategory(method.getAnnotation(Test.class).groups());
	}

	// -------------------------- screenshot
	public String captureScreenshot(String name) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dstn = new File("Screenshots/" + name + ".png");
		try {
			Files.copy(src, dstn);
			System.out.println("Screenshot captured");
		} catch (Exception e) {
			System.out.println("Screenshot could not be captured >>" + e.getMessage());
		}

		return dstn.getAbsolutePath();
	}

}
