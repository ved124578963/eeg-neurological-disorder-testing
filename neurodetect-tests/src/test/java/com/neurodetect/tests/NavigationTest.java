package com.neurodetect.tests;

import com.neurodetect.pages.NavbarPage;
import com.neurodetect.utils.BaseTest;
import com.neurodetect.utils.WaitUtils;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(description = "All 6 nav links are visible on desktop viewport")
    public void allNavLinksAreVisible() {
        NavbarPage navbar = new NavbarPage(getDriver());
        Assert.assertTrue(navbar.areAllNavLinksDisplayed(),
            "All 6 navigation links should be visible on desktop");
    }

    @Test(description = "Home nav link navigates to /")
    public void homeNavLinkNavigatesToDashboard() {
        NavbarPage navbar = new NavbarPage(getDriver());
        navbar.clickAboutProject(); // navigate away first
        navbar.clickHome();
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.endsWith("/") || url.endsWith("5173"),
            "Home link should navigate to the root route");
    }

    @Test(description = "About Project nav link navigates to /about")
    public void aboutNavLinkNavigatesToAboutPage() {
        NavbarPage navbar = new NavbarPage(getDriver());
        navbar.clickAboutProject();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/about"),
            "About Project link should navigate to /about");
    }

    @Test(description = "Predict nav link navigates to /analyze")
    public void predictNavLinkNavigatesToAnalyzePage() {
        NavbarPage navbar = new NavbarPage(getDriver());
        navbar.clickPredict();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/analyze"),
            "Predict link should navigate to /analyze");
    }

    @Test(description = "Supported Disorders nav link navigates to /disorders")
    public void disordersNavLinkNavigatesToDisordersPage() {
        NavbarPage navbar = new NavbarPage(getDriver());
        navbar.clickSupportedDisorders();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/disorders"),
            "Supported Disorders link should navigate to /disorders");
    }

    @Test(description = "Team nav link navigates to /team")
    public void teamNavLinkNavigatesToTeamPage() {
        NavbarPage navbar = new NavbarPage(getDriver());
        navbar.clickTeam();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/team"),
            "Team link should navigate to /team");
    }

    @Test(description = "Contact nav link navigates to /contact")
    public void contactNavLinkNavigatesToContactPage() {
        NavbarPage navbar = new NavbarPage(getDriver());
        navbar.clickContact();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/contact"),
            "Contact link should navigate to /contact");
    }

    @Test(description = "Logo click navigates back to home from any page")
    public void logoClickNavigatesHome() {
        NavbarPage navbar = new NavbarPage(getDriver());
        navbar.clickTeam(); // navigate away
        navbar.clickLogo(); // click the logo
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.endsWith("/") || url.endsWith("5173"),
            "Clicking the logo should navigate to the home page");
    }
}
