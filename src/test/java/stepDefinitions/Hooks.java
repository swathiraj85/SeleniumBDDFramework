package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	BaseStepData baseStepData;   
 
	// Inject the fields of BaseStepData class using constructor injection pattern
	public Hooks(BaseStepData baseStepData) {
		this.baseStepData = baseStepData;
	}

	@Before
	public void getScenario()
	{
     System.out.println("Before Scenario Hook");
	}

	@After
	public void tearDown(Scenario scenario) 
	{
       if(scenario.isFailed())
       {
    	   TakesScreenshot takesScreenshot = (TakesScreenshot)baseStepData.driver;
    	   byte[] srcBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
    	   scenario.embed(srcBytes, "image/png");
       }
       baseStepData.driver.close();
	}
}
