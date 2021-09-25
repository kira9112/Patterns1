package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.Keys.DELETE;
import static ru.netology.data.DataGenerator.generateDate;

public class CardDeliveryTest {

    private final String date = generateDate(3);
    private final String anotherDate = generateDate(4);


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        $(cssSelector("[data-test-id=date] .input__control")).doubleClick().sendKeys(DELETE);
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

    @Test
    void shouldTestOrderWithValidData() {
        val validUser = DataGenerator.Registration.generateUser("ru");
        $(cssSelector("[data-test-id=city] .input__control")).setValue(validUser.getCity());
        $(cssSelector("[data-test-id=date] .input__control")).sendKeys(date);
        $(cssSelector("[data-test-id=name] .input__control")).setValue(validUser.getName());
        $(cssSelector("[data-test-id=phone] .input__control")).setValue(validUser.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $$("button").find(Condition.exactText("Запланировать")).click();
        $(".notification__content").waitUntil(Condition.visible,
                1500).shouldHave(exactText("Встреча успешно запланирована на " + date));
    }

    @Test
    void shouldTestOrderWithValidDataAndAnotherMeetingDate() {
        val validUser = DataGenerator.Registration.generateUser("ru");
        $(cssSelector("[data-test-id=city] .input__control")).setValue(validUser.getCity());
        $(cssSelector("[data-test-id=date] .input__control")).sendKeys(date);
        $(cssSelector("[data-test-id=name] .input__control")).setValue(validUser.getName());
        $(cssSelector("[data-test-id=phone] .input__control")).setValue(validUser.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $$("button").find(Condition.exactText("Запланировать")).click();
        $(".notification__content").waitUntil(Condition.visible,
                1500).shouldHave(exactText("Встреча успешно запланирована на " + date));
        $(cssSelector("[data-test-id=date] .input__control")).doubleClick().sendKeys(DELETE, anotherDate);
        $$("button").find(Condition.exactText("Запланировать")).click();
        $$(".button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(Condition.visible,
                1500).shouldHave(exactText("Успешно! Встреча успешно запланирована на " + anotherDate));
    }

   }