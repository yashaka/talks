package com.automician.talks.kisspageobjects.google.kiss_widgets.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;

/**
 * Created by yashaka on 1/7/17.
 */
public class Result{
    private final SelenideElement container;

    public Result(SelenideElement container) {
        this.container = container;
    }

    public void followLink() {
        this.container.find(".r>a").click();
    }

    public Result shouldContain(String text) {
        this.container.shouldHave(text(text));
        return this;
    }
}
