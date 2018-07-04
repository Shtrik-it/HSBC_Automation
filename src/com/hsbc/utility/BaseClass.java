package com.hsbc.utility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.hsbc.pageobjects.AboutYouPage;
import com.hsbc.pageobjects.BusinessDetailsPage;
import com.hsbc.pageobjects.BusinessFinancePage;
import com.hsbc.pageobjects.BusinessOverviewPage;
import com.hsbc.pageobjects.FastTrackApplicationModal;
import com.hsbc.pageobjects.GettingStartedPage;
import com.hsbc.pageobjects.ManagingYourAccountPage;
import com.hsbc.pageobjects.RelatedPersonsPage;
import com.hsbc.pageobjects.SharedObjects;
import com.hsbc.pageobjects.SummaryPage;
import com.hsbc.pageobjects.TaxDeclarationPage;

public class BaseClass 
{
	protected SharedObjects sharedObjects;
	protected GettingStartedPage gettingStartedPage;
	protected BusinessOverviewPage businessOverviewPage;
	protected BusinessDetailsPage businessDetailsPage;
	protected BusinessFinancePage businessFinancePage;
	protected AboutYouPage aboutYouPage;
	protected RelatedPersonsPage relatedPersonsPage;
	protected ManagingYourAccountPage managingYourAccountPage;
	protected TaxDeclarationPage taxDeclarationPage;
	protected SummaryPage summaryPage;
	protected FastTrackApplicationModal fastTrackApplicationModal;
	protected String url;
	
	public void initPageObjects(WebDriver driver) 
	{
		sharedObjects = new SharedObjects(driver);
		gettingStartedPage = new GettingStartedPage(driver);
		businessOverviewPage = new BusinessOverviewPage(driver);
		businessDetailsPage = new BusinessDetailsPage(driver);
		businessFinancePage = new BusinessFinancePage(driver);
		aboutYouPage = new AboutYouPage(driver);
		relatedPersonsPage = new RelatedPersonsPage(driver);
		managingYourAccountPage = new ManagingYourAccountPage(driver);
		taxDeclarationPage = new TaxDeclarationPage(driver);
		summaryPage = new SummaryPage(driver);
		fastTrackApplicationModal = new FastTrackApplicationModal(driver);
		StaticDriver.setDriver(driver);
	}
	
	@BeforeSuite (alwaysRun=true)
	public void beforeSuite() 
	{
		Log.startReport();
	}
	
	@AfterSuite (alwaysRun=true)
	public void afterSuite() 
	{
		Log.endReportAndSendData();
	}
}
