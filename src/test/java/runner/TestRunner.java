package runner;

import org.testng.annotations.DataProvider;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features= {"src/test/resources"},
		tags= {"@guardian_standard_ui_validation"},
		glue= {"stepDefinitions"},
	    plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/cucumber-report.json"}

)
public class TestRunner extends AbstractTestNGCucumberTests{
	

//	
//  ***************************************************************************************************************************************************
//    The cucumber test scenarios are enabled to run in parallel mode by overriding the scenarios method
//  *****************************************************************************************************************************************************
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}

	
}
