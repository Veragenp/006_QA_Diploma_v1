package ru.netology.web.testPay;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.CurrentData;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentCreditPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldPayTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }

    @ParameterizedTest
    @CsvFileSource(
           resources = "/data/2_3_1...2_DataCardNumber.csv")
    void shouldCheckCardNumberAndGetWrongFormatAndFieldWithCardNumberForPayment(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String expectedCardNumber, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCardNumber(alarmText);
        String actualCardNumber = paymentPage.getFieldCardNumber();
        assertEquals(expectedCardNumber, actualCardNumber);
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_3_DataCardNumber.csv")
    void shouldCheckCardNumberAndGetWrongFormatAndFieldWithCardNumberEmptyForPayment(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCardNumber(alarmText);
        String actualCardNumber = paymentPage.getFieldCardNumber();
        assertTrue(actualCardNumber.isEmpty());
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_4...5_DataMonth.csv")
    void shouldCheckMonthAndGetAlarmAndFieldWithMonthForPayment(int plusYear, String month, String cardNumber, String owner, String cvc, String expectedMonth, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldMonth(alarmText);
        String actualMonth = paymentPage.getFieldMonth();
        assertEquals(expectedMonth, actualMonth);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_6_DataMonth.csv")
    void shouldCheckMonthAndGetAlarmAndFieldWithMonthEmptyForPayment(int plusYear, String month, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldMonth(alarmText);
        String actualMonth = paymentPage.getFieldMonth();
        assertTrue(actualMonth.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_7...8_DataYear.csv")
    void shouldCheckYearAndGetAlarmAndFieldWithYearEmptyForPayment(String year, int plusMonth, String cardNumber, String owner, String cvc, String expectedYear, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldYear(alarmText);
        String actualYear = paymentPage.getFieldYear();
        assertTrue(actualYear.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_7_DataYear.csv")
    void shouldCheckYearAndGetAlarmAndFieldWithFieldYearZeroAmountForPayment(String year, int plusMonth, String cardNumber, String owner, String cvc, String expectedYear, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldYear(alarmText);
        String actualYear = paymentPage.getFieldYear();
        assertEquals(expectedYear, actualYear);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_9_DataYear.csv")
    void shouldCheckYearAndGetAlarmWithFieldYearForPayment(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldYear(alarmText);
        String actualYear = paymentPage.getFieldYear();
        String expectedYear = CurrentData.currentYear(plusYear);
        assertEquals(expectedYear, actualYear);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_10_DataCvc.csv")
    void shouldCheckCvcAndGetAlarmWithFieldCvcForPayment(int plusMonth, int plusYear, String cardNumber, String owner, String cvc, String expectedCvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCvc(alarmText);
        String actualCvc = paymentPage.getFieldCvc();
        assertEquals(expectedCvc, actualCvc);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_11_DataCvc.csv")
    void shouldCheckCvcAndGetAlarmWithFieldEmptyForPayment(int plusMonth, int plusYear, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCvc(alarmText);
        String actualCvc = paymentPage.getFieldCvc();
        assertTrue(actualCvc.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_12_DataOwner.csv")
    void shouldCheckOwnerAndGetAlarmWithFieldOwnerForPayment(int plusMonth, int plusYear, String cardNumber, String owner, String cvc, String expectedOwner, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldOwner(alarmText);
        String actualOwner = paymentPage.getFieldOwner();
        assertEquals(expectedOwner, actualOwner);
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_13_DataOwner.csv")
    void shouldCheckOwnerAndGetAlarmAndGetEmptyFieldForPayment(int plusMonth, int plusYear, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldOwner(alarmText);
        String actualOwner = paymentPage.getFieldOwner();
        assertTrue(actualOwner.isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_14...15_DataYearMonth.csv")
    void shouldCheckMonthAndYearAndGetAlarmWrongValidityForPayment(int plusYear, int plusMonth, String cardNumber, String owner, String cvc, String alarmText) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldMonth(alarmText);
    }
}
