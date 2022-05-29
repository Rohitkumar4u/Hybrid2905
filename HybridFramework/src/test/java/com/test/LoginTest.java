package com.test;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.LoginPage;
import com.utility.PropertiesUtils;


public class LoginTest extends BaseClass {
	public LoginPage lp=null;

	@BeforeSuite
	public void setup() throws Exception {
		initialization();
		reportInit();
		lp=new LoginPage(driver);
	}

	@Test(priority = 1)
	public void loginTest() throws Exception {
		String uname=PropertiesUtils.readProperty("username");
		String pass=PropertiesUtils.readProperty("password");

		lp.loginToApplication(uname, pass);
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Dashboard");
	}
	@Test(priority = 2)
	public void failTest() {
		Assert.assertEquals(driver.getTitle(), "test");
	}
	@Test(priority = 3)
	public void skipTest() {
		throw new SkipException("skipping a testCase");
	}
}
