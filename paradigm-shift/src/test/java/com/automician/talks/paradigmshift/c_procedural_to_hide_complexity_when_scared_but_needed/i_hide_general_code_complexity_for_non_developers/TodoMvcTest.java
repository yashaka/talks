package com.automician.talks.paradigmshift.c_procedural_to_hide_complexity_when_scared_but_needed.i_hide_general_code_complexity_for_non_developers;

import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodoMvcTest {

    /*
     * + first of all - for hiding complexity
     *   * not for DRYness
     *     - needs additional at first sight (for developer) "redundant" implementation
     *
     * + good for E2E style tests - makes them much more readable
     *
     * + "as a bonus" makes code DRYer
     *   + easier to refactor duplicated code especially for non-developers
     *     especially for "duplicated over different source files"
     * + enables autocompletion in all areas of usage
     * + handy and easy for non-experienced new-comers
     *
     * + handy for "hiding" structural complexity" (see next package for examples)
     *
     * - "unstructured" procedures, meaning:
     *   no ability to structure them by context
     *   which makes hard to use them
     *   if you have more than one context (e.g. page) in a Test Class
     *
     * - not handy for reuse in other test classes
     *   (if you have to break tests for one page
     *   into different test classes)
     */

    @Test
    public void createsTasks() {
        GivenTodoMvc.withEmptyTasks();

        add("a");
        add("b");
        add("c");

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

        /* * though did not give additional DRYness
         * + more consistent with all other code of "even non-developer can understand" style
         *
         * >
        clearCompletedButton.shouldBe(hidden);
         */

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

    private void add(String text) {
        $("#new-todo").setValue(text).pressEnter();
    }

    private void toggle(String taskText) {
        tasks.findBy(exactText(taskText)).find(".toggle").click();
    }

    private SelenideElement clearCompletedButton = $("#clear-completed");

    private void clearCompleted() {
        clearCompletedButton.click();
    }

    private void filterAll() {
        $(By.linkText("All")).click();
    }

    private void filterActive() {
        $(By.linkText("Active")).click();
    }

    private void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    private void assertTasksAre(String... texts) {
        tasks.filterBy(visible).shouldHave(exactTexts(texts));
    }

    private void assertTasksAreEmpty() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    private void assertItemsLeft(int number) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(number)));
    }

    private void assertClearCompletedIsVisible() {
        clearCompletedButton.shouldBe(visible);
    }

    private void assertClearCompletedIsHidden() {
        clearCompletedButton.shouldBe(hidden);
    }
}
