package com.hsbc.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.hsbc.utility.GlobalPaths.FilePaths;
import com.hsbc.utility.GlobalPaths.WebDriverExePaths;

public class BrowserFactory 
{
	static WebDriver driver;


	/**Starts a browser chosen from the config file.
	 * @param url to be opened in the new browser instance
	 * @return the driver instance
	 */
	public static WebDriver startBrowser(String url) 
	{	
		if(Helper.getCurrentBrowser().equalsIgnoreCase("chrome"))
		{	
			if(isWindows())
			{
				System.setProperty("webdriver.chrome.driver", WebDriverExePaths.CHROME_WIN_EXE);
				driver = new ChromeDriver();
			}
			else
			{
				System.setProperty("webdriver.chrome.driver", WebDriverExePaths.CHROME_MAC_EXE);
				driver = new ChromeDriver();
			}
			
		}
		else if(Helper.getCurrentBrowser().equalsIgnoreCase("firefox"))
		{
			if(isWindows())
			{
				System.setProperty("webdriver.gecko.driver",WebDriverExePaths.FIREFOX_WIN_EXE);
				driver = new FirefoxDriver();
			}
			else
			{
				System.setProperty("webdriver.gecko.driver",WebDriverExePaths.FIREFOX_MAC_EXE);
				driver = new FirefoxDriver();
			}
		}
		
		if(isWindows())
		{
			driver.manage().window().maximize();
		}
		else
		{
			driver.manage().window().fullscreen();
		}
		
		driver.get(url);
		return driver;
	}
	
	public static boolean isWindows()
	{
		return System.getProperty("os.name").toLowerCase().contains("win");
	}
	
	/**
	 * Closes the browser after each test. This can be turned on and off from the config file.
	 */
	public static void closeBrowser()
	{
		if(FileReader.read(FilePaths.CONFIG, "CLOSE_BROWSER_WHEN_DONE").equalsIgnoreCase("Yes"))
		{
			driver.quit();
			Log.info("Browser closed!");
		}
	}
	

	
	/** This returns the url set from the config and scenario property files, based on environment and url type set.
	 * @param pathToScenario
	 * @return url to be used in a specific scenario
	 */
	public static String getUrlChoice(String pathToScenario)
	{
		String environmentChoice = FileReader.read(FilePaths.CONFIG, "ENVIRONMENT");
		String urlTypeChoice = FileReader.read(pathToScenario, "URL_TYPE");
		
		String url = null;
		if(environmentChoice.equalsIgnoreCase("Dev"))
		{
			url = getUrlType(urlTypeChoice, "DEV_CUSTOMER_URL", "DEV_STAFF_URL");
		}
		else if(environmentChoice.equalsIgnoreCase("Preprod"))
		{
			url = getUrlType(urlTypeChoice, "PREPROD_CUSTOMER_URL", "PREPROD_STAFF_URL");
		}
		else if(environmentChoice.equalsIgnoreCase("Live proving"))
		{
			url = getUrlType(urlTypeChoice, "LIVE_PROVING_CUSTOMER_URL", "LIVE_PROVING_STAFF_URL");
		}
		else if(environmentChoice.equalsIgnoreCase("Live"))
		{
			url = getUrlType(urlTypeChoice, "LIVE_CUSTOMER_URL", "LIVE_STAFF_URL");
		}
		else
		{
			System.out.println("Environment value '"+environmentChoice+"' does not match any of the property file options, check for typos.");
		}
		return url;
	}
		
	private static String getUrlType(String urlTypeChoice, String customerUrlKey, String staffUrlKey)
	{
		String url = null;
		if(urlTypeChoice.equalsIgnoreCase("Customer"))
		{
			url = FileReader.read(FilePaths.CONFIG, customerUrlKey);
		}
		else if(urlTypeChoice.equalsIgnoreCase("Staff"))
		{
			url = FileReader.read(FilePaths.CONFIG, staffUrlKey);
		}
		else
		{
			System.out.println("Url type value '"+urlTypeChoice+"' does not match any of the property file options, check for typos.");
		}
		return url;
	}
		
	
}
