package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

public class Column {
    private final SelenideElement container;

    public Column(SelenideElement container) {
        this.container = container;
    }

    @Step
    public SelenideElement element() {
        return this.container;
    }

    @Step
    public ContextMenu menu() {
        return new ContextMenu(this.container);
    }

    @Step
    public void setName(String name) {
        menu().open();
        menu().inputFor("Column name").setValue(name);
        /* + needed click for "focus issues workaround" is already included
         *   in MenuInput#setValue implementation
         * - needed additional implementation
         *   (in context of delegating at least setValue)

         * >=
        menu().inputFor("Column name").click();
        menu().inputFor("Column name").setValue(name);
         * * emulating directly real user click before setting value,
         *   and also as work-around for "focus" issues in test impl.
         * + KISS
         * - user should remember to call this click everytime he uses MenuInput object
         */
        menu().select("Save");

        /* + more concise & KISS
         * - less economic (3 objects created instead of 1)
         *   + though not a big deal in context of performance in UI tests
         *     (selenium actions are much slower...)

         * ~
        ContextMenu contextMenu = contextMenu();
        contextMenu.open();
        contextMenu.inputFor("Name:").setValue(name);
        contextMenu.select("Save");
         * - less concise
         * + economic
         * + more "rational" according to OOP
         */
    }
}
