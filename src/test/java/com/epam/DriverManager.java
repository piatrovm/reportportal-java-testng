package com.epam;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static WebDriver driver = null;

    public static void init() {
        System.setProperty("webdriver.chrome.driver"
                , System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
        // changing language in Chrome to Russian
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=ru");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }

    public static void quit() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception exception) {
                System.out.println("Cannot kill browser.");
            } finally {
                driver = null;
            }
        }
    }
}