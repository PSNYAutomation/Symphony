package com.pro.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	
	Properties prop;	
	
	public ConfigDataProvider() {		
		File src = new File("./src/test/resources/Configuration/Config.properties");		
		
			try {
				FileInputStream fis = new FileInputStream(src);
				prop = new Properties();
				prop.load(fis);
			} catch (Exception e) {
				System.out.println("unable to read config file >> " + e.getMessage());
			}			
	}	
	public String getDataFromConfig(String key) {		
		return prop.getProperty(key);
	}
	
	public String getBrowser() {
		return prop.getProperty("browser");
	}
	
	public String getUrl() {
		return prop.getProperty("Url");
	}	
}


