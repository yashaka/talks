package com.automician.workshops.widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import static java.util.Arrays.asList;

public class Table {
    private final SelenideElement container = $("#table-container");

    @Step
    public SelenideElement head() {
        return this.container.find("thead");
    }

    @Step
    public SelenideElement body() {
        return this.container.find("tbody");
    }

    @Step
    public ElementsCollection headers() {
        return head().findAll("th");
    }

    @Step
    public void shouldHaveColumnHeaders(String... texts) {
        List<String> headerTexts = new ArrayList<String>();
        headerTexts.add("");
        headerTexts.addAll(asList(texts));
        headers().shouldHave(exactTexts(headerTexts.toArray(new String[0])));
    }

    @Step
    public SelenideElement header(int index) {
        return headers().get(index);
    }

    @Step
    public Column column(int index) {
        return new Column(header(index+1));
    }

    @Step
    public void addColumnAfter(int index, String name) {
        row(0).cell(index)
              .menu()
              .open()
              .select("Insert column on the right");

        column(index + 1).setName(name);
    }

    @Step
    private ElementsCollection rowElements() {
        return body().findAll("tr");
    }

    @Step
    private ElementsCollection columnElements(int index) {
        return rowElements().get(index).findAll("td");
    }

    @Step
    public Row row(int index) {
        return new Row(this.rowElements().get(index));
    }

    @Step
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

    @Step
    public void shouldHaveRows(List<String>... textRows) {
        rowElements().shouldHave(size(textRows.length));
        for(int i = 0; i< textRows.length; i++) {
            columnElements(i).shouldHave(exactTexts(textRows[i].toArray(new String[textRows[i].size()])));
        }
    }

    @Step
    public int rowsSize() {
        return this.rowElements().size();
    }

    @Step
    public void shouldHaveRowsSize(int size) {
        this.rowElements().shouldHaveSize(size);
    }

    @Step
    public TableToolTip toolTip() {
        return new TableToolTip();
    }
}
