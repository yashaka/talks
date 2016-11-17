package com.automician.talks.illiteracyelimination.step_12_final_with_solid_explicit_waits.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GooglePage {

    @FindBy(name = "q")
    public WebElement search;

    @FindBy(css = ".srg>.g")
    public List<WebElement> results;

    public GooglePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
