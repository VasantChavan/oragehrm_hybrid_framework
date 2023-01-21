package com.orangehrm.hybdridframework.testbase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangehrm.hybdridframework.utility.ConfigDataProvider;
import com.orangehrm.hybdridframework.utility.ExcelDataProvider;
import com.orangehrm.hybdridframework.utility.Helper;

public class TestBase {

	public WebDriver driver;
	public static ConfigDataProvider configDataProvider;
	public static ExcelDataProvider excelDataProvider;
	public static ExtentHtmlReporter extentHtmlReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	
	
	@BeforeSuite
	public void init()
	{
		
		Reporter.log(" Init all pre-requisite to start the test ", true);
		configDataProvider =new ConfigDataProvider("config");
		excelDataProvider =new ExcelDataProvider("TestData");
		
		
		Reporter.log(" create extent reports to generaate html reports",true);
		extentHtmlReporter= new ExtentHtmlReporter("./Reports/extentReports_"+Helper.getCurrentDateTime()+".html");
		extentHtmlReporter.config().setDocumentTitle(" Automation Test Reports ");
		extentHtmlReporter.config().setReportName(" RT Test Reports ");
		extentHtmlReporter.config().setTheme(Theme.STANDARD);
	
		extentReports =new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		
		extentReports.setSystemInfo("Hostname", "Local Host");
		extentReports.setSystemInfo("Browser", "Chrome");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("TE", "Swaroop");
		extentReports.setSystemInfo("Test Cases", "RT Suite");
		
		Reporter.log(" all pre-requisites are set:",true);
		
	}

	@BeforeMethod
	@Parameters({"browser"})
	public void setUp(@Optional("Chrome")String browser) {

		if (browser.equals("Chrome")) {
			Reporter.log(" set system environment variable path for "+ browser +" and launch the browser",true);
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			Reporter.log(" set system environment variable path for "+ browser +" and launch the browser",true);
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else {
			System.out.println("browser exe not matched , pleaste try with correct browser ");
		}

		driver.manage().window().maximize();
		driver.get(configDataProvider.getAppUrl());
		Reporter.log(" maximize  "+ browser +" and navigate to application under test ",true);
		
		//driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void tearDown(ITestResult results) throws IOException {
		
		if(results.getStatus()== ITestResult.FAILURE)
		{
			//Helper.capturescreenshot(driver);
			
			extentTest.fail("Test Case Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.capturescreenshot(driver)).build());
			Reporter.log("capture screenshot based on test case failure",true);		
		}
		else if(results.getStatus()== ITestResult.SUCCESS)
		{
			extentTest.pass(" Test Case Pass");
		}
		else if(results.getStatus()== ITestResult.SKIP)
		{
			extentTest.skip(" Test Case Skipped");
		}
		driver.quit();
	}

	@AfterTest
	public void extentFlush() {
		extentReports.flush();
	}
}
