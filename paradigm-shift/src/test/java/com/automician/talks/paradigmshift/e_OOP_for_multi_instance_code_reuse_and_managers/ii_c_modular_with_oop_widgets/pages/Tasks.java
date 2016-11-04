package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_c_modular_with_oop_widgets.pages;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_c_modular_with_oop_widgets.widgets.Task;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Tasks {

    private static ElementsCollection list = $$("#todo-list>li");
    private static SelenideElement clearCompletedButton = $("#clear-completed");

    @Step
    public static void add(String... taskTexts) {
        for(String text: taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public static Task task(String text){
        return new Task(list.findBy(exactText(text)));
    }

    /* ?~
    @Step
    public static SelenideElement task(String text){
        return list.findBy(exactText(text));
    }
     */

    @Step
    public static void toggle(String taskText) {
        task(taskText).toggle();
    }

    /* ?~
    @Step
    public static void toggle(String taskText) {
        task(taskText).find(".toggle").click();
    }
     */

    @Step
    public static void shouldBe(String... texts) {
        list.filterBy(visible).shouldHave(exactTexts(texts));
    }

    @Step
    public static void shouldBeEmpty() {
        list.filterBy(visible).shouldBe(empty);
    }

    @Step
    public static void clearCompleted() {
        clearCompletedButton.click();
    }

    @Step
    public static void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public static void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public static void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    public static void shouldHaveItemsLeft(int number) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(number)));
    }

    @Step
    public static void shouldShowClearCompleted() {
        clearCompletedButton.shouldBe(visible);
    }

    @Step
    public static void shouldHideClearCompleted() {
        clearCompletedButton.shouldBe(hidden);
    }
}
