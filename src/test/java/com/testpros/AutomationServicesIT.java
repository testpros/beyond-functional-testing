package com.testpros;

import com.testpros.models.Services;
import com.testpros.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AutomationServicesIT extends TestBase {

    @DataProvider(name = "services")
    public Object[][] services() {
        return new Object[][]{
                {0, "Test Automation"},
                {1, "Quality Gates"},
                {2, "Pipelines"},
                {3, "Environments"},
                {4, "Deployments"},
                {5, "Training"}
        };
    }

    /**
     * Before each of our test cases run which deal with the test automation
     * page, ensure they go to the page where the test automation sections reside (e.g.
     * the services/automation)
     */
    @BeforeMethod
    public void goToPage() {
        drivers.get().get(getBaseUrl() + "/services/automation/");
    }

    /**
     * Ensure we have the correct header displayed
     */
    @Test
    public void recentNewsTest() {
        WebDriver driver = drivers.get();
        assertEquals(driver.findElement(By.tagName("h1")).getText(), "Automation Services");
    }

    /**
     * Ensures that there are 6 widgets displayed for services
     */
    @Test
    public void allServicesExist() {
        WebDriver driver = drivers.get();
        Services services = new Services(driver);
        assertEquals(services.getServiceWidgets().size(), 6);
    }

    /**
     * Checks to ensure each of the 6 services are visible on the page
     */
    @Test(dataProvider = "services")
    public void serviceExists(int element, String service) {
        WebDriver driver = drivers.get();
        Services services = new Services(driver);
        assertEquals(services.getServiceWidgetHeader(element).getText(), service);
    }
}
