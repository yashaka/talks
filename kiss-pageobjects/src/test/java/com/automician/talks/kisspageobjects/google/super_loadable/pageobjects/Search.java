package com.automician.talks.kisspageobjects.google.super_loadable.pageobjects;

import com.automician.talks.kisspageobjects.google.super_loadable.helpers.LoadingComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by yashaka on 1/9/17.
 */
public class Search extends LoadingComponent<Search> {

    @FindBy(name = "q")
    private WebElement element;

    public Search(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return element.isDisplayed();
    }

    public Search query(String text) {
        this.element.clear();
        this.element.sendKeys(text + Keys.ENTER);
        return this;
    }
}
