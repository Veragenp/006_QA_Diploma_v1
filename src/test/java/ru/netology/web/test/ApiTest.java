package ru.netology.web.test;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.AnswerApproved;
import ru.netology.web.data.CardDate;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

import java.net.URI;
import java.net.URL;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiTest {//в общем надо понять как в тело запроса включить данные запроса
    private final static String URL = "http://localhost:8080/";

   @Test
    public void successRegTest() {

       String expectedAnswer = "APPROVED";
//       open("http://localhost:8080");
//       var dashboardPage = new DashboardPage();
//       var paymentPage = new PaymentBuyPage();
//       dashboardPage.clickOnButtonPayCard();
//       paymentPage.validCardApproved();
//       paymentPage.waitAlarmOk(); //работает не стабильно, что то с ожиданием ответа похоже, не дожидается ответа
//       paymentPage.checkAlarmOk();
       SpecificationApi.installSpecification(SpecificationApi.requestSpec(URL), SpecificationApi.responseSpecOk200());
       CardDate data = new CardDate("4444444444444441", "NIK DIK", "123", "01", "23");
       AnswerApproved answer = given()
               .body(data)
               .when()
               .post("api/v1/pay")
               .then().log().all()
               .extract().as(AnswerApproved.class);
       Assertions.assertEquals(expectedAnswer, answer.getStatusApp());


   }





}





