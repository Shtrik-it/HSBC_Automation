package com.hsbc.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;

public class ManagingYourAccountPage 
{
	WebDriver driver;
	
	//-----------------CONSTRUCTOR-------------------------------
	public ManagingYourAccountPage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	
	//Signatory rules
	//HSBC to accept any instruction signed by any one signatory
	@FindBy (xpath = "(//input[contains(@name,'Business.SignatoryRule')]/..)[1]") WebElement radio_SignedByAnyOneSignatory;
	//HSBC to accept any instruction signed by any two signatory
	@FindBy (xpath = "(//input[contains(@name,'Business.SignatoryRule')]/..)[2]") WebElement radio_SignedByAnyTwoSignatories;
	//Other
	@FindBy (xpath = "(//input[contains(@name,'Business.SignatoryRule')]/..)[3]") WebElement radio_Other;
	
	//Tariff Selection
	//Would you like to change your selection?
	@FindBy (xpath = "(//input[contains(@name,'Account.ChangeAccountType')]/..)[1]") WebElement radio_ChangeYourSelection_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Account.ChangeAccountType')]/..)[2]") WebElement radio_ChangeYourSelection_No;
	
	//I being a member confirm my acceptance to the terms and I confirm that the selected people are to be signatories on our bank account with you
	@FindBy (xpath = "//input[@name='Confirmation.signatureMandate']/following-sibling::label") WebElement checkbox_IConfirmTermsAndCondsAsAMember;
	//I confirm that I have read and agree to the terms and conditions
	@FindBy (xpath = "//input[@name='Account.AccountServicesAgreement']/following-sibling::label") WebElement checkbox_IConfirmTermsAndConds;
	
	//Set up your personal security details
	//How do you want to receive your Activation code?
	@FindBy (name = "Account.ActivationCode") WebElement dropdown_HowToReceiveActivationCode;
	@FindBy (name = "Person.SecurityPass") WebElement field_SecurityPassNumber;
	@FindBy (name = "Person.SecuityPassConfirm") WebElement field_ConfirmSecurityPassNumber;
	@FindBy (name = "Person.BTBMemorableWord") WebElement field_MemorableWord;
	@FindBy (name = "Person.BTBMemorablePlace") WebElement field_MemorablePlace;
	
	//Additional products from HSBC
	//Business Credit Card
	@FindBy (xpath = "//div[@data-name='CreditCards']//button") WebElement link_AddToApplication_BCC;
	//Total credit card limit for the business
	@FindBy (name = "Business.ProductsCreditCardAmount") WebElement field_CreditCardLimit;
	//Cash machine withdrawal
	@FindBy (xpath = "(//input[contains(@name,'Business.ProductsCreditCashWithdrawal')]/..)[1]") WebElement radio_CachMachineWithdrawal_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.ProductsCreditCashWithdrawal')]/..)[2]") WebElement radio_CachMachineWithdrawal_No;
	//Would you like your business name on the card?
	@FindBy (xpath = "(//input[contains(@name,'Business.ProductsCreditName')]/..)[1]") WebElement radio_BusinessNameOnCard_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.ProductsCreditName')]/..)[2]") WebElement radio_BusinessNameOnCard_No;
	//Please choose how much you would like to pay via Direct Debit each month. These payments will be in accordance with the card agreement
	@FindBy (xpath = "(//input[contains(@name,'Business.ProductsCreditMonthlyPayment')]/..)[1]") WebElement radio_FullPayment;
	@FindBy (xpath = "(//input[contains(@name,'Business.ProductsCreditMonthlyPayment')]/..)[2]") WebElement radio_MinimumPayment;
	//How do you intend to use your card?
	@FindBy (name = "Business.ProductsCreditCardIntendedUse") WebElement dropdown_HowWillYouUseCard;
	@FindBy (name = "Business.ProductsCardMemorableWord") WebElement field_MemorableWordSecurityDetail;
	@FindBy (name = "Save") WebElement button_Save;
	
	//Business Overdraft
	@FindBy (xpath = "//div[@data-name='Overdraft']//button") WebElement link_AddToApplication_BO;
	@FindBy (name = "Business.ProductOverdraftAmount") WebElement field_BusinessOverdraftAmount;
	//What do you intend to use this overdraft for?
	@FindBy (name = "Business.ProductOverdraftIntendedUse") WebElement dropdown_HowWillYouUseOverdraft;
	
	//Business Savings Account 
	@FindBy (xpath = "//div[@data-name='Savings']//button") WebElement link_AddToApplication_BSA;
	
	@FindBy (xpath = "//input[@name='Business.ProductSavingsConfirmation']/following-sibling::label") WebElement checkbox_IConfirmAndAgreeToBMM;
	//Are you interested in receiving a call about Accepting Card Payments?
	@FindBy (xpath = "(//input[contains(@name,'Business.ProductAcceptCard')]/..)[1]") WebElement radio_receveCallAboutCardPayments_Yes;
	@FindBy (xpath = "(//input[contains(@name,'Business.ProductAcceptCard')]/..)[2]") WebElement radio_receveCallAboutCardPayments_No;
	//Chairperson of the meeting at which the resolutions were passed
	@FindBy (name = "Form.rel_Chairman") WebElement dropdown_ChairpersonOfTheMeeting; 
	//Date resolutions were entered in minute book
	@FindBy (xpath = "(//div[@name='Confirmation.mandateResolutionDate']//input)[1]") WebElement field_ResolutionsEntered_DD;
	@FindBy (xpath = "(//div[@name='Confirmation.mandateResolutionDate']//input)[2]") WebElement field_ResolutionsEntered_MM;
	@FindBy (xpath = "(//div[@name='Confirmation.mandateResolutionDate']//input)[3]") WebElement field_ResolutionsEntered_YY;
	//I being a director confirm my acceptance to the terms
	@FindBy (xpath = "//input[@name='Confirmation.signatureMandate']/following-sibling::label") WebElement checkbox_ConfirmMyAcceptance;
	//-----------------TEST CASES------------------------------------
	public ManagingYourAccountPage chooseSignatoryRules(int position)
	{
		if(position==1){
			Helper.tickRadioButton(radio_SignedByAnyOneSignatory);}
		else if(position==2){
			Helper.tickRadioButton(radio_SignedByAnyTwoSignatories);}
		else if(position==3){
			Helper.tickRadioButton(radio_Other);}
		else {
			Log.info("Option number: "+position+" does not exist. Try 1,2 or 3.");
			Assert.fail("Option number: "+position+" does not exist. Try 1,2 or 3.");
			}
		Log.info(Log.radioByIndex(position));
		return this;
	}
	
	public ManagingYourAccountPage changeYourSelection(boolean choice)
	{
		Helper.yesOrNo(choice, radio_ChangeYourSelection_Yes, radio_ChangeYourSelection_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public ManagingYourAccountPage confirmTermsAndConditions()
	{
		Helper.click(checkbox_IConfirmTermsAndConds);
		Log.info(Log.clickedObject(checkbox_IConfirmTermsAndConds));
		return this;
	}
	
	public ManagingYourAccountPage confirmTermsAndConditionsAsAMember()
	{
		Helper.click(checkbox_IConfirmTermsAndCondsAsAMember);
		Log.info(Log.clickedObject(checkbox_IConfirmTermsAndCondsAsAMember));
		return this;
	}
	
	public ManagingYourAccountPage howToReceiveActivationCode(String optionText)
	{
		Helper.chooseDropdown(dropdown_HowToReceiveActivationCode, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public ManagingYourAccountPage fillSecurityPassNumber(String text)
	{
		Helper.write(text, field_SecurityPassNumber);
		Log.info(Log.textField(text));
		return this;
	}
	public ManagingYourAccountPage fillConfirmSecurityPassNumber(String text)
	{
		Helper.write(text, field_ConfirmSecurityPassNumber);
		Log.info(Log.textField(text));
		return this;
	}
	public ManagingYourAccountPage fillMemorableWord(String text)
	{
		Helper.write(text, field_MemorableWord);
		Log.info(Log.textField(text));
		return this;
	}
	public ManagingYourAccountPage fillMemorablePlace(String text)
	{
		Helper.write(text, field_MemorablePlace);
		Log.info(Log.textField(text));
		return this;
	}
	
	public ManagingYourAccountPage clickAddApplicationBCC()
	{
		Helper.click(link_AddToApplication_BCC);
		Log.info(Log.clickedObject("Add To Application (BCC)"));
		return this;
	}
	
	public ManagingYourAccountPage fillCreditCardLimit(String text)
	{
		Helper.write(text, field_CreditCardLimit);
		Log.info(Log.textField(text));
		return this;
	}
	
	public ManagingYourAccountPage cachMachineWithdrawl(boolean choice)
	{
		Helper.yesOrNo(choice, radio_CachMachineWithdrawal_Yes, radio_CachMachineWithdrawal_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	public ManagingYourAccountPage businessNameOnCard(boolean choice)
	{
		Helper.yesOrNo(choice, radio_BusinessNameOnCard_Yes, radio_BusinessNameOnCard_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	public ManagingYourAccountPage howMuchViaDirectDebit(int position)
	{
		if(position==1) {
			Helper.tickRadioButton(radio_FullPayment);}
		else if(position==2) {
			Helper.tickRadioButton(radio_MinimumPayment);}
		else {
			Log.info("There are only position 1 and 2, you entered: "+position);
			Assert.fail("There are only position 1 and 2, you entered: "+position);
			}
		Log.info(Log.radioByIndex(position));
		return this;
	}
	
	public ManagingYourAccountPage howWillYouUseCard(String optionText)
	{
		Helper.chooseDropdown(dropdown_HowWillYouUseCard, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	public ManagingYourAccountPage fillMemorableWordSecurity(String text)
	{
		Helper.write(text, field_MemorableWordSecurityDetail);
		Log.info(Log.textField(text));
		return this;
	}
	
	public ManagingYourAccountPage clickSave()
	{
		Helper.click(button_Save);//TODO refactor this crap
		try {
			Helper.waitForLoadToFinish();
			button_Save.click();}
		catch(NoSuchElementException e){}
		Log.info(Log.clickedObject("Save"));
		return this;
	}
	
	public ManagingYourAccountPage clickAddApplicationBO()
	{
		Helper.click(link_AddToApplication_BO);
		Log.info(Log.clickedObject("Add to Application (BO)"));
		return this;
	}
	public ManagingYourAccountPage fillOverdraftAmount(String text)
	{
		Helper.write(text, field_BusinessOverdraftAmount);
		Log.info(Log.textField(text));
		return this;
	}
	public ManagingYourAccountPage howWillYouUseOverdraft(String optionText)
	{
		Helper.chooseDropdown(dropdown_HowWillYouUseOverdraft, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public ManagingYourAccountPage clickAddApplicationBSA()
	{
		Helper.click(link_AddToApplication_BSA);
		Log.info(Log.clickedObject("Add to Application (BSA)"));
		return this;
	}
	
	public ManagingYourAccountPage clickIConfirmToBMM()
	{
		//label of the checkbox has links that are interfering with the click, so I am first disabling them before clicking
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		try{
			js.executeScript("var e = document.getElementsByClassName('inline-link')[1];"
				+ "var d = document.createElement('span');d.innerHTML = e.innerHTML;e.parentNode.replaceChild(d, e);");
		}
		catch(WebDriverException e)
		{
			Helper.pauseFor(1);
			js.executeScript("var e = document.getElementsByClassName('inline-link')[1];"
					+ "var d = document.createElement('span');d.innerHTML = e.innerHTML;e.parentNode.replaceChild(d, e);");
		}
		Helper.tickCheckbox(checkbox_IConfirmAndAgreeToBMM);
		Log.info(Log.clickedObject(checkbox_IConfirmAndAgreeToBMM));
		return this;
	} 
	public ManagingYourAccountPage receveCallAboutCardPayments(boolean choice)
	{
		Helper.yesOrNo(choice, radio_receveCallAboutCardPayments_Yes, radio_receveCallAboutCardPayments_No);
		Log.info(Log.yesOrNo(choice));
		return this;
	}
	
	public ManagingYourAccountPage chooseChairpersonOfTheMeeting(String optionText)
	{
		Helper.chooseDropdown(dropdown_ChairpersonOfTheMeeting, optionText);
		Log.info(Log.dropdownChoice(optionText));
		return this;
	}
	
	public ManagingYourAccountPage fillResolutionsDate(String date)
	{
		Helper.fillDate(date, field_ResolutionsEntered_DD, field_ResolutionsEntered_MM, field_ResolutionsEntered_YY);
		Log.info(Log.textField(date));
		return this;
	}
	
	public ManagingYourAccountPage tickConfirmMyAcceptance()
	{
		Helper.tickCheckbox(checkbox_ConfirmMyAcceptance);
		Log.info(Log.clickedObject("I being a director confirm my acceptance to the terms"));
		return this;
	}
}
