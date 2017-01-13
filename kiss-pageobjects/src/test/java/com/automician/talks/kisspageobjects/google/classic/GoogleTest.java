package com.automician.talks.kisspageobjects.google.classic;

import com.automician.talks.kisspageobjects.google.classic.pageobjects.Google;
import com.automician.talks.kisspageobjects.google.classic.testconfigs.BaseTest;
import org.junit.Assert;
import org.junit.matchers.JUnitMatchers;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.automician.talks.kisspageobjects.google.classic.helpers.CustomConditions.numberOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/9/17.
 */
public class GoogleTest extends BaseTest {

    @Test
    public void search() {
        Google google = new Google(driver);
        google.open().search("Selenide");
        wait.until(numberOf(google.getResults(), 10));
        assertThat(google.getResults().get(0).getText(),
                containsString("Selenide: concise UI tests in Java"));
    }

    @Test
    public void followFirstLink() {
        Google google = new Google(driver);
        google.open().search("Selenide");
        google.followResultLink(0);
        wait.until(titleIs("Selenide: concise UI tests in Java"));
    }
}
