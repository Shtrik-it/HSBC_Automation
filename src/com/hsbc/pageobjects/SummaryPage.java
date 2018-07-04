package com.hsbc.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.hsbc.utility.Helper;
import com.hsbc.utility.Log;
import com.hsbc.utility.StaticDriver;

public class SummaryPage extends StaticDriver
{
	WebDriver driver;
	
	//-----------------CONSTRUCTOR-------------------------------
	public SummaryPage(WebDriver localDriver)
	{
		this.driver = localDriver;
		PageFactory.initElements(driver,this);
	}
	
	//-----------------PAGE OBJECTS------------------------------------
	
	//I confirm that I have read and agree to the Terms and Conditions, Pricing Information, UK FSCS Information Sheet and Exclusions List and forthcoming changes.
	@FindBy (xpath = "//input[@name='Confirmation.TermsAndConditionsSummary']/following-sibling::label") WebElement checkbox_IConfirmTermsAndConds;
	
	//I confirm that the business will not be part of an affinity group, or part of a group of companies with a global or national parent company, has not issued bearer shares.SummaryPage.
	@FindBy (xpath = "//input[@name='Confirmation.AffinityGroup']/following-sibling::label") WebElement checkbox_IConfirmNotPartOfAffinity;
	
	@FindBy (name="SubmitAccountApplication") WebElement button_SubmitAccountApplication;
	
	@FindBy (xpath = "(//div[@class='content-item status'])[1]") WebElement span_ApplicationSubmissionStatus;
	
	@FindBy (xpath = "//input[@name='Confirmation.CreditChecksSummary']/following-sibling::label") WebElement checkbox_IHaveReadTheInfoAbove;
	//-----------------TEST CASES------------------------------------
	
	public SummaryPage confirmTermsAndConditions()
	{
		pageLoaded();
		Helper.tickCheckbox(checkbox_IConfirmTermsAndConds);
		Log.info(Log.clickedObject(checkbox_IConfirmTermsAndConds));
		return this;
	}
	
	public SummaryPage confirmNotPartOfAffinity()
	{
		pageLoaded();
		Helper.tickCheckbox(checkbox_IConfirmNotPartOfAffinity);
		Log.info(Log.clickedObject(checkbox_IConfirmNotPartOfAffinity));
		return this;
	}
	
	public SummaryPage clickSubmitAccountApplicationButton()
	{
		pageLoaded();
		Helper.click(button_SubmitAccountApplication);
		Log.info(Log.clickedObject("Submit Account Application"));
		return this;
	}
	
	public SummaryPage checkIfApplicationWasSuccessful()
	{
		Helper.waitUntilClickable(span_ApplicationSubmissionStatus);
		String isCompleted = span_ApplicationSubmissionStatus.getText();
		Assert.assertEquals(isCompleted, "Completed");
		Log.info("APPLICATION SUBMISSION IS COMPLETED");
		return this;
	}
	
	public SummaryPage tickIHaveReadInfoAbove()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("return $('.scrollable-content').animate({scrollTop:'999999px'})");
		
		Helper.pauseFor(0.5);
		Helper.tickCheckbox(checkbox_IHaveReadTheInfoAbove);
		Log.info(Log.clickedObject("I have read the information above and I wish to submit this application"));
		return this;
	}
	
	//--------------------- PRIVATE METHODS ---------------------------
	private void pageLoaded()
	{
		wait10s.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Application reference:')]")));
	}
}
