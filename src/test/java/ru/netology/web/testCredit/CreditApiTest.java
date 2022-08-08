package ru.netology.web.testCredit;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.CardDate;
import ru.netology.web.data.CurrentData;
import ru.netology.web.data.SettingsSQL;
import ru.netology.web.data.SpecificationApi;


public class CreditApiTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    private final static String URL = "http://localhost:8080/";

    @BeforeEach
    public void setUp() {
        SettingsSQL.cleanseTableCredit();
        SettingsSQL.cleanseTablePayment();
        SettingsSQL.cleanseTableOrder();
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_1_Data.csv")
    void shouldCheckValidDataForCreditAndGetApprovedAnswerV1(String month, int plusYear, String cardNumber, String owner, String cvc) {
        String expectedAnswer = "APPROVED";
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), month, owner, cvc);
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date, "api/v1/credit").getStatus());
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_2_Data.csv")
    void shouldCheckValidDataForCreditAndGetApprovedAnswerV2(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        String expectedAnswer = "APPROVED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date, "api/v1/credit").getStatus());
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_1_Data.csv")
    void shouldCheckDeniedDataForCreditAndGetDeclinedAnswer(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        String expectedAnswer = "DECLINED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date, "api/v1/credit").getStatus());
        Assertions.assertEquals(expectedAnswer, SettingsSQL.getStatusOperationFromDbCredit());
        Assertions.assertNull(SettingsSQL.getStatusOperationFromDbPayment());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbCredit());
        Assertions.assertEquals(1, SettingsSQL.getAmountOffRecordFromDbOrder());
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_3_Data.csv")
    void shouldCheckNotValidDataForCreditAndGet500Answer(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecFail500());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertEquals("400 Bad Request", SpecificationApi.getPostRequest500(date, "api/v1/credit").getMessage());
        Assertions.assertEquals(500, SpecificationApi.getPostRequest500(date, "api/v1/credit").getStatus());
    }
}
