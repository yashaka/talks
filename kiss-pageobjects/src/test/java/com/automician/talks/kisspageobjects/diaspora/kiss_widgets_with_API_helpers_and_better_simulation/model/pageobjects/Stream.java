package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by yashaka on 1/7/17.
 */
public class Stream {
    private final SelenideElement container = $("#main_stream");

    private ElementsCollection elements() {
        return this.container.findAll(".stream_element");
    }

    public Post post(int index) {
        return new Post(this.elements().get(index));
    }
}
