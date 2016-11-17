package com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits;

import com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits.pages.GooglePage;
import com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits.testconfigs.BaseTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits.core.CustomConditions.listNthElementHasText;
import static com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits.core.CustomConditions.listNthElementInnerElementLocatedIsVisible;
import static com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits.core.CustomConditions.visible;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class GoogleTest extends BaseTest {
    static GooglePage google;

    @BeforeClass
    public static void init() {
        google = new GooglePage(driver);
    }

    @Test
    public void search() {
        driver.get("http://google.com/ncr");

        wait.until(visible(google.search)).sendKeys("Selenide" + Keys.ENTER);
        wait.until(listNthElementHasText(
                google.results,
                0,
                "Selenide: concise UI tests in Java"));
    }

    @Test
    public void followFirstLink() {
        driver.get("http://google.com/ncr");
        wait.until(visible(google.search)).sendKeys("Selenide" + Keys.ENTER);


        wait.until(listNthElementInnerElementLocatedIsVisible(
                google.results,
                0,
                By.cssSelector("h3>a"))).click();

        wait.until(urlToBe("http://selenide.org/"));
    }
}


