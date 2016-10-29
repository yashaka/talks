package com.automician.workshops.pages;

import com.automician.workshops.widgets.ConfirmationDialog;
import com.automician.workshops.widgets.Dialog;
import com.automician.workshops.widgets.Section;
import com.codeborne.selenide.Selenide;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Home {
    @Step
    public void open() {
        Selenide.open("/");
    }

    @Step
    public void addProduct(String name, String path) {
        $("#btn-add-product").click();

        new Dialog()
                .setForLabel("Name:", name)
                .setForLabel("Path:", path)
                .confirm();


        /* + more universal,
         * + less dependent on deep details of html layout
         * - for first usage, needs some more work (think more)
         *   to create correspondent Widget

         * >
        $(".product-name").setValue(name);
        $(".product-path").setValue(path);
        $("#dialog-btn-add-product").click();
         * + more KISS
         * - in long perspective "less efficient", "longer" in implementation
         */

        new ConfirmationDialog().confirm();
    }

    @Step
    public void addProduct(String name) {
        addProduct(name, name);
    }

    @Step
    public Product openProduct(String productName) {
        Section productSection =
                new Section(productName);

        String productId =
                productSection.element().find(".product-item").getAttribute("id");

        productSection.click();

        /* + more "universal"
         *   + created widget will work for other "sections" not only "product" ones
         * + pretty laconic and readable in usage

         * >
        SelenideElement productItem =
                $$(".product-item").findBy(exactText(productName));

        String productId =
                productItem.getAttribute("id");

        productItem.click();
         * + DRY
         * + readable

         * ~
        String productId =
                $$(".product-item").findBy(exactText(productName)).getAttribute("id");

        $$(".product-item").findBy(exactText(productName)).click();
         * + KISS
         * - though not DRY
         *   + but actually the duplication is concentrated in one place, so you will not forget to modify something when needed
         * - less readable
         */

        return new Product(productId);

        /* + more readable and laconic in usage
         * - less KISS in implementation (additional constructor created)

         * ~
        return new Product(Integer.valueOf(productId));
         */
    }
}
