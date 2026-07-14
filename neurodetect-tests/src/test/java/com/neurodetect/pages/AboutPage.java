package com.neurodetect.pages;

import com.neurodetect.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutPage {

    // --- Page Header ---
    private final By pageTitle       = By.xpath("//header/h1");
    private final By pageSubtitle    = By.xpath("//header/p");

    // --- Problem Statement Section ---
    private final By problemStatementHeading = By.xpath("//section[1]");
    private final By problemStatementText    = By.xpath("//section[1]/div[2]/p");

    // --- Project Objective Section ---
    private final By objectiveHeading = By.xpath("//section[2]");
    private final By objectiveText    = By.xpath("//section[2]/div[2]/p");

    // --- Technology Stack ---
    private final By techStackHeading = By.xpath("//div[@class='section-heading']/h2[contains(text(), 'Technologies')]");
    private final By techCards        = By.xpath("//div[@class='tech-grid']");

    // --- Architecture Diagram ---
    private final By architectureHeading = By.xpath("//div[@class='section-heading']/h2[contains(text(), 'System')]");
    private final By archNodes           = By.xpath("//div[@class='arch-node']");
    private final WebDriver driver;

    // ================================================================

    public AboutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return WaitUtils.waitForVisibility(driver, pageTitle).getText();
    }

    public String getPageSubtitle() {
        return driver.findElement(pageSubtitle).getText();
    }

    // ---- Problem Statement ----

    public boolean isProblemStatementDisplayed() {
        return driver.findElement(problemStatementHeading).isDisplayed();
    }

    public String getProblemStatementText() {
        return driver.findElement(problemStatementText).getText();
    }

    // ---- Project Objective ----

    public boolean isObjectiveSectionDisplayed() {
        return driver.findElement(objectiveHeading).isDisplayed();
    }

    public String getObjectiveText() {
        return driver.findElement(objectiveText).getText();
    }

    // ---- Tech Stack ----

    public boolean isTechStackSectionDisplayed() {
        return driver.findElement(techStackHeading).isDisplayed();
    }

    public int getTechCardCount() {
        return driver.findElements(techCards).size();
    }

    // ---- Architecture Diagram ----

    public boolean isArchitectureSectionDisplayed() {
        return driver.findElement(architectureHeading).isDisplayed();
    }

    public int getArchNodeCount() {
        return driver.findElements(archNodes).size();
    }
}
