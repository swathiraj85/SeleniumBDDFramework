package pages.com.google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import utilities.SeleniumUtilities;

//
// ***************************************************************************************************************************************************
// The class is designed based on the principles of Page Object Model design pattern and implemented 
// using Page Factory class to instantiate Page objects
// ***************************************************************************************************************************************************
//
public class GoogleSearchResultsHomePage extends GoogleHomePage
{
	private WebDriver driver;
	
	//
	// Page Factory to initialize web elements using a constructor for page class
	//
	public GoogleSearchResultsHomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//
	// Page Elements
	//	
	@FindBy(xpath="//h3//following::cite[@role='text'][1]")
	private List<WebElement> results_header_url_list;
	
	@FindBy(xpath="//cite[@role='text'][1]//preceding::h3[1]")
	private List<WebElement> results_header_title_list;
	
	//
	// Page Methods
	//	
	public void verifySection(String tab_name)
	{		
	Assert.assertTrue(SeleniumUtilities.waitForElementDisplayed(driver, driver.findElement(By.xpath("//*[contains(@class,'hdtb-mitem')]/a[contains(text(),'"+tab_name+"')]"))),"The element " + tab_name+ " is not displayed as expected"); 
	}	
	//
	// ***************************************************************************************************************************************************
	// Function name :  displaySearchResults
	// Description : The function to display the contents of Hash Map 
	// Input Parameters : HashMap
	// Output Parameters : None	
	// ***************************************************************************************************************************************************
	//
	public void displaySearchResults(HashMap<String,String> searchresults_map)
	{
		searchresults_map.entrySet().stream().forEach(e->
		{						
			 System.out.println("Website URL: "+"\""+e.getKey()+ "\""+"\t\t" + "Returned Text : "+"\""+e.getValue()+"\"");
			 ExtentCucumberAdapter.addTestStepLog("Website URL: "+"\""+e.getKey()+ "\""+"\t\t" + "Returned Text : "+"\""+e.getValue()+"\"");
		});
	}
	
	//
	// ***************************************************************************************************************************************************
	// Function name :  compareSubStr
	// Description : The function to compare the search string against an expected list with counts specified to match the number of sub strings with 
	// Input Parameters : String,List of String,int
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public boolean compareSubStr(String search_string,List<String> search_string_list,int expected_match_count)
	{
		int count = 0;
		
		for(String search_substr : search_string_list)
		{				
			if(search_string.contains(search_substr))	
				{	
				count = count+1;			
				}	
			//
			// return True if the expected word count of returned results matches with the search string
			//
			if(count==expected_match_count)
			{
				return true;
			}
		}			
		return false;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  validateSearchContent
	// Description : The function to validate if the search string is present with in the HashMap  
	// Input Parameters : String,HashMap,int
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public boolean validateSearchContent(String search_complete_string,HashMap<String,String> searchresults_map,int expected_match_count)
	{
		String search_string_list[] = search_complete_string.split(" ");				
		
		for(Entry<String, String> set : searchresults_map.entrySet())
		{						
			if( compareSubStr(set.getValue(),Arrays.asList(search_string_list),expected_match_count))
			{
				System.out.println("The website " + "\"" + set.getKey() +"\"" + " with the header text : "+"\"" + set.getValue() + "\""+ " &  the search string : \""  + search_complete_string+"\"");
				ExtentCucumberAdapter.addTestStepLog("The website " + "\"" + set.getKey() +"\"" + " with the header text : "+"\"" + set.getValue() + "\""+ " &  the search string : \""  + search_complete_string+"\"");
				return true;		   				
			}				
		}
		return false;		
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  captureSearchResults
	// Description : The function to store the google search results as an HashMap data structure with the URL as the Key and the header text as the value
	// Input Parameters : None
	// Output Parameters : HashMap	
	// ***************************************************************************************************************************************************
	//
	public HashMap<String,String> captureSearchResults()
	{
		HashMap<String,String> searchresults_map = new HashMap<String,String>();				
		
		if(results_header_url_list.size()>0)
		{
			for(int i=0;i<results_header_url_list.size();i++)
			{							
				searchresults_map.put(SeleniumUtilities.getElementText(driver, results_header_url_list.get(i)).split(" ")[0], SeleniumUtilities.getElementText(driver, results_header_title_list.get(i)));
			}
		}					
		return searchresults_map;
	}
}
