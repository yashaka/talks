package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_d_oop_with_widgets;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_d_oop_with_widgets.pages.TasksPage;
import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;

public class TodoOperationsAtAllFilterTest {
    
    TasksPage tasks = new TasksPage();

    @Test
    public void createsTasks() {
        GivenTodoMvc.withEmptyTasks();

        tasks.add("a", "b", "c");
        tasks.shouldBe("a", "b", "c");
    }

    @Test
    public void countsTasksOnAddNew() {
        GivenTodoMvc.withTasks("a");
        tasks.shouldHaveItemsLeft(1);

        tasks.add("b");
        tasks.shouldHaveItemsLeft(2);
    }

    @Test
    public void completesTask() {
        GivenTodoMvc.withTasks("a", "b", "c");
        tasks.shouldHideClearCompleted();
        tasks.toggle("b");

        tasks.shouldBe("a", "b", "c");
        tasks.shouldHaveItemsLeft(2);
        tasks.shouldShowClearCompleted();
    }

    @Test
    public void clearsCompletedTasks() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"),
                new CompletedTask("d"));

        tasks.clearCompleted();
        tasks.shouldBe("a", "c");
        tasks.shouldHaveItemsLeft(2);
    }
}
