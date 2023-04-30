package com.pro.rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BankTest {
	
	@Test
	public void testBank() throws Exception {
		
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//Bank Manager Login 
		driver.findElement(By.xpath("//button[contains(text(), 'Manager')]")).click();
		//Add Customer
		driver.findElement(By.xpath("//button[contains(text(),'Add Customer')]")).click();
		//input first name 
		driver.findElement(By.xpath("//input[@ng-model='fName']")).sendKeys("Stephen");
		//input last name 
		driver.findElement(By.xpath("//input[@ng-model='lName']")).sendKeys("Fleming");
		//add post code 
		driver.findElement(By.xpath("//input[@ng-model='postCd']")).sendKeys("10001");
		//click on customer button 
		driver.findElement(By.xpath("//button[text()='Add Customer']")).click();
		
		//capturing text from alert 
		System.out.println(driver.switchTo().alert().getText());
		
		//accepting alert 
		driver.switchTo().alert().accept();
		//clicking on home button 
		driver.findElement(By.xpath("//button[text()='Home']")).click();
		
		//clicking on Customer Login button 
		driver.findElement(By.xpath("//button[contains(text(), 'Customer')]")).click();
		//clicking on Customer ID drop down icon 
		driver.findElement(By.xpath("//Select[@ng-model= 'custId']")).click();
		//selecting Stephen Fleming 
		Select select = new Select(driver.findElement(By.xpath("//Select[@ng-model= 'custId']")));
		select.selectByVisibleText("Stephen Fleming");
		
		//clicking on login button 
		driver.findElement(By.xpath("//button[text()= 'Login']")).click();
		
		driver.findElement(By.xpath("//span[text()= 'Stephen Fleming']")).isDisplayed();		
		Thread.sleep(3000);
		driver.quit();
		
	}

}
