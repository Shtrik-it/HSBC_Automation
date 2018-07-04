package com.hsbc.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;

public class RelatedPersonsPage 
{
	WebDriver driver;
	//-----------------CONSTRUCTOR-------------------------------
	public RelatedPersonsPage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	
	//Roles in the business
	@FindBy (name = "Person.workPosition_new") WebElement dropdown_RolesInBusiness;
	//Save
	By button_Save = By.name("Save");
	//Position
	@FindBy (name = "Person.workPosition_new") WebElement dropdown_Position;
	
	@FindBy (name = "Person.forenames") WebElement field_Forenames;
	@FindBy (name = "Person.surnames") WebElement field_Surname;
	//Signatory in the account
	@FindBy (xpath = "(//input[contains(@name,'Person.IsSignatory')]/..)[1]") WebElement radio_SignatoryInAccount_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.IsSignatory')]/..)[2]") WebElement radio_SignatoryInAccount_No;
	
	@FindBy (name = "Person.PercentageShareholding") WebElement field_PercentageShareholding;
	@FindBy (name = "Person.PercentageShareholding") WebElement field_PercentageOwnership;
	//Please select the contact you would like to add to the application
	@FindBy (name = "Form.rel_LinkerPerson") WebElement dropdown_ContactToAdd;
	@FindBy (name="Select") WebElement button_Select;
	
	@FindBy (xpath = "(//input[contains(@name,'Person.HasShareholdingPlus25PC')]/..)[1]") WebElement radio_shareholderWith25Percent_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.HasShareholdingPlus25PC')]/..)[2]") WebElement radio_shareholderWith25Percent_No;
	
	@FindBy (xpath = "//div[@name='Person.title']//input") WebElement dropdown_Title;
	@FindBy (xpath = "//input[@title='Phone Number']") WebElement field_MobilePhoneNumber;
	@FindBy (name = "Person.email") WebElement field_EmailAddress;
	//Director
	@FindBy (xpath = "(//input[contains(@name,'Person.IsDirector')]/..)[1]") WebElement radio_Director_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.IsDirector')]/..)[2]") WebElement radio_Director_No;
	
	@FindBy (name = "AddAdditionalParty") WebElement button_AddAnotherParty;
	
	//-----------------TEST CASES------------------------------------	
	public RelatedPersonsPage rolesInBusiness(String optionText)
	{
		Helper.chooseDropdown(dropdown_RolesInBusiness, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public RelatedPersonsPage clickSaveButton()
	{
		Helper.click(button_Save);
		if(Helper.existsInDOM(button_Save))
		{
			Helper.click(button_Save);
		}
		Log.info(Log.clickedObject("Save"));
		return this;
	}
	
	public RelatedPersonsPage choosePosition(String optionText)
	{
		Helper.chooseDropdown(dropdown_Position, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public RelatedPersonsPage signatoryInAccount(boolean choice)
	{
		Helper.yesOrNo(choice, radio_SignatoryInAccount_Yes, radio_SignatoryInAccount_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public RelatedPersonsPage shareholderWith25Percent(boolean choice)
	{
		Helper.yesOrNo(choice, radio_shareholderWith25Percent_Yes ,radio_shareholderWith25Percent_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public RelatedPersonsPage fillPercentageShareholding(String text)
	{
		Helper.write(text, field_PercentageShareholding);
		Log.info(Log.textField(text));
		return this;
	}
	
	public RelatedPersonsPage fillPercentageOwnership(String text)
	{
		Helper.write(text, field_PercentageOwnership);
		Log.info(Log.textField(text));
		return this;
	}
	
	public RelatedPersonsPage chooseContactToAdd(String optionText)
	{
		Helper.chooseDropdown(dropdown_ContactToAdd, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public RelatedPersonsPage clickSelectButton()
	{
		Helper.click(button_Select);
		Log.info(Log.clickedObject("Select"));
		return this;
	}
	
	public RelatedPersonsPage chooseTitle(String optionText)
	{
		Helper.chooseDropdown(dropdown_Title, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public RelatedPersonsPage fillForenames(String text)
	{
		Helper.write(text, field_Forenames);
		Log.info(Log.textField(text));
		return this;
	}
	
	public RelatedPersonsPage fillSurname(String text)
	{
		Helper.write(text, field_Surname);
		Log.info(Log.textField(text));
		return this;
	}
	
	public RelatedPersonsPage fillMobilePhoneNumber(String text)
	{
		Helper.write(text, field_MobilePhoneNumber);
		Log.info(Log.textField(text));
		return this;
	}
	
	public RelatedPersonsPage fillEmailAddress(String text)
	{
		Helper.write(text, field_EmailAddress);
		Log.info(Log.textField(text));
		return this;
	}
	
	public RelatedPersonsPage hasRoleInBusiness(boolean choice)
	{
		Helper.yesOrNo(choice, radio_Director_Yes, radio_Director_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public RelatedPersonsPage clickAddAnotherPartyButton()
	{
		Helper.click(button_AddAnotherParty);
		Log.info(Log.clickedObject("Add Another Party"));
		return this;
	}
	
}
