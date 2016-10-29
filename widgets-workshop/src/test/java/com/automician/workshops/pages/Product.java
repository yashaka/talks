package com.automician.workshops.pages;

import com.automician.workshops.widgets.Section;
import com.codeborne.selenide.Selenide;
import ru.yandex.qatools.allure.annotations.Step;

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

    @Step
    public DataStorages openDataStorages() {

        new Section("Data Storages").click();

        /* + more "universal" and "accurate" (so solid and reliable)
         * ~
        $(byText("Data Storages")).click();
         * + more KISS
         */

        return new DataStorages();
    }

    @Step
    public void open() {
        Selenide.open("/?product=" + this.id);
    }

    @Step
    public TestTables openTestTables() {

        new Section("Test Tables").click();
        return new TestTables();
    }
}
