package com.automician.talks.paradigmshift.c_procedural_to_hide_complexity_when_scared_but_needed.ii_hide_structural_complexity;

import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodoMvcTest {

    /*
     * Now we can use Structural Paradigm, hiding its complexity behind procedures!
     */

    @Test
    public void createsTasks() {
        GivenTodoMvc.withEmptyTasks();

        add("a", "b", "c");

        /* ~
        add("a");
        add("b");
        add("c");

         * >
        for(String text: asList("a", "b", "c")){
            add(text);
        }
         */

        assertTasksAre("a", "b", "c");
    }

    @Test
    public void countsTasksOnAddNew() {
        GivenTodoMvc.withTasks("a");
        assertItemsLeft(1);

        add("b");
        assertItemsLeft(2);
    }

    @Test
    public void completesTask() {
        GivenTodoMvc.withTasks("a", "b", "c");
        assertClearCompletedIsHidden();
        toggle("b");

        assertTasksAre("a", "b", "c");
        assertItemsLeft(2);
        assertClearCompletedIsVisible();
    }

    @Test
    public void clearsCompletedTasks() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"),
                new CompletedTask("d"));

        clearCompleted();
        assertTasksAre("a", "c");
        assertItemsLeft(2);
    }

    @Test
    public void filtersActive() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        filterActive();
        assertTasksAre("a", "c");
    }

    @Test
    public void filtersCompleted() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        filterCompleted();
        assertTasksAre("b");
    }

    @Test
    public void filtersAll() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));
        GivenTodoMvc.atCompletedFilter();

        filterAll();
        assertTasksAre("a", "b", "c");
    }

    @Test
    public void tasksLifeCycleE2E() {
        GivenTodoMvc.withEmptyTasks();

        /* >
        if(!tasks.isEmpty()) {
            toggleAll();
            clearCompleted();
        }
         */

        add("a");

        toggle("a");

        filterActive();
        assertTasksAreEmpty();

        add("b");
        assertTasksAre("b");

        filterCompleted();
        assertTasksAre("a");

        filterActive();
        assertTasksAre("b");

        filterAll();
        assertTasksAre("a", "b");

        clearCompleted();
        assertTasksAre("b");
    }

    /*********
     * Steps *
     *********/
    private ElementsCollection tasks = $$("#todo-list>li");
    private SelenideElement clearCompletedButton = $("#clear-completed");

    @Step
    private void add(String... taskTexts) {
        for(String text: taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    /* >
    private void add(String... taskTexts) {
        try {
            for (String text : taskTexts) {
                $("#new-todo").setValue(text).pressEnter();
            }
        } catch (Exception e) {
            throw new Error(
                    "failed on step: add("+ "" + ")",
                    e);
        }
    }
    */

    @Step
    private void toggle(String taskText) {
        tasks.findBy(exactText(taskText)).find(".toggle").click();
    }

    @Step
    private void clearCompleted() {
        clearCompletedButton.click();
        assertClearCompletedIsHidden();
    }

    @Step
    private void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    private void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    private void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    private void assertTasksAre(String... texts) {
        tasks.filterBy(visible).shouldHave(exactTexts(texts));
    }

    @Step
    private void assertTasksAreEmpty() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    @Step
    private void assertItemsLeft(int number) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(number)));
    }

    @Step
    private void assertClearCompletedIsVisible() {
        clearCompletedButton.shouldBe(visible);
    }

    @Step
    private void assertClearCompletedIsHidden() {
        clearCompletedButton.shouldBe(hidden);
    }
}
