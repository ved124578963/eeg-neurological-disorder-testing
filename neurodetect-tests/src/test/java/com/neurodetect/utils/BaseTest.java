package com.neurodetect.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(
            @Optional("chrome")  String browser,
            @Optional("false")   String headless
    ) {
        DriverManager.initDriver(browser, Boolean.parseBoolean(headless));
        getDriver().get(ConfigReader.getBaseUrl());
        WaitUtils.waitForReactRender(getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected void navigateTo(String route) {
        getDriver().get(ConfigReader.getUrl(route));
        WaitUtils.waitForReactRender(getDriver());
    }
}
