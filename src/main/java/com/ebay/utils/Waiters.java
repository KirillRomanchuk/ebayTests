package com.ebay.utils;

import com.ebay.utils.browsers.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Waiters {
    public static final int period = 500;
    public static final int timeout = 30;

    public static WebElement waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(BrowserManager.getBrowser().getDriver(), timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public static boolean waitUntilCondition(Callable<Boolean> condition,
                                             int timeout,
                                             int period,
                                             String message) {
        String msg = message.isEmpty() ? "Condition is not true" : String.format("Condition '%s' is not true", message);
        long timeStart = System.currentTimeMillis();
        long timeExit = timeStart + timeout * 1000;
        do {
            try {
                boolean result = condition.call();
                if (timeout == 0)
                    return result;
                if (!result) {
                    throw new Exception(msg);
                }
                return result;
            } catch (Exception e) {
            }
            try {
                if (timeout > 0)
                    TimeUnit.MILLISECONDS.sleep(period);
            } catch (InterruptedException e) {
            }
        }
        while (System.currentTimeMillis() < timeExit);

        return false;
    }

    public static boolean waitUntilCondition(Callable<Boolean> condition, int timeout, int period) {
        return waitUntilCondition(condition, timeout, period, "");
    }

    public static boolean waitUntilCondition(Callable<Boolean> condition, int timeout) {
        return waitUntilCondition(condition, timeout, period, "");
    }

    public static boolean waitUntilCondition(Callable<Boolean> condition) {
        return waitUntilCondition(condition, timeout, period, "");
    }

    public static boolean waitUntilCondition(Callable<Boolean> condition, String message) {
        return waitUntilCondition(condition, timeout, period, message);
    }

    public static boolean waitUntilCondition(Callable<Boolean> condition, int timeout, String message) {
        return waitUntilCondition(condition, timeout, period, message);
    }

    public static WebElement findElement(By by,
                                         SearchContext context,
                                         int timeout,
                                         int period) {
        WebElement webElement = null;
        long timeStart = System.currentTimeMillis();
        long timeExit = timeStart + timeout * 1000L;
        do {
            try {
                webElement = context.findElement(by);
                return webElement;
            } catch (Exception e) {
                if (timeout == 0)
                    return null;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(period);
            } catch (InterruptedException e) {
            }
        }
        while (System.currentTimeMillis() < timeExit);

        return webElement;
    }

    public static List<WebElement> findElements(By by,
                                                SearchContext context,
                                                int timeout,
                                                int period) {
        List<WebElement> webElements = null;
        long timeStart = System.currentTimeMillis();
        long timeExit = timeStart + timeout * 1000;
        do {
            webElements = context.findElements(by);
            if (!webElements.isEmpty()) return webElements;
            try {
                TimeUnit.MILLISECONDS.sleep(period);
            } catch (InterruptedException e) {
            }
        }
        while (System.currentTimeMillis() < timeExit);

        return webElements;
    }

    public static List<WebElement> findElements(By by, SearchContext context, int timeout) {
        return findElements(by, context, timeout, period);
    }
}
