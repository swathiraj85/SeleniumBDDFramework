package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.google.GoogleHomePage;
import com.google.GoogleSearchResultsHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class GooglePageSteps {
	BaseStepData baseStepData;
	GoogleHomePage homepage;
	GoogleSearchResultsHomePage googleSearchResultsHomePage;

	//
	// Inject the fields of BaseStepData class using constructor injection pattern
	//
	public GooglePageSteps(BaseStepData baseStepData) {
		this.baseStepData = baseStepData;
	}

	@And("I accept the cookies on google home page")
	public void acceptCookies() {
		 homepage = new GoogleHomePage(baseStepData.driver);
		homepage.handleAgreeDialog();
		homepage.verifyCoreElements();
	}

	@When("^I search other news article for the content fetched from \"([^\"]*)\"$")
	public void searchNewsContentfromOtherApp(String string_to_exclude) 
	{
		GoogleHomePage homepage = new GoogleHomePage(baseStepData.driver);
		homepage.enterText(baseStepData.search_content + " -" + string_to_exclude + ".com");
	}

	@When("^I enter the below search text$")
	public void enterSearchText(DataTable datatable) {
		 homepage = new GoogleHomePage(baseStepData.driver);
		List<Map<String, String>> search_list = datatable.asMaps(String.class, String.class);		
		for (Map<String, String> search_str : search_list) {
			String text = search_str.get("search_text");
			baseStepData.search_content = text;
			homepage.enterText(text);
		}
	}

	@When("^I click the submit button$")
	public void clickSubmit() {
		 homepage = new GoogleHomePage(baseStepData.driver);
		homepage.clickSubmit();
		System.out.println("clicked submit button");
	}

	@Then("^I verify that \"([^\"]*)\" section is displayed$")
	public void verifyTab(String tab_name) {
		googleSearchResultsHomePage =  new GoogleSearchResultsHomePage(baseStepData.driver);
		googleSearchResultsHomePage.verifySection(tab_name);
	}
	
	@When("^I verify that matching search results are displayed$")
	public void displaySearchResults() {
		GoogleSearchResultsHomePage googleSearchResultsHomePage = new GoogleSearchResultsHomePage(baseStepData.driver);		
		Assert.assertTrue(!(googleSearchResultsHomePage.captureSearchResults().isEmpty()), "The search results shouldn't be empty");		
		googleSearchResultsHomePage.displaySearchResults(googleSearchResultsHomePage.captureSearchResults());			
				
		
	}
	@When("^I verify that no search results are displayed$")
	public void displayNoSearchResults() {
		GoogleSearchResultsHomePage googleSearchResultsHomePage = new GoogleSearchResultsHomePage(baseStepData.driver);		
		Assert.assertTrue((googleSearchResultsHomePage.captureSearchResults().isEmpty()), "The search results should be empty");													
	}
	@When("^I validate that atleast \"(\\d+)\" words from the search results matches the search string$")
	public void validateSearchResults(int expected_match_count) {
		googleSearchResultsHomePage = new GoogleSearchResultsHomePage(baseStepData.driver);
		Assert.assertTrue(
				googleSearchResultsHomePage.validateSearchContent(baseStepData.search_content,
						googleSearchResultsHomePage.captureSearchResults(), expected_match_count),
				"No results matches the expected results match count");
	}
	
}
