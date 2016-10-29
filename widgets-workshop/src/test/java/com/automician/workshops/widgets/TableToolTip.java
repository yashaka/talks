package com.automician.workshops.widgets;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.$;

public class TableToolTip {

    @Step
    public void shouldHaveKeyRowCells(String... texts) {
        $(".tooltip").find(".key-row").findAll(".table-cell")
                .shouldHave(exactTexts(texts));
    }

    @Step
    public void shouldHaveValueRowCells(String... texts) {
        $(".tooltip").find(".value-row").findAll(".table-cell")
                .shouldHave(exactTexts(texts));
    }

    /* + KISS
     * + with no premature optimisation (which is Evil ;)
     * + still quite readable
     *
     * >=
    public ElementsCollection keyRowCells() {
        return $(".tooltip").find(".key-row").findAll(".table-cell");
    }

    public ElementsCollection valueRowCells() {
        return $(".tooltip").find(".value-row").findAll(".table-cell");
    }

    public void shouldHaveKeyRowCells(String... texts) {
        keyRowCells().shouldHave(exactTexts(texts));
    }

    public void shouldHaveValueRowCells(String... texts) {
        valueRowCells().shouldHave(exactTexts(texts));
    }
     * - over-engineered - we don't know yet whether
     *   we will need separate keyRowCells and valueRowCells methods
     *   somewhere else
     * + more methods but their code is a bit more readable,
     *   because "broken down" into smaller pieces
     */
}
