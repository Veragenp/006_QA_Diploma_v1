package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.web.data.CurrentData;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentBuyPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
      }
    @Test
    void ShouldGetAlarmOk() { //проверка текста сообщения от банка - положительный
        open("http://localhost:8080");
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.validCardApproved();
        paymentPage.checkAlarmOk();
    }

    @Test
    void ShouldGetAlarmFail() { //проверка текста сообщения от банка - отрицательный
        open("http://localhost:8080");
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        dashboardPage.clickOnButtonCreditCard();
     //   paymentPage.validCardDenied();
        paymentPage.checkAlarmFail();
    }
@Test
    //@ParameterizedTest
    //@CsvSource(value = )
    void ShouldCheckDataHelper() { //проверка текста сообщения от банка - отрицательный
        open("http://localhost:8080");
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        dashboardPage.clickOnButtonCreditCard();
        //   paymentPage.validCardDenied();
        paymentPage.checkAlarmFail();
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/data/2_3_1_Data.csv")
    void ShouldCheckNegativeForCardNumberAndGetWrongFormat(String cardNumber, String owner, String cvc) { //проверка текста сообщения от банка - положительный
        open("http://localhost:8080");
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentBuyPage();
        dashboardPage.clickOnButtonPayCard();
        paymentPage.validCardApprovedDataForYearPlusOneCurrentMonth(cardNumber, owner, cvc);
        paymentPage.checkAlarmFieldCardNumberWrongFormat();

        //paymentPage.waitAlarmOk(); //работает не стабильно, что то с ожиданием ответа похоже, не дожидается ответа
       // paymentPage.checkAlarmOk();

    }



  //  class BonusServiceTest {
 //       @ParameterizedTest
  //     @CsvSource(value = {"'registered user, under limit',100060,true,30",
 //              "'registered user, bonus under limit',100000060,true,500"})
  //     void shouldCalculate(String testName, long amount, boolean registered, long expected) {
//            BonusService service = new BonusService();
//            long actual = service.calculate(amount, registered);
//
//            assertEquals(expected, actual);
//        }


   // }
}
