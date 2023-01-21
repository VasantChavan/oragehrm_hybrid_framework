package com.orangehrm.hybdridframework.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class Helper {

	// handle browser pop --> alert 
	// handle bootstrap drop down
	// handle multiple windows
	
	
	
	// handle normal drop-down
	
	public static void handleNormalDropdown(WebElement dropdown, String visibletxt)
	{
		new Select(dropdown).selectByVisibleText(visibletxt);
	}
	
	public static void handleNormalDropdown(WebElement dropdown, int index)
	{
		new Select(dropdown).selectByIndex(index);
	}
	
	public static void handleNormalDropdown(String value, WebElement dropdown)
	{
		new Select(dropdown).selectByValue(value);
	}
	
	// Capture the screenshot

	public static String capturescreenshot(WebDriver driver) {
		
		String screenshotpaath= System.getProperty("user.dir")+"//Screenshot/oragehrm_"+getCurrentDateTime()+".png";
		try {
			// File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File(screenshotpaath));
			
			System.out.println(" Screenshot captured successfully ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return screenshotpaath;
	}
	
	
	public static String getCurrentDateTime()
	{
//		DateFormat customFormat =new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
//		Date currentDateTime= new Date();
		
		return new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
	}

	
	
	// switch to frame
	
	public static void switchToFrame(WebDriver driver, String name_or_id)
	{
		try {
			driver.switchTo().frame(name_or_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void switchToFrame(WebDriver driver, int index)
	{
		try {
			driver.switchTo().frame(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void switchToFrame(WebDriver driver, WebElement ele)
	{
		try {
			driver.switchTo().frame(ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void switchToFrame(WebDriver driver)
	{
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
