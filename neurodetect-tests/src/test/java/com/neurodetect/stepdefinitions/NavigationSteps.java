package com.neurodetect.stepdefinitions;

import com.neurodetect.pages.NavbarPage;
import com.neurodetect.utils.ConfigReader;
import com.neurodetect.utils.DriverManager;
import com.neurodetect.utils.WaitUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * NavigationSteps
 * ===============
 * Step definitions for navigation.feature.
 * Hooks (driver init/quit) are in CucumberHooks.java.
 */
public class NavigationSteps {

    private NavbarPage getNavbar() {
        return new NavbarPage(DriverManager.getDriver());
    }

    // ---- Given ----

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        DriverManager.getDriver().get(ConfigReader.getBaseUrl());
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @Given("I have navigated to the {string} page")
    public void iHaveNavigatedToThePage(String route) {
        DriverManager.getDriver().get(ConfigReader.getUrl(route));
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    // ---- When ----

    @When("I click the {string} nav link")
    public void iClickTheNavLink(String linkName) {
        NavbarPage navbar = getNavbar();
        switch (linkName) {
            case "Home":                 navbar.clickHome();               break;
            case "About Project":        navbar.clickAboutProject();       break;
            case "Predict":              navbar.clickPredict();            break;
            case "Supported Disorders":  navbar.clickSupportedDisorders(); break;
            case "Team":                 navbar.clickTeam();               break;
            case "Contact":              navbar.clickContact();            break;
            default:
                throw new IllegalArgumentException("Unknown nav link: " + linkName);
        }
    }

    @When("I click the NeuroDetect logo")
    public void iClickTheLogo() {
        getNavbar().clickLogo();
    }

    @When("I navigate directly to {string}")
    public void iNavigateDirectlyTo(String route) {
        DriverManager.getDriver().get(ConfigReader.getUrl(route));
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    // ---- Then ----

    @Then("the navbar should be visible")
    public void theNavbarShouldBeVisible() {
        Assert.assertTrue(getNavbar().isNavbarDisplayed(),
            "Navbar should be visible on the page");
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expected) {
        String actual = DriverManager.getDriver().getTitle();
        Assert.assertTrue(actual.contains(expected),
            "Page title should contain '" + expected + "', but found: " + actual);
    }

    @Then("I should be on the {string} page")
    public void iShouldBeOnThePage(String route) {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(route),
            "Expected to be on '" + route + "', but URL was: " + currentUrl);
    }

    @Then("I should be on the home page")
    public void iShouldBeOnTheHomePage() {
        String url = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(url.endsWith("/") || url.endsWith("5173"),
            "Expected to be on the home page, but URL was: " + url);
    }

    @Then("the {string} nav link should be active")
    public void theNavLinkShouldBeActive(String linkName) {
        NavbarPage navbar = getNavbar();
        boolean active;

        switch (linkName) {
            case "Home":
                active = navbar.isHomeLinkActive();
                break;
            case "About Project":
                active = navbar.isAboutLinkActive();
                break;
            case "Predict":
                active = navbar.isPredictLinkActive();
                break;
            case "Supported Disorders":
                active = navbar.isDisordersLinkActive();
                break;
            case "Team":
                active = navbar.isTeamLinkActive();
                break;
            case "Contact":
                active = navbar.isContactLinkActive();
                break;
            default:
                throw new IllegalArgumentException("Unknown link: " + linkName);
        }
        Assert.assertTrue(active, "'" + linkName + "' nav link should have the active class");
    }

    @Then("the {string} nav link should not be active")
    public void theNavLinkShouldNotBeActive(String linkName) {
        NavbarPage navbar = getNavbar();
        boolean active;

        switch (linkName) {
            case "Home":
                active = navbar.isHomeLinkActive();
                break;
            case "About Project":
                active = navbar.isAboutLinkActive();
                break;
            case "Predict":
                active = navbar.isPredictLinkActive();
                break;
            case "Supported Disorders":
                active = navbar.isDisordersLinkActive();
                break;
            case "Team":
                active = navbar.isTeamLinkActive();
                break;
            case "Contact":
                active = navbar.isContactLinkActive();
                break;
            default:
                throw new IllegalArgumentException("Unknown link: " + linkName);
        }
        Assert.assertFalse(active, "'" + linkName + "' nav link should NOT have the active class");
    }
}
