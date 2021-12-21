package com.epam.util;

import org.openqa.selenium.WebElement;

import java.util.List;

public class PrinterUtil {

    public static void printNewsResults(List<WebElement> list) {
        System.out.println("Результаты поиска: \n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getText());
        }
    }

    public static void printTrainAndTimeResults(List<WebElement> listOne, List<WebElement> listTwo) {
        System.out.println("Результаты: \n");
        for (int i = 0; i < listOne.size(); i++) {
            System.out.println((i + 1) + ". \"" + listOne.get(i).getText()
                    + "\" - " + listTwo.get(i).getText());
        }
    }
}