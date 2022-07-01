package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

import static com.codeborne.selenide.Selenide.open;

public class SendFormTest {
    @Test
    void ShouldGetAlarmOk() { //проверка текста сообщения от банка - положительный
        open("http://localhost:8080");
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.validCardApproved();
        paymentPage.waitAlarmOk(); //работает не стабильно, что то с ожиданием ответа похоже, не дожидается ответа
        paymentPage.checkAlarmOk();
    }
}
