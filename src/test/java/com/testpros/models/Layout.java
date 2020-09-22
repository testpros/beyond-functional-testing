package com.testpros.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Layout {

    private final WebDriver driver;

    public Layout(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getMain() {
        return this.driver.findElement(By.tagName("main"));
    }

    public WebElement getHeader(WebElement element) {
        return element.findElement(By.tagName("h1"));
    }

    public List<WebElement> getSections() {
        return this.driver.findElements(By.className("elementor-section"));
    }

    public WebElement getPosts() {
        return getMain().findElement(By.className("elementor-posts-container"));
    }

    public List<WebElement> getArticles() {
        return getPosts().findElements(By.tagName("article"));
    }
}
