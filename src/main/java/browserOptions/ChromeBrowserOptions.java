package browserOptions;

import java.util.function.Supplier;

import org.openqa.selenium.chrome.ChromeOptions;


//***************************************************************************************************************************************************
//       The Browser Options for Chromer Webdriver is specified in this class
//****************************************************************************************************************************************************

public class ChromeBrowserOptions {

	public static final Supplier<ChromeOptions> chrome_options = ()->
	{
		ChromeOptions  chromeOptions	= new ChromeOptions();
		//chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("start-maximized");
		return chromeOptions;
		
	};
}
