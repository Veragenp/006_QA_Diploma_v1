package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentBuyPage {
    private SelenideElement cardNumberField = $("*[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("*[placeholder='08']");
    private SelenideElement yearField = $("*[placeholder='22']");
    private SelenideElement ownerField = $x("//*[text()='Владелец']//following-sibling::*//*");
    private SelenideElement cvcField = $("*[placeholder='999']");
    private SelenideElement continueButton = $x("//span[contains(@class, 'spin')]//..");
    private SelenideElement alarmCardNumberField = $x("//*[text()='Номер карты']//following-sibling::span//following-sibling::span");//Неверный формат/
    private SelenideElement alarmMonthField = $x("//*[text()='Месяц']//following-sibling::span//following-sibling::span"); //Истек срок действия карты/Неверно указан срок действия карты/Поле пустое
    private SelenideElement alarmYearField = $x("//*[text()='Год']//following-sibling::span//following-sibling::span"); //Истек срок действия карты/Неверно указан срок действия карты/Поле пустое
    private SelenideElement alarmOwnerField = $x("//*[text()='Владелец']//following-sibling::span//following-sibling::span"); //Неверный формат/
    private SelenideElement alarmCvcField = $x("//*[text()='CVC/CVV']//following-sibling::span//following-sibling::span"); //Неверный формат/
    private SelenideElement alarmOK = $x("//div[contains(@class,'status_ok')]//div[@class='notification__title']"); //Успешно
    private SelenideElement alarmOkText = $x("//div[contains(@class,'status_ok')]//div[@class='notification__content']"); //Операция одобрена Банком.
    private SelenideElement alarmFail = $x("//div[contains(@class,'error')]//div[@class='notification__title']"); //Ошибка
    private SelenideElement alarmFailText = $x("//div[contains(@class,'error')]//div[@class='notification__content']"); //Ошибка! Банк отказал в проведении операции.

    //проверка сообщения под полем Номер карты
    @Step("Проверка сообщения об ошибке под полем 'Номер карты'")
    public void checkAlarmFieldCardNumber(String textAlarm) {
        alarmCardNumberField.shouldHave(text(textAlarm));
    }

    //проверка сообщения под полем Месяц
    @Step("Проверка сообщения об ошибке под полем 'Месяц'")
    public void checkAlarmFieldMonth(String textAlarm) {
        alarmMonthField.shouldHave(text(textAlarm));
    }

    //проверка сообщения под полем Год
    @Step("Проверка сообщения об ошибке под полем 'Год'")
    public void checkAlarmFieldYear(String textAlarm) {
        alarmYearField.shouldHave(text(textAlarm));
    }

    //проверка сообщения под полем Владелец
    @Step("Проверка сообщения об ошибке под полем 'Владелец'")
    public void checkAlarmFieldOwner(String textAlarm) {
        alarmOwnerField.shouldHave(text(textAlarm));
         }

    //проверка сообщения под полем CVC
    @Step("Проверка сообщения об ошибке под полем 'CVC/CVV'")
    public void checkAlarmFieldCvc(String textAlarm) {
        alarmCvcField.shouldHave(text(textAlarm));
            }


    //Проверка на поле месяц
    @Step("Проверка отправки формы")
    public PaymentBuyPage checkField(String year, String month, String cardNumber, String owner, String cvc) {
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvcField.setValue(cvc);
        continueButton.click();
        return new PaymentBuyPage();

    }


    @Step("Получение значения в поле 'Номер карты'")
    public String getFieldCardNumber() {
        if (cardNumberField.getTagName().equals("input")) {
            return cardNumberField.getValue();
        } else {
            return cardNumberField.getText();
        }
    }

    @Step("Получение значения в поле 'Месяц'")
    public String getFieldMonth() {
        if (monthField.getTagName().equals("input")) {
            return monthField.getValue();
        } else {
            return monthField.getText();
        }
    }

    @Step("Получение значения в поле 'Год'")
    public String getFieldYear() {
        if (yearField.getTagName().equals("input")) {
            return yearField.getValue();
        } else {
            return yearField.getText();
        }
    }

    @Step("Получение значения в поле 'CVC/CVV'")
    public String getFieldCvc() {
        if (cvcField.getTagName().equals("input")) {
            return cvcField.getValue();
        } else {
            return cvcField.getText();
        }
    }

    @Step("Получение значения в поле 'Владелец'")
    public String getFieldOwner() {
        if (ownerField.getTagName().equals("input")) {
            return ownerField.getValue();
        } else {
            return ownerField.getText();
        }
    }

    @Step("Получение сообщения от банка 'Ошибка'")
    public void checkAlarmFail() {
        alarmFail.shouldHave(text("Ошибка"), Duration.ofSeconds(30));
        alarmFailText.shouldHave(text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(30));

    }

    @Step("Получение сообщения от банка 'Успешно'")
    public void checkAlarmOk() {
        alarmOK.shouldHave(text("Успешно"), Duration.ofSeconds(30));
        alarmOkText.shouldHave(text("Операция одобрена Банком."), Duration.ofSeconds(30));
        }


}