package com.ebay.components;

public class ButtonComponent extends BaseComponent {

    public boolean isEnabled() {
        boolean status = webElement.isEnabled();
        return status;
    }

    public boolean isDisabled() {
        boolean status = webElement.getAttribute("disabled") != null;
        return status;
    }

    public boolean isDisabledByStyle() {
        return !webElement.getCssValue("background").contains("255, 255, 255");
    }
}
