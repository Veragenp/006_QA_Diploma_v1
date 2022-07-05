package ru.netology.web.data;

import lombok.Value;

import java.time.LocalDate;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
       // private String year;
        private String owner;
        private String CVC;


       }



    public static CardInfo getCardInfoApproved() {

        return new CardInfo("4444 4444 4444 4441", "01","NIK DIK", "123");
    }


//    public static String getCurrentYear() {//с годом пока ничего не получается, как его добавить не понятно...
//        LocalDate currentDate = LocalDate.now();
//       int currentYear = currentDate.getYear() - 2000 + 1;
//        return String.format("%02d" + currentYear);
//         }

    public static CardInfo getCardInfoDenied() {

        return new CardInfo("4444 4444 4444 4442", "01", "NIK DIK", "123");
    }

    @Value
    public static class CardInfo2 {
        private String cardNumber;
        private String month;
        // private String year;
        private String owner;
        private String CVC;

        public CardInfo2(String cardNumber, String month, String owner, String CVC) {
            this.cardNumber = cardNumber;
            this.month = month;
            this.owner = owner;
            this.CVC = CVC;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String getMonth() {
            return month;
        }

        public String getOwner() {
            return owner;
        }

        public String getCVC() {
            return CVC;
        }
    }





//    @Value
//    public static class DataInfo {
//        private int year;
//        private int month;
//    }
//    public static DataInfo getCurrentData() {
//        LocalDate currentDate = LocalDate.now();
//        int currentYear = currentDate.getYear() - 2000;
//        int currentMonth = currentDate.getMonthValue();
//        return new DataInfo(currentYear, currentMonth);
//    }
//


}











