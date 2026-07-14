package com.neurodetect.tests;

import com.neurodetect.pages.AboutPage;
import com.neurodetect.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AboutPageTest extends BaseTest {

    @BeforeMethod
    public void goToAboutPage() {
        navigateTo("/about");
    }

    @Test(description = "About page title is displayed")
    public void pageTitleIsDisplayed() {
        AboutPage page = new AboutPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertFalse(title.isEmpty(), "About page title should not be empty");
    }

    @Test(description = "Problem Statement section is displayed")
    public void problemStatementIsDisplayed() {
        AboutPage page = new AboutPage(getDriver());
        Assert.assertTrue(page.isProblemStatementDisplayed(),
            "Problem Statement section should be visible");
    }

    @Test(description = "Problem Statement text is not empty")
    public void problemStatementTextIsNotEmpty() {
        AboutPage page = new AboutPage(getDriver());
        Assert.assertFalse(page.getProblemStatementText().isEmpty(),
            "Problem Statement text content should not be empty");
    }

    @Test(description = "Project Objective section is displayed")
    public void projectObjectiveSectionIsDisplayed() {
        AboutPage page = new AboutPage(getDriver());
        Assert.assertTrue(page.isObjectiveSectionDisplayed(),
            "Project Objective section should be visible");
    }

    @Test(description = "Technology stack section is displayed")
    public void techStackSectionIsDisplayed() {
        AboutPage page = new AboutPage(getDriver());
        Assert.assertTrue(page.isTechStackSectionDisplayed(),
            "Technologies Used section should be visible");
    }
}
