package com.hsbc.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;

public class BusinessOverviewPage
{
	WebDriver driver;
	WebDriverWait wait10s;
	
	//-----------------CONSTRUCTOR-------------------------------
	public BusinessOverviewPage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
		wait10s = new WebDriverWait(driver, 10);
	}
	
	//-----------------PAGE OBJECTS------------------------------------	
	
	//ABOUT YOUR BUSINESS
	@FindBy (name ="Business.CompaniesHouseNumber") WebElement field_CompaniesHourseNo;
	@FindBy (xpath = "//div[@name='Person.title']/rich-dropdown/span/span") WebElement dropdown_Title;
	@FindBy (name = "Person.forenames") WebElement field_Forenames;
	@FindBy (name = "Person.surnames") WebElement field_Surname;
	@FindBy (xpath = "(//div[@name='Business.IncorporationDate']//input)[1]") WebElement field_Date_DD;
	@FindBy (xpath = "(//div[@name='Business.IncorporationDate']//input)[2]") WebElement field_Date_MM;
	@FindBy (xpath = "(//div[@name='Business.IncorporationDate']//input)[3]") WebElement field_Date_YY;
	@FindBy (name = "Business.TradingName") WebElement field_TradingName;
	//What influenced your decision to open an account with HSBC?
	@FindBy (name = "Business.MarketingInfluence") WebElement dropdown_WhatInfluencedDecision;
	//Does your business have any accounts with the HSBC Group outside of the UK?
	@FindBy (xpath = "(//input[contains(@name,'Business.GlobalAccountBool')]/..)[1]") WebElement radio_AnyAccountsWithHSBC_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.GlobalAccountBool')]/..)[2]") WebElement radio_AnyAccountsWithHSBC_No;
	
    //BUSINESS ACTIVITY
	@FindBy (name = "SicCode_Search.keywords") WebElement field_SearchActivity;
	@FindBy (name = "SearchForActivity") WebElement button_SearchForActivity;
	@FindBy (name = "SearchAgain") WebElement button_SearchAgain;
	@FindBy (name = "Business.explain") WebElement textarea_WhatYourBusinessDoes;
	
	@FindBy (xpath = "(//input[@name='Business.ProductOrService'])[1]/following-sibling::label") WebElement checkbox_Products;
	@FindBy (xpath = "(//input[@name='Business.ProductOrService'])[2]/following-sibling::label") WebElement checkbox_Services;
	@FindBy (name = "Business.ProductFeeeText") WebElement textarea_DetailsOfTheProducs;
	@FindBy (name = "Business.ServicesFreeText") WebElement textarea_DetailsOfTheServices;
	
	@FindBy (xpath = "(//input[@name='Business.CustomerType'])[1]/following-sibling::label") WebElement checkbox_PublicIndividuals;
	@FindBy (xpath = "(//input[@name='Business.CustomerType'])[2]/following-sibling::label") WebElement checkbox_GovernmentPublicSector;
	@FindBy (xpath = "(//input[@name='Business.CustomerType'])[3]/following-sibling::label") WebElement checkbox_BanksFinancialInstitutions;
	@FindBy (xpath = "(//input[@name='Business.CustomerType'])[4]/following-sibling::label") WebElement checkbox_OtherBusinesses;
	
	@FindBy (name="Business.OperatingFrom") WebElement dropdown_WhereBusinessOperatesFrom;
	
	//BUSINESS ADDRESS
	@FindBy (name = "Address_Search.postcode") WebElement field_Postcode;
	@FindBy (name = "FindAddress") WebElement button_FindAddress;
	@FindBy (name = "SICDescription") WebElement element_resultList1;
	@FindBy (name = "FullAddress") WebElement element_resultList2;
	//Is this also the business address of your company?
	@FindBy (xpath = "(//input[contains(@name,'Business.RegisteredAddressSameAsBusinessAddress')]/..)[1]") WebElement radio_AlsoAddressOfCompany_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.RegisteredAddressSameAsBusinessAddress')]/..)[2]") WebElement radio_AlsoAddressOfCompany_No;
	//SAVING YOUR APPLICATION
	@FindBy (name = "Person.email") WebElement field_EmailAddress;
	@FindBy (xpath = "//input[@title='Phone Number']") WebElement field_MobilePhone;
	@FindBy (xpath = "(//div[@name='Person.dob']//input)[1]") WebElement field_DateOfBirth_DD;
	@FindBy (xpath = "(//div[@name='Person.dob']//input)[2]") WebElement field_DateOfBirth_MM;
	@FindBy (xpath = "(//div[@name='Person.dob']//input)[3]") WebElement field_DateOfBirth_YY;

	@FindBy (name = "Business.CompanyName") WebElement field_CompanyName;
	//----------------TEST CASES---------------------------------------
	
	//About your business TCs
	
	public BusinessOverviewPage fillCompaniesHouseNo(String text)
	{
		Helper.write(text, field_CompaniesHourseNo);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage chooseTitle(String option)
	{
		Helper.chooseDropdown(dropdown_Title, option);
		Log.info(Log.dropdownChoice(option));
		return this;
	}
	
	public BusinessOverviewPage fillForenames(String text)
	{
		Helper.write(text, field_Forenames);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage fillSurname(String text)
	{
		Helper.write(text, field_Surname);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage fillBusinessStartDate(String text)
	{
		Helper.fillDate(text, field_Date_DD, field_Date_MM, field_Date_YY);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage fillTradingName(String text)
	{
		Helper.write(text, field_TradingName);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage chooseWhatInfluencedYourDecision(String optionName)
	{
		Helper.chooseDropdown(dropdown_WhatInfluencedDecision, optionName);
		Log.info(Log.dropdownChoice(optionName));
		return this;
	}
	
	public BusinessOverviewPage anyAccountsOutsideUK(boolean choice)
	{
		Helper.yesOrNo(choice, radio_AnyAccountsWithHSBC_Yes, radio_AnyAccountsWithHSBC_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	//Business activity
	
	public BusinessOverviewPage searchForActivity(String searchTerm)
	{
		Helper.write(searchTerm, field_SearchActivity);
		wait10s.until(ExpectedConditions.or(
				      ExpectedConditions.elementToBeClickable(button_SearchForActivity),
				      ExpectedConditions.elementToBeClickable(button_SearchAgain)));
		try
		{
			button_SearchForActivity.click();
			Log.info(Log.clickedObject("Search for activity")+" Attempting...");
		}
		catch(NoSuchElementException e)
		{
			button_SearchAgain.click();
			Log.info(Log.clickedObject("Search Again"));
		}
		return this;
	}
	
	public BusinessOverviewPage clickSearchActivityResultLink(String resultLinkText)
	{
		Helper.clickSearchResultLink(resultLinkText, element_resultList1);
		Log.info(Log.clickedObject(resultLinkText));
		return this;
	}
	
	public BusinessOverviewPage fillWhatYourBusinessDoes(String text)
	{
		Helper.write(text, textarea_WhatYourBusinessDoes);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage tickProducts()
	{
		Helper.tickCheckbox(checkbox_Products);
		Log.info(Log.clickedObject("Products"));
		return this;
	}
	
	public BusinessOverviewPage tickServices()
	{
		Helper.tickCheckbox(checkbox_Services);
		Log.info(Log.clickedObject("Services"));
		return this;
	}
	
	public BusinessOverviewPage fillDetailsOfTheProducts(String text)
	{
		Helper.write(text, textarea_DetailsOfTheProducs);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage fillDetailsOfTheServices(String text)
	{
		Helper.write(text, textarea_DetailsOfTheServices);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage tickPublicOrIndividuals()
	{
		Helper.tickCheckbox(checkbox_PublicIndividuals);
		Log.info(Log.clickedObject(checkbox_PublicIndividuals));
		return this;
	}
	public BusinessOverviewPage tickGovernmentOrPublicSector()
	{
		Helper.tickCheckbox(checkbox_GovernmentPublicSector);
		Log.info(Log.clickedObject(checkbox_GovernmentPublicSector));
		return this;
	}
	
	public BusinessOverviewPage chooseWhereBussinesOperatesFrom(String optionName)
	{
		Helper.chooseDropdown(dropdown_WhereBusinessOperatesFrom, optionName);
		Log.info(Log.dropdownChoice(optionName));
		return this;
	}
	
	//Business address
	
	public BusinessOverviewPage fillPostcode(String text)
	{
		Helper.write(text, field_Postcode);
		Log.info(Log.textField(text));
		return this;
		
	}
	public BusinessOverviewPage clickFindAddressButton()
	{
		Helper.click(button_FindAddress);
		Log.info(Log.clickedObject(button_FindAddress));
		return this;
	}
	
	public BusinessOverviewPage clickFindAddressResultLink(String resultLinkText)
	{
		Helper.clickSearchResultLink(resultLinkText, element_resultList2);
		Log.info(Log.clickedObject(resultLinkText));
		return this;
	}
	
	public BusinessOverviewPage alsoAddressOfCompany(boolean choice)
	{
		Helper.pauseFor(0.5);
		Helper.yesOrNo(choice, radio_AlsoAddressOfCompany_Yes, radio_AlsoAddressOfCompany_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	//Saving your application
	
	public BusinessOverviewPage fillEmailAddress(String text)
	{
		Helper.pauseFor(0.5);
		Helper.write(text, field_EmailAddress);
		Helper.pauseFor(0.3);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage fillMobileNumber(String text)
	{
		Helper.pauseFor(0.3);
		Helper.write(text, field_MobilePhone);
		Helper.pauseFor(0.3);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessOverviewPage fillBirthDate(String text)
	{
		Helper.fillDate(text, field_DateOfBirth_DD, field_DateOfBirth_MM, field_DateOfBirth_YY);
		Log.info(Log.textField(text));
		return this;
	}	
	
	public BusinessOverviewPage fillCompanyName(String text)
	{
		Helper.write(text, field_CompanyName);
		Log.info(Log.textField(text));
		return this;
	}
}
