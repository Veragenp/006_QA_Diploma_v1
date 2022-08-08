package ru.netology.web.testCredit;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.*;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentCreditPage;

import static com.codeborne.selenide.Selenide.open;


public class SendFormCreditTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    private final static String URL = "http://localhost:8080/";

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
    void shouldCheckValidDataForCreditAndGetEntryInDbV1(String month, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmOk();
        String expectedAnswer = "APPROVED";
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());

    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_2_Data.csv")
    void shouldCheckValidDataForCreditAndGetEntryInDbV2(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmOk();
        String expectedAnswer = "APPROVED";
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_1_Data.csv")
    void shouldCheckDeniedDataForCreditAndGetEntryInDb(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFail();
        String expectedAnswer = "DECLINED";
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_3_Data.csv")
    void shouldCheckNotValidDataForCreditAndNotGetEntryInDb(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFail();
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertNull(SettingsSQL.getIdFromOrder());
    }
}
