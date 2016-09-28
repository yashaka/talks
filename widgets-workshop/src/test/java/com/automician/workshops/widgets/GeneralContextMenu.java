package com.automician.workshops.widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;

public class GeneralContextMenu implements ContextMenu {

    private final SelenideElement container;
    private final SelenideElement element;

    public GeneralContextMenu(SelenideElement container, SelenideElement element) {
        this.container = container;
        this.element = element;
    }

    public ContextMenu open() {
        this.element.contextClick();
        return this;
    }

    public void select(String item) {
        this.container.find(byText(item)).click();
    }

    public ContextMenu set(String cssSelector, String name) {
//        Selenide.sleep(500);
        this.container.find(cssSelector).setValue(name);
        return this;
    }
}
