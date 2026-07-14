package com.neurodetect.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    // Where to find the .feature files
    features = "src/test/resources/features",

    // Where to find the step definition classes
    glue = "com.neurodetect.stepdefinitions",

    // Output formats
    plugin = {
        "pretty",                                          // readable console output
        "html:target/cucumber-reports/cucumber.html",      // HTML report
        "json:target/cucumber-reports/cucumber.json",      // JSON for CI integration
        "timeline:target/cucumber-reports/timeline"        // timeline view
    },

    // Show full monochrome output (useful in CI where colour codes break logs)
    monochrome = true,

    // Uncomment to run only scenarios tagged with @smoke:
    // tags = "@smoke",

    // Publish a Cucumber report to https://reports.cucumber.io (optional, needs token)
    publish = false
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
