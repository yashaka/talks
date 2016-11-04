package com.automician.talks.paradigmshift.helpers;

import static com.codeborne.selenide.Selenide.$$;

public class CompletedTask implements Task {

    private final ActiveTask task;

    public CompletedTask(ActiveTask task) {
        this.task = task;
    }

    public CompletedTask(String text) {
        this(new ActiveTask(text));
    }

    public void add() {
        this.task.add();
        this.task.toggle();
    }

    public void toggle() {
        this.task.toggle();
    }
}
