package com.pro.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pro.utilities.ConfigDataProvider;
import com.pro.utilities.ExcelDataProvider;
import com.pro.utilities.Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {

	public static void main(String[] args) {

		ConfigDataProvider config = new ConfigDataProvider();
		System.out.println(config.getDataFromConfig("Student1"));
		System.out.println(config.getBrowser());		
		System.out.println(config.getDataFromConfig("Name"));		
		System.out.println(config.getUrl());
		
		ExcelDataProvider excel = new ExcelDataProvider();
		System.out.println(excel.getStringData("Customer", 1, 0));
		System.out.println(excel.getStringData("Customer", 1, 1));
		System.out.println(excel.getStringData("Customer", 1, 2));
	}
	
	@org.testng.annotations.Test
	public void googleSearchTest() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Khalil Biriani", Keys.ENTER);
		Util.captureScreenshot(driver, "Google");
		driver.close();		
	}

}













