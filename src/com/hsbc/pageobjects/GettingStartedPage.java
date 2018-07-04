package com.hsbc.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;

public class GettingStartedPage 
{
	WebDriver driver;
	
	//-----------------CONSTRUCTOR-------------------------------
	public GettingStartedPage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	
	//Does your business currently have an account with another bank?
	@FindBy (xpath = "(//input[contains(@name,'Form.startOrSwitch')]/..)[1]") WebElement eligibilityConfirmation_Q1_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Form.startOrSwitch')]/..)[2]") WebElement eligibilityConfirmation_Q1_No;
	
	//What is the legal structure of your business?
	@FindBy (name = "Business.Type") WebElement eligibilityConfirmation_Q2_Dropdown;
	
	//I confirm that everything above is true about me, any other signatories and the business
	@FindBy (xpath = "(//input[contains(@name,'Confirmation.EligibilityConfirmation')]/..)[1]") WebElement yourApplicationIsFor_True;
	//Some or all of these statements are not true about me, other signatories and/or the business
	@FindBy (xpath = "(//input[contains(@name,'Confirmation.EligibilityConfirmation')]/..)[2]") WebElement yourApplicationIsFor_NotTrue;
	
	//Are you interested in applying for a credit card or an overdraft in this application?
	@FindBy (xpath= "(//input[contains(@name,'Business.CMALending')]/..)[1]")  WebElement aboutYourBusiness_Q1_Yes;
	@FindBy (xpath= "(//input[contains(@name,'Business.CMALending')]/..)[2]")  WebElement aboutYourBusiness_Q1_No;
	
	//Does your business operate, trade and make payments exclusively within the UK?
	@FindBy (xpath= "(//input[contains(@name,'Business.CMAUKTrade')]/..)[1]")  WebElement aboutYourBusiness_Q2_Yes;
	@FindBy (xpath= "(//input[contains(@name,'Business.CMAUKTrade')]/..)[2]")  WebElement aboutYourBusiness_Q2_No;
	
	//Does your business have any accounts with the HSBC group outside of the UK?
	@FindBy (xpath= "(//input[contains(@name,'Business.CMAHSBCGroupAccount')]/..)[1]")  WebElement aboutYourBusiness_Q3_Yes;
	@FindBy (xpath= "(//input[contains(@name,'Business.CMAHSBCGroupAccount')]/..)[2]")  WebElement aboutYourBusiness_Q3_No;
	
	//Is your business part of a franchise group?
	@FindBy (xpath= "(//input[contains(@name,'Business.CMAFranchise')]/..)[1]")  WebElement aboutYourBusiness_Q4_Yes;
	@FindBy (xpath= "(//input[contains(@name,'Business.CMAFranchise')]/..)[2]")  WebElement aboutYourBusiness_Q4_No;
	
	//I confirm that I have read and agree to the terms and conditions and pricing
	@FindBy (xpath = "//input[@name='Confirmation.TermsAndConditionsEligibility']/following-sibling::label") WebElement checkbox_TermsAndConditions;
	
	@FindBy (name = "Continue") WebElement button_Continue;
	
	
	//----------------TEST CASES---------------------------------------
	public GettingStartedPage haveAccountWithAnotherBank(boolean choice)
	{
		Helper.yesOrNo(choice, eligibilityConfirmation_Q1_Yes, eligibilityConfirmation_Q1_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public GettingStartedPage chooseLegalStructureOfBusiness(String optionName)
	{
		Helper.chooseDropdown(eligibilityConfirmation_Q2_Dropdown, optionName);
		Log.info(Log.dropdownChoice(optionName));
		return this;
	}
	
	public GettingStartedPage everythingAboveIsTrue(boolean choice)
	{
		Helper.yesOrNo(choice, yourApplicationIsFor_True , yourApplicationIsFor_NotTrue);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public GettingStartedPage aboutYourBusiness_Q1(boolean choice)
	{
		Helper.yesOrNo(choice, aboutYourBusiness_Q1_Yes, aboutYourBusiness_Q1_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public GettingStartedPage aboutYourBusiness_Q2(boolean choice)
	{
		Helper.yesOrNo(choice, aboutYourBusiness_Q2_Yes, aboutYourBusiness_Q2_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public GettingStartedPage aboutYourBusiness_Q3(boolean choice)
	{
		Helper.yesOrNo(choice, aboutYourBusiness_Q3_Yes, aboutYourBusiness_Q3_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public GettingStartedPage aboutYourBusiness_Q4(boolean choice)
	{
		Helper.yesOrNo(choice, aboutYourBusiness_Q4_Yes, aboutYourBusiness_Q4_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public GettingStartedPage clickTermsAndConditions()
	{
		Helper.tickCheckbox(checkbox_TermsAndConditions);
		Log.info(Log.clickedObject(checkbox_TermsAndConditions));
		return this;
	}
	
	public GettingStartedPage clickContinueButton()
	{
		Helper.click(button_Continue);
		Log.info(Log.clickedObject("Continue"));
		return this;
	}
}
