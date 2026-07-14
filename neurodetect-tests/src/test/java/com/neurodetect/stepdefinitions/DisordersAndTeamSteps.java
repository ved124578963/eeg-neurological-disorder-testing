package com.neurodetect.stepdefinitions;

import com.neurodetect.pages.SupportedDisordersPage;
import com.neurodetect.pages.TeamPage;
import com.neurodetect.utils.ConfigReader;
import com.neurodetect.utils.DriverManager;
import com.neurodetect.utils.WaitUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class DisordersAndTeamSteps {

    private SupportedDisordersPage getDisordersPage() {
        return new SupportedDisordersPage(DriverManager.getDriver());
    }

    private TeamPage getTeamPage() {
        return new TeamPage(DriverManager.getDriver());
    }

    // ================================================================
    //  Given — navigation to each page
    // ================================================================

    @Given("I am on the supported disorders page")
    public void iAmOnTheSupportedDisordersPage() {
        DriverManager.getDriver().get(ConfigReader.getUrl("/disorders"));
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @Given("I am on the team page")
    public void iAmOnTheTeamPage() {
        DriverManager.getDriver().get(ConfigReader.getUrl("/team"));
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    // ================================================================
    //  Then — Supported Disorders page
    // ================================================================

    @Then("the disorders page title should be visible")
    public void theDisordersPageTitleShouldBeVisible() {
        String title = getDisordersPage().getPageTitle();
        Assert.assertFalse(title.isEmpty(),
            "Supported Disorders page title should not be empty");
    }

    @Then("exactly 4 disorder profile cards should be displayed")
    public void exactlyFourDisorderProfileCardsShouldBeDisplayed() {
        Assert.assertEquals(getDisordersPage().getDisorderCardCount(), 4,
            "Exactly 4 disorder profile cards should be displayed");
    }

    @Then("the {string} profile card should be displayed")
    public void theProfileCardShouldBeDisplayed(String disorder) {
        boolean visible;

        switch (disorder) {
            case "Epilepsy":
                visible = getDisordersPage().isEpilepsyCardDisplayed();
                break;

            case "Autism Spectrum Disorder":
                visible = getDisordersPage().isAsdCardDisplayed();
                break;

            case "Alzheimer's Disease":
                visible = getDisordersPage().isAlzheimerCardDisplayed();
                break;

            case "Schizophrenia":
                visible = getDisordersPage().isSchizophreniaCardDisplayed();
                break;

            default:
                throw new IllegalArgumentException("Unknown disorder: " + disorder);
        }
        Assert.assertTrue(visible, "Profile card for '" + disorder + "' should be displayed");
    }

    @Then("all disorder cards should have a Symptoms section")
    public void allDisorderCardsShouldHaveSymptomsSection() {
        Assert.assertEquals(getDisordersPage().getSymptomsBlockCount(), 4,
            "Each of the 4 disorder cards should have a Symptoms section");
    }

    @Then("all disorder cards should have an Early Detection section")
    public void allDisorderCardsShouldHaveEarlyDetectionSection() {
        Assert.assertEquals(getDisordersPage().getEarlyDetectionBlockCount(), 4,
            "Each of the 4 disorder cards should have an Early Detection section");
    }

    @Then("all disorder cards should have a Role of EEG section")
    public void allDisorderCardsShouldHaveEegRoleSection() {
        Assert.assertEquals(getDisordersPage().getEegRoleBlockCount(), 4,
            "Each of the 4 disorder cards should have a Role of EEG section");
    }

    // ================================================================
    //  Then — Team page
    // ================================================================

    @Then("the team page title should be visible")
    public void theTeamPageTitleShouldBeVisible() {
        String title = getTeamPage().getPageTitle();
        Assert.assertFalse(title.isEmpty(), "Team page title should not be empty");
    }

    @Then("the team page subtitle should be visible")
    public void theTeamPageSubtitleShouldBeVisible() {
        Assert.assertTrue(getTeamPage().isPageSubtitleDisplayed(),
            "Team page subtitle should be visible");
    }

    @Then("exactly 4 team member cards should be displayed")
    public void exactlyFourTeamCardsShouldBeDisplayed() {
        Assert.assertEquals(getTeamPage().getTeamCardCount(), 4,
            "Exactly 4 team member cards should be displayed");
    }

    @Then("each team card should display a member name")
    public void eachTeamCardShouldDisplayMemberName() {
        Assert.assertEquals(getTeamPage().getMemberNameCount(), 4,
            "Each team card should have a member name (h3 element)");
    }

    @Then("each team card should have a GitHub link")
    public void eachTeamCardShouldHaveGithubLink() {
        Assert.assertEquals(getTeamPage().getGithubLinkCount(), 4,
            "Each team card should have a GitHub link");
    }

    @Then("each team card should have a LinkedIn link")
    public void eachTeamCardShouldHaveLinkedinLink() {
        Assert.assertEquals(getTeamPage().getLinkedinLinkCount(), 4,
            "Each team card should have a LinkedIn link");
    }

    @Then("all team cards should display an avatar placeholder")
    public void allTeamCardsShouldDisplayAvatarPlaceholder() {
        Assert.assertTrue(getTeamPage().areAvatarsDisplayed(),
            "All team cards should display an avatar placeholder");
    }
}
