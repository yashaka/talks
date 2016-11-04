package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_a_to_oop.pages;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.core.SelenideDriver;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.exactTexts;

public class TodoMvcPage {

    private final SelenideDriver driver;

    private ElementsCollection tasks;

    public TodoMvcPage(SelenideDriver driver){
        this.driver = driver;

        this.tasks = this.driver.findAll("#todo-list>li");
    }


    public void open(){
        this.driver.get("http://todomvc4tasj.herokuapp.com");
    }

    public void add(String... texts){
        for(String text: texts) {
            this.driver.find("#new-todo").setValue(text).pressEnter();
        }
    }

    public void shouldHaveTasks(String... texts){
        this.tasks.shouldHave(exactTexts(texts));
    }
}
