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


public class SendFormTestCredit {
    @BeforeAll
    static void setUpAll(){
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
    void ShouldCheckValidDataForCreditAndGetMessageSuccessGetApprovedAnswerGetEntryInDbV1(String month, int plusYear,String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year,month, cardNumber, owner, cvc);
        paymentPage.checkAlarmOk();
        String expectedAnswer = "APPROVED";
        CardDate date = new CardDate(cardNumber,CurrentData.currentYear(plusYear), month, owner, cvc);
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        Assertions.assertNotNull(SpecificationApi.getPostRequest200(date).getStatus());
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date).getStatus());
        Assertions.assertEquals(expectedAnswer,  SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1,SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1,SettingsSQL.getAmountOffRecordFromDbOrder());

    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_2_Data.csv")
    void ShouldCheckValidDataForCreditAndGetMessageSuccessGetApprovedAnswerGetEntryInDbV2(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmOk();
        String expectedAnswer = "APPROVED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertNotNull(SpecificationApi.getPostRequest200(date).getStatus());
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date).getStatus());
        Assertions.assertEquals(expectedAnswer,  SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1,SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1,SettingsSQL.getAmountOffRecordFromDbOrder());
    }



    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_1_Data.csv")
    void ShouldCheckDeniedDataForCreditAndGetMessageFailGetDeclinedAnswerGetEntryInDb(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFail();
        String expectedAnswer = "DECLINED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertNotNull(SpecificationApi.getPostRequest200(date).getStatus());
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date).getStatus());
        Assertions.assertEquals(expectedAnswer,  SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1,SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1,SettingsSQL.getAmountOffRecordFromDbOrder());
    }



    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_3_Data.csv")
    void ShouldCheckNotValidDataForCreditAndGetMessageFailGet500AnswerNotGetEntryInDb(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
        var paymentPage = new PaymentCreditPage();
        String month = CurrentData.getMonthString(plusMonth);
        String year = CurrentData.getYearString(plusYear);
        paymentPage.checkField(year, month, cardNumber, owner, cvc);
        paymentPage.checkAlarmFail();
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecFail500());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertEquals("400 Bad Request", SpecificationApi.getPostRequest500(date).getMessage());
        Assertions.assertEquals(500, SpecificationApi.getPostRequest500(date).getStatus());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertNull(SettingsSQL.getIdFromOrder());
    }
}
