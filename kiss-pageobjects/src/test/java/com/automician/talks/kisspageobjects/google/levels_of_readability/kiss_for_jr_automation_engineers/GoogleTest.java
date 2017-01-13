package com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_jr_automation_engineers;

import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_jr_automation_engineers.pageobjects.Google;
import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_jr_automation_engineers.pageobjects.SearchResults;
import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_jr_automation_engineers.testconfigs.BaseTest;
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
