package com.testpros;

import com.testpros.models.Careers;
import com.testpros.models.Contact;
import com.testpros.utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EndToEndIT extends TestBase {

    /**
     * Before each of our test cases run which will be our full workflows, ensure
     * they start on the main landing page - our base URL
     */
    @BeforeMethod
    public void goToPage() {
        drivers.get().get(getBaseUrl());
    }

    @Test
    public void sendMessage() {
        WebDriver driver = drivers.get();
        Contact contact = new Contact(driver);
        contact.navigateTo();
        contact.fillOutForm("Max Saperstone", "msaperstone@testpros.com", "571-245-3351", "Automation", "This is just a test message, enjoy the spam Tom!");
        contact.submitForm();
        contact.assertSent();
    }

    @Test
    public void searchForJob() {
        WebDriver driver = drivers.get();
        Careers careers = new Careers(driver);
        careers.navigateTo();
        careers.searchFor("tester");
        careers.assertSearched();
    }
}
