package com.hsbc.utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hsbc.utility.GlobalPaths.FilePaths;


public class Helper extends StaticDriver
{
	
	/**Waits for element to be clickable, clicks it and waits for the page loader to complete
	 * @param element to be clicked
	 * @author milos
	 */
	public static void click(WebElement element) 
	{ 
		waitForLoadToFinish();
		wait10s.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		waitForLoadToFinish();
	}
	
	/**Waits for element to be clickable, clicks it and waits for the page loader to complete
	 * @param By object to be clicked
	 * @author milos
	 */
	public static void click(By element) 
	{ 
		waitForLoadToFinish();
		wait10s.until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(element).click();
		waitForLoadToFinish();
	}
	
	/**Custom method for filling in text fields and text areas
	 * @param text to input
	 * @param element to put text into
	 * @author milos
	 */
	public static void write(String text, WebElement element) 
	{ 
		waitForLoadToFinish();
		wait10s.until(ExpectedConditions.elementToBeClickable(element));
		waitForLoadToFinish();
		element.clear();
		waitForLoadToFinish();
		element.sendKeys(text);
		waitForLoadToFinish();
		if(!textIsWrittenCorrectly(text, element))
		{
			element.clear();
			waitForLoadToFinish();
			element.sendKeys(text);
			waitForLoadToFinish();
		}
	}
	
	/** Used for quickly parsing single date input into 3 values for day, month and year, and filling in the passed fields
	 * @param text
	 * @param field_Day
	 * @param field_Month
	 * @param field_Year
	 * @author milos
	 */
	public static void fillDate(String text, WebElement field_Day, WebElement field_Month, WebElement field_Year)
	{
		String[] date = text.split("\\.");
		String day = date[0];
		String month = date[1];
		String year = date[2];
		
		Helper.write(day, field_Day);
		Helper.write(month, field_Month);
		Helper.write(year, field_Year);
	}
	/**Handles YES/NO radio buttons or any other radios with two contrary choices.
	 * @param answer Pass in a boolean true or false if you want the checkbox to be ticked on YES or NO answer respectively.
	 * @param button_Yes Pass in the webelement for Yes
	 * @param button_No Pass in the webelement for No
	 * @author milos
	 */
	public static void yesOrNo(boolean answer, WebElement radio_Yes, WebElement radio_No) 
	{ 
		if(answer)
		{
			tickRadioButton(radio_Yes);
		}
		else
		{
			tickRadioButton(radio_No);
		}
	}
	
	/**Handy method for handling aplication's dropdowns since sometimes just focusing on an element triggers loader
	 * which can block the clicking and expanding of the dropdown.
	 * @param dropdown WebElement of the dropdown
	 * @param optionName String name of the dropdown
	 * @author milos
	 */
	public static void chooseDropdown(WebElement dropdown, String optionName)
	{
		click(dropdown);
		pauseFor(0.3);
		if(!existsInDOM(By.className("select-open")))
		{
			click(dropdown);
		}
		if(Helper.getCurrentBrowser().equalsIgnoreCase("firefox"))
		{
			scrollDropdownAndClickOption(optionName);
		}
		else
		{
			click(dropdownOption(optionName));
		}
		waitForLoadToFinish();
	}

	/**Used for pausing the driver. Should only be used when Selenium's Wait methods don't do the trick.
	 * @param seconds the amount of seconds to pause the driver 
	 * @author milos
	 */
	public static void pauseFor(double seconds)
	{
		try
		{
			double miliseconds = seconds*1000;
			Thread.sleep((long) miliseconds);
		}
		catch(InterruptedException e)
		{
			System.out.println(e);
		}
	}
	
	
	/**Clicks the search result link by passing the expected link text and result list div since it needs to be clickable first.
	 * @param resultLinkText
	 * @param resultList
	 * @author milos
	 */
	public static void clickSearchResultLink(String resultLinkText, WebElement resultList)
	{
		Helper.waitUntilClickable(resultList);
		Helper.click(driver.findElement(By.xpath("//span[normalize-space()='"+resultLinkText+"']")));
	}
	
	
	/**A packed and ready to go method for 'wait until clickable' action, already initialized for quick access.
	 * @param element
	 * @author milos
	 */
	public static void waitUntilClickable(WebElement element)
	{
		wait10s.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	/**Special method for ticking checkboxes. It tries ticking the checkbox, then checks if it is actually ticked,
	 * if not, it ticks it again. A solution for when the page loader interferes with clicking.
	 * @param idOfLabelFor
	 * @author milos
	 */
	public static void tickCheckbox(WebElement checkbox)
	{
		click(checkbox);
		WebElement checkboxWrapper = driver.findElement(By.xpath(getAbsoluteXPath(checkbox, "")+"/.."));
		if (!checkboxWrapper.getAttribute("class").contains("active"))
		{
			click(checkbox);
		}
		waitForLoadToFinish();
	}
	
	
	
	/**For checboxes that have the same locator, you can pass an array of checkbox positions (starting from position 1)
	 * and tick them in one go. e.g. new int[]{1,2,3} 
	 * @param checkboxList 
	 * @param elementList
	 * @param elementListTicked
	 * @author milos
	 */
	public static void tickMultiCheckbox(int[] checkboxList, List <WebElement> elementList, List <WebElement> elementListTicked)
	{
		for(int i: checkboxList)
		{
			click(elementList.get(i-1));
			if(!checkboxIsTicked(i, elementListTicked))
			{
				Helper.click(elementList.get(i-1));
			}
			Log.info(Log.clickedObject(elementList.get(i-1)));
		}
	}
	
	/**In situations where a classic click wont do the trick you can use this method to tick a radio button
	 * which checks if it is ticked, if not it will tick it again. Again just a failsafe in case of page loader interference. 
	 * @param radioButton
	 * @author milos
	 */
	public static void tickRadioButton(WebElement radioButton)
	{
		click(radioButton);
		boolean isClicked = radioButton.getAttribute("class").contains("active");
		if(!isClicked)
		{
			click(radioButton);
		}
		waitForLoadToFinish();
	}
	
	
	/**Check if an element exists in DOM at all. Can only take a By object, not a WebElement.
	 * @param byObject
	 * @return true if element exists in DOM
	 * @author milos
	 */
	public static boolean existsInDOM(By byObject)
	{
		return driver.findElements(byObject).size() > 0;
	}
	
	
	
	/**Given that firefox browser can be a bit slower than chrome this method will pause for the amount of 
	 * seconds specified only the ff browser.
	 * @param seconds
	 * @author milos
	 */
	public static void waitIfFirefox(double seconds)
	{
		if(getCurrentBrowser().equalsIgnoreCase("firefox"))
		{
			pauseFor(seconds);
		}
	}
	
	public static String getCurrentBrowser()
	{
		return FileReader.read(FilePaths.CONFIG, "CHOSEN_BROWSER");
	}
	
	/**
	 * This methods returns absolute x path of a given webelement
	 * @param childElement 
	 * @param current
	 * @return absolute xPath as a string
	 * @author milosh
	 */
	public static String getAbsoluteXPath(WebElement childElement, String current) 
	{
	    String childTag = childElement.getTagName();
	    if(childTag.equals("html")) 
	    {
	        return "/html[1]"+current;
	    }
	    WebElement parentElement = childElement.findElement(By.xpath("..")); 
	    List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
	    int count = 0;
	    for(int i=0;i<childrenElements.size(); i++) 
	    {
	        WebElement childrenElement = childrenElements.get(i);
	        String childrenElementTag = childrenElement.getTagName();
	        if(childTag.equals(childrenElementTag)) 
	        {
	            count++;
	        }
	        if(childElement.equals(childrenElement)) 
	        {
	            return getAbsoluteXPath(parentElement, "/" + childTag + "[" + count + "]"+current);
	        }
	    }
	    return null;
	}
	
	public static boolean isAttributePresent(WebElement element, String attribute) {
	    Boolean result = false;
	    try {
	        String value = element.getAttribute(attribute);
	        if (value != null){
	            result = true;
	        }
	    } catch (Exception e) {}

	    return result;
	}
	
	
	//------------------------- PRIVATE METHODS ------------------------------------------------------
	
	/**Returns true if text inside a text field is as expected
	 * @param textInput
	 * @param element
	 */
	private static boolean textIsWrittenCorrectly(String textInput, WebElement element)
	{
		if(textInput == null)
		{
			textInput = "";
		}
		
		Boolean isTextCorrect = false;
		String actualText = element.getAttribute("value");
		if(actualText.equals(textInput))
		{
			isTextCorrect = true;
		}
		return isTextCorrect;
	}
	
	private static WebElement dropdownOption(String optionName)
	{
		return driver.findElement(By.xpath("//li[text()='"+optionName+"']"));	
	}
	
	public static void waitForLoadToFinish()
	{
		wait20s.until(ExpectedConditions.and(ExpectedConditions.invisibilityOfElementLocated(By.className("bbform-loading")),
											 ExpectedConditions.invisibilityOfElementLocated(By.className("bbform-indicator"))));
	}
	
	private static boolean checkboxIsTicked(int i, List <WebElement> ticked) 
	{
		try{
			ticked.get(i-1).isDisplayed();
				return true;
		}
		catch(IndexOutOfBoundsException e)
		{
			return false;
		}
	}
	
	/**This is a custom hack for Firefox driver only. For what ever reason, people that make firefox driver decided that
	 * it is a good idea to not click the webelement itself but to click what ever is on the coordinates of that element,
	 * resulting in different behavior than chrome driver and the necessity for this method.
	 * @param optionName text from the dropdown option
	 * @author milos
	 */
	private static void scrollDropdownAndClickOption(String optionName)
	{
		WebElement option = dropdownOption(optionName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		String firstLetter = optionName.substring(0, 1); //Get the first letter of the dropdown option text
		action.sendKeys(firstLetter).perform(); //Typing the first letter of dropdown option, thus scrolling the dropdown closer to option needed
		for(int i=1;i<=10;i++)
        {
			try
			{
				action.moveToElement(option).perform(); //Hovers over element to trigger background color change
			}
			catch(MoveTargetOutOfBoundsException e) //If element is outside of the page, scroll the page for 250px
			{
				long pagePosition = (long)js.executeScript("return Math.floor(window.pageYOffset);");
				long pp = pagePosition+250;
				js.executeScript("scroll(0, "+pp+");");
				action.moveToElement(option).perform(); // Try again to hover the element
			}
			
			pauseFor(0.3);
			if(!option.getCssValue("background-color").toString().equals("rgba(0, 0, 0, 0)")) // if bg-color changed that means the element is clickable
			{
				option.click(); // Click the element and exist the loop
				break;
			}
			else
			{
				long scrollableDivPosition = (long) js.executeScript("return $('.select-content').scrollTop()"); // Get the current scrolling position of the dropdown div
				int additionalScroll = 100*i;
				long newPos = scrollableDivPosition + additionalScroll;
	        	js.executeScript("$('.select-content').animate({scrollTop:'"+newPos+"px'})"); //Scroll the dropdown div by 100px
	        	pauseFor(0.3);
			}
        }
	}
	

}
