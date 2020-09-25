package com.testpros.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class Careers {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By notice = By.id("notice");

    public Careers(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 20);
        this.driver = driver;
    }

    public void navigateTo() {
        this.driver.findElement(By.linkText("Careers")).click();
        this.driver.findElement(By.linkText("Current Openings")).click();
    }

    public void searchFor(String career) {
        driver.switchTo().frame("resumator-job-frame");
        this.driver.findElement(By.id("q")).sendKeys(career);
        this.driver.findElement(By.id("btn_search_jobs")).click();
    }

    public void assertSearched() {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(notice));
        assertEquals(driver.findElement(notice).getText(), "Cancel Search\nYour search results are below.");
    }
}
