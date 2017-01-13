package com.automician.talks.kisspageobjects.google.kiss_widgets;

import com.automician.talks.kisspageobjects.google.kiss_widgets.pageobjects.SearchResults;
import com.automician.talks.kisspageobjects.google.kiss_widgets.testconfigs.BaseTest;
import com.automician.talks.kisspageobjects.google.kiss_widgets.pageobjects.Google;
import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/7/17.
 */
public class GoogleTest extends BaseTest{

    @Test
    public void search() {
        new Google().open().search("Selenide");
        new SearchResults()
                .shouldHaveSize(10)
                .result(0).shouldContain("Selenide: concise UI tests in Java");
    }

    @Test
    public void followFirstLink() {
        new Google().open().search("Selenide");
        new SearchResults().result(0).followLink();
        Selenide.Wait().until(titleIs("Selenide: concise UI tests in Java"));
    }
}
