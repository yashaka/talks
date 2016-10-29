package com.automician.workshops.widgets;

import com.codeborne.selenide.CollectionCondition;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Categories {
    @Step
    public Categories add(String name) {
        $("#btn-add-category").click();

        new Dialog()
                .setForLabel("Name:", name)
                .confirm();

        /* + more universal,
         * + less dependent on deep details of html layout
         * - for first usage, needs some more work (think more)
         *   to create correspondent Widget

         * >
        $(".category-name").setValue(name);
        $("#dialog-btn-add-category").click();
         * + more KISS
         * - in long perspective "less efficient", "longer" in implementation
         */
        return this;
    }

    /*
    @Step
    public Categories shouldBe(String... texts) {
        $$(".category-item").shouldHave(exactTexts(texts));
        return this;
    }
     * not needed so far, because in the test we use "implicit checks" for categories
     * by creating data storage under specific category we implicitly check that
     * needed category was created
     */


}
