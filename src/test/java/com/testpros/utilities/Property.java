package com.testpros.utilities;

import org.testng.log4testng.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

    private static final Logger log = Logger.getLogger(Property.class);

    public static String getProperty(String property) {
        if( System.getProperty(property) != null ) {
            return System.getProperty(property);
        }
        if( System.getenv(property) != null) {
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
