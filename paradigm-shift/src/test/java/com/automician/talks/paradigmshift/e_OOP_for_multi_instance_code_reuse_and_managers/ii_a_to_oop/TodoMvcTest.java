package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_a_to_oop;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.core.SelenideDriver;
import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_a_to_oop.pages.TodoMvcPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TodoMvcTest {
    SelenideDriver driver1;
    SelenideDriver driver2;

    @Before
    public void setupDrivers() {
        driver1 = new SelenideDriver(new FirefoxDriver());
        driver2 = new SelenideDriver(new FirefoxDriver());
    }

    @After
    public void tearDownDrivers(){
        driver1.quit();
        driver2.quit();
    }

    @Test
    public void createdTaskSetsShouldBeDifferentPerBrowser(){

        TodoMvcPage todomvc1 = new TodoMvcPage(driver1);

        todomvc1.open();
        todomvc1.add("a", "b", "c");
        todomvc1.shouldHaveTasks("a", "b", "c");

        TodoMvcPage todomvc2 = new TodoMvcPage(driver2);

        todomvc2.open();
        todomvc2.add("x", "y");
        todomvc2.shouldHaveTasks("x", "y");
        driver2.quit();

        todomvc1.open();
        todomvc1.shouldHaveTasks("a", "b", "c");
    }
}
