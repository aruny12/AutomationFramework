package com.autoZoneAutomationProject.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.autoZoneAutomationProject.TestBase.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class Reporter extends TestBase {
	public static ExtentTest test;
	static ExtentReports report;
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	
	public static ExtentReports setupReport() {
		
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy.hh_mm_ss");
		Date date = new Date();
		String actualDate = formater.format(date);
		String reportPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\autoZoneAutomationProject\\report\\ExecutionReport_"+actualDate+".html";
		
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		
		report = new ExtentReports();
		report.attachReporter(sparkReport);
		logger.info("Reports initalized----");
		
		sparkReport.config().setDocumentTitle("Document Title");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("ReportName");
		
		
		return report;
		
		
	}
}
