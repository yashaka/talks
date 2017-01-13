package com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_manual_engineers.testconfigs;

import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_manual_engineers.pageobjects.GoogleApp;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by yashaka on 1/7/17.
 */
public class BaseTest {

    public GoogleApp google;

    {
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://google.com";
        Configuration.holdBrowserOpen = true;
        google = new GoogleApp();
    }

    public <T> T assertThat(ExpectedCondition<T> condition) {
        return Selenide.Wait().until(condition);
    }
}
