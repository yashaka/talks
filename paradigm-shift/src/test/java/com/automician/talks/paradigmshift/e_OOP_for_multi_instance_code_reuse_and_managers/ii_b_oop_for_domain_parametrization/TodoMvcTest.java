package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_b_oop_for_domain_parametrization;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.core.SelenideDriver;
import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_b_oop_for_domain_parametrization.pages.TodoMvcPage;
import com.automician.talks.paradigmshift.helpers.ActiveTask;
import com.automician.talks.paradigmshift.helpers.CompletedTask;
import com.automician.talks.paradigmshift.helpers.GivenTodoMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TodoMvcTest {

    @After
    public void clearData(){
        executeJavaScript("localStorage.clear()");
    }

    @Test
    public void createTasksOnActiveFilter() {

        TodoMvcPage tasksOnActive = new TodoMvcPage("active");

        tasksOnActive.open();
        tasksOnActive.add("a", "b", "c");
        tasksOnActive.shouldHaveTasks("a", "b", "c");

        // ... some more code, moving us out of "active" apge

        tasksOnActive.open();
        tasksOnActive.shouldHaveTasks("a", "b", "c");

        /* ?~

        TodoMvc.open("active");
        TodoMvc.add("a", "b", "c");
        TodoMvc.shouldHaveTasks("a", "b", "c");

        // ... some more code, moving us out of "active" apge

        TodoMvc.open("active");
        TodoMvc.shouldHaveTasks("a", "b");
        */
    }

    @Test
    public void filtersCompletedFromActive() {
        GivenTodoMvc.withTasks(
                new ActiveTask("a"),
                new CompletedTask("b"),
                new ActiveTask("c"));

        TodoMvcPage activeFilter = new TodoMvcPage("active");

        activeFilter.open();
        activeFilter.shouldHaveTasks("a", "c");

        TodoMvcPage completedFilter = activeFilter.filterCompleted();
        completedFilter.shouldHaveTasks("b");
    }
}
