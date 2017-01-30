package com.automician.talks.paradigmshift.helpers;

/**
 * Created by yashaka on 1/29/17.
 */
public class Task {

    private final String text;
    private final Boolean isCompleted;

    Task(String text, boolean isCompleted) {
        this.text = text;
        this.isCompleted = isCompleted;
    }

    public static Task active(String text) {
        return new Task(text, false);
    }

    public static Task completed(String text) {
        return new Task(text, true);
    }

    @Override
    public String toString() {
        return "{\\\"completed\\\":" + isCompleted + ", \\\"title\\\":\\\"" + text + "\\\"}";
    }
}
