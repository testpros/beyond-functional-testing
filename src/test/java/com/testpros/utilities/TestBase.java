package com.testpros.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    // a threadsafe variable which we will use to pass out driver to each of our tests
    protected ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    /**
     * Provides access to the url that we will be testing. It should be specified as 'baseUrl'. If not set,
     * the production instance will be used. To override this value, set 'baseUrl' in the properties file
     * (src/test/resources/test.properties), or provide it as a command line value or environment variable.
     *
     * @return the url of the site under test
     */
    public static String getBaseUrl() {
        String baseUrl = Property.getProperty("baseUrl");
        if (baseUrl == null) {
            return "http://testpros.com";
        }
        return baseUrl;
    }

    /**
     * Determines which browser we will be testing with. By default, if no browser is specified, then Chrome will be
     * used. To override this value, set 'browser' in the properties file (src/test/resources/test.properties), or
     * provide it as a command line value or environment variable. Note, this browser must be installed locally on the
     * system.
     *
     * @return the browser to test with
     */
    public String getBrowser() {
        String browser = Property.getProperty("browser");
        if (browser == null) {
            return "chrome";
        }
        return browser;
    }

    /**
     * Before we run any of our tests, we need to setup our system for test. This involves determining the browser to
     * test with, and to configure it, and then launch it. Rather than downloading the proper webdriver drivers, we're
     * using WebDriverManager, which automagically downloads and sets them for us.
     */
    @BeforeMethod
    public void setupBrowser() {
        WebDriver driver;
        switch (getBrowser().toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().forceCache().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(true);
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().forceCache().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);
                chromeOptions.setAcceptInsecureCerts(true);
                driver = new ChromeDriver(chromeOptions);
        }
        driver.manage().window().setSize(new Dimension(1500, 1200));
        drivers.set(driver);
    }

    /**
     * After we run each of our tests, we need to tear down the system. But before we do, we are first going to take a
     * screenshot, and write it into the TestNG logger.
     *
     * @param result TestNG interface containing run details of the test; automatically provided by dependency injection
     */
    @AfterMethod(alwaysRun = true)
    public void destroyBrowser(ITestResult result) {
        WebDriver driver = drivers.get();
        String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        Reporter.setCurrentTestResult(result);
        Reporter.log("<img width='100%' src=\"data:image/png;base64," + screenshot + "\"/>");
        drivers.get().quit();
    }
}
