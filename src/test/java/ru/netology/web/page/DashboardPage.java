package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $x("//*[@id='root']/div/h2");
    private SelenideElement clickOnButtonPayCard = $x("//*[text()='Купить']");
    private SelenideElement clickOnButtonCreditCard = $x("//*[text()='Купить в кредит']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public PaymentBuyPage clickOnButtonPayCard() {
        clickOnButtonPayCard.click();
        return new PaymentBuyPage();
    }

    public PaymentCreditPage clickOnButtonCreditCard() {
        clickOnButtonCreditCard.click();
        return new PaymentCreditPage();
    }
}