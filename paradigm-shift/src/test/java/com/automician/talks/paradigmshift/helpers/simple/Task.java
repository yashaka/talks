package com.automician.talks.paradigmshift.helpers.simple;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Task {

    private final String text;
    private final Status status;

    Task(String text, Status status) {
        this.text = text;
        this.status = status;
    }

    public void add() {
        $("#new-todo").setValue(this.text).pressEnter();
        if (this.status == Status.COMPLETED) {
            $$("#todo-list>li").findBy(exactText(this.text)).find(".toggle").click();
        }
    }

    public static Task active(String text) {
        return new Task(text, Status.ACTIVE);
    }

    public static Task completed(String text) {
        return new Task(text, Status.COMPLETED);
    }
}
