package com.neurodetect.pages;

import com.neurodetect.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SupportedDisordersPage {

    private final WebDriver driver;

    // --- Page Header ---
    private final By pageTitle        = By.tagName("h1");
    private final By pageSubtitle     = By.xpath("//h1/following-sibling::p");

    // --- Disorder Profile Cards ---
    private final By allDisorderCards = By.xpath("//div[@class='disorder-profile-list']/article");

    // Individual disorder cards (by their heading text or unique class)
    private final By epilepsyCard     = By.xpath("//div[@class='disorder-profile-list']/article[1]");
    private final By asdCard          = By.xpath("//div[@class='disorder-profile-list']/article[2]");
    private final By alzheimerCard    = By.xpath("//div[@class='disorder-profile-list']/article[3]");
    private final By schizophreniaCard = By.xpath("//div[@class='disorder-profile-list']/article[4]");

    // Sections within each card (use relative locators when testing a specific card)
    private final By cardHeadings     = By.xpath("//div[@class='disorder-profile-icon']/following-sibling::h2");
    private final By symptomsBlocks   = By.xpath("//h3[contains(text(), 'Common')]/ancestor::div[@class='disorder-profile-block']");
    private final By earlyDetectionBlocks = By.xpath("//h3[contains(text(), 'Detection')]/ancestor::div[@class='disorder-profile-block']");
    private final By eegRoleBlocks    = By.xpath("//h3[contains(text(), 'Role')]/ancestor::div[@class='disorder-profile-block']");

    // ================================================================

    public SupportedDisordersPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return WaitUtils.waitForVisibility(driver, pageTitle).getText();
    }

    public boolean isPageSubtitleDisplayed() {
        return driver.findElement(pageSubtitle).isDisplayed();
    }

    // ---- Disorder cards ----

    public int getDisorderCardCount() {
        return driver.findElements(allDisorderCards).size();
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

    // ---- Content checks ----

    public int getCardHeadingCount() {
        return driver.findElements(cardHeadings).size();
    }

    public int getSymptomsBlockCount() {
        return driver.findElements(symptomsBlocks).size();
    }

    public int getEarlyDetectionBlockCount() {
        return driver.findElements(earlyDetectionBlocks).size();
    }

    public int getEegRoleBlockCount() {
        return driver.findElements(eegRoleBlocks).size();
    }
}
