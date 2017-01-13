package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model.pageobjects;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by yashaka on 1/7/17.
 */
public class NavBar {
    private ElementsCollection items = $$(".nav a");

    public void select(String item) {
        this.items.findBy(exactText(item)).click();
    }
}
