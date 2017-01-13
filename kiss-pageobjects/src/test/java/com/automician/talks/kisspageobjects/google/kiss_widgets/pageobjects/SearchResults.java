package com.automician.talks.kisspageobjects.google.kiss_widgets.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by yashaka on 1/7/17.
 */
public class SearchResults {
    private final SelenideElement container = $(".srg");

    private ElementsCollection elements(){
        return this.container.findAll(".g");
    }

    public Result result(int index) {
        return new Result(this.elements().get(index));
    }

    public SearchResults shouldHaveSize(int number) {
        this.elements().shouldHave(size(number));
        return this;
    }
}
