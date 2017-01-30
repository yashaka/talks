package com.automician.talks.paradigmshift.a_imperative_for_simplicity;

import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TodoMvcTest {

    /***********************************
     * Atomic tests for solid coverage *
     ***********************************/

    @Test
    public void createsTasks() {
        GivenTodoMvc.withEmptyTasks();    /* << fast precondition helpers via API or fixtures*/

        $("#new-todo").setValue("a").pressEnter();
        $("#new-todo").setValue("b").pressEnter();
        $("#new-todo").setValue("c").pressEnter();

        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));
    }

    @Test
    public void hidesCounterOnNoTasks() {
        GivenTodoMvc.withEmptyTasks();
        $("#todo-count>strong").shouldBe(hidden);
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


    /*********************************************
     * 1 End to End  test with simplified checks *
     *********************************************/

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

    /*
     * * duplicated locators not a problem
     *   * because Find&Replace works great, especially per one src file
     * * atomic tests fully readable for developers
     *   * especially taking into account
     *     that developers know internal structure of their application
     *
     * + KISS
     * + fast in implementation
     * + fast enough in support for developers
     * - even atomic tests not so readable for non-developers,
     *   and new-comers with lack of automation experience
     *   - and so slow in support for them
     * - long e2e tests become harder readable for developers too
     * - not so handy in "typing" because of no autocompletion for strings/locators
     *   + but Copy&Paste Development solves "all problems" ;)
     *
     * + developers can make tests more detailed
     *   though more detailed tests will run longer,
     *   but devs it's easier for devs to optimise tests run time in other ways
     *   (automation engineers usually will concentrate only on functional high priority cases in selenium tests)
     */
}
