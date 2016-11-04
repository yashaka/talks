package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

public class Dialog {
    private SelenideElement container = $(".ui-dialog");
    private SelenideElement buttons = $(".dialog-buttons");

    @Step
    public Dialog setForLabel(String label, String value) {
        inputFor(label).setValue(value);
        return this;
    }

    /* + more universal and "user-friendly" (user operates texts not locators)

     * >=
    public Dialog setInDialog(String cssSelector, String value) {
        this.container.find(cssSelector).setValue(value);
        return this;
    }
     * + more accurate
     * - more fragile (if locator changes - test will fail,
     *   while probability of text change is lower)
     */

    @Step
    public SelenideElement inputFor(String label) {
        return new DialogInput(this.container, label).element();
    }

    @Step
    public void confirm() {
        buttons.find("[id^='dialog-btn'").click();
    }
}
