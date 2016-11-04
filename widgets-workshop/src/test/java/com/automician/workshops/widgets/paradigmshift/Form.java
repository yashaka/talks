package com.automician.workshops.widgets.paradigmshift;

import com.automician.workshops.widgets.ContextMenu;
import com.codeborne.selenide.SelenideElement;

public class Form {
    private final SelenideElement dialog;

    public Form(SelenideElement dialog) {
        this.dialog = dialog;
    }

    public void fill(WidgetValue... inputValues) {
        for (WidgetValue value: inputValues) {
            value.setInDialog(this.dialog);
        }
    }
}
