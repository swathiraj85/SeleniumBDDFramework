package browserManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import browserOptions.EdgeBrowserOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

//***************************************************************************************************************************************************
//  The Edge driver child class overriding the abstract method of Create driver with its own definition.
//
//****************************************************************************************************************************************************
public class EdgeDriverManager extends DriverManager
{

	@Override
	public WebDriver createDriver() {
		
		WebDriverManager.edgedriver().setup();
		tl_driver.set(new EdgeDriver(EdgeBrowserOptions.edgeOptions.get()));
		return getDriver();
		
	}

}
