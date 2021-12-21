package com.epam;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RwEnPage extends BasePage {

    @CacheLookup
    @FindBy(className = "copyright")
    private WebElement bottomText;

    @CacheLookup
    @FindBy(xpath = "//dt[starts-with(@id, 'bx_1373509569')]")
    private List<WebElement> allNews;

    @CacheLookup
    @FindBy(xpath = "//dt[starts-with(@id, 'bx_1373509569')]")
    private WebElement oneOfNews;

    @CacheLookup
    @FindBy(xpath = "//*[@class=' ' or @class=' last']//b")
    private List<WebElement> buttonsPath;

    public RwEnPage(WebDriver driver) {
        super(driver);
    }

    public int waitAndGetAmountOfNews() {
        waitForVisibilityOfElement(oneOfNews);
        return allNews.size();
    }

    public String waitAndGetBottomText() {
        waitForVisibilityOfElement(bottomText);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.border='3px solid green'", bottomText);
        return bottomText.getText();
    }

    public List<WebElement> waitAndGetButtons() {
        waitForVisibilityOfAllWebElements(buttonsPath);
        return buttonsPath;
    }
}