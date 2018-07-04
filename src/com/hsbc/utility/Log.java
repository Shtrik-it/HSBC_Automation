package com.hsbc.utility;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hsbc.utility.GlobalPaths.FilePaths;

public class Log 
{
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports report;
	static ExtentTest test;
	public static String latestReport;
		
	
	/**
	 * Starts overall report for all tests in the testrun
	 * @author milos
	 */
	public static void startReport() 
	{		
		// Cleanup of screenshot folder on every new testrun
		File screenshotFolder = new File(getProjectRoot() + "/Reports/Screenshots");
		try {
			FileUtils.cleanDirectory(screenshotFolder);
		} catch (IOException e) {
			info(e.toString());
		}
		
		// Cleanup of HTML Reports folder and removes files older than 2 days
		String daysBeforeDeletion = FileReader.read(FilePaths.CONFIG, "DELETE_REPORTS_AFTER_DAYS");
		int numOfdays = Integer.parseInt(daysBeforeDeletion);
		File reportsFolder = new File(getProjectRoot() + "/Reports/HTML Reports");
		int numOfDeletedFiles = 0;
		for(File file : reportsFolder.listFiles())
		{
			long diff = new Date().getTime() - file.lastModified();
			if (diff > numOfdays * 24 * 60 * 60 * 1000) {
			    file.delete();
			    numOfDeletedFiles += 1;
			}
		}
		if(numOfDeletedFiles>0) 
		{
			String plural = "s";
			if(numOfDeletedFiles == 1)
			{
				plural = "";
			}
			System.out.println(numOfDeletedFiles+" html report"+plural+" older than "+numOfdays+" day"+plural+" got deleted");
		}
		
		
		// Create a report object and give timestamped name to a new report.html
		report = new ExtentReports();
		String timeStamp = getTimeStamp();
		latestReport = getProjectRoot() + "/Reports/HTML Reports/Report_" + timeStamp +".html";
		htmlReporter = new ExtentHtmlReporter(latestReport);
		configReport();
		report.attachReporter(htmlReporter);
	}
	
	
	/**
	 * Ends overall report for all tests in the testrun and creates report.html
	 * @author milos
	 */
	public static void endReportAndSendData() 
	{		
		//Populate the report with info
		report.flush();
		
		//Automatically opens browser with the latest report.html at the end of the testrun
		
		if(BrowserFactory.isWindows())
		{
			System.setProperty("webdriver.chrome.driver", getProjectRoot() + "/Configuration/Drivers/Win/chromedriver"+getExtension());
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			File reportHTML = new File(latestReport);
			driver.navigate().to(reportHTML.getAbsolutePath());
		}
		else //for macs only
		{
			System.setProperty("webdriver.chrome.driver", getProjectRoot() + "/Configuration/Drivers/Mac/chromedriver"+getExtension());
			WebDriver driver = new ChromeDriver();
			driver.manage().window().fullscreen();
			driver.navigate().to("file://"+latestReport);
		}
	}

	
	/**Starts Extent Report log recording for test scenario currently running
	 * @param testName
	 * @author milos
	 */
	public static void startTestReport(String testName)
	{
		test = report.createTest(testName);
	}
	
	
	/**Gets the name of the test/method to be used in skipped tests listener
	 * @return test
	 * @author milos
	 */
	public static ExtentTest getTest()
	{
		return test;
	}
	
	
	/**Used for logging individual actions performed
	 * @param details
	 */
	public static void info(String details)
	{
		StackTraceElement e = Thread.currentThread().getStackTrace()[2];
		String simpleClassName = e.getClassName().substring(e.getClassName().lastIndexOf('.') + 1);
		test.info(simpleClassName+"."+e.getMethodName()+":  "+details);
		System.out.println(simpleClassName+"."+e.getMethodName()+":  "+details);
	}
	
	public static void fail(String message)
	{
		info(message);
		System.out.print("FAILED: "+message);
		Assert.fail();
	}
	
	
	/**Takes exception text and screenshot of the failed test cases
	 * @param driver
	 * @param result
	 * @author milos
	 */
	public static void endTestReport(WebDriver driver,ITestResult result) 
	{
		if(ITestResult.FAILURE == result.getStatus())
		{	
			String testMethodName = result.getName();
			String screenshotPath = Log.takeScreenshot(driver, testMethodName+getTimeStamp());
			if(BrowserFactory.isWindows())
			{
				screenshotPath = "../../"+screenshotPath;
			}
			test.log(Status.FAIL, MarkupHelper.createLabel(testMethodName+ ": FAILED", ExtentColor.RED));
			try {
				test.fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);
				System.out.print(result.getThrowable());
			} catch (IOException e) {
				info("Coud not get the error because of: "+e.toString());
				
			}
		}
	}
	
	
	/**Takes screenshot in the moment it is called
	 * @param driver
	 * @param screenshotName
	 * @author milos
	 */
	public static String takeScreenshot(WebDriver driver, String screenshotName) 
	{
		try
		{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File imageBuffered = ts.getScreenshotAs(OutputType.FILE);
		String dest = getProjectRoot() + "/Reports/Screenshots/" + screenshotName + ".jpg";
		File destination = new File(dest);
		FileUtils.copyFile(imageBuffered, destination);
		System.out.println("Screenshot taken!");
		return dest;
		}
		catch (Exception e)
		{
			System.out.println("Exception fired while taking screenshot: " + e.getMessage());
			return e.getMessage();
		}
	}
	
	
	/**
	 * Configures the UI of the html reporter
	 */
	public static void configReport() 
	{
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setReportName("HSBC AUTOMATION REPORT");
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("HSBC Report");
		htmlReporter.config().setEncoding("utf-8");
		
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("Host Name",getHostName());
		report.setSystemInfo("User Name", System.getProperty("user.name"));
		report.setSystemInfo("Environment", FileReader.read(FilePaths.CONFIG, "ENVIRONMENT"));
		report.setSystemInfo("Browser", Helper.getCurrentBrowser());
	}
	
	//-------------------- PRIVATE METHODS -------------------------------
	
	private static String getProjectRoot()
	{
		String projectRoot = null;
		if(BrowserFactory.isWindows())
		{
			projectRoot = ".";
		}
		else
		{
			projectRoot = System.getProperty("user.dir");
		}
		return projectRoot;
	}
	
	private static String getExtension()
	{
		String extension = "";
		if(BrowserFactory.isWindows())
		{
			extension = ".exe";
		}
		return extension;
	}
	
	private static String getTimeStamp()
	{
		return new SimpleDateFormat("yyyy-MM-dd_HH.mm").format(new Date());
	}
	
	private static String getHostName()
	{
		String hostName;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			hostName = "Unknown";
			e.printStackTrace();
		}
		return hostName;
	}
	
	//PREMADE STRING LOGS
	
	public static String yesOrNo(boolean choice)
	{
		String log = "Radio button 'NO' is clicked.";
		if(choice) {
			log = "Radio button 'YES' is clicked.";;
		}
		return log;	
	}
	
	public static String dropdownChoice(String optionText)
	{
		return "Dropdown option chosen '"+optionText+"'";	
	}
	
	public static String textField(String text)
	{
		return "Field populated with '"+text+"'";	
	}
	
	public static String clickedObject(WebElement object)
	{
		return "Object '"+object.getText()+"' clicked";	
	}
	
	public static String clickedObject(String objectText)
	{
		return "Object '"+objectText+"' clicked";	
	}
	
	public static String radioByIndex(int position)
	{
		String log = "First radio button clicked.";
		if(position==2) {
			log = "Second radio button clicked.";;
		}
		return log;	
	}
	

}
