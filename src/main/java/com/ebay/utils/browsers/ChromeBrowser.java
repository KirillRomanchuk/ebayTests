package com.ebay.utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class ChromeBrowser extends BaseBrowser {

    ChromeBrowser() {
        ChromeOptions options = setupChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private ChromeOptions setupChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logs);
        options.setAcceptInsecureCerts(true);

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--allow-file-a8ccess-from-files");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--allow-cross-origin-auth-prompt");
        options.addArguments("--allow-file-access");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--test-type");

        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            options.addArguments("--headless");
        }

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("loggingPrefs", logPrefs);

        return options;
    }
}
