package com.orangehrm.hybdridframework.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage {

	// Object Repository

	@FindBy(name = "username")
	@CacheLookup
	WebElement txt_username;

	@FindBy(name = "password")
	@CacheLookup
	WebElement txt_password;

	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	WebElement btn_login;

	@FindBy(xpath = "//img[@alt='company-branding']")
	@CacheLookup
	WebElement img_Logo;

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Reporter.log(" Login page object are initilised ",true);
	}

	public void setUserName(String uname) {
		try {
			
			txt_username.sendKeys(uname);
			Reporter.log(" Enetr username to txt_username field ",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPassword(String upass) {
		try {
			txt_password.sendKeys(upass);
			Reporter.log(" Enetr password to txt_password field ",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnLoginBtn() {
		try {
			btn_login.click();
			Thread.sleep(3000);
			Reporter.log("click on loggin button ",true);

			if (driver.getPageSource().contains("Dashboard")) {
				Assert.assertTrue(true);
				System.out.println(" Login Success ");
				Reporter.log("user shoulld be landed on dashboard page ",true);
			} else {
				System.out.println(" Login Failed ");
				Reporter.log("Login Failed invalid user credentials ",true);
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateOrangeHRMLogo() {
		if (img_Logo.isDisplayed()) {

			Assert.assertTrue(true);
			System.out.println(" We are on Login Page ");
		} else {
			System.out.println(" We are not landed on Login Page ");
			Assert.assertTrue(false);
		}
	}

}
