package com.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utility.PropertiesUtils;

public class BaseClass {
	public static WebDriver driver=null;
	public static Logger Log=Logger.getLogger(BaseClass.class);

	public static ExtentReports report=null;
	public static ExtentTest test=null;
	public static ExtentSparkReporter spark=null;

	//below method to initialize the Webdriver 
	public static void initialization() throws Exception {
		System.out.println("reading a browser name from config file"); //it prints only on console
		Log.info("reading a browser name from config file");           // it prints as well as saved at other locations
		String browser=PropertiesUtils.readProperty("browser");
		Log.info("browser name found in config file as : " + browser);
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
			driver=new ChromeDriver();
		}
		if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(PropertiesUtils.readProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); //waits 30 sec for loading the page
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void reportInit() {
		report=new ExtentReports();
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/target/ExtentReport.html");
		report.attachReporter(spark);
	}
}
