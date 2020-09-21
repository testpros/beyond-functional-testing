package com.testpros;

import com.testpros.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RecentNewsIT extends TestBase {

    @BeforeMethod
    public void goToPage() {
        drivers.get().get(getBaseUrl());
    }

    @Test
    public void recentNewsTest() {
        WebDriver driver = drivers.get();
        assertTrue(driver.findElement(By.tagName("main")).isDisplayed());
    }

    @Test
    public void recentNewsHeaderTest() {
        WebDriver driver = drivers.get();
        WebElement header = driver.findElement(By.tagName("main")).findElement(By.tagName("h1"));
        assertTrue(header.isDisplayed());
        assertEquals(header.getText(), "Recent News");
    }

    @Test
    public void recentNewsThreeItems() {
        WebDriver driver = drivers.get();
        WebElement postsContainer = driver.findElement(By.tagName("main")).findElement(
                By.className("elementor-posts-container"));
        List<WebElement> articles = postsContainer.findElements(By.tagName("article"));
        assertEquals(articles.size(), 3);
    }
}
