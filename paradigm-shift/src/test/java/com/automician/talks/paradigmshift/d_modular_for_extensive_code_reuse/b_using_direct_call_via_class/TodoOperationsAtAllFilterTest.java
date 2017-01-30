package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.b_using_direct_call_via_class;

import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;

import com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.b_using_direct_call_via_class.pages.TodoMvc;

public class TodoOperationsAtAllFilterTest {

    @Test
    public void createsTasks() {
        GivenTodoMvc.withEmptyTasks();

        TodoMvc.add("a", "b", "c");
        TodoMvc.assertTasksAre("a", "b", "c");
    }

    @Test
    public void countsTasksOnAddNew() {
        GivenTodoMvc.withTasks("a");
        TodoMvc.assertItemsLeft(1);

        TodoMvc.add("b");
        TodoMvc.assertItemsLeft(2);
    }

    @Test
    public void completesTask() {
        GivenTodoMvc.withTasks("a", "b", "c");
        TodoMvc.assertClearCompletedIsHidden();
        TodoMvc.toggle("b");

        TodoMvc.assertTasksAre("a", "b", "c");
        TodoMvc.assertItemsLeft(2);
        TodoMvc.assertClearCompletedIsVisible();
    }

    @Test
    public void clearsCompletedTasks() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"),
                new CompletedTask("d"));

        TodoMvc.clearCompleted();
        TodoMvc.assertTasksAre("a", "c");
        TodoMvc.assertItemsLeft(2);
    }
}
