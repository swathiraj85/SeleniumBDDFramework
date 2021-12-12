package stepDefinitions;

import java.util.List;
import java.util.Map;

import com.google.GoogleHomePage;

import browserManager.DriverFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class BrowserPage
{	
	BaseStepData baseStepData;
	
	// Inject the fields of BaseStepData class using constructor injection pattern
	public BrowserPage(BaseStepData baseStepData)
	{
		this.baseStepData = baseStepData;
	}
	
@Given("^I open the google application on \"([^\"]*)\" browser$")
public void openBrowser(String browser)
{
	baseStepData.driver = DriverFactory.getManager(browser).createDriver();
	System.out.println(browser+ " browser is chosen for execution");
	baseStepData.driver.get("https://www.google.com/");
	

}

@When("^I enter the below search text$")
public void enterSearchText(DataTable datatable)
{
	GoogleHomePage homepage = new GoogleHomePage(baseStepData.driver);
	List<Map<String,String>> browsers = datatable.asMaps(String.class,String.class);
	for(Map<String,String> browser :browsers )
	{
		String text = browser.get("text");
		homepage.enterText(text);
		
	}
}

@When("^I click the submit button$")
public void clickSubmit()
{
	
}
@When("^I verify that search results for \"([^\"]*)\" is displayed$")
public void verifySearchResults(String name)
{
	
}
}
