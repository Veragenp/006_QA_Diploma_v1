package ru.netology.web.testPay;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.sun.xml.bind.v2.TODO;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.CurrentData;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {
    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }

    @ParameterizedTest
    @CsvFileSource(
            //TODO все же надо понять как быть со значениями: валидные они или нет 4444 4444 4444 4441 2, 0000 0000 0000 0000
            resources = "/data/2_3_1...2_DataCardNumber.csv")
    void ShouldCheckCardNumberAndGetWrongFormatAndFieldWithCardNumber(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String expectedCardNumber, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCardNumber(alarmText);
        String actualCardNumber = paymentPage.getFieldCardNumber();
        assertEquals(expectedCardNumber, actualCardNumber);
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_3_DataCardNumber.csv")
    void ShouldCheckCardNumberAndGetWrongFormatAndFieldWithCardNumberEmpty(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCardNumber(alarmText);
        String actualCardNumber = paymentPage.getFieldCardNumber();
        assertTrue(actualCardNumber.isEmpty());
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_4...5_DataMonth.csv")
    void ShouldCheckMonthAndGetAlarmAndFieldWithMonth(int plusYear, String month, String cardNumber, String owner, String cvc, String expectedMonth, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkFieldMonth(plusYear, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldMonth(alarmText);
        String actualMonth = paymentPage.getFieldMonth();
        assertEquals(expectedMonth, actualMonth);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_6_DataMonth.csv")
    void ShouldCheckMonthAndGetAlarmAndFieldWithMonthEmpty(int plusYear, String month, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkFieldMonth(plusYear, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldMonth(alarmText);
        String actualMonth = paymentPage.getFieldMonth();
        assertTrue(actualMonth.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_7...8_DataYear.csv")
    void ShouldCheckYearAndGetAlarmAndFieldWithYearEmpty(String year, int plusMonth, String cardNumber, String owner, String cvc, String expectedYear, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkFieldYear(year, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldYear(alarmText);
        String actualYear = paymentPage.getFieldYear();
        assertTrue(actualYear.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_7_DataYear.csv")
    void ShouldCheckYearAndGetAlarmAndFieldWithFieldYearZeroAmount(String year, int plusMonth, String cardNumber, String owner, String cvc, String expectedYear, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkFieldYear(year, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldYear(alarmText);
        String actualYear = paymentPage.getFieldYear();
        assertEquals(expectedYear, actualYear);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_9_DataYear.csv")
    void ShouldCheckYearAndGetAlarmWithFieldYear(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        var currentDate = new CurrentData();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldYear(alarmText);
        String actualYear = paymentPage.getFieldYear();
        String expectedYear = currentDate.currentYear(plusYear);
        assertEquals(expectedYear, actualYear);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_10_DataCvc.csv")
    void ShouldCheckCvcAndGetAlarmWithFieldCvc(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String expectedCvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCvc(alarmText);
        String actualCvc = paymentPage.getFieldCvc();
        assertEquals(expectedCvc, actualCvc);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_11_DataCvc.csv")
    void ShouldCheckCvcAndGetAlarmWithFieldEmpty(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCvc(alarmText);
        String actualCvc = paymentPage.getFieldCvc();
        assertTrue(actualCvc.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_12_DataOwner.csv")
    void ShouldCheckOwnerAndGetAlarmWithFieldOwner(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String expectedOwner, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldOwner(alarmText);
        String actualOwner = paymentPage.getFieldOwner();
        assertEquals(expectedOwner, actualOwner);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_13_DataOwner.csv")
    void ShouldCheckOwnerAndGetAlarmAndGetEmptyField(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldOwner(alarmText);
        String actualOwner = paymentPage.getFieldOwner();
        assertTrue(actualOwner.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_14...15_DataYearMonth.csv")
    void ShouldCheckMonthAndYearAndGetAlarmWrongValidity(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldMonth(alarmText);
    }
}
