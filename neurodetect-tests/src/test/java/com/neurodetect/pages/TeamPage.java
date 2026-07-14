package com.neurodetect.pages;

import com.neurodetect.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeamPage {

    private final WebDriver driver;

    // --- Page Header ---
    private final By pageTitle     = By.tagName("h1");
    private final By pageSubtitle  = By.xpath("//h1/following-sibling::p");

    // --- Team Cards ---
    private final By allTeamCards  = By.xpath("//div[@class='team-grid']/div");

    // Elements inside each card
    private final By memberNames   = By.xpath("//div[@class='team-card glass-panel']/h3");
    private final By githubLinks   = By.xpath("//h3/following-sibling::div/a[@href='https://github.com/']");
    private final By linkedinLinks = By.xpath("//h3/following-sibling::div/a[@href='https://linkedin.com/']");
    private final By avatarIcons   = By.xpath("//div[@class='team-avatar']");

    // ================================================================

    public TeamPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return WaitUtils.waitForVisibility(driver, pageTitle).getText();
    }

    public boolean isPageSubtitleDisplayed() {
        return driver.findElement(pageSubtitle).isDisplayed();
    }

    public int getTeamCardCount() {
        return driver.findElements(allTeamCards).size();
    }

    public int getMemberNameCount() {
        return driver.findElements(memberNames).size();
    }

    public int getGithubLinkCount() {
        return driver.findElements(githubLinks).size();
    }

    public int getLinkedinLinkCount() {
        return driver.findElements(linkedinLinks).size();
    }

    public boolean areAvatarsDisplayed() {
        return driver.findElements(avatarIcons).stream()
                     .allMatch(e -> e.isDisplayed());
    }
}
