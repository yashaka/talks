package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.withText;

public class DialogInput {
    private final String label;
    private final SelenideElement container;

    public DialogInput(SelenideElement container, String label) {
        this.container = container;
        this.label = label;
    }

    @Step
    public SelenideElement element() {
        return this.container.find(withText(this.label))
                   .find(byXpath("./following-sibling::*/input"));
    }
}
