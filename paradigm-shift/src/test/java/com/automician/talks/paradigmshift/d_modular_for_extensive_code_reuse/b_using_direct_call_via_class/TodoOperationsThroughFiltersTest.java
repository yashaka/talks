package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.b_using_direct_call_via_class;

import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;
import com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.b_using_direct_call_via_class.pages.TodoMvc;

public class TodoOperationsThroughFiltersTest {

    @Test
    public void filtersActive() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        TodoMvc.filterActive();
        TodoMvc.assertTasksAre("a", "c");
    }

    @Test
    public void filtersCompleted() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        TodoMvc.filterCompleted();
        TodoMvc.assertTasksAre("b");
    }

    @Test
    public void filtersAll() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));
        GivenTodoMvc.atCompletedFilter();

        TodoMvc.filterAll();
        TodoMvc.assertTasksAre("a", "b", "c");
    }

    @Test
    public void tasksLifeCycleE2E() {
        GivenTodoMvc.withEmptyTasks();

        TodoMvc.add("a");

        TodoMvc.toggle("a");

        TodoMvc.filterActive();
        TodoMvc.assertTasksAreEmpty();

        TodoMvc.add("b");
        TodoMvc.assertTasksAre("b");

        TodoMvc.filterCompleted();
        TodoMvc.assertTasksAre("a");

        TodoMvc.filterActive();
        TodoMvc.assertTasksAre("b");

        TodoMvc.filterAll();
        TodoMvc.assertTasksAre("a", "b");

        TodoMvc.clearCompleted();
        TodoMvc.assertTasksAre("b");
    }
}
