package com.automician.workshops.widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class Column {
    private final SelenideElement container;

    public Column(SelenideElement container) {
        this.container = container;
    }

    public void setName(String name) {
        GribleContextMenu menu = new GribleContextMenu(this.container);
        menu.open();
        menu.set("[name='context-menu-input-name']", name);
        menu.select("Save");
    }
}
