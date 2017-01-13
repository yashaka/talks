package com.automician.talks.kisspageobjects.google.stepsobjects.steps;

import com.codeborne.selenide.Selenide;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/12/17.
 */
public class UserOnNewPage {

    public UserOnNewPage expectsTitle(String text) {
        Selenide.Wait().until(titleIs(text));
        return this;
    }
}
