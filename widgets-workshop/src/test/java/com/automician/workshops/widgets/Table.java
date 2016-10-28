package com.automician.workshops.widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;

public class Table {
    private final SelenideElement container = $("#table-container");

    public SelenideElement head() {
        return this.container.find("thead");
    }

    public SelenideElement body() {
        return this.container.find("tbody");
    }

    public SelenideElement header(int index) {
        return head().findAll("th").get(index);
    }

    public Column column(int index) {
        return new Column(header(index+1));
    }

    public void addColumnAfter(int index, String name) {
        row(0).cell(index)
              .menu()
              .open()
              .select("Insert column on the right");

        column(index + 1).setName(name);
    }

    private ElementsCollection rowElements() {
        return body().findAll("tr");
    }

    private ElementsCollection columnElements(int index) {
        return rowElements().get(index).findAll("td");
    }

    public Row row(int index) {
        return new Row(this.rowElements().get(index));
    }

    public Row addRowAfter(int index) {
        row(index).cell(0)
                .menu()
                .open()
                .select("Insert row below");
        /* + more readable and concise
         * - maybe more complicated for someone's taste

         * ~
        ContextMenu menu = this.table.row(index).cell(0).contextMenu();
        menu.open();
        menu.select("Insert row below");
         */

        return row(index + 1);
    }

    public void shouldHaveRows(List<String>... textRows) {
        rowElements().shouldHave(size(textRows.length));
        for(int i = 0; i< textRows.length; i++) {
            columnElements(i).shouldHave(exactTexts(textRows[i].toArray(new String[textRows[i].size()])));
        }
    }

    public int rowsSize() {
        return this.rowElements().size();
    }

    public void shouldHaveRowsSize(int size) {
        this.rowElements().shouldHaveSize(size);
    }

    public TableToolTip toolTip() {
        return new TableToolTip();
    }
}
