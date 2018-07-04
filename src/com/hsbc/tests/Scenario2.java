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
public class Scenario2 extends BaseClass
{
	WebDriver driver;
	ITestResult result;
	PropertyReader read;
	String scenarioInfo = ScenarioConfigPaths.SCENARIO_2;
	
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
	public void fastTrackTheApplication()  
	{
		fastTrackApplicationModal.fillCompaniesHouseNo(read.companiesHouseNo)
								 .clickSearchButton()
								 .clickSearchResultLink(read.fastTrackSearchResultLink)
								 .clickContinueButton();
	}

	@Test(dependsOnMethods="fastTrackTheApplication")
	public void bussinessOverviewPage()  
	{
		businessOverviewPage.chooseWhatInfluencedYourDecision(read.whatInfluencedYourDecision)
						    .anyAccountsOutsideUK(false)
						    .searchForActivity(read.searchForActivity).clickSearchActivityResultLink(read.searchActivityResultLink)
						    .fillWhatYourBusinessDoes(read.whatYourBusinessDoes)
						    .tickProducts()
						    .fillDetailsOfTheProducts(read.detailsOfTheProducts)
						    .tickPublicOrIndividuals()
						    .tickGovernmentOrPublicSector()
						    .chooseWhereBussinesOperatesFrom(read.whereBussinesOperatesFrom)
						    .fillEmailAddress(read.emailAddress)
						    .fillMobileNumber(read.mobileNumber)
						    .fillBirthDate(read.birthDate);
			   sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="bussinessOverviewPage")
	public void businessDetailsPage()  
	{
		businessDetailsPage.printApplicationNumber()
						   .businessStructure().is25PercentOwnedByFamily(false)
											   .isPartOfFranchize(false)
											   .ownsThePremises(true)
											   .chooseTypeOfPremises(2)
											   .licenceToOperate(read.licenceToOperate)
											   .registeredForVAT(true).fillVATNumber(read.vatNumber)
											   .payTaxInUK(true)
											   .payTaxInOtherCountry(false);
		businessDetailsPage.internationalBusiness().dealWithOtherCountriesNext12Months(false)
						   						   .dealWithOtherCountriesAfterOneYear(true);
		businessDetailsPage.businessContactDetails().fillEmailAddress(read.businessEmailAddress)
						   							.fillContactNumber(read.businessContactNumber);
		businessDetailsPage.personalContactDetails().chooseTitle(read.personalTitle)
						   							.mainPointOfContact(true);
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
					.chooseGender(2)
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
					.fillSourceAmount(read.sourceAmount1, 1)
					
					.clickAddNewSourceButton()
					.chooseSourceOfInvesment(read.sourceOfInvesment2, 2)
					.fillSourceAmount(read.sourceAmount2, 2)
					.chooseOcupationType(read.ocupationType)
					.chooseOcupation(read.ocupation)
					.fillEmployersName(read.employersName);
					aboutYouPage.fillHowManyYearsEmployed(read.howManyYearsEmployed);
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="aboutYouPage")
	public void relatedPersonsPage()  
	{
		relatedPersonsPage.choosePosition(read.position1)
						  .shareholderWith25Percent(true)
						  .fillPercentageShareholding(read.percentageShareholding)
						  .clickSaveButton()
						  
						  .chooseContactToAdd(read.contactToAdd).clickSelectButton()
						  .chooseTitle(read.realatedPersonsTitle)
						  .fillMobilePhoneNumber(read.relatedPersonsPhoneNumber)
						  .fillEmailAddress(read.relatedPersonsEmailAddress)
						  .hasRoleInBusiness(false)
						  .choosePosition(read.relatedPersonsPosition)
						  .shareholderWith25Percent(false)
						  .signatoryInAccount(false)
						  .clickSaveButton();
		sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="relatedPersonsPage")
	public void managingYourAccountPage()  
	{
		managingYourAccountPage.chooseChairpersonOfTheMeeting(read.chairpersonOfTheMeeting)
							   .fillResolutionsDate(read.resolutionsDate)
							   .tickConfirmMyAcceptance();
		sharedObjects.clickSaveAndContinueButton();
		
		managingYourAccountPage.changeYourSelection(false)
							   .confirmTermsAndConditions()
							   .howToReceiveActivationCode(read.howToReceiveActivationCode)
							   .fillSecurityPassNumber(read.securityPassNumber)
							   .fillConfirmSecurityPassNumber(read.confirmSecurityPassNumber)
							   .fillMemorableWord(read.memorableWord)
							   .fillMemorablePlace(read.memorablePlace);
		sharedObjects.clickSaveAndContinueButton();
		
		managingYourAccountPage.clickAddApplicationBSA()
							   .clickIConfirmToBMM()
							   .clickSave()
							   
							   .receveCallAboutCardPayments(true);
				  sharedObjects.clickSaveAndContinueButton();
	}
	
	@Test(dependsOnMethods="managingYourAccountPage")
	public void taxDeclarationPage()  
	{
		taxDeclarationPage.agreeToFATCADeclaration(1);
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