package com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_jr_automation_engineers.testconfigs;

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
