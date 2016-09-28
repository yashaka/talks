package com.automician.workshops.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Product {
    public DataStorages openDataStorages() {
        $(byText("Data Storages")).click();
        return new DataStorages();
    }
}
