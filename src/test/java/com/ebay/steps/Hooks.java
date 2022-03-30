package com.ebay.steps;

import com.ebay.utils.data.scenarioContext.ScenarioContext;
import com.ebay.websites.BaseWebSite;
import com.ebay.websites.WebSiteManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void beforeScenario(Scenario scenario) {
        WebSiteManager.getSite("");
    }

    @SneakyThrows
    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()){
            BaseWebSite site = WebSiteManager.getSiteInstance();

            final byte[] screenshot = ((TakesScreenshot)site.getDriver()).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        ScenarioContext.clear();
        WebSiteManager.resetSite();
    }
}
