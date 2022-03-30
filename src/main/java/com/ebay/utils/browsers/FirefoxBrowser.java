package com.ebay.utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class FirefoxBrowser extends BaseBrowser {

    FirefoxBrowser() {
        FirefoxOptions options = setupFirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private FirefoxOptions setupFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("credentials_enable_service", false);
        profile.setPreference("profile.password_manager_enabled", false);
        options.setProfile(profile);

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
