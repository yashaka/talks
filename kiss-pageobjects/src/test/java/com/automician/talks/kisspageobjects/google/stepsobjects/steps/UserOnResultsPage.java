package com.automician.talks.kisspageobjects.google.stepsobjects.steps;

import com.automician.talks.kisspageobjects.google.stepsobjects.pageobjects.Google;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;

/**
 * Created by yashaka on 1/12/17.
 */
public class UserOnResultsPage extends GoogleUser {

    private final Google google = new Google();

    public UserOnResultsPage expectsNumberOfResults(int number) {
        this.google.results().shouldHave(size(number));
        return this;
    }

    public UserOnResultsPage expectsResultWithText(int index, String text) {
        this.google.results().get(index).shouldHave(text(text));
        return this;
    }

    public UserOnNewPage followsResultLink(int index) {
        this.google.resultLink(index).click();
        return new UserOnNewPage();
    }
}
