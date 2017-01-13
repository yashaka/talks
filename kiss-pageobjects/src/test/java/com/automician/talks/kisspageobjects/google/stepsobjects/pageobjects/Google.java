package com.automician.talks.kisspageobjects.google.stepsobjects.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by yashaka on 1/7/17.
 */
public class Google {
    public final SelenideElement search = $(By.name("q"));
    public final ElementsCollection results = $$(".srg>.g");

    public SelenideElement search() {
        return $(By.name("q"));
    }

    public ElementsCollection results(){
        return $$(".srg>.g");
    }

    public SelenideElement resultLink(int index) {
        return this.results().get(index).find(".r>a");
    }
}
