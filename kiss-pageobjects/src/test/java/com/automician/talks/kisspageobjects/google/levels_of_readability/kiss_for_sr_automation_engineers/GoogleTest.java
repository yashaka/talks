package com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_sr_automation_engineers;

import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_sr_automation_engineers.pageobjects.Google;
import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_sr_automation_engineers.testconfigs.BaseTest;
import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/7/17.
 */
public class GoogleTest extends BaseTest {

    @Test
    public void search() {
        Google google = new Google();
        google.open().search("Selenide");
        google.results().shouldHave(size(10));
        google.results().get(0).shouldHave(text("Selenide: concise UI tests in Java"));
    }

    @Test
    public void followFirstLink() {
        Google google = new Google();
        google.open().search("Selenide");
        google.followResultLink(0);
        Selenide.Wait().until(titleIs("Selenide: concise UI tests in Java"));
    }
}
