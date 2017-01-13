package com.automician.talks.kisspageobjects.google.kiss.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by yashaka on 1/7/17.
 */
public class SearchResults {
    private ElementsCollection elements(){
        return $$(".srg>.g");
    }

    public void followResultLink(int index) {
        this.elements().get(index).find(".r>a").click();
    }

    public SearchResults shouldHaveSize(int number) {
        this.elements().shouldHave(size(number));
        return this;
    }

    public SearchResults shouldHaveResultText(int index, String text) {
        this.elements().get(index).shouldHave(text(text));
        return this;
    }
}
