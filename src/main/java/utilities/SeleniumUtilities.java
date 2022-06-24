package utilities;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtilities {
	    	
	//
	// ***************************************************************************************************************************************************
	// Function name :  sendKeys
	// Description : The function to enter the values on web element
	// Input Parameters : WebDriver,WebElement,String
	// Output Parameters : None	
	// ***************************************************************************************************************************************************
	//
	public static void sendKeys(WebDriver driver,WebElement element,String text)
	{
		try
		{
			//
			// wait for the element before entering the value
			//
			if (waitForElementClickable(driver,element));
			{			
				//
				// enter the text value
				//
				element.sendKeys(text);
			}
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");
			//
			// retry the operation
			//
			sendKeys(driver,element,text);
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
	}	
	//
	// ***************************************************************************************************************************************************
	// Function name :  click
	// Description : The function to click on an web element
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : None	
	// ***************************************************************************************************************************************************
	//
	public static void click(WebDriver driver,WebElement element) throws WebDriverException
	{
		try
		{
			//
			// wait for the element before entering the value
			//
			if (waitForElementClickable(driver,element));
			{
				//
				// click on the web element
				//
				element.click();
			}
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");
			
			//
			// retry the operation
			//
			click(driver,element);
		}
		catch(ElementClickInterceptedException e)
		{
			System.out.println("The web driver couldn't click using regular click.Retrying the action using JS click");
			
			//
			// retry the operation
			//
			jsClick(driver,element);
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  jsClick
	// Description : The function to click on an web element using JavaScript Executor
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : None	
	// ***************************************************************************************************************************************************
	//
	public static void jsClick(WebDriver driver,WebElement element)
	{
		try
		{
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
			javascriptExecutor.executeScript("arguments[0].click()", element);
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");
			
			//
			// retry the operation
			//
			jsClick(driver,element);
		}
		catch(ElementClickInterceptedException e)
		{
			System.out.println("The web driver couldn't click using regular click.Retrying the action using JS click");
			
			//
			// retry the operation
			//
			click(driver,element);
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  waitForElementDisplayed
	// Description : The function to wait for a web element to be displayed
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public static boolean waitForElementDisplayed(WebDriver driver,WebElement element)
	{		
		boolean b_found =  false;
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigSupplier.getInstance().getTimeOut())));
		try
		{	
		//
		// wait for the visibility of the element
		//
		wait.until(ExpectedConditions.visibilityOf(element));
		b_found =  true;
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element didn't load within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();			
		}	
		
		return b_found;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  waitForElementsDisplayed
	// Description : The function to wait for a list of web elements to be displayed
	// Input Parameters : WebDriver,List of WebElements
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public static boolean waitForElementsDisplayed(WebDriver driver,List<WebElement> elements)
	{
		boolean b_found =  false;
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigSupplier.getInstance().getTimeOut())));
		try
		{	
		//
		// wait for the visibility of all the elements
		//
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		b_found =  true;
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element didn't load within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();			
		}
		return b_found;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  waitForElementClickable
	// Description : The function to wait for a web element to be clickable
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public static boolean waitForElementClickable(WebDriver driver,WebElement element)
	{
		boolean b_found =  false;
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigSupplier.getInstance().getTimeOut())));
		try
		{
		//
		// wait for the element to perform click operation
		//
		wait.until(ExpectedConditions.elementToBeClickable(element));
	
		b_found =  true;
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element clickable property cannot be verified within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();			
		}
		return b_found;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  getElementText
	// Description : The function to retrieve the contents of web element
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : String	
	// ***************************************************************************************************************************************************
	//
	public static String getElementText(WebDriver driver,WebElement element)
	{
		String elementText = "";
		
		try
		{
			//
			// wait for the element before entering the value
			//
			if (waitForElementDisplayed(driver,element));
			{
				//
				// click on the web element
				//
				elementText = element.getText().trim();
			}
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");
			
			//
			// retry the operation
			//
			click(driver,element);
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
		return elementText;	
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  validateBrowserTitle
	// Description : The function to validate if the browser contains the specified title text
	// Input Parameters : WebDriver,String
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public static boolean validateBrowserTitle(WebDriver driver,String title)
	{	
		boolean b_found	= false;	
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigSupplier.getInstance().getTimeOut())));
		try
		{	
		//
		// wait for the browser title to contain the substring
		//
			b_found = wait.until(ExpectedConditions.titleContains(title));		
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element didn't load within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();			
		}
		return b_found;			
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  openBrowser
	// Description : The function to open the browser using the given url
	// Input Parameters : WebDriver,String
	// Output Parameters : WebDriver	
	// ***************************************************************************************************************************************************
	//
	public static WebDriver openBrowser(WebDriver driver,String url)
	{
		try
		{
			driver.get(url);			
			System.out.println("The title of the url is " + driver.getTitle());
		}
		catch(WebDriverException e)
		{
			System.out.println("An exception has occured while opening the URL");
			throw e;
		}
		return driver;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  swictchToIframe
	// Description : The function to switch to an IFrame
	// Input Parameters : WebDriver,String
	// Output Parameters : WebDriver	
	// ***************************************************************************************************************************************************
	//
	public static WebDriver swictchToIframe(WebDriver driver,String nameOrID)
	{
		try
		{
			driver.switchTo().frame(nameOrID);			
		}
		catch(NoSuchFrameException e)
		{
			System.out.println("A frame with the specified name or Id " + nameOrID+ " couldn't be located");
			throw e;
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
		return driver;
	}
}
