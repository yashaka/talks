package com.automician.talks.kisspageobjects.google.stepsobjects;

import com.automician.talks.kisspageobjects.google.stepsobjects.steps.GoogleUser;
import com.automician.talks.kisspageobjects.google.stepsobjects.testconfigs.BaseTest;
import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 1/7/17.
 */
public class GoogleTest extends BaseTest {

    @Test
    public void search() {
        new GoogleUser().opensGoogle()
                .searches("Selenide")
                .expectsNumberOfResults(10)
                .expectsResultWithText(0, "Selenide: concise UI tests in Java");
    }

    @Test
    public void followFirstLink() {
        new GoogleUser().opensGoogle()
                .searches("Selenide")
                .followsResultLink(0)
                .expectsTitle("Selenide: concise UI tests in Java");
    }
}
