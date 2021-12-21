package com.epam;

import com.epam.util.GlobalConstants;
import com.epam.util.PrinterUtil;
import com.epam.util.RandomUtil;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class BelarusianRailwayTest {

    @BeforeTest
    public void setup() {
        DriverManager.init();
    }

    @AfterTest(alwaysRun = true)
    public void quit() {
        DriverManager.quit();
    }

    @Test
    public void openFromGoogle() {

        GoogleSearchPage googleSearchPage;
        RwSitePage rwSitePage;

        googleSearchPage = new GoogleSearchPage(DriverManager.driver);
        googleSearchPage.maximizeWindow();

        //opening google
        googleSearchPage.getPageUrl(GlobalConstants.GOOGLE_URL + "/?hl=ru");
        assertEquals(googleSearchPage.getPageTitle(), GlobalConstants.GOOGLE_TITLE);

        //searching
        googleSearchPage.setSearchTab(GlobalConstants.GOOGLE_SEARCH_TEXT);
        googleSearchPage.submitSearchTab();

        //opening a website
        googleSearchPage.waitAndClickUrl();

        rwSitePage = new RwSitePage(DriverManager.driver);

        //checking correct loading
        rwSitePage.waitUntilPageLoading();
        assertEquals(rwSitePage.getPageTitle(), GlobalConstants.SITE_TITLE);
    }

    @Test
    public void workWithMainPage() {

        RwSitePage rwSitePage;
        RwEnPage rwEnPage;

        rwSitePage = new RwSitePage(DriverManager.driver);
        rwSitePage.maximizeWindow();

        //opening site
        rwSitePage.getPageUrl(GlobalConstants.RW_SITE_URL + "ru/");
        assertEquals(rwSitePage.getPageTitle(), GlobalConstants.SITE_TITLE);

        //switching to english
        rwSitePage.waitForVisibilityOfLanguages();
        rwSitePage.clickOnEnLanguage();

        rwEnPage = new RwEnPage(DriverManager.driver);

        //checking amount of news
        assertTrue(rwEnPage.waitAndGetAmountOfNews() >= GlobalConstants.AMOUNT_OF_NEWS);

        //checking text in the bottom
        assertTrue(rwEnPage.waitAndGetBottomText().contains(GlobalConstants.BOTTOM_RW_SITE_TEXT));

        //checking that 5 buttons are present
        assertTrue(rwEnPage.waitAndGetButtons().size() == GlobalConstants.BUTTONS_NAMES.length);

        for (int i = 0; i < rwEnPage.waitAndGetButtons().size(); i++) {
            assertTrue(GlobalConstants.BUTTONS_NAMES[i]
                    .equalsIgnoreCase(rwEnPage.waitAndGetButtons().get(i).getText()));
        }
    }

    @Test
    public void workWithSearch() {

        RwSitePage rwSitePage;
        RwSearchPage rwSearchPage;

        rwSitePage = new RwSitePage(DriverManager.driver);
        rwSitePage.maximizeWindow();

        //opening site
        rwSitePage.getPageUrl(GlobalConstants.RW_SITE_URL + "ru/");
        assertEquals(rwSitePage.getPageTitle(), GlobalConstants.SITE_TITLE);

        //searching
        String randomString = RandomUtil.getRandomString(GlobalConstants.RANDOM_STRING_LENGTH);
        rwSitePage.setSearch(randomString);
        rwSitePage.submitSearch();

        rwSearchPage = new RwSearchPage(DriverManager.driver);

        //checking address changing
        String url = GlobalConstants.FIRST_PART_OF_RANDOM_SEARCH_URL + randomString;
        assertEquals(rwSearchPage.waitAndGetUrlAddress(randomString), url);

        //checking an error message
        assertEquals(rwSearchPage.waitAndGetErrorText(), GlobalConstants.EXPECTED_ERROR_TEXT);

        //removing old request and creating new
        rwSearchPage.clearSearch();
        rwSearchPage.setSearch(GlobalConstants.SEARCHED_CITY);

        //clicking a button
        rwSearchPage.clickOnButton();

        //checking amount of results
        assertTrue(rwSearchPage.waitAndGetResults().size()
                == GlobalConstants.AMOUNT_OF_SEARCH_RESULTS);

        PrinterUtil.printNewsResults(rwSearchPage.waitAndGetResults());
    }

    @Test
    public void workWithCalendar() {

        RwSitePage rwSitePage;
        RwSearchPage rwSearchPage;

        rwSitePage = new RwSitePage(DriverManager.driver);
        rwSitePage.maximizeWindow();

        //opening site
        rwSitePage.getPageUrl(GlobalConstants.RW_SITE_URL + "ru/");
        assertEquals(rwSitePage.getPageTitle(), GlobalConstants.SITE_TITLE);

        //sending valid destination keys
        rwSitePage.waitAndSendKeyFrom(GlobalConstants.FROM_CITY);
        rwSitePage.waitAndSendKeyTo(GlobalConstants.TO_CITY);

        //clicking calendar button
        rwSitePage.clickOnCalendar();
        rwSitePage.clickOnDate(LocalDate.now(), GlobalConstants.CALENDAR_DAY_DELTA);

        rwSearchPage = new RwSearchPage(DriverManager.driver);

        //clicking search button
        rwSearchPage.clickOnSearchButton();

        //writing name and time to the console and clicking the first link
        PrinterUtil.printTrainAndTimeResults(rwSearchPage.waitAndGetSearchTrainNameResults()
                , rwSearchPage.waitAndGetSearchTrainTimeResults());

        rwSearchPage.waitAndGetSearchTrainNameResults().get(0).click();

        //checking that name is displayed
        rwSearchPage.waitForVisibilityOfTrainHeading();
        assertTrue(rwSearchPage.getTrainHeadingText().contains(GlobalConstants.TRAIN_HEADING_TEXT));

        //checking that text is not empty
        assertFalse(rwSearchPage.getGoDaysHeadingText().isEmpty());

        //clicking on site logo
        rwSearchPage.clickOnSiteLogo();

        //checking correct loading
        rwSearchPage.waitUntilUrlContains(GlobalConstants.PASSENGER_PAGE_URL);
        assertEquals(rwSearchPage.getPageTitle(), GlobalConstants.ON_LOGO_CLICK_TITLE);
    }
}
