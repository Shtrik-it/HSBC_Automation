package com.hsbc.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;

public class BusinessDetailsPage 
{
	WebDriver driver;
	
	//-----------------CONSTRUCTOR-------------------------------
	public BusinessDetailsPage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	@FindBy (xpath = "//*[@code-name='SaveSuccess']/span/span") WebElement section_ApplicationNumber;
	
	@FindBy (name = "Business.NoOfEmployees") WebElement field_HowManyEmployees;
	//Is your business part of a franchise?
	@FindBy (xpath = "(//input[contains(@name,'Business.FranchiseBool')]/..)[1]") WebElement radio_PartOfFranchize_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.FranchiseBool')]/..)[2]") WebElement radio_PartOfFranchize_No;
	//Does your business own the premises?
	@FindBy (xpath = "(//input[contains(@name,'Business.PremisesBool')]/..)[1]") WebElement radio_OwnsThePremises_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.PremisesBool')]/..)[2]") WebElement radio_OwnsThePremises_No;
	//Type of premises
	@FindBy (xpath = "(//input[contains(@name,'Business.PremisesType')]/following-sibling::label)[1]") WebElement radio_Freehold;
	@FindBy (xpath = "(//input[contains(@name,'Business.PremisesType')]/following-sibling::label)[2]") WebElement radio_Leasehold;
	//Does your business have a licence/permission to operate?
	@FindBy (name = "Business.LicenseBool") WebElement dropdown_LicenceToOperate;
	//Is your business registered for VAT?
	@FindBy (xpath = "(//input[contains(@name,'Business.VatBool')]/..)[1]") WebElement radio_RegisteredForVAT_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.VatBool')]/..)[2]") WebElement radio_RegisteredForVAT_No;
	//VAT number
	@FindBy (name = "Business.VATNumber") WebElement field_VATNumber;
	//Is your business resident to pay tax in the UK?
	@FindBy (xpath = "(//input[contains(@name,'Business.taxInUk')]/..)[1]") WebElement radio_PayTaxInUK_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.taxInUk')]/..)[2]") WebElement radio_PayTaxInUK_No;
	//Is your business resident to pay tax in any country outside of the UK?
	@FindBy (xpath = "(//input[contains(@name,'Business.taxOutsideUk')]/..)[1]") WebElement radio_PayTaxInOtherCountry_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.taxOutsideUk')]/..)[2]") WebElement radio_PayTaxInOtherCountry_No;
	//Will your business deal with any countries outside the UK over the next 12 months?
	@FindBy (xpath = "(//input[contains(@name,'Business.InternationalDealingsBool')]/..)[1]") WebElement radio_OtherCountriesNext12Months_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.InternationalDealingsBool')]/..)[2]") WebElement radio_OtherCountriesNext12Months_No;
	//Does your business intend to have dealings with any countries outside the UK after the next year of operation?
	@FindBy (xpath = "(//input[contains(@name,'Business.InternationalDealingsFutureBool')]/following-sibling::label)[1]") WebElement radio_OtherCountriesAfterOneYear_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.InternationalDealingsFutureBool')]/following-sibling::label)[2]") WebElement radio_OtherCountriesAfterOneYear_No;
	
	//BUSSINESS STRUCTURE
	//Is 25% or more of the business owned by a family group?
	@FindBy (xpath = "(//input[contains(@name,'Business.FamGroups')]/..)[1]") WebElement radio_Is25PercentOwnedByFamily_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.FamGroups')]/..)[2]") WebElement radio_Is25PercentOwnedByFamily_No;
	
	//BUSINESS CONTACT DETAILS
	@FindBy (name = "Business.email") WebElement field_BusinessEmailAddress;
	@FindBy (xpath = "(//input[@title='Phone Number'])[1]") WebElement field_BusinessContactNumber;
		
	// YOUR PERSONAL CONTACT DETAILS
	@FindBy (xpath = "//div[@name='Person.title']//input") WebElement dropdown_PersonalContactTitle;
	@FindBy (name = "Person.forenames") WebElement field_PersonalContactForenames;
	@FindBy (name = "Person.surnames") WebElement field_PersonalContactSurname;
	
	// MAIN CONTACT DETAILS
	@FindBy (xpath = "(//div[@name='Person.title']//input)[2]") WebElement dropdown_MainContactTitle;
	@FindBy (xpath = "(//input[@name='Person.forenames'])[2]") WebElement field_MainContactForenames;
	@FindBy (xpath = "(//input[@name='Person.surnames'])[2]") WebElement field_MainContactSurname;
	@FindBy (xpath = "(//input[@title='Phone Number'])[4]") WebElement field_MainContactPhoneNumber;
	@FindBy (xpath = "(//input[@name='Person.email'])") WebElement field_MainContactEmailAddress;
	
	//Will you be the main point of contact?
	@FindBy (xpath="(//input[contains(@name,'Person.IsMainContact')]/..)[1]") WebElement radio_MainPointOfContact_Yes;
	@FindBy (xpath="(//input[contains(@name,'Person.IsMainContact')]/..)[2]") WebElement radio_MainPointOfContact_No;
	
	//What type of members does your organisation have? Tick all that apply
	@FindBy (xpath="//input[@name='Business.Membertype']/following-sibling::label") 
	List <WebElement> checkboxes_MemberTypes;
	@FindBy (xpath="//div[contains(@class,'active')]/input[@name='Business.Membertype']/following-sibling::label") 
	List <WebElement> checkboxes_MemberTypes_Ticked;
	
	@FindBy (name="Business.OrgCountry") WebElement dropdown_CountryMajorityBasedIn;
	
	//----------------TEST CASES---------------------------------------
	
	//PAGE SECTIONS
	public BusinessStructue businessStructure()
	{
		BusinessStructue bs = new BusinessStructue();
		return bs;
	}
	
	public InternationalBusiness internationalBusiness()
	{
		InternationalBusiness ib = new InternationalBusiness();
		return ib;
	}
	public BusinessContactDetails businessContactDetails()
	{
		BusinessContactDetails bcd = new BusinessContactDetails();
		return bcd;
	}
	
	public PersonalContactDetails personalContactDetails()
	{
		PersonalContactDetails pcd = new PersonalContactDetails();
		return pcd;
	}
	
	public MainContactDetails mainContactDetails()
	{
		MainContactDetails mcd = new MainContactDetails();
		return mcd;
	}
	
	//BUSINESS STRUCTURE
	public class BusinessStructue
	{
		public BusinessStructue is25PercentOwnedByFamily(boolean choice)
		{
			Helper.yesOrNo(choice, radio_Is25PercentOwnedByFamily_Yes, radio_Is25PercentOwnedByFamily_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
		
		public BusinessStructue fillHowManyEmployees(String text)
		{
			Helper.write(text, field_HowManyEmployees);
			Log.info(Log.textField(text));
			return this;
		}
		
		public BusinessStructue isPartOfFranchize(boolean choice)
		{
			Helper.yesOrNo(choice, radio_PartOfFranchize_Yes, radio_PartOfFranchize_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
		
		public BusinessStructue ownsThePremises(boolean choice)
		{
			Helper.yesOrNo(choice, radio_OwnsThePremises_Yes, radio_OwnsThePremises_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
		
		public BusinessStructue chooseTypeOfPremises(int optionIndex)
		{
			if(optionIndex == 1)
			{
				Helper.click(radio_Freehold);
			}
			else if(optionIndex == 2)
			{
				Helper.click(radio_Leasehold);
			}
			else
			{
				Log.info("No radio button with index: "+optionIndex+", available index are 1 or 2");
				Assert.fail("No radio button with index: "+optionIndex+", available index are 1 or 2");	
			}
			Log.info(Log.radioByIndex(optionIndex));
			return this;
		}
		
		public BusinessStructue licenceToOperate(String optionText)
		{
			Helper.chooseDropdown(dropdown_LicenceToOperate, optionText);
			Log.info(Log.dropdownChoice(optionText));
			return this;
		}
		
		public BusinessStructue registeredForVAT(boolean choice)
		{
			Helper.yesOrNo(choice, radio_RegisteredForVAT_Yes, radio_RegisteredForVAT_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
		
		public BusinessStructue fillVATNumber(String text)
		{
			Helper.write(text, field_VATNumber);
			Log.info(Log.textField(text));
			return this;
		}
		
		public BusinessStructue payTaxInUK(boolean choice)
		{
			Helper.yesOrNo(choice, radio_PayTaxInUK_Yes, radio_PayTaxInUK_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
		public BusinessStructue payTaxInOtherCountry(boolean choice)
		{
			Helper.yesOrNo(choice, radio_PayTaxInOtherCountry_Yes, radio_PayTaxInOtherCountry_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
		
		public BusinessStructue tickMemberTypesByPosition(int[] checkboxes)
		{
			Helper.tickMultiCheckbox(checkboxes, checkboxes_MemberTypes, checkboxes_MemberTypes_Ticked);
			return this;
		}
		
		public BusinessStructue chooseCountryMajorityBasedIn(String optionText)
		{
			Helper.chooseDropdown(dropdown_CountryMajorityBasedIn, optionText);
			Log.info(Log.dropdownChoice(optionText));
			return this;
		}
	}
	
	//INTERNATIONAL BUSINESS
	public class InternationalBusiness
	{
		public InternationalBusiness dealWithOtherCountriesNext12Months(boolean choice)
		{
			Helper.yesOrNo(choice, radio_OtherCountriesNext12Months_Yes, radio_OtherCountriesNext12Months_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
		
		public InternationalBusiness dealWithOtherCountriesAfterOneYear(boolean choice)
		{
			Helper.yesOrNo(choice, radio_OtherCountriesAfterOneYear_Yes, radio_OtherCountriesAfterOneYear_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
	}
	
	//BUSINESS CONTACT DETAILS
	public class BusinessContactDetails
	{
		public BusinessContactDetails fillEmailAddress(String text)
		{
			Helper.pauseFor(0.3);
			Helper.write(text, field_BusinessEmailAddress);
			Helper.pauseFor(0.3);
			Log.info(Log.textField(text));
			return this;
		}
		
		public BusinessContactDetails fillContactNumber(String number)
		{
			Helper.pauseFor(0.3);
			Helper.write(number, field_BusinessContactNumber);
			Helper.pauseFor(0.3);
			Log.info(Log.textField(number));
			return this;
		}
	}
	
	//PERSONAL CONTACT DETAILS
	public class PersonalContactDetails
	{
		public PersonalContactDetails chooseTitle(String optionText)
		{
			Helper.chooseDropdown(dropdown_PersonalContactTitle, optionText);
			Log.info(Log.dropdownChoice(optionText));
			return this;
		}
		
		public PersonalContactDetails fillForenames(String text)
		{
			Helper.write(text, field_PersonalContactForenames);
			Log.info(Log.textField(text));
			return this;
		}
		
		public PersonalContactDetails fillSurname(String text)
		{
			Helper.write(text, field_PersonalContactSurname);
			Log.info(Log.textField(text));
			return this;
		}
		
		public PersonalContactDetails mainPointOfContact(boolean choice)
		{
			Helper.yesOrNo(choice, radio_MainPointOfContact_Yes, radio_MainPointOfContact_No);
			Log.info(Log.yesOrNo(choice));
			return this;
		}
	}
	
	//MAIN CONTACT DETAILS
	public class MainContactDetails
	{
		public MainContactDetails chooseTitle(String optionText)
		{
			Helper.click(dropdown_MainContactTitle);
			Helper.click(driver.findElement(By.xpath("(//li[text()='"+optionText+"'])[2]")));
			Log.info(Log.dropdownChoice(optionText));
			return this;
		}
		
		public MainContactDetails fillForenames(String text)
		{
			Helper.write(text, field_MainContactForenames);
			Log.info(Log.textField(text));
			return this;
		}
		
		public MainContactDetails fillSurname(String text)
		{
			Helper.write(text, field_MainContactSurname);
			Log.info(Log.textField(text));
			return this;
		}
		
		public MainContactDetails fillMobilePhoneNumber(String number)
		{
			Helper.pauseFor(0.3);
			Helper.write(number, field_MainContactPhoneNumber);
			Helper.pauseFor(0.3);
			Log.info(Log.textField(number));
			return this;
		}
		
		public MainContactDetails fillEmailAddress(String text)
		{
			Helper.pauseFor(0.3);
			Helper.write(text, field_MainContactEmailAddress);
			Helper.pauseFor(0.3);
			Log.info(Log.textField(text));
			return this;
		}
	}
	
	public BusinessDetailsPage printApplicationNumber()
	{
		Helper.waitUntilClickable(section_ApplicationNumber);
		Log.info("APPLICATION NUMBER: "+section_ApplicationNumber.getText());
		return this;
	}
}
