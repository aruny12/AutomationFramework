package com.autoZoneAutomationProject.TestBase;

import java.util.Properties;

public class ConfigReader {
private Properties OR;
	
	public ConfigReader(Properties OR){
		this.OR = OR;
	}
	public String getURL() {
		return OR.getProperty("url");
	}

}
