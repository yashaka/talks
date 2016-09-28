package com.automician.workshops.pages;

import com.automician.workshops.widgets.GribleContextMenu;
import com.automician.workshops.widgets.Dialog;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DataStorages {
    public void addCategory(String name) {
        $("#btn-add-category").click();
        $(".category-name").setValue(name);
        $("#dialog-btn-add-category").click();
    }

    public void addDataStorage(String category, String name) {
        new GribleContextMenu(this.category(category)).open().select("Add storage");
        Dialog dialog = new Dialog();
        dialog.set(".data-item-name", name);
        dialog.set(".data-storage-class-name", name);
        dialog.confirm();

    }

    private SelenideElement category(String name) {
        return $$(".category-item").findBy(exactText(name));
    }
}
