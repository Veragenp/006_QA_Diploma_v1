package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {
    private SelenideElement cardNumberField = $("*[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("*[placeholder='08']");
    private SelenideElement yearField = $("*[placeholder='22']");
    private SelenideElement ownerField = $("[data-test-id=action-login]");
  //  private SelenideElement CVCField = $("[data-test-id=action-login]");
  //  private SelenideElement continueButton = $("[data-test-id=action-login]");


    public PaymentPage validCard () {
cardNumberField.setValue("0000000000000000");
monthField.setValue("02");
yearField.setValue("24");
ownerField.setValue("Vasya");

return new PaymentPage();
    }

}

