package com.automician.workshops.widgets;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GribleContextMenu {

    private ContextMenu contextMenu;

    public GribleContextMenu(SelenideElement element) {
        this.contextMenu = new GeneralContextMenu(
                $$(".context-menu-list").findBy(visible),
                element);
    }

    public ContextMenu open() {
        return contextMenu.open();
    }

    public void select(String item) {
        contextMenu.select(item);
    }

    public ContextMenu set(String cssSelector, String name) {
        $$(".context-menu-list").filterBy(visible).shouldHave(size(1));
        return contextMenu.set(cssSelector, name);
    }
}
