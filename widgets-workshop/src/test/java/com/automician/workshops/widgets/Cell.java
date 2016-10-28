package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Cell {

    private final SelenideElement container;

    public Cell(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement element() {
        return this.container;
    }

    public ContextMenu menu() {
        return new ContextMenu($(".htContextMenu"), this.container);
    }

    public Cell fill(String value) {
        if (!this.container.has(cssClass("current"))) {
            this.container.click();
        }
        this.container.doubleClick();
        $$(".handsontableInput").findBy(visible).setValue(value).pressEnter();
        return this;
    }

    public void hover() {
        this.container.hover();
    }
}
