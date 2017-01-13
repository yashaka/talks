package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers.model.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;

/**
 * Created by yashaka on 1/7/17.
 */
public class Post {
    private final SelenideElement container;

    public Post(SelenideElement container) {
        this.container = container;
    }

    public void shouldBe(String withText) {
        this.container.shouldHave(text(withText));
    }
}
