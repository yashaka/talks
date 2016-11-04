package com.automician.workshops.widgets;

import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

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
    /*
     * + KISS
     * - not a true OOP
     *   actually these two constructors describes two different type
     *   of objects. So better would be to create two separate classes,
     *   one of which will reuse the other via composition.
     *   Other "at least better than two constructors" option would be
     *   to leave one constructor,
     *   and add additional static method
     *   to construct "object with default container"
     */

    @Step
    public ContextMenu open() {
        this.element.contextClick();
        return this;
    }

    @Step
    public void select(String item) {
        this.container.find(byText(item)).click();
    }

    @Step
    public MenuInput inputFor(String label) {
        return new MenuInput(this.container, label);
    }

    @Step
    public ContextMenu editInput(String label, String value) {
        inputFor(label).setValue(value);
        return this;
    }
    /* is not used anywhere, but left here "just in case" "Fluent page object"
     * will be needed somewhere
     */

    @Step
    public MenuSelect selectFor(String label) {
        return new MenuSelect(this.container, label);
    }

    public SelenideElement element() {
        return this.container;
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
