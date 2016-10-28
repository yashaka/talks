package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class Category {
    private final String name;

    public Category(String name) {
        this.name = name;
    }

    public ContextMenu menu() {
        return new ContextMenu(element());
    }

    public SelenideElement element() {
        return $$(".category-item").findBy(exactText(this.name));
    }
}
