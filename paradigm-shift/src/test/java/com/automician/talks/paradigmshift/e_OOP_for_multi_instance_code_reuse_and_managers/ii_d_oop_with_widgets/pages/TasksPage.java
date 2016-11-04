package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_d_oop_with_widgets.pages;

import com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_d_oop_with_widgets.widgets.Task;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TasksPage {

    private ElementsCollection list = $$("#todo-list>li");
    private SelenideElement clearCompletedButton = $("#clear-completed");

    @Step
    public void add(String... taskTexts) {
        for(String text: taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public Task task(String text){
        return new Task(list.findBy(exactText(text)));
    }

    /* ?~
    @Step
    public SelenideElement task(String text){
        return list.findBy(exactText(text));
    }
     */

    @Step
    public void toggle(String taskText) {
        task(taskText).toggle();
    }

    /* ?~
    @Step
    public void toggle(String taskText) {
        task(taskText).find(".toggle").click();
    }
     */

    @Step
    public void shouldBe(String... texts) {
        list.filterBy(visible).shouldHave(exactTexts(texts));
    }

    @Step
    public void shouldBeEmpty() {
        list.filterBy(visible).shouldBe(empty);
    }

    @Step
    public void clearCompleted() {
        clearCompletedButton.click();
    }

    @Step
    public void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    public void shouldHaveItemsLeft(int number) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(number)));
    }

    @Step
    public void shouldShowClearCompleted() {
        clearCompletedButton.shouldBe(visible);
    }

    @Step
    public void shouldHideClearCompleted() {
        clearCompletedButton.shouldBe(hidden);
    }
}
