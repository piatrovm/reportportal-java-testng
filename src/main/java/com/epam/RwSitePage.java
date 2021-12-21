package com.epam;

import com.epam.util.CalendarUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;

public class RwSitePage extends BasePage {

    private static String THIS_MONTH_PART = "first";
    private static String NEXT_MONTH_PART = "middle";

    @CacheLookup
    @FindBy(xpath = "//title")
    private WebElement siteTitle;

    @CacheLookup
    @FindBy(className = "top-lang")
    private WebElement siteLang;

    @CacheLookup
    @FindBy(xpath = "//a[@href='/en/']")
    private WebElement enLang;

    @CacheLookup
    @FindBy(name = "from")
    private WebElement from;

    @CacheLookup
    @FindBy(name = "to")
    private WebElement to;

    @CacheLookup
    @FindBy(name = "q")
    private WebElement searchTab;

    @CacheLookup
    @FindBy(className = "calendar")
    private WebElement calendar;

    public RwSitePage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageLoading() {
        waitUntilUrlContains("rw.by");
    }

    public void waitForVisibilityOfLanguages() {
        waitForVisibilityOfElement(siteLang);
    }

    public void clickOnEnLanguage() {
        enLang.click();
    }

    public void setSearch(String string) {
        searchTab.sendKeys(string);
    }

    public void submitSearch() {
        searchTab.submit();
    }

    public void waitAndSendKeyTo(String keys) {
        waitForVisibilityOfElement(to);
        to.sendKeys(keys);
    }

    public void waitAndSendKeyFrom(String keys) {
        waitForVisibilityOfElement(from);
        from.sendKeys(keys);
    }

    public void clickOnCalendar() {
        calendar.click();
    }

    public By getMonthLocator(String monthPart, LocalDate currentDay) {
        return By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-"
                + monthPart + "']//a[text()='" + CalendarUtil.getCalendarDay(currentDay) + "']");
    }

    public void clickOnDate(LocalDate currentDate, int dayDelta) {

        LocalDate futureDate = CalendarUtil.getFutureDate(currentDate, dayDelta);
        int monthDifference = CalendarUtil.getMonthDifference(currentDate, futureDate);

        if (monthDifference == 1) {
            driver.findElement(getMonthLocator(NEXT_MONTH_PART, currentDate)).click();
        } else {
            driver.findElement(getMonthLocator(THIS_MONTH_PART, currentDate)).click();
        }
    }
}