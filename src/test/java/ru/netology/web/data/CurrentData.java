package ru.netology.web.data;

import java.time.LocalDate;

public class CurrentData {
    public static String currentYear(int plusYears) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateDelivery = currentDate.plusYears(plusYears);
        int currentYear1 = dateDelivery.getYear() - 2000;
        String currentYear = String.format("%02d", currentYear1);
        return currentYear;
    }

    public static String currentMonth(int plusMonth) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateDelivery = currentDate.plusMonths(plusMonth);
        int currentMonth1 = currentDate.getMonthValue();
        String currentMonth = String.format("%02d", currentMonth1);
        return currentMonth;
    }

    public static int currentYearInt(int plusYears) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateDelivery = currentDate.plusYears(plusYears);
        int currentYearInt = dateDelivery.getYear() - 2000;
        return currentYearInt;
    }

    public static int getCurrentMonthInt(int plusMonth) {
        LocalDate currentDate = LocalDate.now();
        int currentMonthInt = currentDate.getMonthValue();
        return currentMonthInt;
    }

    public static String getMonthString(int plusMonth) {
        int currentMonthInt = CurrentData.getCurrentMonthInt(0);
        int monthInt = currentMonthInt + plusMonth;
        String month = String.format("%02d", monthInt);
        return month;
    }

    public static String getYearString(int plusYear) {
        int currentYearInt = CurrentData.currentYearInt(0);
        int yearInt = currentYearInt + plusYear;
        String year = String.format("%02d", yearInt);
        return year;
    }
}
