package com.ebay.utils.javascript_executor;

import com.ebay.utils.browsers.BrowserManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSExecutor {
    public static Object scrollTo(WebElement webElement) {
        return executeJSScript("return arguments[0].scrollIntoView({ behavior: 'instant', block: 'center' });", webElement);
    }

    private static Object executeJSScript(String script, WebElement webElement) {
        WebDriver driver = BrowserManager.getBrowser().getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return js.executeScript(script, webElement);
    }

    public static Object clearInput(WebElement webElement) {
        return executeJSScript("return arguments[0].value = '';", webElement);
    }

    public static Object click(WebElement webElement) {
        return executeJSScript("return arguments[0].click();", webElement);
    }
}