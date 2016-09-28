package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Dialog {
    private SelenideElement container = $(".ui-dialog");
    private SelenideElement buttons = $(".dialog-buttons");

    public Dialog set(String cssSelector, String value) {
        this.container.find(cssSelector).setValue(value);
        return this;
    }

    public void confirm() {
        buttons.find("[id^='dialog-btn'").click();
    }
}
