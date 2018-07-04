package com.hsbc.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hsbc.utility.BaseClass;
import com.hsbc.utility.BrowserFactory;
import com.hsbc.utility.GlobalPaths.ScenarioConfigPaths;
import com.hsbc.utility.Log;
import com.hsbc.utility.PropertyReader;
import com.hsbc.utility.TestNGListener;


@Listeners({TestNGListener.class})
public class Scenario1 extends BaseClass
{
	WebDriver driver;
	ITestResult result;
	PropertyReader read;
	String scenarioInfo = ScenarioConfigPaths.SCENARIO_1;
	
	//----------------------SETUP----------------------------------------
	@BeforeClass
	public void startBrowserAndInitClasses() 
	{
		read = new PropertyReader(scenarioInfo);
		url = BrowserFactory.getUrlChoice(scenarioInfo);
		driver = BrowserFactory.startBrowser(url);
		initPageObjects(driver);
		Log.startTestReport(this.getClass().getSimpleName());
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult result)
	{
		Log.endTestReport(driver, result);
	}
	
	//--------------------TESTS------------------------------------------
	@Test 
	public void gettingStartedPage()  
	{
		gettingStartedPage.haveAccountWithAnotherBank(false)
						  .chooseLegalStructureOfBusiness(read.legalStructureOfBusiness)
						  .everythingAboveIsTrue(true)
						  .aboutYourBusiness_Q1(true)
						  .aboutYourBusiness_Q2(false)
						  .aboutYourBusiness_Q3(false)
						  .aboutYourBusiness_Q4(false)
						  .clickTermsAndConditions()
						  .clickContinueButton();
	}
	
	@Test(dependsOnMethods="gettingStartedPage")
	public void bussinessOverviewPage()  
	{
		businessOverviewPage.chooseTitle(read.title)
						    .fillForenames(read.forenames)
						    .fillSurname(read.surname)
						    .fillBusinessStartDate(read.businessStartDate)
						    .chooseWhatInfluencedYourDecision(read.whatInfluencedYourDecision)
						    .anyAccountsOutsideUK(false)
						    .searchForActivity(read.searchForActivity).clickSearchActivityResultLink(read.searchActivityResultLink)
						    .fillWhatYourBusinessDoes(read.whatYourBusinessDoes)
						    .tickProducts()
						    .fillDetailsOfTheProducts(read.detailsOfTheProducts)
						    .tickPublicOrIndividuals()
						    .tickGovernmentOrPublicSector()
						    .chooseWhereBussinesOperatesFrom(read.whereBussinesOperatesFrom)
						    .fillPostcode(read.postcode)
						    .clickFindAddressButton().clickFindAddressResultLink(read.findAddressResultLink)
						    .fillEmailAddress(read.emailAddress)
						    .fillMobileNumber(read.mobileNumber)
						    .fillBirthDate(read.birthDate);
			   sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="bussinessOverviewPage")
	public void businessDetailsPage()  
	{
		businessDetailsPage.printApplicationNumber()
						   .businessStructure().fillHowManyEmployees(read.howManyEmployees)
						   					   .isPartOfFranchize(false)
						   					   .ownsThePremises(true)
						   					   .chooseTypeOfPremises(2)
						   					   .licenceToOperate(read.licenceToOperate)
						   					   .registeredForVAT(true)
						   					   .fillVATNumber(read.vatNumber)
						   					   .payTaxInUK(true)
						   					   .payTaxInOtherCountry(false);
		businessDetailsPage.internationalBusiness().dealWithOtherCountriesNext12Months(false)
						   						   .dealWithOtherCountriesAfterOneYear(true);
		businessDetailsPage.businessContactDetails().fillEmailAddress(read.businessEmailAddress)
						   						    .fillContactNumber(read.businessContactNumber);
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="businessDetailsPage")
	public void businessFinancePage()  
	{
		businessFinancePage.fillTurnoverForNext12Months(read.turnoverForNext12Months)
					       .fillForcastProfitNextYear(read.forcastProfitNextYear)
					       .relyOnOneCustomer(false)
					       .dealingWithCash(false);
		      sharedObjects.clickSaveAndContinueButton();
		  
		businessFinancePage.fillInitiallyInvested(read.initiallyInvested)
						   .anyAdditionalFunds(false)
						   .chooseSourceType(read.sourceType)
						   .clickEdit(1)
						   .fillAmountInGPBAndSave(read.amountInGBP1)
						   
						   .clickAddNewInvestorButton()
						   .chooseTitle(read.investorTitle)
						   .fillForenames(read.investorForenames)
						   .fillSurname(read.investorSurname)
						   .fillInvestedAmountInGBP(read.investedAmount)
						   .investorHoldsPosition(true)
						   .fillMobilePhone(read.investorMobilePhone)
						   .fillEmailAddress(read.investorEmailAddress)
						   .clickRedSaveButton()
		
						   .fillHowMuchInvestmentWillBeDeposited(read.howMuchInvestmentWillBeDeposited)
						   .chooseHowWillThisBePaidIn(read.howWillThisBePaidIn)
						   .saveTheForm();
		      sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="businessFinancePage")
	public void aboutYouPage()  
	{
		aboutYouPage.existingPersonalCustomer(false)
					.chooseMeritalStatus(read.meritalStatus)
					.chooseGender(1)
					.residentialStatus(read.residentialStatus)
					.residentToPayTaxInUk(true)
					.residentToPayTaxInOtherCountry(false)
					.citizenOfAnyOtherCountry(false)
					.grossIncomeLastYear(read.grossIncomeLastYear)
					.yearsWithExistingBank(read.yearsWithExistingBank)
					.debitCardWithAnotherBank(true)
					.creditCardWithAnotherBank(true)
					.homeAddressSameAsBusiness(true)
					.fillDateYouMovedIn(read.dateYouMovedIn)
					.homeAlsoCorrespondance(true);
		sharedObjects.clickSaveAndContinueButton();
		
		aboutYouPage.chooseSourceOfInvesment(read.sourceOfInvesment1, 1)
					.fillSourceAmount(read.sourceAmount1, 1);
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="aboutYouPage")
	public void relatedPersonsPage()  
	{
		relatedPersonsPage.rolesInBusiness(read.rolesInBusiness)
						  .clickSaveButton()
						  .choosePosition(read.position1)
						  .signatoryInAccount(true)
						  .clickSaveButton();
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="relatedPersonsPage")
	public void managingYourAccountPage()  
	{
		managingYourAccountPage.chooseSignatoryRules(1);
		sharedObjects.clickSaveAndContinueButton();
		
		managingYourAccountPage.changeYourSelection(false)
							   .confirmTermsAndConditions()
							   .howToReceiveActivationCode(read.howToReceiveActivationCode)
							   .fillSecurityPassNumber(read.securityPassNumber)
							   .fillConfirmSecurityPassNumber(read.confirmSecurityPassNumber)
							   .fillMemorableWord(read.memorableWord)
							   .fillMemorablePlace(read.memorablePlace);
		sharedObjects.clickSaveAndContinueButton();
		
		managingYourAccountPage.clickAddApplicationBCC()
							   .fillCreditCardLimit(read.creditCardLimit)
							   .cachMachineWithdrawl(true)
							   .businessNameOnCard(false)
							   .howMuchViaDirectDebit(1)
							   .howWillYouUseCard(read.howWillYouUseCard)
							   .fillMemorableWordSecurity(read.memorableWordSecurity)
							   .clickSave()
							   
							   .clickAddApplicationBO()
							   .fillOverdraftAmount(read.overdraftAmount)
							   .howWillYouUseOverdraft(read.howWillYouUseOverdraft)
							   .clickSave()
							   
							   .receveCallAboutCardPayments(true);
				  sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="managingYourAccountPage")
	public void taxDeclarationPage()  
	{
		taxDeclarationPage.agreeToPersonalTaxDeclaration(1);
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="taxDeclarationPage")
	public void summaryPage()  
	{
		summaryPage.confirmTermsAndConditions()
				   .confirmNotPartOfAffinity()
				   .tickIHaveReadInfoAbove()
				   .clickSubmitAccountApplicationButton()
				   .checkIfApplicationWasSuccessful();
	}
	
	@AfterClass
	public void tearDown()
	{
		BrowserFactory.closeBrowser();
	}
}
