package ru.netology.web.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.CurrentData;

import java.time.Duration;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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
    public PaymentBuyPage checkAlarmFieldCardNumber(String textAlarm) {
        alarmCardNumberField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();

    }

    //проверка сообщения под полем Месяц
    @Step("Проверка сообщения об ошибке под полем 'Месяц'")
    public PaymentBuyPage checkAlarmFieldMonth(String textAlarm) {
        alarmMonthField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();
    }

    //проверка сообщения под полем Год
    @Step("Проверка сообщения об ошибке под полем 'Год'")
    public PaymentBuyPage checkAlarmFieldYear(String textAlarm) {
        alarmYearField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();
    }

    //проверка сообщения под полем Владелец
    @Step("Проверка сообщения об ошибке под полем 'Владелец'")
    public PaymentBuyPage checkAlarmFieldOwner(String textAlarm) {
        alarmOwnerField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();
    }

    //проверка сообщения под полем CVC
    @Step("Проверка сообщения об ошибке под полем 'CVC/CVV'")
    public PaymentBuyPage checkAlarmFieldCvc(String textAlarm) {
        alarmCvcField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();
    }

    //Общая проверка на поля кроме года и месяца
    @Step("Проверка отправки формы с изменяемыми годом и месяцем")
    public PaymentBuyPage checkField(int plusYear, int plusMonth, String cardNumber, String owner, String CVC) {
        var currentData = new CurrentData();
        int currentYearInt = currentData.currentYearInt(0);
        int currentMonthInt = currentData.getCurrentMonthInt(0);
        int monthInt = currentMonthInt + plusMonth;
        int yearInt = currentYearInt + plusYear;
        int monthRemainder = 12 - currentYearInt;
//        if(monthInt < currentMonthInt & yearInt == currentYearInt) {
//            monthInt = 0;
//            yearInt = currentYearInt;
//        }
//        if(monthInt >= currentMonthInt & yearInt >= currentYearInt) {
//           monthInt = monthInt;
//           yearInt = yearInt;
//        }
//        if(yearInt < currentYearInt) {
//            monthInt = monthInt;
//            yearInt = 0;
//        }
        String month = String.format("%02d", monthInt);
        String year = String.format("%02d", yearInt);
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvcField.setValue(CVC);
        continueButton.click();
        Configuration.timeout = 70000;
        return new PaymentBuyPage();

    }


    //Проверка на поле месяц
    @Step("Проверка отправки формы с изменяемыми годом")
    public PaymentBuyPage checkFieldMonth(int plusYear, String month, String cardNumber, String owner, String CVC) {
        var currentData = new CurrentData();
        int currentYearInt = currentData.currentYearInt(0);
        int currentMonthInt = currentData.getCurrentMonthInt(0);
        int yearInt = currentYearInt + plusYear;
        String year = String.format("%02d", yearInt);
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvcField.setValue(CVC);
        continueButton.click();
        Configuration.timeout = 70000;
        return new PaymentBuyPage();

    }

    //проверка поле год
    @Step("Проверка отправки формы с изменяемыми месяцем")
    public PaymentBuyPage checkFieldYear(String year, int plusMonth, String cardNumber, String owner, String CVC) {
        var currentData = new CurrentData();
        int currentYearInt = currentData.currentYearInt(0);
        int currentMonthInt = currentData.getCurrentMonthInt(0);
        int monthInt = currentMonthInt + plusMonth;
        String month = String.format("%02d", monthInt);
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvcField.setValue(CVC);
        continueButton.click();
        Configuration.timeout = 70000;
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


    public PaymentBuyPage checkAlarmField() {
        alarmCardNumberField.shouldHave(text("Неверный формат"));
        return new PaymentBuyPage();

    }

    @Step("Получение сообщения от банка 'Ошибка'")
    public PaymentBuyPage checkAlarmFail() {
        alarmFail.shouldHave(text("Ошибка"));
        alarmFailText.shouldHave(text("Ошибка! Банк отказал в проведении операции."));
        return new PaymentBuyPage();

    }
    @Step("Получение сообщения от банка 'Успешно'")
    public PaymentBuyPage checkAlarmOk() {
        alarmOK.shouldHave(text("Успешно"));
        alarmOkText.shouldHave(text("Операция одобрена Банком."));
        return new PaymentBuyPage();
    }
    @Step("Получение любого сообщения от банка (успешно/не успешно)")
    public PaymentBuyPage checkAlarm() {
        if (alarmOK.isDisplayed()) {
            alarmOK.shouldHave(text("Успешно"));
        } else alarmFail.shouldHave(text("Ошибка"));
        return new PaymentBuyPage();
    }

    public PaymentBuyPage checkAlarm2() {
        if (alarmFail.isDisplayed()) {
            alarmFail.shouldHave(text("Ошибка"));
        } else alarmOK.shouldHave(text("Успешно"));
        return new PaymentBuyPage();
    }


    public void waitAlarmOk() {
        alarmOK.should(appear, Duration.ofSeconds(30)); //работает
        alarmOkText.shouldBe(appear);
    }


    public void shouldCheckParametrizedTest() {
        alarmOK.should(appear, Duration.ofSeconds(30)); //работает
        alarmOkText.shouldBe(appear);
    }


}