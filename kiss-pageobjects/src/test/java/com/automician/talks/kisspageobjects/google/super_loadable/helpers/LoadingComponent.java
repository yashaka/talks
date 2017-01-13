package com.automician.talks.kisspageobjects.google.super_loadable.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

/**
 * Created by yashaka on 1/9/17.
 */
public abstract class LoadingComponent<T extends LoadingComponent<T>> {

    public abstract boolean isLoaded();

    protected WebDriver driver;
    protected WebDriverWait wait;

    public LoadingComponent(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 4);
        PageFactory.initElements(driver, this);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        wait.until(loadingFinished((T) this));
        return (T) this;
    }

    private ExpectedCondition<Boolean> loadingFinished(final LoadingComponent page) {
        return new ExpectedCondition<Boolean>() {
            @Nullable
            public Boolean apply(@Nullable WebDriver webDriver) {
                return page.isLoaded();
            }
        };
    }
}
