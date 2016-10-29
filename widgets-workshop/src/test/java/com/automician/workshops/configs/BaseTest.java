package com.automician.workshops.configs;

import com.codeborne.selenide.Configuration;

public class BaseTest {
    {
        Configuration.fastSetValue = true;
        /* makes entering new values to text inputs faster
         * and serves as workaround for some "issues" with entering values to some fields
         */

        Configuration.baseUrl = "http://0.0.0.0:8123";
    }
}
