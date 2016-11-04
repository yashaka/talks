package com.automician.talks.paradigmshift.helpers;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.*;

public class GivenTodoMvc {

    @Step
    public static void opened() {
        open("http://todomvc4tasj.herokuapp.com");
    }


    @Step
    public static void atCompletedFilter() {
        open("http://todomvc4tasj.herokuapp.com/#/completed");
    }

    @Step
    public static void withEmptyTasks() {
        if (!$$("#todo-list>li").isEmpty()) {
            executeJavaScript("localStorage.clear()");
        }
        GivenTodoMvc.opened();
    }

    @Step
    public static void withTasks(String... texts) {
        GivenTodoMvc.withEmptyTasks();
        for(String text: texts) {
            new ActiveTask(text).add();
        }
    }

    @Step
    public static void withTasks(Task... tasks) {
        GivenTodoMvc.withEmptyTasks();
        for(Task task: tasks) {
            task.add();
        }
    }
}
