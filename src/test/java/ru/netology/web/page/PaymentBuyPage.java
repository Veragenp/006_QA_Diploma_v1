package ru.netology.web.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.CurrentData;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentBuyPage {
    private SelenideElement cardNumberField = $("*[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("*[placeholder='08']");
    private SelenideElement yearField = $("*[placeholder='22']");
    private SelenideElement ownerField = $x("//*[text()='Владелец']//following-sibling::*//*");
    private SelenideElement CVCField = $("*[placeholder='999']");
    private SelenideElement continueButton = $x("//span[contains(@class, 'spin')]//..");
    private SelenideElement alarmCardNumberField = $x("//*[text()='Номер карты']//following-sibling::span//following-sibling::span");//Неверный формат/
    private SelenideElement alarmMonthField = $x("//*[text()='Месяц']//following-sibling::span//following-sibling::span"); //Истек срок действия карты/Неверно указан срок действия карты/Поле пустое
    private SelenideElement alarmYearField = $x("//*[text()='Год']//following-sibling::span//following-sibling::span"); //Истек срок действия карты/Неверно указан срок действия карты/Поле пустое
    private SelenideElement alarmOwnerField = $x("//*[text()='Владелец']//following-sibling::span//following-sibling::span"); //Неверный формат/
    private SelenideElement alarmCVCField = $x("//*[text()='CVC/CVV']//following-sibling::span//following-sibling::span"); //Неверный формат/
    private SelenideElement alarmOK = $x("//div[contains(@class,'status_ok')]//div[@class='notification__title']"); //Успешно
    private SelenideElement alarmOkText = $x("//div[contains(@class,'status_ok')]//div[@class='notification__content']"); //Операция одобрена Банком.
    private SelenideElement alarmFail = $x("//div[contains(@class,'error')]//div[@class='notification__title']"); //Ошибка
    private SelenideElement alarmFailText = $x("//div[contains(@class,'error')]//div[@class='notification__content']"); //Ошибка! Банк отказал в проведении операции.


    public PaymentBuyPage validCardApproved() { //проверка валидной карты
        var currentYear = new CurrentData();
        cardNumberField.setValue(DataHelper.getCardInfoApproved().getCardNumber());
        monthField.setValue(DataHelper.getCardInfoApproved().getMonth());
        yearField.setValue(currentYear.currentYear(1));
        ownerField.setValue(DataHelper.getCardInfoApproved().getOwner());
        CVCField.setValue(DataHelper.getCardInfoApproved().getCVC());
        continueButton.click();
        // Configuration.timeout = 70000;
        // alarmOK.shouldBe(visible); //не понятно пока как стабилизировать, чтобы дожидался страницы
        //alarmOkText.shouldBe(visible);
        return new PaymentBuyPage();
    }

    public PaymentBuyPage validCardApprovedDataForYearPlusOne(String cardNumber, String month, String owner, String CVC) {
        var currentYear = new CurrentData();
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(currentYear.currentYear(1));
        ownerField.setValue(owner);
        CVCField.setValue(CVC);
        continueButton.click();
        Configuration.timeout = 70000;
        return new PaymentBuyPage();
    }

    //проверки для сценария 2.3.1, с добавлением даты и месяца отдельно
    public PaymentBuyPage validCardApprovedDataForYearPlusOneCurrentMonth(String cardNumber, String owner, String CVC) {
        var currentData = new CurrentData();
        cardNumberField.setValue(cardNumber);
        monthField.setValue(currentData.getCurrentMonth());
        yearField.setValue(currentData.currentYear(1));
        ownerField.setValue(owner);
        CVCField.setValue(CVC);
        continueButton.click();
        Configuration.timeout = 70000;
        return new PaymentBuyPage();
    }
    //проверка сообщения под полем Номер карты
    public PaymentBuyPage checkAlarmFieldCardNumber(String textAlarm) {
        alarmCardNumberField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();

    }

    //проверка сообщения под полем Месяц
    public PaymentBuyPage checkAlarmFieldMonth(String textAlarm) {
        alarmMonthField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();
    }

    //проверка сообщения под полем Год
    public PaymentBuyPage checkAlarmFieldYear(String textAlarm) {
        alarmYearField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();
    }

    //проверка сообщения под полем Владелец
    public PaymentBuyPage checkAlarmFieldOwner(String textAlarm) {
        alarmOwnerField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();
    }

    //проверка сообщения под полем CVC
    public PaymentBuyPage checkAlarmFieldCvc(String textAlarm) {
        alarmCVCField.shouldHave(text(textAlarm));
        return new PaymentBuyPage();
    }

    //Общая проверка на поля кроме года и месяца
    public PaymentBuyPage checkField (int plusYear, int plusMonth, String cardNumber, String owner, String CVC) {
        var currentData = new CurrentData();
        int currentYearInt = currentData.currentYearInt(0);
        int currentMonthInt = currentData.getCurrentMonthInt();
        int monthInt = currentMonthInt + plusMonth;
        int yearInt = currentYearInt + plusYear;
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
        String month = String.format("%02d",monthInt);
        String year = String.format("%02d",yearInt);
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        CVCField.setValue(CVC);
        continueButton.click();
        Configuration.timeout = 70000;
        return new PaymentBuyPage();

    }

    //Проверка на поле месяц
    public PaymentBuyPage checkFieldMonth (int plusYear, String month, String cardNumber, String owner, String CVC) {
        var currentData = new CurrentData();
        int currentYearInt = currentData.currentYearInt(0);
        int currentMonthInt = currentData.getCurrentMonthInt();
        int yearInt = currentYearInt + plusYear;
        String year = String.format("%02d",yearInt);
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        CVCField.setValue(CVC);
        continueButton.click();
        Configuration.timeout = 70000;
        return new PaymentBuyPage();

    }



    public String getFieldCardNumber() {
        if (cardNumberField.getTagName().equals("input")) {
            return cardNumberField.getValue();
        } else {
            return cardNumberField.getText();
        }
    }

    public String getFieldMonth() {
        if (monthField.getTagName().equals("input")) {
            return monthField.getValue();
        } else {
            return monthField.getText();
        }
    }


    //Проверка что сообщение под полем Номер карты "неверный формат"


//    public PaymentBuyPage validCardDenied() {
//        cardNumberField.setValue(DataHelper.getCardInfoDenied().getCardNumber());
//        monthField.setValue(DataHelper.getCardInfoDenied().getMonth());
//        yearField.setValue(DataHelper.getCurrentYear());
//        ownerField.setValue(DataHelper.getCardInfoDenied().getOwner());
//        CVCField.setValue(DataHelper.getCardInfoDenied().getCVC());
//        continueButton.click();
//        return new PaymentBuyPage();
//    }

    public PaymentBuyPage checkAlarmField() {
        alarmCardNumberField.shouldHave(text("Неверный формат"));
        return new PaymentBuyPage();


    }

    public PaymentBuyPage checkAlarmFail() {
        alarmFail.shouldHave(text("Ошибка"));
        alarmFailText.shouldHave(text("Ошибка! Банк отказал в проведении операции."));
        return new PaymentBuyPage();


    }

    public PaymentBuyPage checkAlarmOk() {
        alarmOK.shouldHave(text("Успешно"));
        alarmOkText.shouldHave(text("Операция одобрена Банком."));
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


//    public PaymentBuyPage setCurrentData() {
//        yearField.setValue(String.format("%02d", DataHelper.getCurrentData().getYear()));
//        monthField.setValue(String.format("%02d", DataHelper.getCurrentData().getMonth()));
//        return new PaymentBuyPage();
//    }

//    public int checkData(int month, int year) {
//        int currentMonth = DataHelper.getCurrentData().getMonth();
//        int currentYear = DataHelper.getCurrentData().getYear();
//        if (month < currentMonth & year == currentYear) {
//            return currentMonth;
//        }
//        if (month >= currentMonth & year >= currentYear) {
//        return month;
//        }
//        if (year < currentYear) {
//            return currentYear;
//        }
//        return //??
//    }return

}