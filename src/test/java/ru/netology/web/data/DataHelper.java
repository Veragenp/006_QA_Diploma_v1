package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}
//здесь надо подумать какие данные заносить в итоге
    //$x("//*[text()='Владелец']//following-sibling::span//following-sibling::span") - поле для текста предупреждения
// $x("//*[text()='Номер карты']//following-sibling::span//following-sibling::span") - поле для текста предупреждения
    //$x("//*[text()='Месяц']//following-sibling::span//following-sibling::span")
//$x("//*[text()='Год']//following-sibling::span//following-sibling::span")
//$x("//*[text()='CVC/CVV']//following-sibling::span//following-sibling::span")



    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String CVC;
    }

 //   public static CardInfo () {
  //      return new CardInfo("vasya", "qwerty123");
   // }

}
