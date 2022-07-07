package ru.netology.web.test;

import com.sun.xml.bind.v2.TODO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
      }
    @Test
    void ShouldGetAlarmOk() { //проверка текста сообщения от банка - положительный
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.validCardApproved();
        paymentPage.checkAlarmOk();
    }

    @Test
    void ShouldGetAlarmFail() { //проверка текста сообщения от банка - отрицательный
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        dashboardPage.clickOnButtonCreditCard();
     //   paymentPage.validCardDenied();
        paymentPage.checkAlarmFail();
    }
@Test
    //@ParameterizedTest
    //@CsvSource(value = )
    void ShouldCheckDataHelper() { //проверка текста сообщения от банка - отрицательный
         var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        dashboardPage.clickOnButtonCreditCard();
        //   paymentPage.validCardDenied();
        paymentPage.checkAlarmFail();
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_1_Data.csv")
    void ShouldCheckNegativeForCardNumberAndGetWrongFormatV1(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String expectedCardNumber, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear,plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCardNumber(alarmText);
        String actualCardNumber = paymentPage.getFieldCardNumber();
        assertEquals(expectedCardNumber, actualCardNumber);
       }
    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_2_Data.csv")
    void ShouldCheckNegativeForCardNumberAndGetWrongFormatV2(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String expectedCardNumber, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear,plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCardNumber(alarmText);
        String actualCardNumber = paymentPage.getFieldCardNumber();
        assertEquals(expectedCardNumber, actualCardNumber);
    }

   //TODO не работает тест, не понятно какое значение ставить, пусто и нул - разные значения...
    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_3_Data.csv")
    void ShouldCheckNegativeForCardNumberAndGetWrongFormatV3(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String expectedCardNumber, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear,plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCardNumber(alarmText);
        String actualCardNumber = paymentPage.getFieldCardNumber();
        assertEquals(expectedCardNumber, actualCardNumber);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_4_DataMonth.csv")
    void ShouldCheckNegativeForMonthAndGetWrongFormatV3(int plusYear, String month, String cardNumber, String owner, String cvc, String expectedMonth, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkFieldMonth(plusYear,month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldMonth(alarmText);
        String actualMonth = paymentPage.getFieldMonth();
        assertEquals(expectedMonth, actualMonth);
    }



}
