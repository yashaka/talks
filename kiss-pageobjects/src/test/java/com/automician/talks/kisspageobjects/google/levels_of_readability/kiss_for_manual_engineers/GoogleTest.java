package com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_manual_engineers;

import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_manual_engineers.pageobjects.Google;
import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_manual_engineers.pageobjects.SearchResults;
import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_manual_engineers.testconfigs.BaseTest;
import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/7/17.
 */
public class GoogleTest extends BaseTest {

    @Test
    public void search() {
        google.home.open().search("Selenide");
        google.results
                .shouldHaveSize(10)
                .result(0).shouldContain("Selenide: concise UI tests in Java");
    }

    @Test
    public void followFirstLink() {
        google.home.open().search("Selenide");
        google.results.result(0).followLink();
        assertThat(titleIs("Selenide: concise UI tests in Java"));
    }
}
