package com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity.testconfigs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void driverSetUp()
    {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 2);
    }

    @AfterClass
    public static void driverTearDown()
    {
        driver.quit();
    }
}
