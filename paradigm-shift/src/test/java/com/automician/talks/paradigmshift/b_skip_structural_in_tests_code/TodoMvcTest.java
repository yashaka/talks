package com.automician.talks.paradigmshift.b_skip_structural_in_tests_code;

import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static junit.framework.TestCase.fail;

public class TodoMvcTest {

    /*
     * Control structures are too complicated to be used in tests code!
     * Test code HAVE TO be simple in order to:
     * - be obvious to be fast in support
     * Because:
     * - there are too many tests
     * - they are written by too many guys
     * - they should be investigated/changed/updated rather often
     * - and you have not time to read them too long and try to understand
     *
     * Readability is one of the most valuable things for tests code.
     * Maybe even the most.
     */

    @Test
    public void createsTasks() {
        GivenTodoMvc.withEmptyTasks();

        /* >
        if(!$$("#todo-list>li").isEmpty()) {
            $("#toggle-all").click();
            $("#clear-completed").click();
        }
         */

        $("#new-todo").setValue("a").pressEnter();
        $("#new-todo").setValue("b").pressEnter();
        $("#new-todo").setValue("c").pressEnter();

        /* >
        for(String text: asList("a", "b", "c")){
            $("#new-todo").setValue(text).pressEnter();
        }
         */

        /* >>
        try {
            $("#new-todo").setValue("a").pressEnter();
            $("#new-todo").setValue("b").pressEnter();
            $("#new-todo").setValue("c").pressEnter();
        } catch (Exception e) {
            fail(String.format(
                    "message: %s" +
                    "locator: %s" +
                    "screenshot: %s"
                    ,
                    "failed to add three tasks a, b, c",
                    "#new-todo",
                    "file:///..."));
        }
         */

        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));
    }

    @Test
    public void countsTasksOnAddNew() {
        GivenTodoMvc.withTasks("a");
        $("#todo-count>strong").shouldHave(exactText("1"));

        $("#new-todo").setValue("b").pressEnter();
        $("#todo-count>strong").shouldHave(exactText("2"));
    }

    @Test
    public void completesTask() {
        GivenTodoMvc.withTasks("a", "b", "c");
        $("#clear-completed").shouldBe(hidden);

        $$("#todo-list>li").findBy(exactText("b")).find(".toggle").click();

        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));
        $("#todo-count>strong").shouldHave(exactText("2"));
        $("#clear-completed").shouldBe(visible);
    }

    @Test
    public void clearsCompletedTasks() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"),
                new CompletedTask("d"));

        $("#clear-completed").click();
        $$("#todo-list>li").shouldHave(exactTexts("a", "c"));
        $("#todo-count>strong").shouldHave(exactText("2"));
    }

    @Test
    public void hidesCounterOnNoTasks() {
        GivenTodoMvc.withEmptyTasks();
        $("#todo-count>strong").shouldBe(hidden);
    }

    @Test
    public void filtersActive() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        $(By.linkText("Active")).click();
        $$("#todo-list>li").filterBy(visible).shouldHave(exactTexts("a", "c"));
    }

    @Test
    public void filtersCompleted() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        $(By.linkText("Completed")).click();
        $$("#todo-list>li").filterBy(visible).shouldHave(exactTexts("b"));
    }

    @Test
    public void filtersAll() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));
        GivenTodoMvc.atCompletedFilter();

        $(By.linkText("All")).click();
        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));
    }

    @Test
    public void tasksLifeCycleE2E() {
        GivenTodoMvc.withEmptyTasks();

        $("#new-todo").setValue("a").pressEnter();

        $$("#todo-list>li").findBy(exactText("a")).find(".toggle").click();

        $(By.linkText("Active")).click();
        $$("#todo-list>li").filterBy(visible).shouldBe(empty);

        $("#new-todo").setValue("b").pressEnter();
        $$("#todo-list>li").filterBy(visible).shouldHave(exactTexts("b"));

        $(By.linkText("Completed")).click();
        $$("#todo-list>li").filterBy(visible).shouldHave(exactTexts("a"));

        $(By.linkText("Active")).click();
        $$("#todo-list>li").filterBy(visible).shouldHave(exactTexts("b"));

        $(By.linkText("All")).click();
        $$("#todo-list>li").shouldHave(exactTexts("a", "b"));

        $("#clear-completed").click();
        $$("#todo-list>li").shouldHave(exactTexts("b"));
    }
}
