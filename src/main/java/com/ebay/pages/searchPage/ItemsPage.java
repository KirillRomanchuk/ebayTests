package com.ebay.pages.searchPage;

import com.ebay.components.*;
import com.ebay.pages.BasePage;
import com.ebay.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsPage extends BasePage {
    protected final String mainXPath = ".//div[@id='mainContent']";
    protected final String SEARCH_COMPONENT = ".//table//td//form/table[@role='presentation']";
    protected final String ITEMS_RESULT = ".//div/ul[contains(@class,'results')]/li";
    protected final String FILTER_LIST = mainXPath.concat("//ul[contains(@class,'fake-tabs__items')]/li");
    protected final String SORT_BUTTON = mainXPath.concat("//div[contains(@class,'__sort')]");
    protected final String SORT_LIST = mainXPath.concat("//div[contains(@class,'__sort')]//ul/li");
    protected final String ITEMS_DETAILS_PRICE = ITEMS_RESULT.concat("//span[contains(@class,'_price')]");
    protected final String ITEMS_DETAILS_POSTAGE = ITEMS_RESULT.concat("//span[contains(@class,'shipping')]");
    protected final String ITEMS_DETAILS_BIDS = ITEMS_RESULT.concat("//span[contains(@class,'bids')]");
    protected final String ITEMS_DETAILS_BUY_NOW = ITEMS_RESULT.concat("//span[contains(@class,'purchase-options')]");
    protected final String SELECTED_CATEGORY = "//span[contains(text(),'Selected category')]/parent::*";
    protected final String PAGINATION = ".//div[contains(@class,'pagination')]";

    public String getPageUrlPattern() {
        return "^https?:\\/\\/www.ebay.+";
    }

    public SearchComponent searchComponent() {
        return (SearchComponent) new SearchComponent().init(By.xpath(SEARCH_COMPONENT), this.webElement);
    }

    public PaginationComponent paginationComponent() {
        return (PaginationComponent) new PaginationComponent().init(By.xpath(PAGINATION), this.webElement, 1);
    }

    public List<ItemCard> itemResultList() {
        List<ItemCard> items = new ArrayList<>();
        List<WebElement> webElements = Waiters.findElements(By.xpath(ITEMS_RESULT), this.webElement, 2);
        for (WebElement we : webElements) {
            ItemCard item = (ItemCard) new ItemCard().init(we, this.webElement);
            items.add(item);
        }

        return items;
    }

    public List<BaseComponent> itemResultListPrices() {
        return listInfo(ITEMS_DETAILS_PRICE);
    }

    public List<BaseComponent> itemResultListPostage() {
        return listInfo(ITEMS_DETAILS_POSTAGE);
    }

    public List<BaseComponent> itemResultListBids() {
        return listInfo(ITEMS_DETAILS_BIDS);
    }

    public List<BaseComponent> itemResultListBuyNow() {
        return listInfo(ITEMS_DETAILS_BUY_NOW);
    }

    public Map<String, SelectComponent> filterMap() {
        return selectComponentMap(FILTER_LIST);
    }

    public Map<String, SelectComponent> sortVariantsMap() {
        return selectComponentMap(SORT_LIST);
    }

    public String selectedCategory() {
        BaseComponent bc = new BaseComponent().init(By.xpath(SELECTED_CATEGORY), this.webElement);
        return bc.getText();
    }

    public ButtonComponent sortButton() {
        return (ButtonComponent) new ButtonComponent().init(By.xpath(SORT_BUTTON), this.webElement);
    }

    public List<BaseComponent> listInfo(String xpath) {
        List<BaseComponent> items = new ArrayList<>();
        List<WebElement> webElements = Waiters.findElements(By.xpath(xpath), this.webElement, 2);
        for (WebElement we : webElements) {
            BaseComponent item = new BaseComponent().init(we, this.webElement);
            items.add(item);
        }

        return items;
    }

    public Map<String, SelectComponent> selectComponentMap(String xpath) {
        Map<String, SelectComponent> filters = new HashMap<>();
        List<WebElement> webElements = Waiters.findElements(By.xpath(xpath), this.webElement, this.timeout);
        for (WebElement we : webElements) {
            SelectComponent item = (SelectComponent) new SelectComponent().init(we, this.webElement);
            filters.put(item.getName(), item);
        }

        return filters;
    }

}
