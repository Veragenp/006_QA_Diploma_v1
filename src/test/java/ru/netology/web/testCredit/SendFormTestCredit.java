package ru.netology.web.testCredit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.*;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;
import ru.netology.web.page.PaymentCreditPage;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class SendFormTestCredit {
    private final static String URL = "http://localhost:8080/";
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }
    @Test
    void ShouldOpenFormPay() { //проверка текста сообщения от банка - положительный
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonCreditCard();
    }

    @Test
    void ShouldOpenFormPayAfterBottomCredit() { //проверка текста сообщения от банка - положительный
        var dashboardPage = new DashboardPage();
        dashboardPage.clickOnButtonPayCard();
        dashboardPage.clickOnButtonCreditCard();
    }



    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_1_Data.csv")
    void ShouldCheckValidDataAndGetApprovedAnswerV1(String month, int plusYear,String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentCreditPage();
        dashboardPage.clickOnButtonCreditCard();
        paymentPage.checkFieldMonth(plusYear,month, cardNumber, owner, cvc);
        paymentPage.checkAlarmOk();
        String expectedAnswer = "APPROVED";
        CurrentData data2 = new CurrentData();
        CardDate date = new CardDate(cardNumber,data2.currentYear(plusYear), month, owner, cvc);
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        Answer200 answer = given()
                .body(date)
                .when()
                .post("api/v1/pay")
                .then().log().all()
                .extract().as(Answer200.class);
        Assertions.assertNotNull(answer.getStatus());
        Assertions.assertEquals(expectedAnswer, answer.getStatus());
        Assertions.assertEquals(expectedAnswer,  SettingsSQL.getStatusOperationFromDbCredit());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_1_1_2_Data.csv")
    void ShouldCheckValidDataAndGetApprovedAnswerV2(int plusYear, int plusMonth, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentCreditPage();
        dashboardPage.clickOnButtonCreditCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmOk();
        String expectedAnswer = "APPROVED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CurrentData data2 = new CurrentData();
        CardDate data = new CardDate(cardNumber, data2.currentYear(plusYear), data2.currentMonth(plusMonth), owner, cvc);
        Answer200 answer = given()
                .body(data)
                .when()
                .post("api/v1/pay")
                .then().log().all()
                .extract().as(Answer200.class);
        Assertions.assertNotNull(answer.getStatus());
        Assertions.assertEquals(expectedAnswer, answer.getStatus());
        Assertions.assertEquals(expectedAnswer,  SettingsSQL.getStatusOperationFromDbCredit());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_1_Data.csv")
    void ShouldCheckNotValidDataAndGetDeclinedAnswer(int plusYear, int plusMonth, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentCreditPage();
        dashboardPage.clickOnButtonCreditCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        //paymentPage.checkAlarmFail();
        String expectedAnswer = "DECLINED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CurrentData data2 = new CurrentData();
        CardDate data = new CardDate(cardNumber, data2.currentYear(plusYear), data2.currentMonth(plusMonth), owner, cvc);
        Answer200 answer = given()
                .body(data)
                .when()
                .post("api/v1/pay")
                .then().log().all()
                .extract().as(Answer200.class);
        Assertions.assertNotNull(answer.getStatus());
        Assertions.assertEquals(expectedAnswer, answer.getStatus());
        Assertions.assertEquals(0,  SettingsSQL.getStatusOperationFromDbCredit());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_4_3_Data.csv")
    void ShouldCheckNotValidDataAndGet500Answer(int plusYear, int plusMonth, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        paymentPage.checkAlarmFail();
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecFail500());
        CurrentData data2 = new CurrentData();
        CardDate data = new CardDate(cardNumber, data2.currentYear(plusYear), data2.currentMonth(plusMonth), owner, cvc);
        Answer500 answer = given()
                .body(data)
                .when()
                .post("api/v1/pay")
                .then().log().all()
                .extract().as(Answer500.class);
        // Assertions.assertEquals("400 Bad Request", answer.getError());
    }



}
