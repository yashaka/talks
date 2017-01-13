package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.pageobjects;

import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.core.SelenideDriver;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by yashaka on 1/7/17.
 */
public class NavBar {
    private final SelenideDriver driver;
    private ElementsCollection items;

    public NavBar(SelenideDriver driver) {
        this.driver = driver;
        this.items = this.driver.all(".nav a");
    }

    public void select(String item) {
        this.items.findBy(exactText(item)).click();
    }
}
