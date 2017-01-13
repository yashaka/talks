package com.automician.talks.kisspageobjects.google.classic.testconfigs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by yashaka on 1/9/17.
 */
public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setupDriver() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 4);
    }

    @AfterClass
    public static void teardownDriver() {
        driver.quit();
    }
}
