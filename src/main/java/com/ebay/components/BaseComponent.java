package com.ebay.components;

import com.ebay.utils.Waiters;
import com.ebay.utils.browsers.BrowserManager;
import com.ebay.utils.javascript_executor.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.ebay.utils.Waiters.findElement;
import static com.ebay.utils.Waiters.waitUntilCondition;

public class BaseComponent {
    protected WebDriver driver = BrowserManager.getBrowser().getDriver();
    protected final int period = Waiters.period;
    protected final int timeout = Waiters.timeout;
    protected WebElement webElement;
    protected By by;
    protected SearchContext context;

    public BaseComponent init(By by, SearchContext context, int timeout, int period) {
        this.by = by;
        this.context = context;
        this.webElement = findElement(by, context, timeout, period);
        if (this.webElement == null) {
            return this;
        }

        scrollTo();
        return this;
    }

    public BaseComponent init(By by) {
        return init(by, driver, timeout, period);
    }

    public BaseComponent init(By by, SearchContext context, int timeout) {
        return init(by, context, timeout, period);
    }

    public BaseComponent init(By by, SearchContext context) {
        return init(by, context, timeout, period);
    }

    public BaseComponent init(WebElement webElement, SearchContext context) {
        this.context = context;
        this.webElement = webElement;
        if (this.webElement == null) {
            return this;
        }
        scrollTo();
        return this;
    }

    public BaseComponent reInit(int timeout) {
        this.webElement = findElement(by, context, timeout, period);

        return this;
    }

    public BaseComponent scrollTo() {
        JSExecutor.scrollTo(this.webElement);
        return this;
    }

    public String getText() {
        String text = webElement.getText();
        return text;
    }

    public void click() {
        webElement.click();
    }

    public void jsClick() {
        JSExecutor.click(this.webElement);
    }

    public boolean isDisplayed(int timeout) {
        boolean status = waitUntilCondition(
                () -> this.reInit(0).getWebElement().isDisplayed(),
                timeout,
                period,
                String.format("Component's '%s' display status to be true", this.getClass()));
        return status;
    }

    public boolean isDisplayed() {
        return this.isDisplayed(this.timeout);
    }

    public boolean isNotDisplayed(int timeout) {
        waitUntilCondition(() -> !this.isDisplayed(0),
                timeout,
                String.format("Component's '%s' display status to be false", this.getClass()));
        boolean status = this.reInit(0).isDisplayed(0);
        return !status;
    }

    public boolean isNotDisplayed() {
        return this.isNotDisplayed(this.timeout);
    }

    public String getAttribute(String attribute) {
        String value = webElement.getAttribute(attribute);
        return value;
    }

    public WebElement getWebElement() {
        return webElement;
    }
}
