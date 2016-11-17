package com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity;

import com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity.pages.GooglePage;
import com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity.testconfigs.BaseTest;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class GoogleTest extends BaseTest {
    static GooglePage google;

    @BeforeClass
    public static void init() {
        google = new GooglePage(driver);
    }

    @Test
    public void search() {
        google.open();

        google.search("Selenide");
        google.assertNthResultHasText(0, "Selenide: concise UI tests in Java");
    }

    @Test
    public void followFirstLink() {
        google.open();
        google.search("Selenide");

        google.followLink(0);
        wait.until(urlToBe("http://selenide.org/"));
    }
}


