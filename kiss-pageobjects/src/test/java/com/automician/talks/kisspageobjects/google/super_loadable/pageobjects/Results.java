package com.automician.talks.kisspageobjects.google.super_loadable.pageobjects;

import com.automician.talks.kisspageobjects.google.super_loadable.helpers.LoadingComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by yashaka on 1/9/17.
 */
public class Results extends LoadingComponent<Results> {

    @FindBy(css = ".srg>.g")
    private List<WebElement> elements;

    public Results(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return this.elements.size() == 10;
    }

    public void followResultLink(int index){
        this.elements.get(index).findElement(By.cssSelector(".r>a")).click();
    }

    public int size() {
        return this.elements.size();
    }

    public WebElement get(int index) {
        return this.elements.get(index);
    }
}
