package com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.model.pageobjects;

import com.automician.talks.kisspageobjects.diaspora.kiss_widgets_with_driver.core.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by yashaka on 1/7/17.
 */
public class NewPost {

    private final SelenideDriver driver;
    private final SelenideElement container;
    private final SelenideElement textArea;

    public NewPost(SelenideDriver driver) {
        this.driver = driver;
        this.container = this.driver.element("#publisher");
        this.textArea = this.container.find("#status_message_fake_text");
    }

    public NewPost start() {
        this.textArea.click();
        return this;
    }
    public NewPost write(String text) {
        this.textArea.setValue(text);
        return this;
    }

    public void share(){
        this.container.find("#submit").click();
    }
}
