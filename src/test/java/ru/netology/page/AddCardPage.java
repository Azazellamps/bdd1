package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class AddCardPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement topUpButton = $("[data-test-id='action-transfer']");

    public AddCardPage() {
        SelenideElement heading = $(withText("Пополнение карты"));
        heading.shouldBe(visible);
    }

    public DashboardPage upperCard(int amount, DataHelper.InfoCard infoCard) {
        amountField.sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.BACK_SPACE);
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(infoCard.getNumber());
        topUpButton.click();
        return new DashboardPage();
    }

}

