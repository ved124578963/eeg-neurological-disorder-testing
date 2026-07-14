package com.neurodetect.pages;

import com.neurodetect.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavbarPage {

    private final WebDriver driver;

    // The main <nav> element
    private final By navbar = By.tagName("nav");

    // The NeuroDetect logo / brand link (top-left)
    private final By logoText = By.className("navbar-logo");

    // Individual nav links — by their visible label text
    private final By homeLink             = By.xpath("//div[@class='navbar-links ']/a[1]");
    private final By aboutProjectLink     = By.xpath("//span[contains(text(), 'About')]");
    private final By predictLink          = By.xpath("//span[contains(text(), 'Predict')]");
    private final By supportedDisordersLink = By.xpath("//span[contains(text(), 'Supported')]");
    private final By teamLink             = By.xpath("//span[contains(text(), 'Team')]");
    private final By contactLink          = By.xpath("//span[contains(text(), 'Contact')]");

    // Hamburger toggle button (visible only on mobile/narrow viewport)
    private final By hamburgerButton = By.xpath("//button[@class='navbar-toggle']");

    // The links container (the div that collapses on mobile)
    private final By navLinksContainer = By.xpath("//div[@class='navbar-links open']");

    // ================================================================

    public NavbarPage(WebDriver driver) {
        this.driver = driver;
    }

    // ---- Visibility checks ----

    public boolean isNavbarDisplayed() {
        return driver.findElement(navbar).isDisplayed();
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(logoText).isDisplayed();
    }

    public String getLogoText() {
        return driver.findElement(logoText).getText();
    }

    // ---- Navigation actions ----

    public void clickHome() {
        WaitUtils.waitForClickability(driver, homeLink).click();
        WaitUtils.waitForUrlContains(driver, "/");
    }

    public void clickAboutProject() {
        WaitUtils.waitForClickability(driver, aboutProjectLink).click();
        WaitUtils.waitForUrlContains(driver, "/about");
    }

    public void clickPredict() {
        WaitUtils.waitForClickability(driver, predictLink).click();
        WaitUtils.waitForUrlContains(driver, "/analyze");
    }

    public void clickSupportedDisorders() {
        WaitUtils.waitForClickability(driver, supportedDisordersLink).click();
        WaitUtils.waitForUrlContains(driver, "/disorders");
    }

    public void clickTeam() {
        WaitUtils.waitForClickability(driver, teamLink).click();
        WaitUtils.waitForUrlContains(driver, "/team");
    }

    public void clickContact() {
        WaitUtils.waitForClickability(driver, contactLink).click();
        WaitUtils.waitForUrlContains(driver, "/contact");
    }

    public void clickLogo() {
        WaitUtils.waitForClickability(driver, logoText).click();
        WaitUtils.waitForUrlContains(driver, "/");
    }

    // ---- Active state ----

    /**
     * Returns the CSS class attribute of the currently active nav link.
     * Use this to verify the correct link is highlighted on each page.
     */
    public String getActiveLinkClass(By linkLocator) {
        return driver.findElement(linkLocator).getAttribute("class");
    }

    public boolean isLinkActive(By linkLocator) {
        String classes = driver.findElement(linkLocator).getAttribute("class");
        return classes != null && classes.contains("active");
    }

    // Convenience wrappers for each link's active state
    public boolean isHomeLinkActive()             { return isLinkActive(homeLink); }
    public boolean isAboutLinkActive()            { return isLinkActive(aboutProjectLink); }
    public boolean isPredictLinkActive()          { return isLinkActive(predictLink); }
    public boolean isDisordersLinkActive()        { return isLinkActive(supportedDisordersLink); }
    public boolean isTeamLinkActive()             { return isLinkActive(teamLink); }
    public boolean isContactLinkActive()          { return isLinkActive(contactLink); }

    // ---- Mobile / responsive ----

    public boolean isHamburgerButtonDisplayed() {
        WebElement btn = driver.findElement(hamburgerButton);
        return btn.isDisplayed();
    }

    public void clickHamburgerButton() {
        WaitUtils.waitForClickability(driver, hamburgerButton).click();
        WaitUtils.waitForReactRender(driver);
    }

    public boolean isNavLinksContainerOpen() {
        WebElement container = driver.findElement(navLinksContainer);
        String classes = container.getAttribute("class");
        // The navbar links container gets the "open" class when the
        // hamburger is toggled — see Navbar.css
        return classes != null && classes.contains("open");
    }

    // ---- All links visibility check ----

    public boolean areAllNavLinksDisplayed() {
        return driver.findElement(homeLink).isDisplayed()
            && driver.findElement(aboutProjectLink).isDisplayed()
            && driver.findElement(predictLink).isDisplayed()
            && driver.findElement(supportedDisordersLink).isDisplayed()
            && driver.findElement(teamLink).isDisplayed()
            && driver.findElement(contactLink).isDisplayed();
    }
}
