package com.neurodetect.pages;

import com.neurodetect.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage {

    private final WebDriver driver;

    // ================================================================
    //  LOCATORS — fill these in after inspecting the rendered HTML
    // ================================================================

    // --- Hero Section ---
    private final By heroTitle        = By.xpath("//header/h1");
    private final By heroDescription  = By.xpath("//header/p");
    private final By startPredictionBtn = By.xpath("//a[@class='btn-primary']");
    private final By learnHowItWorksBtn = By.xpath("//a[@class='btn-outline' and contains(text(), 'Learn')]");

    // --- Stat Cards ---
    private final By statCards        = By.xpath("//div[@class='stats-grid']");
    private final By firstStatValue   = By.xpath("//div[@class='stat-card glass-panel'][1]/div[2]");
    private final By secondStatValue  = By.xpath("//div[@class='stat-card glass-panel'][2]/div[2]");
    private final By thirdStatValue   = By.xpath("//div[@class='stat-card glass-panel'][3]/div[2]");

    // --- Workflow Section ---
    private final By workflowSectionHeading = By.xpath("//div[@class='section-heading']/h2[contains(text(), 'How')]");
    private final By workflowSteps    = By.xpath("//div[@class='workflow-track']/div");

    // --- Disorder Cards ---
    private final By disorderCards    = By.xpath("//div[@class='disease-grid']/div");
    private final By epilepsyCard     = By.xpath("//div[@class='disease-grid']/div[1]");
    private final By asdCard          = By.xpath("//div[@class='disease-grid']/div[2]");
    private final By alzheimerCard    = By.xpath("//div[@class='disease-grid']/div[3]");
    private final By schizophreniaCard = By.xpath("//div[@class='disease-grid']/div[4]");

    // --- Why EEG Section ---
    private final By whyEegHeading    = By.xpath("//h2[contains(text(), 'Why')]");
    private final By whyEegCards      = By.xpath("//div[@class='why-eeg-grid']");

    // --- "View Detailed Disorder Profiles" link ---
    private final By viewDisordersLink = By.xpath("//a[@class='btn-outline' and contains(text(), 'View')]");

    // ================================================================

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // ---- Hero ----

    public String getHeroTitle() {
        return WaitUtils.waitForVisibility(driver, heroTitle).getText();
    }

    public String getHeroDescription() {
        return driver.findElement(heroDescription).getText();
    }

    public boolean isStartPredictionBtnDisplayed() {
        return driver.findElement(startPredictionBtn).isDisplayed();
    }

    public void clickStartPrediction() {
        WaitUtils.waitForClickability(driver, startPredictionBtn).click();
        WaitUtils.waitForUrlContains(driver, "/analyze");
    }

    public void clickLearnHowItWorks() {
        WaitUtils.waitForClickability(driver, learnHowItWorksBtn).click();
        WaitUtils.waitForUrlContains(driver, "/about");
    }

    // ---- Stat Cards ----

    public int getStatCardCount() {
        return driver.findElements(statCards).size();
    }

    public String getFirstStatValue() {
        return driver.findElement(firstStatValue).getText();
    }

    public String getSecondStatValue() {
        return driver.findElement(secondStatValue).getText();
    }

    public String getThirdStatValue() {
        return driver.findElement(thirdStatValue).getText();
    }

    // ---- Workflow ----

    public boolean isWorkflowHeadingDisplayed() {
        return driver.findElement(workflowSectionHeading).isDisplayed();
    }

    public int getWorkflowStepCount() {
        return driver.findElements(workflowSteps).size();
    }

    // ---- Disorder Cards ----

    public int getDisorderCardCount() {
        return driver.findElements(disorderCards).size();
    }

    public boolean isEpilepsyCardDisplayed() {
        return driver.findElement(epilepsyCard).isDisplayed();
    }

    public boolean isAsdCardDisplayed() {
        return driver.findElement(asdCard).isDisplayed();
    }

    public boolean isAlzheimerCardDisplayed() {
        return driver.findElement(alzheimerCard).isDisplayed();
    }

    public boolean isSchizophreniaCardDisplayed() {
        return driver.findElement(schizophreniaCard).isDisplayed();
    }

    public void clickViewDisordersLink() {
        WaitUtils.waitForClickability(driver, viewDisordersLink).click();
        WaitUtils.waitForUrlContains(driver, "/disorders");
    }

    // ---- Why EEG ----

    public boolean isWhyEegHeadingDisplayed() {
        return driver.findElement(whyEegHeading).isDisplayed();
    }

    public int getWhyEegCardCount() {
        return driver.findElements(whyEegCards).size();
    }

    // ---- General ----

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
