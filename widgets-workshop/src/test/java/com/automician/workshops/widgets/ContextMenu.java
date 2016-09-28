package com.automician.workshops.widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;

public interface ContextMenu {

    ContextMenu open();
    void select(String item);
    ContextMenu set(String cssSelector, String name);
}
