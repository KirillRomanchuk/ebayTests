package com.ebay.components;

import com.ebay.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PaginationComponent extends BaseComponent {
    protected final String PAGINATION = ".//ol[@class='pagination__items']";
    protected final String ACTIVE_PAGE_NUMBER = PAGINATION.concat("//a[@aria-current='page']");
    protected final String PAGINATION_LIST = PAGINATION.concat("/li");

    public BaseComponent activePage() {
        return new BaseComponent().init(By.xpath(ACTIVE_PAGE_NUMBER), this.webElement, 1);
    }

    public List<BaseComponent> pageList() {
        List<BaseComponent> items = new ArrayList<>();
        List<WebElement> webElements = Waiters.findElements(By.xpath(PAGINATION_LIST), this.webElement, 2);
        for (WebElement we : webElements) {
            BaseComponent item = new BaseComponent().init(we, this.webElement);
            items.add(item);
        }

        return items;
    }
}
