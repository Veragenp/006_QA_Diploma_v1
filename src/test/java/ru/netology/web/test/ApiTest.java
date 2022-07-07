package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.AnswerApproved;
import ru.netology.web.data.CardDate;
import ru.netology.web.data.CurrentData;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;


public class ApiTest {//в общем надо понять как в тело запроса включить данные запроса
    private final static String URL = "http://localhost:8080/";

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/ForExperiment.csv")
    void ShouldCheckCardNumberAndGetWrongFormatV1(int plusYear, int plusMonth, String cardNumber, String owner, String cvc) {
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.checkField(plusYear, plusMonth, cardNumber, owner, cvc);
        String expectedAnswer = "APPROVED";
        SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
        CurrentData data2 = new CurrentData();
        CardDate data = new CardDate(cardNumber, data2.currentYear(1), data2.getCurrentMonth(), owner, cvc);
        AnswerApproved answer = given()
                .body(data)
                .when()
                .post("api/v1/pay")
                .then().log().all()
                .extract().as(AnswerApproved.class);
        Assertions.assertNotNull(answer.getStatus());
        Assertions.assertEquals(expectedAnswer, answer.getStatus());


    }


}





