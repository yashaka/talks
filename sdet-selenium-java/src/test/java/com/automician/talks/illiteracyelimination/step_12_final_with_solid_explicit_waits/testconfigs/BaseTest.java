package com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits.testconfigs;

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
