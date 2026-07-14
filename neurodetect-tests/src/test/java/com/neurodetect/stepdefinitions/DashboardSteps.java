package com.neurodetect.stepdefinitions;

import com.neurodetect.pages.DashboardPage;
import com.neurodetect.utils.ConfigReader;
import com.neurodetect.utils.DriverManager;
import com.neurodetect.utils.WaitUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DashboardSteps {

    private DashboardPage getPage() {
        return new DashboardPage(DriverManager.getDriver());
    }

    @When("I click the {string} button on the home page")
    public void iClickTheButtonOnTheHomePage(String buttonLabel) {
        switch (buttonLabel) {
            case "Start Prediction" : getPage().clickStartPrediction();
            default : throw new IllegalArgumentException("Unknown button: " + buttonLabel);
        }
    }

    @When("I click the \"Learn How It Works\" button")
    public void iClickLearnHowItWorksButton() {
        getPage().clickLearnHowItWorks();
    }

    @When("I click the \"View Detailed Disorder Profiles\" link")
    public void iClickViewDisordersLink() {
        getPage().clickViewDisordersLink();
    }

    @Then("the hero title should contain {string}")
    public void theHeroTitleShouldContain(String expected) {
        String actual = getPage().getHeroTitle();
        Assert.assertTrue(actual.contains(expected),
            "Hero title should contain '" + expected + "', but found: " + actual);
    }

    @Then("the hero description should be displayed")
    public void theHeroDescriptionShouldBeDisplayed() {
        Assert.assertFalse(getPage().getHeroDescription().isEmpty(),
            "Hero description should be visible and not empty");
    }

    @Then("the {string} button should be visible on the home page")
    public void theButtonShouldBeVisibleOnHomePage(String buttonLabel) {
        switch (buttonLabel) {
            case "Start Prediction" :
                Assert.assertTrue(getPage().isStartPredictionBtnDisplayed(),
                    "'Start Prediction' button should be visible");
            default : throw new IllegalArgumentException("Unknown button: " + buttonLabel);
        }
    }

    @Then("exactly 3 stat cards should be displayed")
    public void exactlyThreeStatCardsShouldBeDisplayed() {
        Assert.assertEquals(getPage().getStatCardCount(), 3,
            "Exactly 3 stat cards should be displayed");
    }

    @Then("the first stat card value should be {string}")
    public void theFirstStatCardValueShouldBe(String expected) {
        Assert.assertEquals(getPage().getFirstStatValue(), expected,
            "First stat card value mismatch");
    }

    @Then("the second stat card value should be {string}")
    public void theSecondStatCardValueShouldBe(String expected) {
        Assert.assertEquals(getPage().getSecondStatValue(), expected,
            "Second stat card value mismatch");
    }

    @Then("the workflow section should be displayed")
    public void theWorkflowSectionShouldBeDisplayed() {
        Assert.assertTrue(getPage().isWorkflowHeadingDisplayed(),
            "Workflow section heading should be visible");
    }

    @Then("exactly 5 workflow steps should be displayed")
    public void exactlyFiveWorkflowStepsShouldBeDisplayed() {
        Assert.assertEquals(getPage().getWorkflowStepCount(), 5,
            "Exactly 5 workflow steps should be displayed");
    }

    @Then("exactly 4 disorder cards should be displayed on the home page")
    public void exactlyFourDisorderCardsShouldBeDisplayed() {
        Assert.assertEquals(getPage().getDisorderCardCount(), 4,
            "Exactly 4 disorder cards should be displayed on the home page");
    }

    @Then("the {string} disorder card should be visible on the home page")
    public void theDisorderCardShouldBeVisible(String disorder) {
        boolean visible;

        switch (disorder) {
            case "Epilepsy":
                visible = getPage().isEpilepsyCardDisplayed();
                break;

            case "Autism":
                visible = getPage().isAsdCardDisplayed();
                break;

            case "Alzheimer":
                visible = getPage().isAlzheimerCardDisplayed();
                break;

            case "Schizophrenia":
                visible = getPage().isSchizophreniaCardDisplayed();
                break;

            default:
                throw new IllegalArgumentException("Unknown disorder: " + disorder);
        }
        Assert.assertTrue(visible, "'" + disorder + "' card should be visible on the home page");
    }

    @Then("the {string} section should be displayed")
    public void theSectionShouldBeDisplayed(String sectionName) {
        switch (sectionName) {
            case "Why EEG?" :
                Assert.assertTrue(getPage().isWhyEegHeadingDisplayed(),
                    "'Why EEG?' section should be visible");
            default : throw new IllegalArgumentException("Unknown section: " + sectionName);
        }
    }

    @Then("exactly 4 {string} cards should be displayed")
    public void exactlyFourCardsShouldBeDisplayed(String cardType) {
        switch (cardType) {
            case "Why EEG" :
                Assert.assertEquals(getPage().getWhyEegCardCount(), 4,
                    "Exactly 4 'Why EEG?' cards should be displayed");
            default : throw new IllegalArgumentException("Unknown card type: " + cardType);
        }
    }
}
