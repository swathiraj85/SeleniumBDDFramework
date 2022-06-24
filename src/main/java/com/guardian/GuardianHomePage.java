package com.guardian;

import org.openqa.selenium.By;
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
public class GuardianHomePage 
{
	private WebDriver driver;

	//
	// Page Factory to initialize web elements using a constructor for page class
	//
	public GuardianHomePage(WebDriver driver)
	{
		this.driver = driver;  
		PageFactory.initElements(driver, this);
	}
	
	//
	// Page Elements
	//
	@FindBy(xpath="//button[contains(@title,'happy')]")
	WebElement accept_cookies;
	
	@FindBy(xpath="//a[contains(@data-link-name,'signin')]")
	private WebElement signIn;
	
	@FindBy(xpath="//a[contains(@href,'theguardian.com')][contains(@data-link-name,'logo')]")
	private WebElement newsLogo;
			
	@FindBy(id="main-menu-toggle")
	private WebElement guardianMainMenu;
	
	String frame_id_cookies = "sp_message_iframe_658013";
	
	//
	// Page Methods
	//	
	public void handleCookies()
	{			
		SeleniumUtilities.swictchToIframe(driver, frame_id_cookies);
		SeleniumUtilities.click(driver,accept_cookies);	
		driver.switchTo().defaultContent();
	}	
	
	public void expandGuardianMainMenu()
	{
		SeleniumUtilities.click(driver, guardianMainMenu);
	}
	public void navigateToSection(String tab_name)
	{
		SeleniumUtilities.click(driver, driver.findElement(By.xpath("//ul[@class='pillars']//a[contains(@data-link-name,'"+tab_name+"')]")));
	}	
	public void verifySection(String tab_name)
	{		
	Assert.assertTrue(SeleniumUtilities.waitForElementDisplayed(driver, driver.findElement(By.xpath("//ul[@class='pillars']//a[contains(@data-link-name,'"+tab_name+"')]"))),"The element " + tab_name+ " is not displayed as expected"); 
	}	
	
	public void verifyCoreElements()
	{	
		Assert.assertTrue(SeleniumUtilities.waitForElementDisplayed(driver, signIn), "The Guardian news tab should be displayed ");
		Assert.assertTrue(SeleniumUtilities.waitForElementDisplayed(driver, newsLogo), "The Guardian Logo should be displayed  ");	
	}
	
}  
