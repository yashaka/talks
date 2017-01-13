package com.automician.talks.kisspageobjects.google.stepsobjects.steps;

import com.automician.talks.kisspageobjects.google.stepsobjects.pageobjects.Google;

import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by yashaka on 1/12/17.
 */
public class GoogleUser {

    private final Google google = new Google();

    public GoogleUser opensGoogle() {
        open("/ncr");
        return this;
    }

    public UserOnResultsPage searches(String text) {
        this.google.search().setValue(text).pressEnter();
        return new UserOnResultsPage();
    }
}
