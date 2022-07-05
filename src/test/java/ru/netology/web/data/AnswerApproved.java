package ru.netology.web.data;

public class AnswerApproved {
    private String statusApp;
    //private String statusDen;

    public AnswerApproved(String statusApp, String statusDen) {
        this.statusApp = statusApp;
     //   this.statusDen = statusDen;
    }

    public String getStatusApp() {
        return statusApp;
    }
//
//    public String getStatusDen() {
//        return statusDen;
//    }
}
