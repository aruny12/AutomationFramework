package com.autoZoneAutomationProject.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.autoZoneAutomationProject.TestBase.TestBase;
import com.autoZoneAutomationProject.report.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends TestBase implements ITestListener {
	static ExtentReports report;
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = report.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, result.getName() + " test is pass");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.FAIL, result.getName() + " test is failed" + result.getThrowable());
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy.HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		
		String screenshotPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\autoZoneAutomationProject\\screenshot\\"+actualDate+".png";
		File destFile = new File(screenshotPath);
		try {
			FileUtils.copyFile(src, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Here in OnStart==========");
		report = Reporter.setupReport();
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
		
	}

}
