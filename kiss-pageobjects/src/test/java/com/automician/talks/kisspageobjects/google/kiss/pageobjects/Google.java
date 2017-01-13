package com.automician.talks.kisspageobjects.google.kiss.pageobjects;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by yashaka on 1/7/17.
 */
public class Google {
    public Google open() {
        Selenide.open("/ncr");
        return this;
    }

    public void search(String text) {
        $(By.name("q")).setValue(text).pressEnter();
    }
}
