package com.ebay;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        tags = "",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        features = {"classpath:features"},
        glue = {"com/ebay/steps"},
        monochrome = true
)
@RunWith(Cucumber.class)
public class TestRunner {
}