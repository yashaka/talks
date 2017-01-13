package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver;

import com.automician.talks.kisspageobjects.diaspora.SecretData;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.core.SelenideDriver;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.Diaspora;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.pageobjects.NewPost;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.pageobjects.Stream;
import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.testconfigs.BaseTest;
import org.junit.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DiasporaTest extends BaseTest {
    SelenideDriver selenideBrowser;
    SelenideDriver yashakaBrowser;

    @Before
    public void setupDrivers() {
        this.selenideBrowser = new SelenideDriver(new FirefoxDriver());
        this.yashakaBrowser = new SelenideDriver(new FirefoxDriver());
    }

    @After
    public void teardownDrivers() {
        this.selenideBrowser.quit();
        this.yashakaBrowser.quit();
    }

    @Test
    public void shareMessageToFollowers() {
        new Diaspora(selenideBrowser).open().signIn(
                SecretData.Selenide.username, SecretData.Selenide.password);
        /* Yashaka follows Selenide ;) */
        new Diaspora(yashakaBrowser).open().signIn(
                SecretData.Yashaka.username, SecretData.Yashaka.password);

        new NewPost(selenideBrowser).start().write("Selenide 4.2 released!").share();
        new Stream(yashakaBrowser).refresh().post(0).shouldBe("Selenide 4.2 released!");
    }
}
