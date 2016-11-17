package com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class CustomConditions {

    public static ExpectedCondition<Boolean> listNthElementHasText(final List<WebElement> elements, final int index, final String expectedText) {
        return new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver webDriver) {
                Boolean result;
                try {
                    WebElement element = elements.get(index);
                    result = element.getText().contains(expectedText);
                } catch (Exception e) {
                    result = false;
                }
                return result;
            }
        };
    }

    public static ExpectedCondition<Boolean> minimumSizeOf(final List<WebElement> elements, final int expectedSize) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return elements.size() >= expectedSize;
            }
        };
    }

    public static ExpectedCondition<WebElement> visible(final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return element.isDisplayed() ? element : null;
            }
        };
    }

    public static ExpectedCondition<WebElement> listNthElementInnerElementLocatedIsVisible(final List<WebElement> elements, final int index, final By innerLocator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                try {
                    WebElement element = elements.get(index).findElement(innerLocator);
                    return element.isDisplayed() ? element : null;
                } catch (Exception e) {
                    return null;
                }
            }
        };
    }
}
