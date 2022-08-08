package ru.netology.web.testPay;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.*;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

import static com.codeborne.selenide.Selenide.open;

public class SendFormPayTest {
    private final static String URL = "http://localhost:8080/";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        SettingsSQL.cleanseTableCredit();
        SettingsSQL.cleanseTablePayment();
        SettingsSQL.cleanseTableOrder();
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_1_Data.csv")
    void shouldCheckValidDataForPaymentAndGetEntryInDbV1(String month, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonPayCard();
        var paymentPage = new PaymentBuyPage();
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmOk();
        String expectedAnswer = "APPROVED";
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_2_Data.csv")
    void shouldCheckValidDataForPaymentAndGetEntryInDbV2(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonPayCard();
        var paymentPage = new PaymentBuyPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmOk();
        String expectedAnswer = "APPROVED";
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_1_Data.csv")
    void shouldCheckDeniedDataForPaymentAndGetEntryInDb(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonPayCard();
        var paymentPage = new PaymentBuyPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFail();
        String expectedAnswer = "DECLINED";
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_3_Data.csv")
    void shouldCheckNotValidDataForPaymentNotGetEntryInDb(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonPayCard();
        var paymentPage = new PaymentBuyPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFail();
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertNull(SettingsSQL.getIdFromOrder());
    }

}
