package com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity.pages;

import com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity.sel.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity.sel.CustomConditions.listNthElementHasText;
import static com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity.sel.CustomConditions.listNthElementInnerElementLocatedIsVisible;
import static com.automician.talks.sdetseleniumjava.step00_initial_with_page_objects_with_steps_to_hide_complexity.sel.CustomConditions.visible;

public class GooglePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public GooglePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Configuration.timeout);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    private WebElement search;

    @FindBy(css = ".srg>.g")
    private List<WebElement> results;

    public void open() {
        driver.get("http://google.com/ncr");
    }

    public void search(String text) {
        wait.until(visible(this.search)).sendKeys(text + Keys.ENTER);
    }

    public void followLink(int index) {
        wait.until(listNthElementInnerElementLocatedIsVisible(
                this.results,
                index,
                By.cssSelector("h3>a"))).click();
    }

    public void assertNthResultHasText(int index, String text) {
        wait.until(listNthElementHasText(
                this.results,
                index,
                text));
    }
}
