package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;

public class HtTable {
    private final SelenideElement container;

    public HtTable(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement head() {
        return this.container.find("thead");
    }

    public SelenideElement body() {
        return this.container.find("tbody");
    }

    public SelenideElement header(int index) {
        return head().findAll("th").get(index);
    }

    public Row row(int index) {
        return new Row(body().findAll("tr").get(index));
    }
}
