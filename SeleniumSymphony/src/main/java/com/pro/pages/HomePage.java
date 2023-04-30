package com.pro.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase{
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		//this.driver = driver;
	}

	//@FindBy(xpath = "//button[contains(text(), 'abc')]")
	@FindBy(xpath = "//button[contains(text(), 'Manager')]")
	public WebElement managerLoginBtn;

	@FindBy(xpath = "//button[contains(text(),'Add Customer')]")
	public WebElement addCustomerBtn;

	@FindBy(xpath = "//input[@ng-model='fName']")
	public WebElement firstName;

	@FindBy(xpath = "//input[@ng-model='lName']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@ng-model='postCd']")
	public WebElement postCode;

	@FindBy(xpath = "//button[text()='Add Customer']")
	public WebElement addCustomer;
	
	public void bankManagerLogin() {
		doClick(managerLoginBtn, 5);
//		managerLoginBtn.click();
	}

	public void setupNewCustomer(String firstname, String lastname, String zipcode) {		
		doClick(addCustomerBtn, 1);		
		type(firstName, firstname);		
		type(lastName, lastname);		
		type(postCode, zipcode);		
		doClick(addCustomer, 2);
	}
}
