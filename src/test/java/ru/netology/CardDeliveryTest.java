package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.generateDate;


public class CardDeliveryTest {
    private RegistrationByCardInfo userInfo;

    @BeforeAll
    static void setupAll() {
        Configuration.browser = "firefox";
    }

    @BeforeEach
    void setUpAll() {
        userInfo = DataGenerator.Registration.generateByCard("ru");

    }


    @Test
    void allCorrect() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(userInfo.getCity());
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, generateDate(false));
        $("[data-test-id=name] input").setValue(userInfo.getName());
        $("[data-test-id=phone] input").setValue(userInfo.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='success-notification']>.notification__content").shouldBe(Condition.visible, Duration.ofSeconds(5)).shouldHave(text("Встреча успешно запланирована на " + generateDate(false)));
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, DataGenerator.Registration.generateDate(true));
        $(".button").click();
        $("[data-test-id=replan-notification] .notification__title").shouldBe(Condition.visible, Duration.ofSeconds(5)).shouldHave(Condition.exactText("Необходимо подтверждение"));
        $("[data-test-id=replan-notification] .button").click();
        $("[data-test-id='success-notification']>.notification__content").shouldBe(Condition.visible, Duration.ofSeconds(5)).shouldHave(text("Встреча успешно запланирована на " + generateDate(true)));
    }
}
