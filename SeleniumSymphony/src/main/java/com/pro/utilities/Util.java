package com.pro.utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Util {
	
	public static void captureScreenshot(WebDriver driver, String name) {
		
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy_hh_mm_ss");
		String time = currentTime.format(formatTime);
		
		
		File src =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dstn = new File("./Screenshots/"+name+"_"+time+".png");		
		try {
			Files.copy(src, dstn);
			System.out.println("Screenshot captured");
		} catch (Exception e) {			
			System.out.println("Unable to capture screenshot >> "+ e.getMessage());
		}
		
	}

}
