package com.automician.talks.kisspageobjects.google.super_loadable;

import com.automician.talks.kisspageobjects.google.super_loadable.pageobjects.GooglePage;
import com.automician.talks.kisspageobjects.google.super_loadable.pageobjects.GoogleSearchResultsPage;
import com.automician.talks.kisspageobjects.google.super_loadable.testconfigs.BaseTest;
import org.junit.Test;

import static com.automician.talks.kisspageobjects.google.super_loadable.helpers.CustomConditions.numberOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/9/17.
 */
public class GoogleTest extends BaseTest {

    @Test
    public void search() {
        GooglePage google = new GooglePage(driver);
        GoogleSearchResultsPage resultsPage = google.get().search("Selenide");
        assertThat(resultsPage.getResults().size(), equalTo(10));
        assertThat(resultsPage.getResults().get(0).getText(),
                containsString("Selenide: concise UI tests in Java"));
    }

    @Test
    public void followFirstLink() {
        GooglePage google = new GooglePage(driver);
        google.get().search("Selenide")
                .getResults().followResultLink(0);
        wait.until(titleIs("Selenide: concise UI tests in Java"));
    }
}
