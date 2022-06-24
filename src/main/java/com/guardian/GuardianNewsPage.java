package com.guardian;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtilities;

//
// ***************************************************************************************************************************************************
// The class is designed based on the principles of Page Object Model design pattern and implemented 
// using Page Factory class to instantiate Page objects
//***************************************************************************************************************************************************
//
public class GuardianNewsPage extends GuardianHomePage
{
	private WebDriver driver;

	//
	// Page Factory to initialize web elements using a constructor for page class
	//
	public GuardianNewsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;  
		PageFactory.initElements(driver, this);
	}
	
	//
	// Page Elements
	//
	
	@FindBy(xpath="//section//a[@data-link-name='article'][contains(@class,'item')]")
	private List<WebElement> news_article_list;
	    
	@FindBy(xpath="//a[contains(@data-link-name,'Pagination view next')]")
	private WebElement next_page;
	
	@FindBy(xpath="//a[contains(@data-link-name,'Pagination view prev')]")
	private WebElement prev_page;
	
	//
	// Page Methods
	//
	public String getNthNewsArticle(int article_index)
	{
		String newsHeadline = "";
		if (SeleniumUtilities.waitForElementsDisplayed(driver, news_article_list))
		{			
			for(int i=0;i<news_article_list.size();i++)
			{
				if(i == article_index-1)
				{
					newsHeadline = SeleniumUtilities.getElementText(driver, news_article_list.get(i));
					break;
				}
			}
		}
		
		return newsHeadline;
		
	}
	public void navigateToNextPage()
	{
		SeleniumUtilities.click(driver, next_page);
	}
	public void navigateToPreviousPage()
	{
		SeleniumUtilities.click(driver, prev_page);
	}
}
