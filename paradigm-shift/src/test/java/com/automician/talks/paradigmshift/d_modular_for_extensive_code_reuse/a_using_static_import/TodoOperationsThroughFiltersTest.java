package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.a_using_static_import;

import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;

import static com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.a_using_static_import.pages.TodoMvc.*;

public class TodoOperationsThroughFiltersTest {

    @Test
    public void filtersActive() {
        GivenTodoMvc.withTasks(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        filterActive();
        assertTasksAre("a", "c");
    }

    @Test
    public void filtersCompleted() {
        GivenTodoMvc.withTasks(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        filterCompleted();
        assertTasksAre("b");
    }

    @Test
    public void filtersAll() {
        GivenTodoMvc.withTasks(
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
}
