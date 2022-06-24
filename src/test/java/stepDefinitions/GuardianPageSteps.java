package stepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.guardian.GuardianNewsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GuardianPageSteps {

BaseStepData baseStepData;

GuardianNewsPage guardianNewsPage;

	//
	// Inject the fields of BaseStepData class using constructor injection pattern
	//
	public GuardianPageSteps(BaseStepData baseStepData)
	{
		this.baseStepData = baseStepData;
	}	
	
	@Given("^I accept the cookies on guardian home page$")
	public void acceptCookies()
	{
		guardianNewsPage =  new GuardianNewsPage(baseStepData.driver);
		guardianNewsPage.handleCookies();
		guardianNewsPage.verifyCoreElements();
	}
	@Given("^I fetch the content of \"(\\d+[st|th|rd|nd]*)\" news article$")
	public void getNewsArticle(String article_index)
	{
		guardianNewsPage =  new GuardianNewsPage(baseStepData.driver);		
		baseStepData.search_content = guardianNewsPage.getNthNewsArticle(Integer.parseInt(article_index.replaceAll("\\D", "")));
		System.out.println("The content of " + article_index + " news article is " + baseStepData.search_content);
		ExtentCucumberAdapter.addTestStepLog("The content of " + article_index + " news article is " + baseStepData.search_content);
	}
	
	@When("^I click on the next page$")
	public void navigateToNextPage()
	{
		guardianNewsPage =  new GuardianNewsPage(baseStepData.driver);
		guardianNewsPage.navigateToNextPage();
		
	}
	@When("^I click on the previous page$")
	public void navigateToPrevPage()
	{
		GuardianNewsPage guardianNewsPage =  new GuardianNewsPage(baseStepData.driver);
		guardianNewsPage.navigateToPreviousPage();
	}
	
	@Then("^I verify that \"([^\"]*)\" tab is displayed$")
	public void verifyTab(String tab_name) {
		guardianNewsPage =  new GuardianNewsPage(baseStepData.driver);
		guardianNewsPage.verifySection(tab_name);
	}

}
