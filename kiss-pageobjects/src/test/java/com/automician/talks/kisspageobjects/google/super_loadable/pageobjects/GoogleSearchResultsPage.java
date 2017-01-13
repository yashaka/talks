package com.automician.talks.kisspageobjects.google.super_loadable.pageobjects;

import com.automician.talks.kisspageobjects.google.super_loadable.helpers.LoadingComponent;
import org.openqa.selenium.WebDriver;

/**
 * Created by yashaka on 1/9/17.
 */
public class GoogleSearchResultsPage
        extends LoadingComponent<GoogleSearchResultsPage> {
    private Search search;
    private Results results;

    public GoogleSearchResultsPage(WebDriver driver) {
        super(driver);
        this.search = new Search(driver);
        this.results = new Results(driver);
    }

    public boolean isLoaded() {
        return this.results.isLoaded();
    }

    public Results getResults() {
        return this.results;
    }
}
