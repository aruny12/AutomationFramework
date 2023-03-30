package com.autoZoneAutomationProject.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class TestBase {

	public static WebDriver driver;
	public static Properties PR;
	public File f1;
	public FileInputStream fis;
	public ITestResult result;
	public static ConfigReader config;
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());

	// Method to load properties file
	public Properties loadPropertiesFile() throws IOException {
		PR = new Properties();
		f1 = new File(System.getProperty("user.dir") + "\\config.properties");
		fis = new FileInputStream(f1);
		PR.load(fis);
		logger.info("Config Property File is loaded");
		return PR;
	}

	// Method to take screenshot whenever there is a failure
	public String getScreenshot(String imageName) throws IOException {
		if (imageName.equals("")) {
			imageName = "blank";
		}
		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\autoZoneAutomationProject\\screenshot";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imagelocation + imageName + "_" + formater.format(calendar.getTime()) + ".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);
		return actualImageName;

	}

	public void initBrowser(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("wedriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}

		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("wedriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		else if (browser.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
	}


	public void killBrowser() {
		driver.quit();
	}

	public WebElement waitForElement(WebDriver driver, long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement waitForElementWithPollingInterval(WebDriver driver, long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void impliciteWait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void initReport(Method result) {
		
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void gettingURL() throws IOException {
		logger.info("In the Test base class before navigationg ot url====");
		PR = loadPropertiesFile();
		logger.info(PR.getProperty("url"));
		driver.get(PR.getProperty("url"));
	}

	public void endTest() {
	}

	
	public static void main(String[] args) throws IOException {
		TestBase ts = new TestBase();
		ts.loadPropertiesFile();
		ts.initBrowser("chrome");
		
	}
}
