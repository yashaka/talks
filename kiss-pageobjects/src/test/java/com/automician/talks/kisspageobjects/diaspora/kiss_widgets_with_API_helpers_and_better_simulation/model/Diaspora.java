package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model;

import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model.pageobjects.Form;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model.pageobjects.NavBar;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by yashaka on 1/7/17.
 */
public class Diaspora {

    public Diaspora open() {
        Selenide.open("/");
        return this;
    }

    public void signIn(String username, String password) {
        new NavBar().select("Sign in");
        new Form($("#new_user"))
                .set("USERNAME", username)
                .set("PASSWORD", password)
                .submit();
    }

    public void ensureSignedIn(String username, String password) {
        new ApiCall().ensureSignedIn(username, password);
    }
}
