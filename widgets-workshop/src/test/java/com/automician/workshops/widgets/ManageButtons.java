package com.automician.workshops.widgets;

import static com.codeborne.selenide.Selenide.$;

public class ManageButtons {
    public void save() {
        $("#btn-save-data-item").click();
    }
}
