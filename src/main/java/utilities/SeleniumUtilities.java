package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtilities {
	

	public static String getPropertyValue(String key) {
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\globalConfig\\config.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// load a properties file
		try {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
	public static void sendKeys(WebElement element,String text)
	{
		try
		{
			element.sendKeys(text);
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");
			click(element);
		}
		
	}
	public static void click(WebElement element)
	{
		try
		{
			element.click();
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");
			click(element);
		}
		
	}
	public static void WaitForElementDisplayed(WebDriver driver,WebElement element)
	{
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(getPropertyValue("timeout"))));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
}
