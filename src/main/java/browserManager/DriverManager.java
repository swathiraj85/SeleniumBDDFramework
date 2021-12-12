package browserManager;

import org.openqa.selenium.WebDriver;

//***************************************************************************************************************************************************
//The class is designed as an abstract driver class with abstract methods for Create driver and get current driver which can be overridden by the child class.
//
//****************************************************************************************************************************************************
public abstract class DriverManager 
{
protected static ThreadLocal<WebDriver> tl_driver = new ThreadLocal<WebDriver>();
abstract public WebDriver createDriver();
abstract public WebDriver getDriver();
}
