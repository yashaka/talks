package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.core;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.BySelectorCollection;
import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class SelenideDriver implements WebDriver {

    private final WebDriver driver;

    public SelenideDriver(WebDriver driver) {
        this.driver = driver;
    }

    public SelenideElement find(By seleniumLocator) {
        return ElementFinder.wrap(this.driver, seleniumLocator, 0);
    }

    public SelenideElement find(String cssSelector) {
        return find(By.cssSelector(cssSelector));
    }

    public ElementsCollection findAll(By seleniumLocator) {
        return new ElementsCollection(new BySelectorCollection(this.driver, seleniumLocator));
    }

    public ElementsCollection findAll(String cssSelector) {
        return findAll(By.cssSelector(cssSelector));
    }

    public void get(String s) {
        this.driver.get(s);
    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public String getTitle() {
        return this.driver.getTitle();
    }

    /*
      impl. should return findAll(by)
      but currently ElementsCollection impl. does not support this...
     */
    public List<WebElement> findElements(By by) {
        return this.driver.findElements(by);
    }

    public WebElement findElement(By by) {
        return find(by);
    }

    public String getPageSource() {
        return this.driver.getPageSource();
    }

    public void close() {
        this.driver.close();
    }

    public void quit() {
        this.driver.quit();
    }

    public Set<String> getWindowHandles() {
        return this.driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return this.driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return this.driver.switchTo();
    }

    public Navigation navigate() {
        return this.driver.navigate();
    }

    public Options manage() {
        return this.driver.manage();
    }
}
