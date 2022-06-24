package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}
//здесь надо подумать какие данные заносить в итоге
    //получается надо пустое значение по каждому полю, и общие - для положительного ответа и для отрицательного
    //и для проверки полей, тоже надо подумать, скорее всего отдельно для каждого поля
    //Селекторы на все поля:
    //главная страница - $x('//*[@id="root"]/div/h2')
    //Кнопка "Купить в кредит" - $x('//*[text()="Купить в кредит"]')
    //Кнопка "Купить" - $x('//*[text()="Купить"]')
    //страница "Оплата по карте" - $x('//*[text()="Оплата по карте"]')
    //страница "Кредит по данным карты" - $x('//*[text()="Кредит по данным карты"]')
    //Номер карты - $x('//*[text()="Номер карты"]')
    //Месяц - $x('//*[text()="Месяц"]'), $$ ('*[placeholder="08"]')
    //Год - $x('//*[text()="Год"]')
    //Владелец - $x('//*[text()="Владелец"]')
    //CVC/CVV - $x('//*[text()="CVC/CVV"]')
    //Кнопка Продолжить - $x('//*[@class="spin spin_size_m spin_theme_alfa-on-white"]/parent::*')
    //Отправляем запрос в банк $$('span[class="spin spin_size_m spin_visible spin_theme_alfa-on-white"]')
    //

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
