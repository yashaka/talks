package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model;

import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.core.SelenideDriver;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.pageobjects.Form;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.pageobjects.NavBar;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by yashaka on 1/7/17.
 */
public class Diaspora {

    private final SelenideDriver driver;

    public Diaspora(SelenideDriver driver) {
        this.driver = driver;
    }

    public Diaspora open() {
        this.driver.get("https://laba.mba/");
        return this;
    }

    public void signIn(String username, String password) {
        new NavBar(this.driver).select("Sign in");
        new Form(this.driver.element("#new_user"))
                .set("USERNAME", username)
                .set("PASSWORD", password)
                .submit();
    }
}
