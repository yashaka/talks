package com.automician.talks.kisspageobjects.diaspora.kiss_widgets;

import com.automician.talks.kisspageobjects.diaspora.SecretData;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets.model.Diaspora;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets.model.pageobjects.NewPost;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets.model.pageobjects.Stream;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets.testconfigs.BaseTest;
import org.junit.Test;


public class DiasporaTest extends BaseTest {

    @Test
    public void shareMessage() {
        new Diaspora().open().signIn(
                SecretData.Selenide.username, SecretData.Selenide.password);
        new NewPost().start().write("Selenide 4.2 released!").share();
        new Stream().post(0).shouldBe("Selenide 4.2 released!");
    }
}
