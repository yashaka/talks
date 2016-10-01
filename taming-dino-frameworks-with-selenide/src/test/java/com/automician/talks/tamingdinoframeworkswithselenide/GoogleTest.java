package com.automician.talks.tamingdinoframeworkswithselenide;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTest {

    WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void search() {

        driver.get("http://google.com/ncr");
        driver.findElement(By.name("q")).sendKeys("Selenide" + Keys.ENTER);

        new GooglePage(driver).firstResult
                .findElement(By.cssSelector("h3>a")).click();

        new WebDriverWait(driver, 4).until(
                ExpectedConditions.urlContains("selenide.org")
        );
    }

    public static class GooglePage {

        @FindBy(css = ".srg .g:nth-of-type(1)")
        WebElement firstResult;

        public GooglePage(WebDriver driver) {
            /*
             * wrapping driver to SelenideDriver enables
             * Selenide's implicit waits for WebElement
             * (which are more powerful than selenium ones)
             * SelenideDriver can be found at https://gist.github.com/yashaka/dc7607239518bd37298ef5eb5b08da9b
             */
            PageFactory.initElements(new SelenideDriver(driver), this);

            /* >
            PageFactory.initElements(driver, this);
             * makes test to fail with
             *   org.openqa.selenium.NoSuchElementException: Unable to locate element: {"method":"css selector","selector":".srg .g:nth-of-type(1)"}
             */
        }
    }
}
