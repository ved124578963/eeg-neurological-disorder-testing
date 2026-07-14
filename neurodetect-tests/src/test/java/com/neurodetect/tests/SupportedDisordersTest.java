package com.neurodetect.tests;

import com.neurodetect.pages.SupportedDisordersPage;
import com.neurodetect.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SupportedDisordersTest extends BaseTest {

    @BeforeMethod
    public void goToDisordersPage() {
        navigateTo("/disorders");
    }

    @Test(description = "Page title is displayed")
    public void pageTitleIsDisplayed() {
        SupportedDisordersPage page = new SupportedDisordersPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertTrue(title.toLowerCase().contains("disorder"),
            "Page title should mention 'disorder', found: " + title);
    }

    @Test(description = "Exactly 4 disorder profile cards are displayed")
    public void fourDisorderCardsAreDisplayed() {
        SupportedDisordersPage page = new SupportedDisordersPage(getDriver());
        Assert.assertEquals(page.getDisorderCardCount(), 4,
            "Exactly 4 disorder profile cards should be displayed");
    }

    @Test(description = "Epilepsy card is displayed")
    public void epilepsyCardIsDisplayed() {
        SupportedDisordersPage page = new SupportedDisordersPage(getDriver());
        Assert.assertTrue(page.isEpilepsyCardDisplayed(), "Epilepsy card should be visible");
    }

    @Test(description = "Autism Spectrum Disorder card is displayed")
    public void asdCardIsDisplayed() {
        SupportedDisordersPage page = new SupportedDisordersPage(getDriver());
        Assert.assertTrue(page.isAsdCardDisplayed(), "ASD card should be visible");
    }

    @Test(description = "Alzheimer's Disease card is displayed")
    public void alzheimerCardIsDisplayed() {
        SupportedDisordersPage page = new SupportedDisordersPage(getDriver());
        Assert.assertTrue(page.isAlzheimerCardDisplayed(), "Alzheimer's card should be visible");
    }

    @Test(description = "Schizophrenia card is displayed")
    public void schizophreniaCardIsDisplayed() {
        SupportedDisordersPage page = new SupportedDisordersPage(getDriver());
        Assert.assertTrue(page.isSchizophreniaCardDisplayed(), "Schizophrenia card should be visible");
    }
}
