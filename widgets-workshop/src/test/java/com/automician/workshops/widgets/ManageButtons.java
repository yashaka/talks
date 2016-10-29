package com.automician.workshops.widgets;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

public class ManageButtons {

    @Step
    public void save() {
        $("#btn-save-data-item").click();
    }
}
