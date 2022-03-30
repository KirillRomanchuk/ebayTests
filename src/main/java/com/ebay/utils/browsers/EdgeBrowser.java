package com.ebay.utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class EdgeBrowser extends BaseBrowser {

    EdgeBrowser() {
        EdgeOptions options = setupEdgeOptions();
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private EdgeOptions setupEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setCapability("prefs", prefs);
        options.setCapability("excludeSwitches", new String[]{"enable-automation"});

        options.setPageLoadStrategy(String.valueOf(PageLoadStrategy.NONE));

        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("loggingPrefs", logPrefs);

        return options;
    }
}
