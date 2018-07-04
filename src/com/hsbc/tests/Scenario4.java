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
public class Scenario4 extends BaseClass
{
	WebDriver driver;
	ITestResult result;
	PropertyReader read;
	String scenarioInfo = ScenarioConfigPaths.SCENARIO_4;
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
						  .clickTermsAndConditions()
						  .clickContinueButton();
	}
	
	@Test(dependsOnMethods="gettingStartedPage")
	public void bussinessOverviewPage()  
	{
		businessOverviewPage.fillBusinessStartDate(read.businessStartDate)
							.fillCompanyName(read.companyName)
						    .chooseWhatInfluencedYourDecision(read.whatInfluencedYourDecision)
						    .anyAccountsOutsideUK(false)
						    .fillWhatYourBusinessDoes(read.whatYourBusinessDoes)
						    .tickServices()
						    .fillDetailsOfTheServices(read.detailsOfTheService)
						    .tickPublicOrIndividuals()
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
											   .tickMemberTypesByPosition(new int[]{1,2})
											   .chooseCountryMajorityBasedIn(read.countryMajorityBasedIn)
											   .isPartOfFranchize(false)
											   .ownsThePremises(true)
										       .chooseTypeOfPremises(1)
											   .licenceToOperate(read.licenceToOperate)
											   .registeredForVAT(true).fillVATNumber(read.vatNumber)
											   .payTaxInUK(true)
										       .payTaxInOtherCountry(false);
		businessDetailsPage.internationalBusiness().dealWithOtherCountriesNext12Months(false)
						   						   .dealWithOtherCountriesAfterOneYear(false);
		businessDetailsPage.businessContactDetails().fillEmailAddress(read.businessEmailAddress)
						   							.fillContactNumber(read.businessContactNumber);
		businessDetailsPage.personalContactDetails().chooseTitle(read.personalTitle)
						   							.fillForenames(read.personalForenames)
						   							.fillSurname(read.personalSurname)
						   							.mainPointOfContact(true);
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="businessDetailsPage")
	public void businessFinancePage()  
	{
		businessFinancePage.fillTurnoverForNext12Months(read.turnoverForNext12Months)
						   .receivingSubscriptions(true)
						   .tickSubscriptionMethods(new int[]{1,2,3})
						   .receivingDonations(true)
						   .tickDonationMethods(new int[]{1,2,3})
					       .dealingWithCash(true)
						   .chooseCashAmountEachYear(read.cashAmountEachYear)
						   .chooseHowOftenCashDeposits(read.howOftenCashDeposits)
						   .chooseTypicalCashPayment(read.typicalCashPayment)
						   .fillInfoOnCashPayments(read.infoOnCashPayments);
		      sharedObjects.clickSaveAndContinueButton();
		  
		businessFinancePage.fillInitiallyInvested(read.initiallyInvested)
						   .anyAdditionalFunds(false)
						   .chooseSourceType(read.sourceType)
						   .fillInvestedAmount(read.investedAmount)
						   .fillAmountToBeDeposited(read.howMuchInvestmentWillBeDeposited)
						   .fillNumOfMembers(read.numOfMembers)
						   .chooseHowWillThisBePaidIn(read.howWillThisBePaidIn)
						   .clickGreenSaveButton();
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
					.debitCardWithAnotherBank(true)
					.creditCardWithAnotherBank(true)
					.homeAddressSameAsBusiness(true)
					.fillDateYouMovedIn(read.dateYouMovedIn)
					.homeAlsoCorrespondance(true);
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="aboutYouPage")
	public void relatedPersonsPage()  
	{
		relatedPersonsPage.choosePosition(read.position1)
						  .clickSaveButton()
		
						  .clickAddAnotherPartyButton()
						  .chooseTitle(read.realatedPersonsTitle)
						  .fillForenames(read.relatedPersonsForenames)
						  .fillSurname(read.relatedPersonsSurname)
						  .fillMobilePhoneNumber(read.relatedPersonsPhoneNumber)
						  .fillEmailAddress(read.relatedPersonsEmailAddress)
						  .hasRoleInBusiness(true)
						  .choosePosition(read.relatedPersonsPosition)
						  .signatoryInAccount(false)
						  .clickSaveButton();
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="relatedPersonsPage")
	public void managingYourAccountPage()  
	{
		managingYourAccountPage.tickConfirmMyAcceptance();
				  sharedObjects.clickSaveAndContinueButton();
				  
		managingYourAccountPage.confirmTermsAndConditions()
							   .howToReceiveActivationCode(read.howToReceiveActivationCode)
							   .fillSecurityPassNumber(read.securityPassNumber)
							   .fillConfirmSecurityPassNumber(read.confirmSecurityPassNumber)
							   .fillMemorableWord(read.memorableWord)
							   .fillMemorablePlace(read.memorablePlace);
		sharedObjects.clickSaveAndContinueButton();
							   
		managingYourAccountPage.receveCallAboutCardPayments(false);
				  sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="managingYourAccountPage")
	public void taxDeclarationPage()  
	{
		taxDeclarationPage.clickSelectButton(1)
						  .fillActiveTrade(read.activeTrade)
						  .agreeToFATCADeclaration(1)
						  .clickSelectButton(1)
						  .agreeToCRSDeclaration(1);
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
