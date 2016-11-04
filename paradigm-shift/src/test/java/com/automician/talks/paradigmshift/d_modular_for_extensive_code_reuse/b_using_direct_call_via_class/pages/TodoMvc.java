package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.b_using_direct_call_via_class.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodoMvc {

    private static ElementsCollection tasks = $$("#todo-list>li");
    private static SelenideElement clearCompletedButton = $("#clear-completed");

    @Step
    public static void add(String... taskTexts) {
        for(String text: taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public static void toggle(String taskText) {
        tasks.findBy(exactText(taskText)).find(".toggle").click();
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
    public static void assertTasksAre(String... texts) {
        tasks.filterBy(visible).shouldHave(exactTexts(texts));
    }

    @Step
    public static void assertTasksAreEmpty() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    @Step
    public static void assertItemsLeft(int number) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(number)));
    }

    @Step
    public static void assertClearCompletedIsVisible() {
        clearCompletedButton.shouldBe(visible);
    }

    @Step
    public static void assertClearCompletedIsHidden() {
        clearCompletedButton.shouldBe(hidden);
    }
}
