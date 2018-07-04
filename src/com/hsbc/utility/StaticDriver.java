package com.hsbc.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StaticDriver 
{
	protected static WebDriver driver;
	protected static WebDriverWait wait1s;
	protected static WebDriverWait wait10s;
	protected static WebDriverWait wait20s;
	
	/**
	 * To avoid passing driver object every time a static method is used. 
	 * @author milosh
	 */
	public static void setDriver(WebDriver staticDriver)
	{
		driver = staticDriver;
		wait1s = new WebDriverWait(driver, 1);
		wait10s = new WebDriverWait(driver, 10);
		wait20s = new WebDriverWait(driver, 20);
	}
}
