package com.automician.talks.kisspageobjects.google.super_loadable.helpers;

import org.openqa.selenium.WebDriver;

/**
 * Created by yashaka on 1/9/17.
 */
public abstract class WaitingLoadableComponent<T extends WaitingLoadableComponent<T>> extends LoadingComponent {

    public WaitingLoadableComponent(WebDriver driver){
        super(driver);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        try {
            if (isLoaded()) {
                return (T) this;
            }
        } catch (Exception e) {
            load();
        }
        return (T) super.get();
    }

    protected abstract void load();
    public abstract boolean isLoaded();
}

