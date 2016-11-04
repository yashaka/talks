package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.c_widgets.widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Tasks {

    private static ElementsCollection list = $$("#todo-list>li");

    @Step
    public static void add(String... taskTexts) {
        for(String text: taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public static SelenideElement task(String text){
        return list.findBy(exactText(text));
    }

    @Step
    public static void toggle(String taskText) {
        task(taskText).find(".toggle").click();
    }

    @Step
    public static void shouldBe(String... texts) {
        list.filterBy(visible).shouldHave(exactTexts(texts));
    }

    @Step
    public static void shouldBeEmpty() {
        list.filterBy(visible).shouldBe(empty);
    }

}
