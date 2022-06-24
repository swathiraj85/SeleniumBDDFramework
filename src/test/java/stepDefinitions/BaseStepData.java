package stepDefinitions;

import org.openqa.selenium.WebDriver;

import browserManager.DriverFactory;
import browserManager.DriverManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utilities.ConfigSupplier;
import utilities.SeleniumUtilities;

//***************************************************************************************************************************************************
// The common fields that needs to be shared across multiple step definitions are specified in this class and utilized in the dependent classes using
//  dependency injection pattern
//****************************************************************************************************************************************************

public class BaseStepData {

	protected WebDriver driver;

	protected DriverManager driver_manager;

	protected String search_content;

	@Given("^I open the \"([^\"]*)\" application on \"([^\"]*)\" browser$")
	public void openBrowser(String application, String browser) {
		driver_manager = DriverFactory.getManager(browser);
		driver_manager.createDriver();
		System.out.println(browser + " browser is chosen for execution");
		driver = driver_manager.getDriver();
		SeleniumUtilities.openBrowser(driver, ConfigSupplier.getInstance().getApplicationURL(application));
		ConfigSupplier.getInstance().setCurrentBrowser(browser);
	}
	
	@And("^I close the browser$")
	public void closeBrowser() {
		DriverFactory.getManager(ConfigSupplier.getInstance().getCurrentBrowser()).getDriver().close();
	}
}
