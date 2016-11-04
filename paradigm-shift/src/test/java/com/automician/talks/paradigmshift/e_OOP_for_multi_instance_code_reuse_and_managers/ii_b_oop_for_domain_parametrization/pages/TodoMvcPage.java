package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_b_oop_for_domain_parametrization.pages;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.core.SelenideDriver;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodoMvcPage {

    private final String filter;
    private ElementsCollection tasks = $$("#todo-list>li");

    public TodoMvcPage(String filter){
        this.filter = filter;
    }
    /* this is simplified implementation
     * - using string instead of enum, etc.
     * - without counting the "all" case, which will have different behaviour for open();
     */

    public void open(){
        Selenide.open("http://todomvc4tasj.herokuapp.com/#/" + this.filter);
    }

    public void add(String... texts){
        for(String text: texts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    public void shouldHaveTasks(String... texts){
        this.tasks.shouldHave(exactTexts(texts));
    }

    public TodoMvcPage filterCompleted() {
        $(By.linkText("Completed")).click();
        return new TodoMvcPage("completed");
    }
}
