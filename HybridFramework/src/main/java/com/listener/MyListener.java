package com.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utility.DriverUtils;

public class MyListener extends BaseClass implements ITestListener{

	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getName());

	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Testcase passed with name : " + result.getName());

	}

	public void onTestFailure(ITestResult result) {
		String path=DriverUtils.getScreenshot(result.getName());
		test.log(Status.FAIL, "Testcase failed with name : " + result.getName());
		test.addScreenCaptureFromBase64String(path);
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Testcase skipped with name : " + result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


	}

	public void onStart(ITestContext context) {
		Log.info("test suit is ready for the execution");

	}

	public void onFinish(ITestContext context) {
		Log.info("test suit is successfully executed");
		report.flush();                                    //important line to generate report.
	}

}
