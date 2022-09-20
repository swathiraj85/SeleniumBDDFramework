package browserManager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import browserOptions.ChromeBrowserOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

//***************************************************************************************************************************************************
//  The Chrome driver child class overriding the abstract method of Create driver with its own definition.
//
//****************************************************************************************************************************************************

public class ChromeDriverManager extends DriverManager{

	@Override
	public WebDriver createDriver() {
				
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		tl_driver.set(new ChromeDriver(ChromeBrowserOptions.chrome_options.get()));		
		return getDriver();
	}
}
