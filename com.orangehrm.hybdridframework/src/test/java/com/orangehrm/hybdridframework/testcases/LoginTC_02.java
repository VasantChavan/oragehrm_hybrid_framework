package com.orangehrm.hybdridframework.testcases;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.hybdridframework.pageobject.LoginPage;
import com.orangehrm.hybdridframework.testbase.TestBase;

public class LoginTC_02 extends TestBase {

	LoginPage lp;
	
	/*
	 * @Test() public void loginTC_01() {
	 * 
	 * lp = new LoginPage(driver); //
	 * lp.setUserName(excelDataProvider.getStringCellData(0, 1, 0)); //
	 * lp.setPassword(excelDataProvider.getStringCellData(0, 1, 1));
	 * 
	 * lp.setUserName(excelDataProvider.getStringCellData("Login", 1, 0));
	 * lp.setPassword(excelDataProvider.getStringCellData("Login", 1, 1));
	 * 
	 * lp.clickOnLoginBtn(); }
	 * 
	 */
	
	
	
	@Test(dataProvider="excelData")	
	public void loginTC_02(Object username, Object password)
	{
		
		String uname= (String)username;
		String upass= (String)password;
		
		lp = new LoginPage(driver);
		lp.setUserName(uname);
		lp.setPassword(upass);
		lp.clickOnLoginBtn();
		
	}
	
	@DataProvider(name="excelData")
	public Object[][] getExcelTestData()
	{
		return excelDataProvider.getCellData("regression");
	}
}
