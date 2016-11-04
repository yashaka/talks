package com.automician.talks.paradigmshift.e_OOP_for_multi_instance_code_reuse_and_managers.ii_d_oop_with_widgets.widgets;

import com.codeborne.selenide.SelenideElement;

public class Task {

    private final SelenideElement container;

    public Task(SelenideElement container) {
        this.container = container;
    }

    public Task toggle() {
        this.container.find(".toggle").click();
        return this;
    }

    public void destroy() {
        //todo: implement
    }

    public Task edit(String newText) {
        //todo: implement
        return this;
    }
}
