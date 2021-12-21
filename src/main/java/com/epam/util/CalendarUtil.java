package com.epam.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalendarUtil {

    public static LocalDate getFutureDate(LocalDate currentDate, int daysToAdd) {
        return currentDate
                .plus(daysToAdd, ChronoUnit.DAYS);
    }

    public static int getMonthDifference(LocalDate firstDate, LocalDate secondDate) {
        return secondDate.getMonthValue() - firstDate.getMonthValue();
    }

    public static int getCalendarDay(LocalDate currentDate) {
        return getFutureDate(currentDate,
                5).getDayOfMonth();
    }
}