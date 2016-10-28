package com.automician.workshops.pages;

import com.automician.workshops.widgets.Section;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Product {

    private final int id;

    public Product(int id) {
        this.id = id;
    }

    public Product(String id) {
        this(Integer.valueOf(id));
    }

    public DataStorages openDataStorages() {

        new Section("Data Storages").click();

        /* + more "universal" and "accurate" (so solid and reliable)
         * ~
        $(byText("Data Storages")).click();
         * + more KISS
         */

        return new DataStorages();
    }

    public void open() {
        Selenide.open("/?product=" + this.id);
    }

    public TestTables openTestTables() {

        new Section("Test Tables").click();
        return new TestTables();
    }
}
