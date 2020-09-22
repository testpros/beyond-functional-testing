package com.testpros.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Services {

    private final WebDriver driver;

    public Services(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getServicesSection() {
        Layout layout = new Layout(this.driver);
        return layout.getSections().get(3);
    }

    public List<WebElement> getServiceWidgets() {
        return getServicesSection().findElements(By.className("elementor-col-33"));
    }

    public WebElement getServiceWidgetHeader(int widget) {
        return getServiceWidgets().get(widget).findElement(By.tagName("h2"));
    }
}
