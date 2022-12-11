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

    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String formattedString = LocalDateTime.now().plusDays(4).format(formatter);

    @Test
    void shouldSubmitRequest() {

        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Оренбург");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(formattedString);
        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79270000001");
        $("[data-test-id=agreement]").click(); //Чекбокс
        $x("//*[contains(text(),'Запланировать')]").click();  //Кнопка Запланировать Нужна задержка
        $x("//*[contains(text(),'Успешно!')]").shouldBe(visible,Duration.ofSeconds(5));}
}
