package com.epam;

import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RwSearchPage extends BasePage {

    @CacheLookup
    @FindBy(xpath = "//div[contains(@class,'sch-title__title h2')]")
    private WebElement trainHeading;

    @CacheLookup
    @FindBy(className = "sch-title__descr")
    private WebElement daysGo;

    @CacheLookup
    @FindBy(className = "notetext")
    private WebElement noteText;

    @CacheLookup
    @FindBy(id = "searchinpm")
    private WebElement bigSearchTab;

    @CacheLookup
    @FindBy(xpath = "//input[@name='s' and @type='submit']")
    private WebElement button;

    @CacheLookup
    @FindBy(className = "name")
    private List<WebElement> searchResults;

    @CacheLookup
    @FindBy(className = "name")
    private WebElement oneSearchResult;

    @CacheLookup
    @FindBy(xpath = "//*[@id='fTickets']//span/input")
    private WebElement searchButton;

    @CacheLookup
    @FindBy(className = "sch-table__route")
    private List<WebElement> trainName;

    @CacheLookup
    @FindBy(className = "sch-table__route")
    private WebElement oneTrainName;

    @CacheLookup
    @FindBy(className = "sch-table__time")
    private List<WebElement> trainTime;

    @CacheLookup
    @FindBy(className = "sch-table__time")
    private WebElement oneTrainTime;

    @CacheLookup
    @FindBy(className = "pic")
    private WebElement logoLink;

    public RwSearchPage(WebDriver driver) {
        super(driver);
    }

    public String waitAndGetUrlAddress(String keywords) {
        waitUntilUrlContains(keywords);
        return driver.getCurrentUrl();
    }

    public String waitAndGetErrorText() {
        waitForVisibilityOfElement(noteText);
        return noteText.getText();
    }

    public void clearSearch() {
        bigSearchTab.clear();
    }

    public void setSearch(String keys) {
        bigSearchTab.sendKeys(keys);
    }

    public void clickOnButton() {
        button.click();
    }

    public List<WebElement> waitAndGetResults() {
        waitForVisibilityOfElement(oneSearchResult);
        return searchResults;
    }

    public void clickOnSearchButton() {
        searchButton.click();
    }

    public List<WebElement> waitAndGetSearchTrainNameResults() {
        waitForVisibilityOfElement(oneTrainName);
        return trainName;
    }

    public List<WebElement> waitAndGetSearchTrainTimeResults() {
        waitForVisibilityOfElement(oneTrainTime);
        return trainTime;
    }

    public void waitForVisibilityOfTrainHeading() {
        waitForVisibilityOfElement(trainHeading);
    }

    public String getTrainHeadingText() {
        return trainHeading.getText();
    }

    public String getGoDaysHeadingText() {
        return daysGo.getText();
    }

    public void clickOnSiteLogo() {
        logoLink.click();
    }
}