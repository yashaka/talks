package com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_sr_automation_engineers.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    public ElementsCollection results(){
        return $$(".srg>.g");
    }

    public void followResultLink(int index) {
        this.results().get(index).find(".r>a").click();
    }
}
