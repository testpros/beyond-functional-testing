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

    /**
     * Before each of our test cases run which deal with the recent news
     * widget, ensure they go to the page where the recent news resides (e.g.
     * the main landing page - our base URL)
     */
    @BeforeMethod
    public void goToPage() {
        drivers.get().get(getBaseUrl());
    }

    /**
     * Ensure the container holding the recent news is present and displayed
     */
    @Test
    public void recentNewsTest() {
        WebDriver driver = drivers.get();
        assertTrue(driver.findElement(By.tagName("main")).isDisplayed());
    }

    /**
     * Ensure that the header for the recent news reads properly - 'Recent News'
     */
    @Test
    public void recentNewsHeaderTest() {
        WebDriver driver = drivers.get();
        WebElement header = driver.findElement(By.tagName("main")).findElement(By.tagName("h1"));
        assertTrue(header.isDisplayed());
        assertEquals(header.getText(), "Recent News");
    }

    /**
     * We should always be displaying three news items, so let's ensure that there are three elements
     * there for it. We don't care about the content, as that will change, just that they are present
     */
    @Test
    public void recentNewsThreeItems() {
        WebDriver driver = drivers.get();
        WebElement postsContainer = driver.findElement(By.tagName("main")).findElement(
                By.className("elementor-posts-container"));
        List<WebElement> articles = postsContainer.findElements(By.tagName("article"));
        assertEquals(articles.size(), 3);
    }
}
