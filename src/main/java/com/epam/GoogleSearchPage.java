package com.epam;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchPage extends com.epam.BasePage {

    @CacheLookup
    @FindBy(name = "q")
    private WebElement searchTab;

    @CacheLookup
    @FindBy(xpath = "//a[@href='https://www.rw.by/']")
    private WebElement rwSiteIE;

    @CacheLookup
    @FindBy(className = "LC20lb")
    private WebElement rwSite;

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    public void setSearchTab(String keys) {
        searchTab.sendKeys(keys);
    }

    public void submitSearchTab() {
        searchTab.submit();
    }

    public void waitAndClickUrl() {
        waitForVisibilityOfElement(rwSite);
        rwSite.click();
    }
}