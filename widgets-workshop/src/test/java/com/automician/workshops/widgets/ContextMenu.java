package com.automician.workshops.widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;

public class ContextMenu {

    private final SelenideElement container;
    private final SelenideElement element;

    public ContextMenu(SelenideElement container, SelenideElement element) {
        this.container = container;
        this.element = element;
    }

    public ContextMenu(SelenideElement element) {
        this(
                $$(".context-menu-list").findBy(visible),
                element
        );
    }

    public ContextMenu open() {
        this.element.contextClick();
        return this;
    }

    public void select(String item) {
        this.container.find(byText(item)).click();
    }

    public MenuInput inputFor(String label) {
        return new MenuInput(this.container, label);
    }

    public ContextMenu editInput(String label, String value) {
        inputFor(label).setValue(value);
        return this;
    }
    /* is not used anywhere, but left here "just in case" "Fluent page object"
     * will be needed somewhere
     */

    public MenuSelect selectFor(String label) {
        return new MenuSelect(this.container, label);
    }
    /* + consistent with previous implementation for ContextMenu#inputFor

     * ~
    public SelenideElement selectFor(String label) {
        return new MenuSelect(this.container, label).element();
    }
     * + KISS
     * - not consistent with previous implementation for ContextMenu#inputFor
     * - exposing full SelenideElement
     *   in the case where we need only SelenideElement#selectOption
     *   may be "overcomplicated in context of usage" for some newcomers
     *   + but nevertheless = more powerful with minimum of additional implementation
     */
}
