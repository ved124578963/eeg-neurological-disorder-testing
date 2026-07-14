package com.neurodetect.tests;

import com.neurodetect.pages.DashboardPage;
import com.neurodetect.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    @Test(description = "Hero title is displayed and contains 'NeuroScope'")
    public void heroTitleIsDisplayed() {
        DashboardPage page = new DashboardPage(getDriver());
        String title = page.getHeroTitle();
        Assert.assertTrue(title.contains("NeuroScope"),
            "Hero title should contain 'NeuroScope', found: " + title);
    }

    @Test(description = "Start Prediction button is visible")
    public void startPredictionButtonIsVisible() {
        DashboardPage page = new DashboardPage(getDriver());
        Assert.assertTrue(page.isStartPredictionBtnDisplayed(),
            "'Start Prediction' button should be visible in the hero section");
    }

    @Test(description = "Start Prediction button navigates to /analyze")
    public void startPredictionButtonNavigatesToAnalyzePage() {
        DashboardPage page = new DashboardPage(getDriver());
        page.clickStartPrediction();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/analyze"),
            "'Start Prediction' should navigate to /analyze");
    }

    @Test(description = "Learn How It Works button navigates to /about")
    public void learnHowItWorksBtnNavigatesToAboutPage() {
        DashboardPage page = new DashboardPage(getDriver());
        page.clickLearnHowItWorks();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/about"),
            "'Learn How It Works' should navigate to /about");
    }

    @Test(description = "Workflow section heading is displayed")
    public void workflowSectionIsDisplayed() {
        DashboardPage page = new DashboardPage(getDriver());
        Assert.assertTrue(page.isWorkflowHeadingDisplayed(),
            "Workflow section heading should be visible");
    }

    @Test(description = "View Detailed Disorder Profiles link navigates to /disorders")
    public void viewDisordersLinkNavigatesToDisordersPage() {
        DashboardPage page = new DashboardPage(getDriver());
        page.clickViewDisordersLink();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/disorders"),
            "'View Detailed Disorder Profiles' should navigate to /disorders");
    }

    @Test(description = "Why EEG section is displayed")
    public void whyEegSectionIsDisplayed() {
        DashboardPage page = new DashboardPage(getDriver());
        Assert.assertTrue(page.isWhyEegHeadingDisplayed(),
            "'Why EEG?' section heading should be visible");
    }
}
