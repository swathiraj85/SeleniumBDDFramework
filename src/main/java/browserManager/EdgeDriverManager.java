package browserManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import browserOptions.EdgeBrowserOptions;

//***************************************************************************************************************************************************
//The Edge driver child class overriding the abstract methods for Create driver and get current driver with its own definition.
//
//****************************************************************************************************************************************************
public class EdgeDriverManager extends DriverManager
{

	@Override
	public WebDriver createDriver() {
		
		System.setProperty("webdriver.edge.driver", "");
		tl_driver.set(new EdgeDriver(EdgeBrowserOptions.edgeOptions.get()));
		return getDriver();
		
	}

	@Override
	public synchronized WebDriver getDriver() {
		
		return tl_driver.get();
	}

}
