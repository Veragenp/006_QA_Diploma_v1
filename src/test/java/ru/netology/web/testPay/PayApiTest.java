package ru.netology.web.testPay;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.CardDate;
import ru.netology.web.data.CurrentData;
import ru.netology.web.data.SpecificationApi;

public class PayApiTest {
    private final static String URL = "http://localhost:8080/";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_1_Data.csv")
    void shouldCheckValidDataForPaymentAndGetApprovedAnswerV1(String month, int plusYear, String cardNumber, String owner, String cvc) {
        String expectedAnswer = "APPROVED";
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), month, owner, cvc);
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date, "api/v1/pay").getStatus());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_2_Data.csv")
    void shouldCheckValidDataForPaymentAndGetApprovedAnswerV2(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        String expectedAnswer = "APPROVED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date, "api/v1/pay").getStatus());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_1_Data.csv")
    void shouldCheckDeniedDataForPaymentAndGetDeclinedAnswer(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        String expectedAnswer = "DECLINED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertEquals(expectedAnswer, SpecificationApi.getPostRequest200(date, "api/v1/pay").getStatus());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_3_Data.csv")
    void shouldCheckNotValidDataForPaymentAndGet500Answer(int plusMonth, int plusYear, String cardNumber, String owner, String cvc) {
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecFail500());
        CardDate date = new CardDate(cardNumber, CurrentData.currentYear(plusYear), CurrentData.currentMonth(plusMonth), owner, cvc);
        Assertions.assertEquals("400 Bad Request", SpecificationApi.getPostRequest500(date, "api/v1/pay").getMessage());
        Assertions.assertEquals(500, SpecificationApi.getPostRequest500(date, "api/v1/pay").getStatus());
    }

}
