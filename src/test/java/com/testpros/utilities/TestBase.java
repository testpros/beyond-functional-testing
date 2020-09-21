package com.testpros.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
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

    protected ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public String getBaseUrl() {
        String baseUrl = Property.getProperty("baseUrl");
        if (baseUrl == null) {
            return "http://testpros.com";
        }
        return baseUrl;
    }

    public String getBrowser() {
        String browser = Property.getProperty("browser");
        if (browser == null) {
            return "chrome";
        }
        return browser;
    }

    @BeforeMethod
    public void setupBrowser() {
        WebDriver driver;
        switch(getBrowser().toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().forceCache().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(true);
                driver = new FirefoxDriver(firefoxOptions);
            case "chrome":
            default:
                WebDriverManager.chromedriver().forceCache().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);
                driver = new ChromeDriver(chromeOptions);
        }
        drivers.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void destroyBrowser(ITestResult result) {
        WebDriver driver = drivers.get();
        String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        Reporter.setCurrentTestResult(result);
        Reporter.log("<img src=\"data:image/png;base64," + screenshot + "\"/>");
        drivers.get().quit();
    }
}
