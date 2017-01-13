package com.automician.talks.kisspageobjects.google.kiss;

import com.automician.talks.kisspageobjects.google.kiss.pageobjects.Google;
import com.automician.talks.kisspageobjects.google.kiss.pageobjects.SearchResults;
import com.automician.talks.kisspageobjects.google.kiss.testconfigs.BaseTest;
import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/7/17.
 */
public class GoogleTest extends BaseTest {

    @Test
    public void search() {
        new Google().open().search("Selenide");
        new SearchResults()
                .shouldHaveSize(10)
                .shouldHaveResultText(0, "Selenide: concise UI tests in Java");
    }

    @Test
    public void followFirstLink() {
        new Google().open().search("Selenide");
        new SearchResults().followResultLink(0);
        Selenide.Wait().until(titleIs("Selenide: concise UI tests in Java"));
    }
}
