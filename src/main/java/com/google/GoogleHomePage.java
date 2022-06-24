package com.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.SeleniumUtilities;

//
//***************************************************************************************************************************************************
// The class is designed based on the principles of Page Object Model design pattern and implemented 
// using Page Factory class to instantiate Page objects
//***************************************************************************************************************************************************
//
public class GoogleHomePage 
{

private WebDriver driver;

//
// Page Factory to initialize web elements using a constructor for page class
//
public GoogleHomePage(WebDriver driver)
{
	this.driver = driver; 
	PageFactory.initElements(driver, this);
}

//
// Page Elements
//
@FindBy(xpath="//*[contains(text(),'I agree')]//parent::button | //*[contains(text(),'Accept all')]//parent::button")
WebElement Agree;

@FindBy(name="q")
private WebElement search_field;

@FindBy(name="btnK")
private WebElement submit;

//
// Page Methods
//
public void handleAgreeDialog()
{	
	SeleniumUtilities.click(driver,Agree);	
}
public void enterText(String text)
{		
	SeleniumUtilities.sendKeys(driver,search_field, text);	
}
public void clickSubmit()
{		
	SeleniumUtilities.jsClick(driver, submit);
}
public void verifyCoreElements()
{	
	Assert.assertTrue(SeleniumUtilities.waitForElementDisplayed(driver, search_field), "The Google Search text field should be displayed ");	
}
}
