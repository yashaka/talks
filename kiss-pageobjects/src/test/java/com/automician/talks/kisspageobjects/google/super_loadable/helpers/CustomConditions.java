package com.automician.talks.kisspageobjects.google.super_loadable.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Created by yashaka on 1/9/17.
 */
public class CustomConditions {

    public static ExpectedCondition<List<WebElement>> numberOf(final List<WebElement> proxyElements, final Integer number) {
        return new ExpectedCondition<List<WebElement>>() {
            private Integer currentNumber = 0;

            public List<WebElement> apply(WebDriver webDriver) {
                this.currentNumber = proxyElements.size();
                return this.currentNumber.equals(number)?proxyElements:null;
            }

            public String toString() {
                return String.format("number to be \"%s\". Current number: \"%s\"", new Object[]{number, this.currentNumber});
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> numberIsAtLeast(final List<WebElement> proxyElements, final Integer number) {
        return new ExpectedCondition<List<WebElement>>() {
            private Integer currentNumber = 0;

            public List<WebElement> apply(WebDriver webDriver) {
                this.currentNumber = proxyElements.size();
                return this.currentNumber.compareTo(number)>=0 ? proxyElements:null;
            }

            public String toString() {
                return String.format("number to be \"%s\". Current number: \"%s\"", new Object[]{number, this.currentNumber});
            }
        };
    }
}
