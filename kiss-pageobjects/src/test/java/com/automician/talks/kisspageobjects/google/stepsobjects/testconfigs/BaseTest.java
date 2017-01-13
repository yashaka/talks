package com.automician.talks.kisspageobjects.google.stepsobjects.testconfigs;

import com.codeborne.selenide.Configuration;

/**
 * Created by yashaka on 1/7/17.
 */
public class BaseTest {
    {
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://google.com";
        Configuration.holdBrowserOpen = true;
    }
}
