package com.automician.workshops.pages;

import com.automician.workshops.widgets.*;

public class TestTables {
    public void addCategory(String name) {
        new Categories().add(name);
    }

    public void addTestTable(String category, String name) {

        new ContextMenu(new Category(category).element())
                .open().select("Add table");
        Dialog dialog = new Dialog();
//        dialog.setForLabel("Name:", name);
        dialog.inputFor("Name:").setValue(name);
        dialog.confirm();
    }

    public Table table() {
        return new Table();
    }

    public void save() {
        new ManageButtons().save();
    }
}
