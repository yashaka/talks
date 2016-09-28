package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HtContextMenu implements ContextMenu{

    private ContextMenu contextMenu;

    public HtContextMenu(SelenideElement element) {
        this.contextMenu = new GeneralContextMenu($(".htContextMenu"), element);
    }

    public ContextMenu open() {
        return contextMenu.open();
    }

    public void select(String item) {
        contextMenu.select(item);
    }

    public ContextMenu set(String cssSelector, String name) {
        return contextMenu.set(cssSelector, name);
    }
}
