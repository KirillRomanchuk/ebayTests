package com.ebay.pages;

import com.ebay.components.BaseComponent;
import com.ebay.utils.Waiters;
import org.openqa.selenium.By;

public abstract class BasePage extends BaseComponent {
    protected final String PAGE_LOADER = ".//div[@utils.data-testid='app-Container']/img[@utils.data-testid='app-Loader']";

    protected static final int pageLoadTimeout = 10;

    public String getPageUrlPattern() {
//        return "^https?:\\/\\/.+\\/account/all-balances";  Example
        return "";
    }

    public BasePage() {
        this.waitPageIsOpened(pageLoadTimeout);
        this.pageLoader(0).isNotDisplayed();
    }

    public BasePage(int timeout) {
        this.waitPageIsOpened(timeout);
        if (timeout > 0) {
            this.pageLoader(0).isNotDisplayed(timeout);
        }
    }

    public boolean waitPageIsOpened(int timeout) {
        String pattern = getPageUrlPattern();
        boolean result = Waiters.waitUntilCondition(
                () -> driver.getCurrentUrl().matches(pattern),
                timeout,
                String.format("Page '%s' to be loaded", pattern));
        if (!result && timeout > 0) {
            throw new RuntimeException(String.format("%s page is not loaded. \nExpected page URL pattern: \n'%s'" +
                    "\ndoes not match with actual URL \n'%s'", this.getClass(), pattern, driver.getCurrentUrl()));
        }
        return result;
    }

    public BaseComponent pageLoader() {
        return new BaseComponent().init(By.xpath(PAGE_LOADER));
    }

    public BaseComponent pageLoader(int timeout) {
        return new BaseComponent().init(By.xpath(PAGE_LOADER), driver, timeout);
    }

    public BaseComponent scrollTo() {
        return this;
    }
}
