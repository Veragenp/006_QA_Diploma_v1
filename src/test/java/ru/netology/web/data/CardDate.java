package ru.netology.web.data;

import java.time.LocalDate;

public class CardDate {

    private String number;
    private String holder;
    private String cvc;
    private String month;
    private String year;

    public CardDate(String number, String holder, String cvc, String month, String year) {
        this.number = number;
        this.holder = holder;
        this.cvc = cvc;
        this.month = month;
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public String getHolder() {
        return holder;
    }

    public String getCvc() {
        return cvc;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}
