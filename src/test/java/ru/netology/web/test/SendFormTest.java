package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.web.data.CurrentData;
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

    @ParameterizedTest
    @CsvSource({"'4444444444444441', '01', 'GTYUTVNHBGJN JUHBUJUI', '123'", "'4444444444444441', '06', 'IA T', '000'"})
    void ShouldCheckValidDataForm(String cardNumber, String month, String owner, String cvc) { //проверка текста сообщения от банка - положительный
        open("http://localhost:8080");
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.validCardApprovedDataForYearPlusOne(cardNumber, month, owner, cvc);
        paymentPage.waitAlarmOk(); //работает не стабильно, что то с ожиданием ответа похоже, не дожидается ответа
        paymentPage.checkAlarmOk();
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/dataForTest.csv")
    void ShouldCheckValidDataFormByFile(String cardNumber, String month, String owner, String cvc) { //проверка текста сообщения от банка - положительный
        open("http://localhost:8080");
        var currentData = new CurrentData();
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.validCardApprovedDataForYearPlusOne(cardNumber, month, owner, cvc);
        paymentPage.waitAlarmOk(); //работает не стабильно, что то с ожиданием ответа похоже, не дожидается ответа
        paymentPage.checkAlarmOk();

    }
}
