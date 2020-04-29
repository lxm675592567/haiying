package haiying.service.auth.base;

import com.riversoft.weixin.common.exception.WxRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class WxEndpoint {

    private static Properties endpoints;

    private static synchronized void loadProperties() {
        if (endpoints == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = WxEndpoint.class.getClassLoader().getResourceAsStream("wx.properties");
                properties.load(inputStream);
                endpoints = properties;
            } catch (IOException e) {
                throw new WxRuntimeException(999, "cannot find resource wx-app-endpoint.properties from classpath.");
            }
        }
    }

    public static String get(String key) {
        loadProperties();
        return endpoints.getProperty(key);
    }

}
