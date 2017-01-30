package com.automician.talks.paradigmshift.helpers;

import com.automician.talks.core.conditions.Have;
import com.codeborne.selenide.Selenide;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Arrays;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by yashaka on 1/29/17.
 */
public class GivenTodoMvc {

//    private static String appURL = "https://todomvc4tasj.herokuapp.com/";
    private static String appURL = "file:///Users/ayia/Dropbox/Apps/Heroku/todomvcdemo/home.html";

    @Step
    public static void opened() {
        if (!url().equals(appURL)) {
            Selenide.open(appURL);
            String jQueryLoaded = "return typeof($) !== 'undefined' && $.active == 0";
            Selenide.Wait().until(Have.jsReturnedTrue(jQueryLoaded));
        }
    }

    @Step
    public static void with(Task... tasks) {
        GivenTodoMvc.opened();
        Selenide.executeJavaScript(String.format("localStorage.setItem('todos-troopjs', '%s')", Arrays.toString(tasks)));
        Selenide.refresh();
    }

    @Step
    public static void withEmptyTasks() {
        GivenTodoMvc.with();
    }

    @Step
    public static void withTasks(String... tasks) {
        GivenTodoMvc.with(theTasks(false, tasks));
    }

    @Step
    public static void withCompletedTasks(String... tasks) {
        GivenTodoMvc.with(theTasks(true, tasks));
    }

    @Step
    public static void atActiveFilter() {
        Selenide.open(appURL + "#/active");
    }

    @Step
    public static void atCompletedFilter() {
        Selenide.open(appURL + "#/completed");
    }

    static Task[] theTasks(boolean isCompleted, String... taskTexts) {
        Task[] tasks = new Task[taskTexts.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(taskTexts[i], isCompleted);
        }
        return tasks;
    }
}
