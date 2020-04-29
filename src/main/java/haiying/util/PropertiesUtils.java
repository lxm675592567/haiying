package haiying.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	static Properties property = new Properties();
	static{
        try {  
            property.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("wx.properties"));
        } catch (IOException e) {  
            e.printStackTrace();  
   
        }     
	} 
    public static String getPropertyValue(String key){     
        return property.getProperty(key);  
    } 
    
    public static Properties getProperties() {
    	return property;
    }
}
