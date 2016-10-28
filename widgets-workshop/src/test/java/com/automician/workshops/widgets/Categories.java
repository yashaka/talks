package com.automician.workshops.widgets;

import static com.codeborne.selenide.Selenide.$;

public class Categories {
    public void add(String name) {
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
    }
}
