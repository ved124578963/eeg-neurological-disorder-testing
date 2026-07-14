package com.neurodetect.tests;

import com.neurodetect.pages.ContactPage;
import com.neurodetect.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactPageTest extends BaseTest {

    @BeforeMethod
    public void goToContactPage() {
        navigateTo("/contact");
    }

    @Test(description = "Page title is displayed")
    public void pageTitleIsDisplayed() {
        ContactPage page = new ContactPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertFalse(title.isEmpty(), "Contact page title should not be empty");
    }

    @Test(description = "Info card is displayed on the left")
    public void infoCardIsDisplayed() {
        ContactPage page = new ContactPage(getDriver());
        Assert.assertTrue(page.isInfoCardDisplayed(), "Contact info card should be visible");
    }

    @Test(description = "Department information is displayed in the info card")
    public void departmentInfoIsDisplayed() {
        ContactPage page = new ContactPage(getDriver());
        Assert.assertTrue(page.isDepartmentInfoDisplayed(),
            "Department information should be visible in the info card");
    }

    @Test(description = "GitHub link is present in the info card")
    public void githubLinkIsPresent() {
        ContactPage page = new ContactPage(getDriver());
        Assert.assertTrue(page.isGithubLinkPresent(),
            "GitHub repository link should be present and have an href");
    }

    @Test(description = "Contact form is displayed on the right")
    public void contactFormIsDisplayed() {
        ContactPage page = new ContactPage(getDriver());
        Assert.assertTrue(page.isFormDisplayed(), "Contact form should be visible");
    }

    @Test(description = "Name input is marked as required")
    public void nameInputIsRequired() {
        ContactPage page = new ContactPage(getDriver());
        Assert.assertTrue(page.isNameInputRequired(),
            "Name input should have the 'required' attribute");
    }

    @Test(description = "Email input is marked as required")
    public void emailInputIsRequired() {
        ContactPage page = new ContactPage(getDriver());
        Assert.assertTrue(page.isEmailInputRequired(),
            "Email input should have the 'required' attribute");
    }

    @Test(description = "Message textarea is marked as required")
    public void messageTextareaIsRequired() {
        ContactPage page = new ContactPage(getDriver());
        Assert.assertTrue(page.isMessageRequired(),
            "Message textarea should have the 'required' attribute");
    }

    @Test(description = "Submitting the form with valid data shows the success state")
    public void validFormSubmissionShowsSuccessState() {
        ContactPage page = new ContactPage(getDriver());
        page.submitContactForm(
            "Test User",
            "test@example.com",
            "This is a test message from the automated test suite."
        );
        Assert.assertTrue(page.isSuccessStateDisplayed(),
            "Success confirmation should appear after valid form submission");
    }

    @Test(description = "Success message heading is displayed after submission")
    public void successHeadingIsDisplayed() {
        ContactPage page = new ContactPage(getDriver());
        page.submitContactForm("Test User", "test@example.com", "Test message.");
        String heading = page.getSuccessHeadingText();
        Assert.assertFalse(heading.isEmpty(),
            "Success heading should not be empty after form submission");
    }

//    @Test(description = "'Send another message' button resets the form")
//    public void sendAnotherButtonResetsForm() {
//        ContactPage page = new ContactPage(getDriver());
//        page.submitContactForm("Test User", "test@example.com", "Test message.");
//        Assert.assertTrue(page.isSuccessStateDisplayed(), "Success state should show first");
//
//        page.clickSendAnother();
//        Assert.assertTrue(page.isFormDisplayed(),
//            "Form should be visible again after clicking 'Send another message'");
//        Assert.assertTrue(page.isSubmitBtnDisplayedAfterPreviousResponse(),
//            "Success state should be gone after clicking 'Send another message'");
//    }
}
