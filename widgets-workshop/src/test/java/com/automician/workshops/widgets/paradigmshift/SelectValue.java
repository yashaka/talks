package com.automician.workshops.widgets.paradigmshift;

import com.automician.workshops.widgets.MenuInput;
import com.automician.workshops.widgets.MenuSelect;
import com.codeborne.selenide.SelenideElement;

public class SelectValue implements WidgetValue {
    private final String label;
    private final String value;

    public SelectValue(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public void setInDialog(SelenideElement dialog) {
        new MenuInput(dialog, this.label).click();
        new MenuSelect(dialog, this.label).selectOption(this.value);
    }
}
