package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.a_using_static_import;

import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;

import static com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.a_using_static_import.pages.TodoMvc.*;

public class TodoOperationsAtAllFilterTest {

    @Test
    public void createsTasks() {
        GivenTodoMvc.withEmptyTasks();

        add("a", "b", "c");
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
}
