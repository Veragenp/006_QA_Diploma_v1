package ru.netology.web.data;

import java.time.LocalDate;

public class CardDate {

    private String number;
    private String year;
    private String month;
    private String holder;
    private String cvc;



    public CardDate(String number, String year, String month, String holder, String cvc) {
        this.cvc = cvc;
        this.holder = holder;
        this.month = month;
        this.number = number;
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getHolder() {
        return holder;
    }

    public String getCvc() {
        return cvc;
    }
}
