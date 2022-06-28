package ru.netology.web.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTest {
    @Test
    void ShouldOpenAndPaste() {
        open("http://localhost:8080");
        var dashboardPage = new DashboardPage();
        var paymentPage = new PaymentPage();
        dashboardPage.clickOnButtonPayCard();
        dashboardPage.clickOnButtonCreditCard();
        paymentPage.validCard();
        paymentPage.checkAlarm();


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
