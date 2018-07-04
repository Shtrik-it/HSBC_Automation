package com.hsbc.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;

public class TaxDeclarationPage 
{
	WebDriver driver;
	
	//-----------------CONSTRUCTOR-------------------------------
	public TaxDeclarationPage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	
	//Do you agree to the declaration and certify that the information above is accurate? (3 Choice version)
	@FindBy (xpath = "(//input[contains(@name,'EntityTax.FATCADeclaration')]/..)[1]") WebElement radio_Yes;
	@FindBy (xpath = "(//input[contains(@name,'EntityTax.FATCADeclaration')]/..)[2]") WebElement radio_No;
	@FindBy (xpath = "(//input[contains(@name,'EntityTax.FATCADeclaration')]/..)[3]") WebElement radio_NotNow;
	
	@FindBy (name="Select") List <WebElement> button_Select;
	@FindBy (name="EntityTax.IGAActiveTradeActivity") WebElement field_ActiveTrade;
	
	@FindBy (xpath="//input[contains(@name,'EntityTax.FATCADeclaration')]/following-sibling::label")
	List <WebElement> radios_FATCADeclaration;
	
	@FindBy (xpath="//input[contains(@name,'EntityTax.CRSDeclarationAgreement')]/following-sibling::label")
	List <WebElement> radios_CRSDeclaration;
	
	@FindBy (xpath="//input[contains(@name,'Person.PersonalTaxAgreement')]/following-sibling::label")
	List <WebElement> radios_PersonalDeclaration;
	
	@FindBy (xpath="//input[contains(@name,'EntityTax.IGAControllingUSPerson')]/following-sibling::label")
	List <WebElement> radios_USControllingPerson;
	//-----------------TEST CASES------------------------------------
	
	public TaxDeclarationPage agreeToPersonalTaxDeclaration(int choice)
	{
		Helper.tickRadioButton(radios_PersonalDeclaration.get(choice-1));
		Log.info(Log.clickedObject(radios_PersonalDeclaration.get(choice-1)));
		return this;
	}
	
	public TaxDeclarationPage agreeToFATCADeclaration(int choice)
	{
		Helper.tickRadioButton(radios_FATCADeclaration.get(choice-1));
		Log.info(Log.clickedObject(radios_FATCADeclaration.get(choice-1)));
		return this;
	}
	
	public TaxDeclarationPage agreeToCRSDeclaration(int choice)
	{
		Helper.tickRadioButton(radios_CRSDeclaration.get(choice-1));
		Log.info(Log.clickedObject(radios_CRSDeclaration.get(choice-1)));
		return this;
	}
	
	public TaxDeclarationPage clickSelectButton(int position)
	{
		Helper.click(button_Select.get(position-1));
		Log.info(Log.clickedObject("Select (number "+position+")"));
		return this;
	}
	
	public TaxDeclarationPage fillActiveTrade(String text)
	{
		Helper.write(text, field_ActiveTrade);
		Log.info(Log.textField(text));
		return this;
	}
	
	public TaxDeclarationPage entityHasUSControllingPerson(boolean choice)
	{
		if(choice)
		{
			Helper.tickRadioButton(radios_USControllingPerson.get(0));
			Log.info(Log.clickedObject(radios_USControllingPerson.get(0)));
		}
		else
		{
			Helper.tickRadioButton(radios_USControllingPerson.get(1));
			Log.info(Log.clickedObject(radios_USControllingPerson.get(1)));
		}
		return this;
	}
}
