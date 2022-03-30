package com.ebay.components;

import org.openqa.selenium.By;

public class SelectComponent extends BaseComponent {
    protected final String TEXT = ".//a//span";
    protected final String TEXT_SECOND_VARIANT = ".//span";

    public String getName() {
        BaseComponent bc = new BaseComponent().init(By.xpath(TEXT), this.webElement, 2);
        if (bc.getWebElement() == null)
            bc = new BaseComponent().init(By.xpath(TEXT_SECOND_VARIANT), this.webElement, 1);
        return bc.getText();
    }
}
