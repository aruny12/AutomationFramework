package com.autoZoneAutomationProject.TestCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.autoZoneAutomationProject.Logger.LoggerHelper;
import com.autoZoneAutomationProject.TestBase.TestBase;

import PageObjects.AutoZonePage;

public class SmokeTestCase extends TestBase {

	public AutoZonePage autoZonePage = new AutoZonePage(driver);
	private final Logger log = LoggerHelper.getLogger(AutoZonePage.class);
	
	@BeforeTest
	@Parameters("browser")
	public void LaunchBrowser(String browserName) throws IOException {
		log.info("Logging info: initializing the webDriver");
		loadPropertiesFile();
		initBrowser(browserName);
		
	}
	
	//@Test
	public void clickAction() throws Exception {
		autoZonePage.navigateToURL();
		autoZonePage.clickautAddVehBtn();
	}
	
	@Test
	public void navigateToURL() throws IOException {
		autoZonePage.navigateToURL();
	}
	
	@AfterTest
	public void killDriver() {
		endTest();
		killBrowser();
	}
}
