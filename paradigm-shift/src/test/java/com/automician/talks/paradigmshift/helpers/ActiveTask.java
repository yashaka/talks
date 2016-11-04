package com.automician.talks.paradigmshift.helpers;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ActiveTask implements Task {

    private final String text;

    public ActiveTask(String text) {
        this.text = text;
    }

    public void add() {
        $("#new-todo").setValue(this.text).pressEnter();
    }

    public void toggle() {
        $$("#todo-list>li").findBy(exactText(this.text)).find(".toggle").click();
    }
}
