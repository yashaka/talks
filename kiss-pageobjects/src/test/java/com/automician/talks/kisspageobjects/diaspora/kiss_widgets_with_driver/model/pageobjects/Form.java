package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.pageobjects;

import com.codeborne.selenide.SelenideElement;

/**
 * Created by yashaka on 1/7/17.
 */
public class Form {
    private final SelenideElement container;

    public Form(SelenideElement container) {
        this.container = container;
    }

    public Form set(String placeholder, String value) {
        String normalizedPlaceholder =
                placeholder.substring(0,1).toUpperCase() + placeholder.substring(1).toLowerCase();
        this.container.find("[placeholder='" + normalizedPlaceholder + "']").setValue(value);
        return this;
    }

    public void submit() {
        this.container.find("[type=submit]").click();
    }
}
