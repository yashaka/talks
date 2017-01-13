package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers;

import com.automician.talks.kisspageobjects.diaspora.SecretData;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers.model.API;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers.model.Diaspora;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers.model.pageobjects.NewPost;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers.model.pageobjects.Stream;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers.testconfigs.BaseTest;
import org.junit.Test;


/**
 * This test will work, because API - is just a stub, created for demonstrating the idea of Using API calls
 * to bake automation more efficient where appropriate
 */
public class DiasporaTest extends BaseTest {

    @Test
    public void shareMessageToFollowers() {
        new Diaspora().open().signIn(SecretData.Selenide.username, SecretData.Selenide.password);
        new NewPost().start().write("Selenide 4.2 released!").share();
        new API().ensureLoggedIn(SecretData.Yashaka.username, SecretData.Yashaka.password);
        new Stream().post(0).shouldBe("Selenide 4.2 released!");
    }
}
