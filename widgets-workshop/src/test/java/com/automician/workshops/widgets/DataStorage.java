package com.automician.workshops.widgets;

import static com.codeborne.selenide.Selenide.$;

public class DataStorage {
    private HtTable table = new HtTable($("#table-container"));

    public Column column(int index) {
        return new Column(this.table.header(index+1));
    }

    public void addColumnAfter(int index, String name) {
        ContextMenu menu = new HtContextMenu(this.table.row(0).cell(index));
        menu.open();
        menu.select("Insert column on the right");
        column(1).setName(name);
    }

    public Row row(int index) {
        return this.table.row(index);
    }
}
