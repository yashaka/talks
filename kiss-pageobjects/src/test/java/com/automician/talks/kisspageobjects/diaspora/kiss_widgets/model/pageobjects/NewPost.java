package com.automician.talks.kisspageobjects.diaspora.kiss_widgets.model.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by yashaka on 1/7/17.
 */
public class NewPost {

    private final SelenideElement container = $("#publisher");
    private final SelenideElement textArea = container.find("#status_message_fake_text");

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
