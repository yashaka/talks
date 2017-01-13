package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_API_helpers.testconfigs;

import com.codeborne.selenide.Configuration;

/**
 * Created by yashaka on 1/7/17.
 */
public class BaseTest {
    {
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://laba.mba";
        Configuration.holdBrowserOpen = true;
    }
}
