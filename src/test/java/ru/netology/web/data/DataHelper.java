package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.PaymentPage;

import java.time.LocalDate;
import java.util.Calendar;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String CVC;


    }

    public static CardInfo getCardInfoApproved() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear() - 2000 + 1;
        String year = String.format("%02d" + currentYear);

        return new CardInfo("4444 4444 4444 4441", "01", year, "NIK DIK", "123");
    }

    public static CardInfo getCardInfoDenied() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear() - 2000 + 1;
        String year = String.format("%02d" + currentYear);
        return new CardInfo("4444 4444 4444 4442", "01", year, "NIK DIK", "123");
    }
    @Value
    public static class DataInfo {
        private int year;
        private int month;
    }
    public static DataInfo getCurrentData() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear() - 2000;
        int currentMonth = currentDate.getMonthValue();
        return new DataInfo(currentYear, currentMonth);
    }



}











