package com.pro.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pro.pages.TestBase;
import com.pro.utilities.ExcelDataProvider;
import com.pro.pages.HomePage;

public class HomePageTest extends TestBase {
		
	@Test
	public void testBankManagerLogin() {
		
		HomePage	 homePage =  PageFactory.initElements(driver, HomePage.class);
		homePage.bankManagerLogin();
		homePage.setupNewCustomer(excel.getStringData("Customer", 1, 0), 
				excel.getStringData("Customer", 1, 1), excel.getStringData("Customer", 1, 2));		
		System.out.println(driver.switchTo().alert().getText());		
		Assert.assertTrue(driver.switchTo().alert().getText().contains("Customer added successfully"));
	
	}

}
