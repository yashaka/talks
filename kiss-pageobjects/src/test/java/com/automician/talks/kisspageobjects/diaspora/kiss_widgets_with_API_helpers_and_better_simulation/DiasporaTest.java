package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation;

import com.automician.talks.kisspageobjects.diaspora.SecretData;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model.ApiCall;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model.Diaspora;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model.pageobjects.NewPost;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.model.pageobjects.Stream;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers_and_better_simulation.testconfigs.BaseTest;
import org.junit.Test;


/**
 * This tests will work, because API - is just a stub, created for demonstrating the idea of Using API calls
 * to bake automation more efficient where appropriate
 */
public class DiasporaTest extends BaseTest {

    @Test
    public void authorSharesMessage() {
        new ApiCall().ensureSignedIn(
                SecretData.Selenide.username, SecretData.Selenide.password);
        
        new NewPost().start().write("Selenide 4.2 released!").share();
        new Stream().post(0).shouldBe("Selenide 4.2 released!");
        new ApiCall().assertMessageInStorage(SecretData.Selenide.username, "Selenide 4.2 released!");
    }

    @Test
    public void followerSeesNewMessageInTheStream() {
        new Diaspora().ensureSignedIn(
                SecretData.Yashaka.username, SecretData.Yashaka.password);
        
        new ApiCall().createMessageInStorage(SecretData.Selenide.username, "Selenide 4.2 released!");
        new Stream().post(0).shouldBe("Selenide 4.2 released!");
    }
}
