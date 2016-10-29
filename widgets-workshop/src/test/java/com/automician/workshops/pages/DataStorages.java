package com.automician.workshops.pages;

import com.automician.workshops.widgets.*;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DataStorages {
    @Step
    public void addCategory(String name) {
        new Categories().add(name);
    }

    @Step
    public void addDataStorage(String category, String name) {

        new Category(category)
                .menu()
                .open()
                .select("Add storage");

        /* + simpler & concise (less explicit creation of objects)
         * - one more additional method menu for Category object
         *
         * >=
        new ContextMenu(
                new Category(category).element()
        )
                .open()
                .select("Add storage");
         */

        new Dialog()
                .setForLabel("Name:", name)
                .setForLabel("Class name:", name)
                .confirm();

        /* ~
        Dialog dialog = new Dialog();
        dialog.setForLabel("Name:", name);
        dialog.setForLabel("Class name:", name);
        dialog.confirm();
         */
    }

    @Step
    public void save() {
        new ManageButtons().save();
    }

    @Step
    public Table table() {
        return new Table();
    }
}
