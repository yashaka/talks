package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.pageobjects;

import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.core.SelenideDriver;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by yashaka on 1/7/17.
 */
public class Stream {
    private final SelenideDriver driver;
    private final SelenideElement container;

    public Stream(SelenideDriver driver) {
        this.driver = driver;
        this.container = this.driver.element("#main_stream");
    }

    private ElementsCollection elements() {
        return this.container.findAll(".stream_element");
    }

    public Post post(int index) {
        return new Post(this.elements().get(index));
    }

    public Stream refresh() {
        this.driver.get(this.driver.getCurrentUrl());
        return this;
    }
}
