package com.ebay.components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class SearchComponent extends BaseComponent {
    protected final String SEARCH_BUTTON = ".//tbody//input[@type='submit']";
    protected final String SEARCH_INPUT = ".//tbody//input[@type='text']";
    protected final String SELECT_CATEGORIES = ".//tbody//select";

    public ButtonComponent searchButton() {
        return (ButtonComponent) new ButtonComponent().init(By.xpath(SEARCH_BUTTON), this.webElement);
    }

    public InputComponent searchInput() {
        return (InputComponent) new InputComponent().init(By.xpath(SEARCH_INPUT), this.webElement);
    }

    public Select selectCategory() {
        return new Select(new BaseComponent().init(By.xpath(SELECT_CATEGORIES), this.webElement).webElement);
    }
}
