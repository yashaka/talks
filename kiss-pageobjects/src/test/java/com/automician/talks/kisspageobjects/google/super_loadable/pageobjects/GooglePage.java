package com.automician.talks.kisspageobjects.google.super_loadable.pageobjects;

import com.automician.talks.kisspageobjects.google.super_loadable.helpers.WaitingLoadableComponent;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by yashaka on 1/9/17.
 */
public class GooglePage extends WaitingLoadableComponent<GooglePage> {
    private Search search;

    public GooglePage(WebDriver driver) {
        super(driver);
        this.search = new Search(driver);
    }

    protected void load() {
        this.driver.get("http://google.com/ncr");
    }

    public boolean isLoaded() {
        return this.search.isLoaded();
    }

    public GoogleSearchResultsPage search(String text) {
        this.search.query(text);
        return new GoogleSearchResultsPage(this.driver).get();
    }
}
