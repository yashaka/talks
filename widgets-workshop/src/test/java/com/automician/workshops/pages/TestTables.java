package com.automician.workshops.pages;

import com.automician.workshops.widgets.*;
import ru.yandex.qatools.allure.annotations.Step;

public class TestTables {
    @Step
    public void addCategory(String name) {
        new Categories().add(name);
    }

    @Step
    public void addTestTable(String category, String name) {

        new ContextMenu(new Category(category).element())
                .open().select("Add table");
        Dialog dialog = new Dialog();
//        dialog.setForLabel("Name:", name);
        dialog.inputFor("Name:").setValue(name);
        dialog.confirm();
    }

    @Step
    public Table table() {
        return new Table();
    }

    @Step
    public void save() {
        new ManageButtons().save();
    }
}
