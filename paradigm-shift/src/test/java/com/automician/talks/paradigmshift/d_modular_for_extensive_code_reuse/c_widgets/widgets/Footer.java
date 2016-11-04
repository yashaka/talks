package com.automician.talks.paradigmshift.d_modular_for_extensive_code_reuse.c_widgets.widgets;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Footer {
    private static SelenideElement clearCompletedButton = $("#clear-completed");

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
