package com.hsbc.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;
import com.hsbc.utility.StaticDriver;

public class AboutYouPage extends StaticDriver
{
	WebDriver driver;
	
	//-----------------CONSTRUCTOR-------------------------------
	public AboutYouPage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	
	//Are you an existing HSBC personal customer?
	@FindBy (xpath = "(//input[contains(@name,'Person.HasHSBCAccount_Customer')]/..)[1]") WebElement radio_existingPersonalCustomer_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.HasHSBCAccount_Customer')]/..)[2]") WebElement radio_existingPersonalCustomer_No;
	
	//Your personal details continued
	@FindBy (name = "Person.MaritalStatus") WebElement dropdown_MaritalStatus;
	
	@FindBy (xpath = "(//input[contains(@name,'Person.Gender')]/..)[1]") WebElement radio_GenderMale;
	@FindBy (xpath = "(//input[contains(@name,'Person.Gender')]/..)[2]") WebElement radio_GenderFemale;
	
	@FindBy (name = "Person.ResidentialStatus") WebElement dropdown_ResidentialStatus;
	@FindBy (name = "Person.NationalInsurance") WebElement field_NationalInsuranceNumber;
	//Are you resident to pay tax in the UK ?
	@FindBy (xpath = "(//input[contains(@name,'Person.UKTaxResident')]/..)[1]") WebElement radio_payTaxInUK_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.UKTaxResident')]/..)[2]") WebElement radio_payTaxInUK_No;
	//Are you resident to pay tax in any other country?
	@FindBy (xpath = "(//input[contains(@name,'Person.OtherTaxCountries')]/..)[1]") WebElement radio_payTaxInOtherCountry_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.OtherTaxCountries')]/..)[2]") WebElement radio_payTaxInOtherCountry_No;
	
	@FindBy (name = "Person.CountryofBirth") WebElement dropdown_CountryOfBirth;
	@FindBy (name = "Person.nationality") WebElement dropdown_CountryOfNationality;
	//Are you also a national/citizen of any other country?
	@FindBy (xpath = "(//input[contains(@name,'Person.OtherNationalityYesNo')]/..)[1]") WebElement radio_citizenOfOtherCountry_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.OtherNationalityYesNo')]/..)[2]") WebElement radio_citizenOfOtherCountry_No;
	
	@FindBy (name = "Person.GrossIncome") WebElement field_GrossIncomeInLastYear;
	@FindBy (name = "Person.NoYearsExistingPersonalBank") WebElement field_YearsWithExistingBank;
	//Do you hold a personal debit card with any financial institution other than HSBC?
	@FindBy (xpath = "(//input[contains(@name,'Person.PersonalDebit')]/..)[1]") WebElement radio_personalDebitCard_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.PersonalDebit')]/..)[2]") WebElement radio_personalDebitCard_No;
	//Do you hold a personal credit card with any financial institution other than HSBC?
	@FindBy (xpath = "(//input[contains(@name,'Person.PersonalCredit')]/..)[1]") WebElement radio_personalCreditCard_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.PersonalCredit')]/..)[2]") WebElement radio_personalCreditCard_No;
	
	//Is your home address the same as your business address?
	@FindBy (xpath = "(//input[contains(@name,'Person.HomeAddressMatchBusiness')]/..)[1]") WebElement radio_HomeAddressSameAsBusiness_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.HomeAddressMatchBusiness')]/..)[2]") WebElement radio_HomeAddressSameAsBusiness_No;
	//Date you moved into this address
	@FindBy (xpath = "(//div[@name='Address.movedInDate']//input)[1]") WebElement field_DateMovedIn_DD;
	@FindBy (xpath = "(//div[@name='Address.movedInDate']//input)[2]") WebElement field_DateMovedIn_MM;
	@FindBy (xpath = "(//div[@name='Address.movedInDate']//input)[3]") WebElement field_DateMovedIn_YY;
	//Is your home address also your correspondence address?
	@FindBy (xpath = "(//input[contains(@name,'Person.HomeAddressCorrespondence')]/..)[1]") WebElement radio_HomeAlsoCorrespondance_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.HomeAddressCorrespondence')]/..)[2]") WebElement radio_HomeAlsoCorrespondance_No;
	
	//Please identify the source of your investment
	@FindBy (xpath = "//span[contains(text(),'source of your investment')]") WebElement label_SourceOfInvestment;
	@FindBy (name ="IndividualInvestment.rel_Source") List <WebElement> dropdown_SourceOfInvestment;
	@FindBy (name = "IndividualInvestment.Amount") List <WebElement> field_Amount;
	
	//Add New Source
	@FindBy (name = "AddNewSource") WebElement button_AddNewSource;
	@FindBy (name = "IndividualInvestment.Industry") WebElement dropdown_OccupationType;
	@FindBy (name = "IndividualInvestment.OccupationABCDEHI") WebElement dropdown_Occupation;
	@FindBy (name = "IndividualInvestment.EmployerName") WebElement field_EmployersName;
	@FindBy (name = "IndividualInvestment.YearsEmployment") WebElement field_YearsEmployed;
	@FindBy (name = "IndividualInvestment.FurtherDetails") WebElement textarea_FurtherDetails;
	
	//-----------------TEST CASES------------------------------------
	public AboutYouPage existingPersonalCustomer(boolean choice)
	{
		Helper.yesOrNo(choice, radio_existingPersonalCustomer_Yes, radio_existingPersonalCustomer_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	//Your personal details continued
	public AboutYouPage chooseMeritalStatus(String optionText)
	{
		Helper.chooseDropdown(dropdown_MaritalStatus, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public AboutYouPage chooseGender(int index)
	{
		if(index==1) {
			Helper.tickRadioButton(radio_GenderMale);}
		else if(index==2) {
			Helper.tickRadioButton(radio_GenderFemale);}
		else {
			Log.info("There is no option with the index of: "+index+" , try 1 or 2");
			Assert.fail("There is no option with the index of: "+index+" , try 1 or 2");
			}
		Log.info(Log.radioByIndex(index));
		return this;
	}
	
	public AboutYouPage residentialStatus(String optionText)
	{
		Helper.click(dropdown_ResidentialStatus);
		try{
			driver.findElement(By.xpath("//li[text()='"+optionText+"']")).click();
		}
		catch(ElementNotInteractableException e){
			Helper.click(driver.findElement(By.xpath("(//li[text()='"+optionText+"'])[2]")));
		}
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public AboutYouPage fillNationalInsuranceNumber(String text)
	{
		Helper.write(text, field_NationalInsuranceNumber);
		Log.info(Log.textField(text));
		return this;
	}
	
	public AboutYouPage residentToPayTaxInUk(boolean choice)
	{
		Helper.yesOrNo(choice, radio_payTaxInUK_Yes, radio_payTaxInUK_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public AboutYouPage residentToPayTaxInOtherCountry(boolean choice)
	{
		Helper.yesOrNo(choice, radio_payTaxInOtherCountry_Yes, radio_payTaxInOtherCountry_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public AboutYouPage countryOfBirth(String optionText)
	{
		Helper.chooseDropdown(dropdown_CountryOfBirth, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public AboutYouPage countryOfNationality(String optionText)
	{
		Helper.chooseDropdown(dropdown_CountryOfNationality, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public AboutYouPage citizenOfAnyOtherCountry(boolean choice)
	{
		Helper.yesOrNo(choice, radio_citizenOfOtherCountry_Yes, radio_citizenOfOtherCountry_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public AboutYouPage grossIncomeLastYear(String text)
	{
		Helper.write(text, field_GrossIncomeInLastYear);
		Log.info(Log.textField(text));
		return this;
	}
	
	public AboutYouPage yearsWithExistingBank(String text)
	{
		Helper.write(text, field_YearsWithExistingBank);
		Log.info(Log.textField(text));
		return this;
	}
	
	public AboutYouPage debitCardWithAnotherBank(boolean choice)
	{
		Helper.yesOrNo(choice, radio_personalDebitCard_Yes, radio_personalDebitCard_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public AboutYouPage creditCardWithAnotherBank(boolean choice)
	{
		Helper.yesOrNo(choice, radio_personalCreditCard_Yes, radio_personalCreditCard_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public AboutYouPage homeAddressSameAsBusiness(boolean choice)
	{
		Helper.yesOrNo(choice, radio_HomeAddressSameAsBusiness_Yes, radio_HomeAddressSameAsBusiness_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public AboutYouPage fillDateYouMovedIn(String date)
	{
		Helper.fillDate(date, field_DateMovedIn_DD, field_DateMovedIn_MM, field_DateMovedIn_YY);
		Log.info(Log.textField(date));
		return this;
	}
	
	public AboutYouPage homeAlsoCorrespondance(boolean choice)
	{
		Helper.yesOrNo(choice, radio_HomeAlsoCorrespondance_Yes, radio_HomeAlsoCorrespondance_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public AboutYouPage chooseSourceOfInvesment(String optionText, int position)
	{
		Helper.waitUntilClickable(label_SourceOfInvestment);
		Helper.click(dropdown_SourceOfInvestment.get(position-1));
		Helper.click(driver.findElements(By.xpath("//li[text()='"+optionText+"']")).get(position-1));
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public AboutYouPage fillSourceAmount(String amount, int position)
	{
		Helper.write(amount, field_Amount.get(position-1));
		Log.info(Log.textField(amount));
		return this;
	}
	
	//Personal Investment Details
	
	public AboutYouPage clickAddNewSourceButton()
	{
		Helper.click(button_AddNewSource); //to focus TODO needs refactoring
		Helper.click(button_AddNewSource); // to click
		Log.info(Log.clickedObject("Add new source"));
		return this;
	}
	
	public AboutYouPage fillFurtherDetails(String text)
	{
		Helper.write(text, textarea_FurtherDetails);
		Log.info(Log.textField(text));
		return this;
	}
	
	public AboutYouPage chooseOcupationType(String optionText)
	{
		Helper.chooseDropdown(dropdown_OccupationType, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public AboutYouPage chooseOcupation(String optionText)
	{
		Helper.chooseDropdown(dropdown_Occupation, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public AboutYouPage fillEmployersName(String text)
	{
		Helper.write(text, field_EmployersName);
		Log.info(Log.textField(text));
		return this;
	}
	
	public AboutYouPage fillHowManyYearsEmployed(String text)
	{
		Helper.write(text, field_YearsEmployed);
		Log.info(Log.textField(text));
		return this;
	}
}
