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

    public List<WebElement> getSections() {
        return this.driver.findElements(By.className("elementor-section"));
    }
}
