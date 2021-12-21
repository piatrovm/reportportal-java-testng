package com.epam;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int WAIT_FOR_ELEMENT_SECONDS = 20;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void getPageUrl(String url) {
        driver.get(url);
    }

    public void waitUntilUrlContains(String keywords) {
        wait.until(ExpectedConditions.urlContains(keywords));
    }

    public void waitForVisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityOfAllWebElements(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}