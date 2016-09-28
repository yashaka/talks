package com.automician.workshops.pages;

import com.automician.workshops.widgets.ConfirmationDialog;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Home {
    public void open() {
        Selenide.open("http://0.0.0.0:8123/");
    }

    public void addProduct(String name, String path) {
        $("#btn-add-product").click();
        $(".product-name").setValue(name);
        $(".product-path").setValue(path);
        $("#dialog-btn-add-product").click();
        new ConfirmationDialog().confirm();
    }

    public Product openProduct(String productName) {
        $$(".product-item").findBy(exactText(productName)).click();
        return new Product();
    }
}
