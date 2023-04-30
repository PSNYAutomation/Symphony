package com.pro.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	
	WebDriver driver;
	
	public PageBase (WebDriver driver) {
		this.driver = driver;
	}
	//click on a webElement 
	public void doClick(WebElement element, int waitTime) {		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
	wait.until(ExpectedConditions.elementToBeClickable(element)).click();		
	}
	
	//wait for an element 
	public WebElement waitForElement(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	//type in a web element after waiting 	
	public void type(WebElement element, String text) {
	element = waitForElement(element, 10);
	element.click();
	element.clear();
	element.sendKeys(text);		
	}
	//select from dropdown options 
	public void selectFromDropdown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	// Mouse over an element 
	public void mouseOverAndClick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}
}
