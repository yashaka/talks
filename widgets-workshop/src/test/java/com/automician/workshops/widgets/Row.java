package com.automician.workshops.widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
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

    public Cell cell(int index) {
        return new Cell(cells().get(index));
    }

    public void fill(String... data) {
        for(int i=0; i<size(); i++) {
            cell(i).fill(data[i]);
        }
    }
}
