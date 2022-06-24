package ru.netology.web.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class FieldTest {
    @Test
    void ShouldOpenAndPaste() {
open("http://localhost:8080");
var dashboardPage = new DashboardPage();
var paymentPage = new PaymentPage();
dashboardPage.clickOnButtonPayCard();
dashboardPage.clickOnButtonCreditCard();
paymentPage.validCard();



    }

}
