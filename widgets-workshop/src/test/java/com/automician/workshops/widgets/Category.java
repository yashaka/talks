package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class Category {
    private final String name;

    public Category(String name) {
        this.name = name;
    }

    @Step
    public ContextMenu menu() {
        return new ContextMenu(element());
    }

    @Step
    public SelenideElement element() {
        return $$(".category-item").findBy(exactText(this.name));
    }
}
