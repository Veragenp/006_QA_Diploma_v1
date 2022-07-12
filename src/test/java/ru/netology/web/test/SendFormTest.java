package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.web.data.AnswerApproved;
import ru.netology.web.data.CardDate;
import ru.netology.web.data.CurrentData;
import ru.netology.web.data.SettingsSQL;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendFormTest {
    private final static String URL = "http://localhost:8080/";
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }
//    @Test
//    void ShouldGetAlarmOk() { //проверка текста сообщения от банка - положительный
//        open("http://localhost:8080");
//        var dashboardPage = new DashboardPage();
//        var paymentPage = new PaymentBuyPage();
//        dashboardPage.clickOnButtonPayCard();
//        paymentPage.validCardApproved();
//        paymentPage.waitAlarmOk(); //работает не стабильно, что то с ожиданием ответа похоже, не дожидается ответа
//        paymentPage.checkAlarmOk();
//    }
//
//    @ParameterizedTest
//    @CsvSource({"'4444444444444441', '01', 'GTYUTVNHBGJN JUHBUJUI', '123'", "'4444444444444441', '06', 'IA T', '000'"})
//    void ShouldCheckValidDataForm(String cardNumber, String month, String owner, String cvc) { //проверка текста сообщения от банка - положительный
//        open("http://localhost:8080");
//        var dashboardPage = new DashboardPage();
//        var paymentPage = new PaymentBuyPage();
//        dashboardPage.clickOnButtonPayCard();
//        paymentPage.validCardApprovedDataForYearPlusOne(cardNumber, month, owner, cvc);
//        paymentPage.waitAlarmOk(); //работает не стабильно, что то с ожиданием ответа похоже, не дожидается ответа
//        paymentPage.checkAlarmOk();
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(
//            resources = "/data/dataForTest.csv")
//    void ShouldCheckValidDataFormByFile(String cardNumber, String month, String owner, String cvc) { //проверка текста сообщения от банка - положительный
//        open("http://localhost:8080");
//        var currentData = new CurrentData();
//        var dashboardPage = new DashboardPage();
//        var paymentPage = new PaymentBuyPage();
//        dashboardPage.clickOnButtonPayCard();
//        paymentPage.validCardApprovedDataForYearPlusOne(cardNumber, month, owner, cvc);
//        paymentPage.waitAlarmOk(); //работает не стабильно, что то с ожиданием ответа похоже, не дожидается ответа
//        paymentPage.checkAlarmOk();
//
//    }

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
        String year = String.format("%02d",data2.currentYearInt(plusYear));
        String month = String.format("%02d",data2.getCurrentMonthInt(plusMonth));
        CardDate data = new CardDate(cardNumber, year, month, owner, cvc);
        AnswerApproved answer = given()
                .body(data)
                .when()
                .post("api/v1/pay")
                .then().log().all()
                .extract().as(AnswerApproved.class);
        Assertions.assertNotNull(answer.getStatus());
        Assertions.assertEquals(expectedAnswer, answer.getStatus());
        Assertions.assertEquals(expectedAnswer,  SettingsSQL.getStatusOperationFromDb());
     }

}
