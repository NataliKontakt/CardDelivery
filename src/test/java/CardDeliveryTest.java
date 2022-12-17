import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldSubmitRequest() {


        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Оренбург");
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79270000001");
        $("[data-test-id=agreement]").click(); //Чекбокс
        $x("//*[contains(text(),'Запланировать')]").click();  //Кнопка Запланировать Нужна задержка
        $(".notification__content")
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

}
