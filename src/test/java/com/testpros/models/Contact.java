package com.testpros.models;

import com.testpros.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

public class Contact {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By submitButton = By.cssSelector("button[type='submit']");

    public Contact(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 20);
        this.driver = driver;
    }

    public void navigateTo() {
        this.driver.findElement(By.className("elementor-button-link")).click();
    }

    public void fillOutForm(String name, String email, String number, String request, String message) {
        this.driver.findElement(By.id("form-field-name")).sendKeys(name);
        this.driver.findElement(By.id("form-field-email")).sendKeys(email);
        this.driver.findElement(By.cssSelector("input[type='tel']")).sendKeys(number);
        Select requestSelector = new Select(this.driver.findElement(By.className("elementor-field-type-select")).findElement(By.tagName("select")));
        requestSelector.selectByVisibleText(request);
        this.driver.findElement(By.id("form-field-message")).sendKeys(message);
    }

    public void submitForm() {
        this.driver.findElement(this.submitButton).click();
    }

    public void assertSent() {
        this.wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(this.submitButton)));
        assertFalse(this.driver.findElement(this.submitButton).isEnabled());
        this.wait.until(ExpectedConditions.urlContains("thank-you"));
        assertTrue(this.driver.getCurrentUrl().endsWith("/thank-you/"));
    }
}
