package com.neurodetect.stepdefinitions;

import com.neurodetect.pages.ContactPage;
import com.neurodetect.utils.ConfigReader;
import com.neurodetect.utils.DriverManager;
import com.neurodetect.utils.WaitUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ContactSteps {

    private ContactPage getPage() {
        return new ContactPage(DriverManager.getDriver());
    }

    @Given("I am on the contact page")
    public void iAmOnTheContactPage() {
        DriverManager.getDriver().get(ConfigReader.getUrl("/contact"));
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @Given("I have successfully submitted the contact form")
    public void iHaveSuccessfullySubmittedContactForm() {
        getPage().submitContactForm(
            "BDD Test User",
            "bdd@test.com",
            "This is a BDD test message."
        );
        Assert.assertTrue(getPage().isSuccessStateDisplayed(),
            "Precondition: form should have been submitted successfully");
    }

    @When("I fill in the contact form with name {string} email {string} and message {string}")
    public void iFillInTheContactForm(String name, String email, String message) {
        getPage().enterName(name);
        getPage().enterEmail(email);
        getPage().enterMessage(message);
    }

    @When("I submit the contact form")
    public void iSubmitTheContactForm() {
        getPage().clickSubmit();
    }

    @When("I click {string}")
    public void iClick(String buttonLabel) {
        switch (buttonLabel) {
            case "Send another message": getPage().clickSendAnother();
            default: throw new IllegalArgumentException("Unknown button label: " + buttonLabel);
        }
    }

    @Then("the contact information card should be visible")
    public void theContactInfoCardShouldBeVisible() {
        Assert.assertTrue(getPage().isInfoCardDisplayed(),
            "Contact information card should be visible");
    }

    @Then("the contact form should be visible")
    public void theContactFormShouldBeVisible() {
        Assert.assertTrue(getPage().isFormDisplayed(),
            "Contact form should be visible");
    }

    @Then("the department information should be displayed in the info card")
    public void theDepartmentInfoShouldBeDisplayed() {
        Assert.assertTrue(getPage().isDepartmentInfoDisplayed(),
            "Department information should be displayed in the info card");
    }

    @Then("the GitHub repository link should be present")
    public void theGithubLinkShouldBePresent() {
        Assert.assertTrue(getPage().isGithubLinkPresent(),
            "GitHub repository link should be present with a valid href");
    }

    @Then("the name field should be required")
    public void theNameFieldShouldBeRequired() {
        Assert.assertTrue(getPage().isNameInputRequired(),
            "Name field should have the 'required' attribute");
    }

    @Then("the email field should be required")
    public void theEmailFieldShouldBeRequired() {
        Assert.assertTrue(getPage().isEmailInputRequired(),
            "Email field should have the 'required' attribute");
    }

    @Then("the message field should be required")
    public void theMessageFieldShouldBeRequired() {
        Assert.assertTrue(getPage().isMessageRequired(),
            "Message textarea should have the 'required' attribute");
    }

    @Then("a success confirmation message should be displayed")
    public void aSuccessConfirmationMessageShouldBeDisplayed() {
        Assert.assertTrue(getPage().isSuccessStateDisplayed(),
            "A success confirmation message should be shown after form submission");
    }

    @Then("the contact form should be visible again")
    public void theContactFormShouldBeVisibleAgain() {
        Assert.assertTrue(getPage().isFormDisplayed(),
            "Contact form should be visible again after clicking 'Send another message'");
    }

    @Then("the success message should no longer be displayed")
    public void theSuccessMessageShouldNoLongerBeDisplayed() {
        Assert.assertFalse(getPage().isSuccessStateDisplayed(),
            "Success message should be gone after resetting the form");
    }
}
