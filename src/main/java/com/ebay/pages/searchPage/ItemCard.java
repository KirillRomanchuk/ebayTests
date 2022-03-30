package com.ebay.pages.searchPage;

import com.ebay.components.BaseComponent;
import org.openqa.selenium.By;

public class ItemCard extends BaseComponent {

    protected final String INFO = ".//div[contains(@class,'info')]";
    protected final String NAME = INFO.concat("//h3");
    protected final String INFO_DETAILS = INFO.concat("//div[contains(@class,'details')]");
    protected final String DETAILS_PRICE = INFO_DETAILS.concat("//span[contains(@class,'_price')]");
    protected final String DETAILS_POSTAGE = INFO_DETAILS.concat("//span[contains(@class,'shipping')]");
    protected final String DETAILS_BIDS = INFO_DETAILS.concat("//span[contains(@class,'bids')]");
    protected final String DETAILS_BUY_NOW = INFO_DETAILS.concat("//span[contains(@class,'purchase-options')]");

    public String getName() {
        BaseComponent baseComponent = new BaseComponent().init(By.xpath(NAME), this.webElement, 1);
        return baseComponent.getWebElement() != null ? baseComponent.getText() : "";
    }

    public BaseComponent getPrice(){
        return new BaseComponent().init(By.xpath(DETAILS_PRICE), this.webElement, 1);
    }

    public BaseComponent getPostage(){
        return new BaseComponent().init(By.xpath(DETAILS_POSTAGE), this.webElement, 1);
    }

    public BaseComponent getBids(){
        return new BaseComponent().init(By.xpath(DETAILS_BIDS), this.webElement, 1);
    }

    public BaseComponent getBuyNow(){
        return new BaseComponent().init(By.xpath(DETAILS_BUY_NOW), this.webElement, 1);
    }
}
