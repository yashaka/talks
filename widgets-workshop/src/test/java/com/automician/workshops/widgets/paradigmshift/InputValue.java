package com.automician.workshops.widgets.paradigmshift;

import com.automician.workshops.widgets.MenuInput;
import com.codeborne.selenide.SelenideElement;

public class InputValue implements WidgetValue{
    private final String label;
    private final String value;

    public InputValue(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public void setInDialog(SelenideElement dialog) {
        new MenuInput(dialog, this.label).setValue(this.value);
    }
}
