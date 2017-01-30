package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.c_widgets;

import com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.c_widgets.widgets.Footer;
import com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.c_widgets.widgets.Tasks;
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
        Footer.shouldHaveItemsLeft(1);

        Tasks.add("b");
        Footer.shouldHaveItemsLeft(2);
    }

    @Test
    public void completesTask() {
        GivenTodoMvc.withTasks("a", "b", "c");
        Footer.shouldHideClearCompleted();
        Tasks.toggle("b");

        Tasks.shouldBe("a", "b", "c");
        Footer.shouldHaveItemsLeft(2);
        Footer.shouldShowClearCompleted();
    }

    @Test
    public void clearsCompletedTasks() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"),
                new CompletedTask("d"));

        Footer.clearCompleted();
        Tasks.shouldBe("a", "c");
        Footer.shouldHaveItemsLeft(2);
    }
}
