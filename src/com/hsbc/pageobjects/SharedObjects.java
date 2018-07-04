package com.hsbc.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;
import com.hsbc.utility.StaticDriver;

public class SharedObjects extends StaticDriver
{
	WebDriver driver;
	
	//-----------------CONSTRUCTOR-------------------------------
	public SharedObjects(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	@FindBy (xpath="//button[@name='SaveAndContinue']") WebElement button_SaveAndContinue;
	
	public By loadingWheel = By.className("bbform-loading");
	//-----------------TEST CASES------------------------------------
	public SharedObjects clickSaveAndContinueButton()
	{
		Helper.waitForLoadToFinish();
		wait10s.until(ExpectedConditions.elementToBeClickable(button_SaveAndContinue));
		button_SaveAndContinue.click();
		boolean buttonLoading = button_SaveAndContinue.getAttribute("class").contains("btn-spinning");
		if(!buttonLoading)
		{
			Helper.click(button_SaveAndContinue);
		}
		Log.info(Log.clickedObject("Save And Continue"));
		Helper.waitForLoadToFinish();
		return this;
	}
}
