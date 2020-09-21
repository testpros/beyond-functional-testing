package com.testpros.utilities;

import org.testng.log4testng.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

    private static final Logger log = Logger.getLogger(Property.class);

    /**
     * Retrieves the specified program property. If it exists from the system properties, that is returned, overridding
     * all other values. Otherwise, if it exists from the properties file, that is returned, otherwise, null is returned
     *
     * @param property - what property value to return
     * @return String: the property value, null if unset
     */
    public static String getProperty(String property) {
        if (System.getProperty(property) != null) {
            return System.getProperty(property);
        }
        if (System.getenv(property) != null) {
            return System.getenv(property);
        }
        Properties prop = new Properties();
        try (InputStream input = TestBase.class.getClassLoader().getResourceAsStream("test.properties")) {
            prop.load(input);
        } catch (NullPointerException | IOException e) {
            log.info(e);
        }
        String fullProperty = prop.getProperty(property);
        if (fullProperty != null) {
            return fullProperty.trim();
        }
        return null;
    }
}
