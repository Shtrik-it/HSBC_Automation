package com.hsbc.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;

public class FastTrackApplicationModal 
{
	WebDriver driver;
	WebDriverWait wait10s;
	
	//-----------------CONSTRUCTOR-------------------------------
	public FastTrackApplicationModal(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
		wait10s = new WebDriverWait(driver, 10);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	
	@FindBy (name = "Company_Search.CHNumber") WebElement field_CompaniesHouseNo;
	@FindBy (xpath = "//input[@name='Company_Search.dontKnowCHNumber']/following-sibling::label") WebElement checkbox_IDontKnowThis;
	@FindBy (name = "Search") WebElement button_Search;
	@FindBy (name = "IWouldPreferToFillInMyApplicationManually") WebElement link_fillApplicationManually;
	@FindBy (xpath = "//div[@class='api-lookup-panel']/..") WebElement div_SearchResultList;
	@FindBy (name = "Continue") WebElement button_Continue;
	
	//-----------------TEST CASES--------------------------------------
	public FastTrackApplicationModal fillCompaniesHouseNo(String text)
	{
		Helper.write(text, field_CompaniesHouseNo);
		Log.info(Log.textField(text));
		return this;
	}
	
	public FastTrackApplicationModal tickIDontKnowThisCheckbox()
	{
		Helper.tickCheckbox(checkbox_IDontKnowThis);
		Log.info(Log.clickedObject("I don't know this"));
		return this;
	}
	
	public FastTrackApplicationModal clickSearchButton()
	{
		Helper.click(button_Search);
		Log.info(Log.clickedObject("Search"));
		return this;
	}
	
	public FastTrackApplicationModal clickFillApplicationManuallyLink()
	{
		Helper.pauseFor(0.5);
		Helper.click(link_fillApplicationManually);
		Log.info(Log.clickedObject("I would prefer to fill in my application manually"));
		return this;
	}
	
	public FastTrackApplicationModal clickSearchResultLink(String resultLinkText)
	{
		Helper.pauseFor(1);
		Helper.clickSearchResultLink(resultLinkText, div_SearchResultList);
		Log.info(Log.clickedObject(resultLinkText));
		return this;
	}
	
	public FastTrackApplicationModal clickContinueButton()
	{
		Helper.click(button_Continue);
		Log.info(Log.clickedObject("Continue"));
		return this;
	}
	
	//TODO check if needed?
//	public FastTrackApplicationModal selectYourself(String optionText)
//	{
//		Helper.chooseDropdown(dropdown_SelectYourself, optionText);
//		Log.info(Log.dropdownChoice(optionText));
//		return this;
//	}
//	
//	public FastTrackApplicationModal clickSelectButton()
//	{
//		Helper.click(button_Select);
//		Log.info(Log.clickedObject("Select"));
//		return this;
//	}
}
