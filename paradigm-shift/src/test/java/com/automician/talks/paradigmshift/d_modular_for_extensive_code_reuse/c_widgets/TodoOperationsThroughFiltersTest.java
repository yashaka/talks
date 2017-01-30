package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.c_widgets;

import com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.c_widgets.widgets.Footer;
import com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.c_widgets.widgets.Tasks;
import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.Test;

public class TodoOperationsThroughFiltersTest {

    @Test
    public void filtersActive() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        Footer.filterActive();
        Tasks.shouldBe("a", "c");
    }

    @Test
    public void filtersCompleted() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        Footer.filterCompleted();
        Tasks.shouldBe("b");
    }

    @Test
    public void filtersAll() {
        GivenTodoMvc.with(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));
        GivenTodoMvc.atCompletedFilter();

        Footer.filterAll();
        Tasks.shouldBe("a", "b", "c");
    }

    @Test
    public void tasksLifeCycleE2E() {
        GivenTodoMvc.withEmptyTasks();

        Tasks.add("a");

        Tasks.toggle("a");

        Footer.filterActive();
        Tasks.shouldBeEmpty();

        Tasks.add("b");
        Tasks.shouldBe("b");

        Footer.filterCompleted();
        Tasks.shouldBe("a");

        Footer.filterActive();
        Tasks.shouldBe("b");

        Footer.filterAll();
        Tasks.shouldBe("a", "b");

        Footer.clearCompleted();
        Tasks.shouldBe("b");
    }
}
