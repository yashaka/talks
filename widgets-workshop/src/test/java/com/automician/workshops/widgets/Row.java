package com.automician.workshops.widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class Row {
    private final SelenideElement container;

    public Row(SelenideElement container) {
        this.container = container;
    }

    public int size() {
        return cells().size();
    }

    public ElementsCollection cells() {
        return this.container.findAll("td");
    }

    public SelenideElement cell(int index) {
        return cells().get(index);
    }

    public void fillCell(int index, String value) {
        cell(index).click();
        cell(index).doubleClick();
        $$(".handsontableInput").findBy(visible).setValue(value).pressEnter();

    }

    public void fill(String... data) {
        for(int i=0; i<size(); i++) {
            fillCell(i, data[i]);
        }
    }
}
