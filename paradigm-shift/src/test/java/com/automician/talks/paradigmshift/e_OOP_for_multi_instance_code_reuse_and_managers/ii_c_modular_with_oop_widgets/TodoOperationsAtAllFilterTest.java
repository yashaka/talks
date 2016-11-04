package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_c_modular_with_oop_widgets;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_c_modular_with_oop_widgets.pages.Tasks;
import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;

public class TodoOperationsAtAllFilterTest {

    @Test
    public void createsTasks() {
        GivenTodoMvc.withEmptyTasks();

        Tasks.add("a", "b", "c");
        Tasks.shouldBe("a", "b", "c");
    }

    @Test
    public void countsTasksOnAddNew() {
        GivenTodoMvc.withTasks("a");
        Tasks.shouldHaveItemsLeft(1);

        Tasks.add("b");
        Tasks.shouldHaveItemsLeft(2);
    }

    @Test
    public void completesTask() {
        GivenTodoMvc.withTasks("a", "b", "c");
        Tasks.shouldHideClearCompleted();
        Tasks.toggle("b");

        Tasks.shouldBe("a", "b", "c");
        Tasks.shouldHaveItemsLeft(2);
        Tasks.shouldShowClearCompleted();
    }

    @Test
    public void clearsCompletedTasks() {
        GivenTodoMvc.withTasks(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"),
                new CompletedTask("d"));

        Tasks.clearCompleted();
        Tasks.shouldBe("a", "c");
        Tasks.shouldHaveItemsLeft(2);
    }
}
