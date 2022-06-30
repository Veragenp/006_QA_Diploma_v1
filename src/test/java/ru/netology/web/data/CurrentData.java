package ru.netology.web.data;

import java.time.LocalDate;
import java.time.Month;

public class CurrentData {
    public static void main(String args[]) {
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current date: "+currentDate);
        int currentYear = currentDate.getYear() - 2000;
        System.out.println("Current year: "+currentYear);
        int currentMonth = currentDate.getMonthValue() + 10;
        String monthFormatted = String.format("%02d", currentMonth);
        System.out.println("Current month: " + monthFormatted);
    }

}
