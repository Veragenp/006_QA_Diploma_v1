package ru.netology.web.testPay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.*;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

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
            resources = "/data/ForExperiment.csv")
    void ShouldCheckValidDataAndGetApprovedAnswer(int plusYear, int plusMonth, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        String expectedAnswer = "APPROVED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CurrentData data2 = new CurrentData();
//        String year = String.format("%02d",data2.currentYearInt(plusYear));
//        String month = String.format("%02d",data2.getCurrentMonthInt(plusMonth));
        CardDate data = new CardDate(cardNumber, data2.currentYear(plusYear), data2.currentMonth(plusMonth), owner, cvc);
        Answer200 answer = given()
                .body(data)
                .when()
                .post("api/v1/pay")
                .then().log().all()
                .extract().as(Answer200.class);
        Assertions.assertNotNull(answer.getStatus());
        Assertions.assertEquals(expectedAnswer, answer.getStatus());
        Assertions.assertEquals(expectedAnswer,  SettingsSQL.getStatusOperationFromDb());
     }

}
