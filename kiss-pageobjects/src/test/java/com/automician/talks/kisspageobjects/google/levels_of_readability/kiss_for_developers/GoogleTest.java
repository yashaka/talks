package com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_developers;

import com.automician.talks.kisspageobjects.google.levels_of_readability.kiss_for_developers.testconfigs.BaseTest;
import com.codeborne.selenide.Selenide;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/7/17.
 */
public class GoogleTest extends BaseTest {

    @Test
    public void search() {
        open("/ncr");
        $(By.name("q")).setValue("Selenide").pressEnter();
        $$(".srg>.g").shouldHave(size(10));
        $$(".srg>.g").get(0).shouldHave(text("Selenide: concise UI tests in Java"));
    }

    @Test
    public void followFirstLink() {
        open("/ncr");
        $(By.name("q")).setValue("Selenide").pressEnter();
        $$(".srg>.g").get(0).find(".r>a").click();
        Selenide.Wait().until(titleIs("Selenide: concise UI tests in Java"));
    }
}
