package com.hsbc.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;

public class BusinessFinancePage 
{
	WebDriver driver;
	
	//-----------------CONSTRUCTOR-------------------------------
	public BusinessFinancePage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	
	//What is your predicted turnover for the next 12 months?
	@FindBy (name = "BusinessIncome.PredictedTurnover") WebElement field_TurnoverNext12Months;
	//What is your forecast profit before tax for the next financial year?
	@FindBy (name = "BusinessIncome.ForecastProfitBeforeTax") WebElement field_ForecastProfitNextYear;
	//Does your business rely on one customer for more than 50% of its sales revenue?
	@FindBy (xpath = "(//input[contains(@name,'BusinessIncome.OneCustomerYesNo')]/..)[1]") WebElement radio_RelyOnOneCustomer_Yes;
	@FindBy (xpath = "(//input[contains(@name,'BusinessIncome.OneCustomerYesNo')]/..)[2]") WebElement radio_RelyOnOneCustomer_No;
	//Will your business be dealing with cash?
	@FindBy (xpath = "(//input[contains(@name,'BusinessIncome.BusinessCashBool')]/..)[1]") WebElement radio_DealingWithCash_Yes;
	@FindBy (xpath = "(//input[contains(@name,'BusinessIncome.BusinessCashBool')]/..)[2]") WebElement radio_DealingWithCash_No;
	
	//How much was initially invested into the business?
	@FindBy (name = "Business.howMuchInitialInvestment") WebElement field_initiallyIvested;

	//Source dropdown 
	@FindBy (name = "Funds.rel_Source") WebElement dropdown_chooseNewSource;
	//Edit link
	By link_Edit = By.name("Edit");
	//Invested amount in GBP
	@FindBy (name = "Person.InvestedAmount") WebElement field_AmountInGBP;
	//Save
	@FindBy (className = "btn-secondary-jade") WebElement button_GreenSave;
	@FindBy (className = "btn-primary") WebElement button_RedSave;
	//Add New Investor
	@FindBy (name="AddNewInvestor") WebElement button_AddNewInvestor;
	
	//Investor details Form
	@FindBy (name = "Person.title") WebElement dropdown_Title;
	@FindBy (name = "Person.forenames") WebElement field_Forenames;
	@FindBy (name = "Person.surnames") WebElement field_Surname;
	@FindBy (name = "Person.InvestedAmount") WebElement field_InvestedAmountInGBP;
	//Does the investor hold a position in the business?
	@FindBy (xpath = "(//input[contains(@name,'Person.IsPositionHolder')]/..)[1]") WebElement radio_InvestorHoldsPosition_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Person.IsPositionHolder')]/..)[2]") WebElement radio_InvestorHoldsPosition_No;
	@FindBy (xpath = "//input[@title='Phone Number']") WebElement field_PhoneNumber;
	@FindBy (name = "Person.email") WebElement field_EmailAddress;
	
	//How much of this investment will be deposited into the account, once opened (if any)?
	@FindBy (name="Funds.AmountToBeDeposited") WebElement field_investmentDepositedIntoAccount;
	//How will this be paid in?
	@FindBy (name="Funds.HowPaidIn") WebElement dropdown_HowWillThisBePaidIn;
	
	//Apart from this amount, will you be depositing any additional funds into the account immediately after opening?
	@FindBy (xpath="(//input[contains(@name,'Business.AdditionalDeposit')]/..)[1]") WebElement radio_anyAdditionalFunds_Yes;
	@FindBy (xpath="(//input[contains(@name,'Business.AdditionalDeposit')]/..)[2]") WebElement radio_anyAdditionalFunds_No;
	
	//Will you be receiving subscriptions into the account?
	@FindBy (xpath="(//input[contains(@name,'Business.SubscriptionsBool')]/..)[1]") WebElement radio_subscriptionsIntoAccounts_Yes;
	@FindBy (xpath="(//input[contains(@name,'Business.SubscriptionsBool')]/..)[2]") WebElement radio_subscriptionsIntoAccounts_No;
	//Will you be receiving donations into the account?
	@FindBy (xpath="(//input[contains(@name,'Business.DonationsBool')]/..)[1]") WebElement radio_donationsIntoAccounts_Yes;
	@FindBy (xpath="(//input[contains(@name,'Business.DonationsBool')]/..)[2]") WebElement radio_donationsIntoAccounts_No;
	
	
	//Please tick all methods used to receive the subscriptions
	@FindBy (xpath="//input[@name='Business.SubscriptionMethod']/following-sibling::label")
	List <WebElement> checkboxes_SubscriptionMethod;
	@FindBy (xpath="//div[contains(@class,'active')]/input[@name='Business.SubscriptionMethod']/following-sibling::label")
	List <WebElement> checkboxes_SubscriptionMethodTicked;
	
	//Please tick all methods used to receive the subscriptions
	@FindBy (xpath="//input[@name='Business.DonationsMethod']/following-sibling::label")
	List <WebElement> checkboxes_DonationMethod;
	@FindBy (xpath="//div[contains(@class,'active')]/input[@name='Business.DonationsMethod']/following-sibling::label")
	List <WebElement> checkboxes_DonationMethodTicked;
	
	@FindBy (name = "BusinessIncome.CashAmount") WebElement dropdown_cashAmount;
	@FindBy (name = "BusinessIncome.CashRegularity") WebElement dropdown_cashRegularity;
	@FindBy (name = "BusinessIncome.CashTypical") WebElement dropdown_cashTypical;
	@FindBy (name = "BusinessIncome.CashInformation") WebElement textarea_cashInfo;

	@FindBy (xpath = "//input[@name='Funds.Amount']") WebElement field_InvestedAmount;
	@FindBy (name = "Funds.AmountToBeDeposited") WebElement field_AmountToBeDeposited;
	@FindBy (name = "Funds.Memberdetails") WebElement field_MemberDetails;
	
	//-----------------TEST CASES------------------------------------
	
	//INCOME AND CASH TRANSACTIONS
	public BusinessFinancePage fillTurnoverForNext12Months(String text)
	{
		Helper.write(text, field_TurnoverNext12Months);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage fillForcastProfitNextYear(String text)
	{
		Helper.write(text, field_ForecastProfitNextYear);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage relyOnOneCustomer(boolean answer)
	{
		Helper.yesOrNo(answer, radio_RelyOnOneCustomer_Yes, radio_RelyOnOneCustomer_No);
		Log.info(Log.yesOrNo(answer));
		return this;
	}
	
	public BusinessFinancePage dealingWithCash(boolean answer)
	{
		Helper.yesOrNo(answer, radio_DealingWithCash_Yes, radio_DealingWithCash_No);
		Log.info(Log.yesOrNo(answer));
		return this;
	}
	
	//SOURCE OF FUNDS
	public BusinessFinancePage anyAdditionalFunds(boolean answer)
	{
		Helper.yesOrNo(answer, radio_anyAdditionalFunds_Yes, radio_anyAdditionalFunds_No);
		Log.info(Log.yesOrNo(answer));
		return this;
	}
	
	public BusinessFinancePage fillInitiallyInvested(String text)
	{
		Helper.write(text, field_initiallyIvested);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage chooseSourceType(String optionText)
	{
		Helper.chooseDropdown(dropdown_chooseNewSource, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public BusinessFinancePage clickEdit(int byPositionInColumn)
	{
		Helper.click(driver.findElements(link_Edit).get(byPositionInColumn-1));
		Log.info(Log.clickedObject(driver.findElements(link_Edit).get(byPositionInColumn-1)));
		return this;
	}
	
	public BusinessFinancePage fillAmountInGPBAndSave(String amount)
	{
		Helper.write(amount, field_AmountInGBP);
		Log.info(Log.textField(amount));
		clickRedSaveButton();
		return this;
	}
	
	public BusinessFinancePage clickAddNewInvestorButton()
	{
		Helper.click(button_AddNewInvestor);
		Log.info(Log.clickedObject(button_AddNewInvestor));
		return this;
	}
	
	//INVESTOR DETAILS FORM
	public BusinessFinancePage chooseTitle(String optionText)
	{
		Helper.chooseDropdown(dropdown_Title, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public BusinessFinancePage fillForenames(String text)
	{
		Helper.write(text, field_Forenames);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage fillSurname(String text)
	{
		Helper.write(text, field_Surname);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage fillInvestedAmountInGBP(String text)
	{
		Helper.write(text, field_InvestedAmountInGBP);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage investorHoldsPosition(boolean answer)
	{
		Helper.yesOrNo(answer, radio_InvestorHoldsPosition_Yes, radio_InvestorHoldsPosition_No);
		Log.info(Log.yesOrNo(answer));
		return this;
	}
	
	public BusinessFinancePage fillMobilePhone(String number)
	{
		Helper.write(number, field_PhoneNumber);
		Log.info(Log.textField(number));
		return this;
	}
	
	public BusinessFinancePage fillEmailAddress(String number)
	{
		Helper.write(number, field_EmailAddress);
		Log.info(Log.textField(number));
		return this;
	}
	
	public BusinessFinancePage clickGreenSaveButton()
	{
		Helper.click(button_GreenSave); 
		By isClicked = By.name("Save");
		if(Helper.existsInDOM(isClicked))
		{
			Helper.click(button_GreenSave);
		}
		Log.info(Log.clickedObject("Save"));
		return this;
	}
	
	public BusinessFinancePage clickRedSaveButton()
	{
		Helper.click(button_RedSave); 
		By isClicked = By.name("Save");
		if(Helper.existsInDOM(isClicked))
		{
			Helper.click(button_RedSave);
		}
		Log.info(Log.clickedObject("Save"));
		return this;
	}
	
	public BusinessFinancePage fillHowMuchInvestmentWillBeDeposited(String number)
	{
		Helper.write(number, field_investmentDepositedIntoAccount);
		Log.info(Log.textField(number));
		Helper.click(button_GreenSave); //To focus out
		return this;
	}
	
	public BusinessFinancePage chooseHowWillThisBePaidIn(String optionText)
	{
		Helper.chooseDropdown(dropdown_HowWillThisBePaidIn, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public BusinessFinancePage saveTheForm()
	{
		Helper.click(button_GreenSave);
		Log.info(Log.clickedObject("Saving the form..."));
		return this;
	}
	
	public BusinessFinancePage receivingSubscriptions(boolean answer)
	{
		Helper.yesOrNo(answer, radio_subscriptionsIntoAccounts_Yes, radio_subscriptionsIntoAccounts_No);
		Log.info(Log.yesOrNo(answer));
		return this;
	}
	
	public BusinessFinancePage receivingDonations(boolean answer)
	{
		Helper.yesOrNo(answer, radio_donationsIntoAccounts_Yes, radio_donationsIntoAccounts_No);
		Log.info(Log.yesOrNo(answer));
		return this;
	}
	
	public BusinessFinancePage tickSubscriptionMethods(int[] boxes)
	{
		Helper.tickMultiCheckbox(boxes, checkboxes_SubscriptionMethod, checkboxes_SubscriptionMethodTicked);
		return this;
	}
	
	public BusinessFinancePage tickDonationMethods(int[] boxes)
	{
		Helper.tickMultiCheckbox(boxes, checkboxes_DonationMethod, checkboxes_DonationMethodTicked);
		return this;
	}
	
	public BusinessFinancePage chooseCashAmountEachYear(String optionText)
	{
		Helper.chooseDropdown(dropdown_cashAmount, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public BusinessFinancePage chooseHowOftenCashDeposits(String optionText)
	{
		Helper.chooseDropdown(dropdown_cashRegularity, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public BusinessFinancePage chooseTypicalCashPayment(String optionText)
	{
		Helper.chooseDropdown(dropdown_cashTypical, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public BusinessFinancePage fillInfoOnCashPayments(String text)
	{
		Helper.write(text, textarea_cashInfo);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage fillInvestedAmount(String text)
	{
		Helper.write(text, field_InvestedAmount);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage fillAmountToBeDeposited(String text)
	{
		Helper.write(text, field_AmountToBeDeposited);
		Log.info(Log.textField(text));
		return this;
	}
	
	public BusinessFinancePage fillNumOfMembers(String text)
	{
		Helper.write(text, field_MemberDetails);
		Log.info(Log.textField(text));
		return this;
	}
}
