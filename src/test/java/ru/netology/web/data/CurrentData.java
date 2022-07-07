package ru.netology.web.data;

import java.time.LocalDate;

public class CurrentData {
    public String currentYear(int plusYears) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateDelivery = currentDate.plusYears(plusYears);
        int currentYear1 = dateDelivery.getYear() - 2000;
        String currentYear = String.format("%02d", currentYear1);
        return currentYear;
    }

    public String getCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth1 = currentDate.getMonthValue();
        String currentMonth = String.format("%02d",currentMonth1);
        return currentMonth;
    }

    public int currentYearInt(int plusYears) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateDelivery = currentDate.plusYears(plusYears);
        int currentYearInt = dateDelivery.getYear() - 2000;
        return currentYearInt;
    }

    public int getCurrentMonthInt(int plusMonth) {
        LocalDate currentDate = LocalDate.now();
        int currentMonthInt = currentDate.getMonthValue();
        return currentMonthInt;
    }

}
