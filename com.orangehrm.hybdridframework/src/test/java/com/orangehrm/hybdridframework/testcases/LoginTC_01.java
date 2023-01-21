package com.orangehrm.hybdridframework.testcases;


import org.testng.annotations.Test;

import com.orangehrm.hybdridframework.pageobject.LoginPage;
import com.orangehrm.hybdridframework.testbase.TestBase;
import com.orangehrm.hybdridframework.utility.Helper;

public class LoginTC_01 extends TestBase {

	LoginPage lp;
	
	@Test(priority =1, enabled = false)
	public void validateOrangeHRMLogoOnLoginPage()
	{
		lp = new LoginPage(driver);
	}
	
	@Test(priority = 2)
	public void loginTC_01() {
		
		extentTest = extentReports.createTest("loginTC_01");
		extentTest.info(" set username and password ");

		lp = new LoginPage(driver);

		lp.setUserName(configDataProvider.getUserName());
		lp.setPassword(configDataProvider.getPassword());
		
		//Helper.capturescreenshot(driver);

		lp.clickOnLoginBtn();
		extentTest.info("clicked on login button");
		
		//Helper.capturescreenshot(driver);
	}

}
