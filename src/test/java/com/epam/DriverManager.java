package com.epam;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static WebDriver driver = new ChromeDriver();

    public static void init() {
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
