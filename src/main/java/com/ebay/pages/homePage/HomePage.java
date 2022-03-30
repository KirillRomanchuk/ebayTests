package com.ebay.pages.homePage;

import com.ebay.components.SearchComponent;
import com.ebay.pages.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    protected final String SEARCH_COMPONENT = ".//table//td//form/table[@role='presentation']";


    public String getPageUrlPattern() {
        return "^https?:\\/\\/www.ebay.+";
    }

    public SearchComponent searchComponent() {
        return (SearchComponent) new SearchComponent().init(By.xpath(SEARCH_COMPONENT), this.webElement);
    }
}
