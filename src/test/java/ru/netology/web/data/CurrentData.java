package ru.netology.web.data;

import java.time.LocalDate;
import java.time.Month;

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

}
