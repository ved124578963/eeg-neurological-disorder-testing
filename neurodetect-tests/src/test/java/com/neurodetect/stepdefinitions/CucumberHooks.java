package com.neurodetect.stepdefinitions;

import com.neurodetect.utils.ConfigReader;
import com.neurodetect.utils.DriverManager;
import com.neurodetect.utils.WaitUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CucumberHooks {

    @Before(order = 0)
    public void setUp() {
        String browser  = System.getProperty("browser",  "chrome");
        String headless = System.getProperty("headless", "false");

        DriverManager.initDriver(browser, Boolean.parseBoolean(headless));
        DriverManager.getDriver().get(ConfigReader.getBaseUrl());
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                // Attaches the screenshot to the Cucumber HTML report
                scenario.attach(screenshot, "image/png", "Screenshot on failure");
            } catch (Exception e) {
                System.err.println("Could not take screenshot: " + e.getMessage());
            }
        }
        DriverManager.quitDriver();
    }
}
