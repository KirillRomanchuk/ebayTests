package com.ebay.utils.browsers;

import com.ebay.enums.Browsers;
import com.ebay.utils.configs.Configs;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class BrowserManager {
    private static BaseBrowser browser;

    public static BaseBrowser getBrowser() {
        if (browser == null) {
            new BrowserManager().initDriver();
        }
        return browser;
    }

    private void initDriver() {
        String browserName = Configs.browserName();
        if (Browsers.CHROME.name().equalsIgnoreCase(browserName)) {
            browser = new ChromeBrowser();
        } else if (Browsers.FIREFOX.name().equalsIgnoreCase(browserName)) {
            browser = new FirefoxBrowser();
        } else if (Browsers.EDGE.name().equalsIgnoreCase(browserName)) {
            browser = new EdgeBrowser();
        } else {
            browser = new ChromeBrowser();
        }
    }

    public static void quitSession() {
        getBrowser().getDriver().quit();
        browser = null;
    }

    public static void closeBrowser() {
        getBrowser().getDriver().close();
    }

    public File getScreenshot() {
        return ((TakesScreenshot) getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
    }

}
