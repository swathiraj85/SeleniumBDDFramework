package com.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtilities;

//***************************************************************************************************************************************************
// The class is designed based on the principles of Page Object Model design pattern and implemented 
// using Page Factory class to instantiate Page objects
//***************************************************************************************************************************************************
public class GoogleHomePage 
{

private WebDriver driver;

public GoogleHomePage(WebDriver driver)
{
	this.driver = driver; 
	PageFactory.initElements(driver, this);
}

@FindBy(xpath="//*[contains(text(),'I agree')]//parent::button")
WebElement Agree;

@FindBy(xpath="//*[@title='Search']")
private WebElement search_field;

public void enterText(String text)
{
	SeleniumUtilities.WaitForElementDisplayed(driver, Agree);
	SeleniumUtilities.click(Agree);
	SeleniumUtilities.WaitForElementDisplayed(driver, search_field);
	SeleniumUtilities.sendKeys(search_field, text);
}
}
