package utilities;

import java.util.Properties;

// ***************************************************************************************************************************************************
// the enum is designed based on the principles of Singleton design pattern to get the global variables.
// the instance is loaded only once and used for the entire execution which is inherently thread safe & serializable 
// ***************************************************************************************************************************************************
public enum ConfigSupplier 
{
INSTANCE;
	private final Properties properties;
	
	ConfigSupplier()
	{
		properties = PropertyUtils.loadGlobalProperties("\\src\\test\\resources\\config\\config.properties");
	}
	
	// Static getter
    public static ConfigSupplier getInstance()
    {
        return INSTANCE;
    }
 
    public String getBaseURI() 
	{
    	  String prop = properties.getProperty("baseURI");
          if(prop != null) return prop;
          else throw new RuntimeException("property fbaseURI is not specified in the config.properties file");		

	}

	public String getAPIToken() {
		String prop = properties.getProperty("apiToken");
        if(prop != null) return prop;
        else throw new RuntimeException("property apiToken is not specified in the config.properties file");

	}
}
