package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.i_from_modular.pages;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.core.SelenideDriver;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.exactTexts;

public class TodoMvc {

    private static ElementsCollection tasks(SelenideDriver driver){
        return driver.findAll("#todo-list>li");
    }

    public static void open(SelenideDriver driver){
        driver.get("http://todomvc4tasj.herokuapp.com");
    }

    public static void add(SelenideDriver driver, String... texts){
        for(String text: texts) {
            driver.find("#new-todo").setValue(text).pressEnter();
        }
    }

    public static void shouldHaveTasks(SelenideDriver driver, String... texts){
        tasks(driver).shouldHave(exactTexts(texts));
    }
}
