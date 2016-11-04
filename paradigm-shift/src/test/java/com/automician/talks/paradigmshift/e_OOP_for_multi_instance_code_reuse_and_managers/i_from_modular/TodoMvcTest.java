package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.i_from_modular;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.core.SelenideDriver;
import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.i_from_modular.pages.TodoMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TodoMvcTest {

    /*
     * - Modular approach is not so cute but bulky
     *   when you have a state to be used in all steps of your pages...
     */

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

        TodoMvc.open(driver1);
        TodoMvc.add(driver1, "a", "b", "c");
        TodoMvc.shouldHaveTasks(driver1, "a", "b", "c");

        TodoMvc.open(driver2);
        TodoMvc.add(driver2, "x", "y");
        TodoMvc.shouldHaveTasks(driver2, "x", "y");
        driver2.quit();

        TodoMvc.open(driver1);
        TodoMvc.shouldHaveTasks(driver1, "a", "b", "c");
    }
}
