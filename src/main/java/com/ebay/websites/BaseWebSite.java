package com.ebay.websites;

import com.ebay.pages.homePage.HomePage;
import com.ebay.pages.searchPage.ItemsPage;
import com.ebay.utils.browsers.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseWebSite {
    protected static BaseWebSite site;
    protected WebDriver driver;

    protected final String BODY = ".//body";

    public BaseWebSite() {
        driver = BrowserManager.getBrowser().getDriver();
    }

    public void loadPage(String page) {
        if (!page.startsWith("https://"))
            page = "https://".concat(page);

        driver.get(page);
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage homePage() {
        return (HomePage) new HomePage().init(By.xpath(BODY), driver);
    }

    public ItemsPage itemsPage() {
        return (ItemsPage) new ItemsPage().init(By.xpath(BODY), driver);
    }

}
