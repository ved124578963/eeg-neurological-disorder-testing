package com.neurodetect.stepdefinitions;

import com.neurodetect.pages.InputPage;
import com.neurodetect.utils.DriverManager;
import com.neurodetect.utils.ConfigReader;
import com.neurodetect.utils.WaitUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * PredictPageSteps
 * ================
 * Step definitions for predict_page.feature.
 *
 * Dummy test files are created in the system temp directory so no
 * real EEG recordings are needed for frontend-only testing.
 */
public class PredictPageSteps {

    private Path tempDir;

    private InputPage getPage() {
        return new InputPage(DriverManager.getDriver());
    }

    /**
     * Creates a dummy file with the given extension in a temp folder.
     * Returns the absolute path as a String for sendKeys().
     */
    private String createDummyFile(String extension) throws IOException {
        if (tempDir == null || !tempDir.toFile().exists()) {
            tempDir = Files.createTempDirectory("neurodetect_bdd_");
        }
        Path file = tempDir.resolve("test_file" + extension);
        Files.write(file, ("dummy eeg data for bdd testing").getBytes());
        return file.toAbsolutePath().toString();
    }

    // ================================================================
    //  Given
    // ================================================================

    @Given("I am on the predict page")
    public void iAmOnThePredictPage() {
        DriverManager.getDriver().get(ConfigReader.getUrl("/analyze"));
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @Given("I have filled in all required patient details")
    public void iHaveFilledInAllRequiredPatientDetails() {
        getPage().fillRequiredPatientDetails("Test Patient", "40", "Male");
    }

    @Given("I enter age {string} and select gender {string}")
    public void iEnterAgeAndSelectGender(String age, String gender) {
        getPage().enterAge(age);
        getPage().selectGender(gender);
    }

    @Given("I enter patient name {string} and select gender {string}")
    public void iEnterPatientNameAndSelectGender(String name, String gender) {
        getPage().enterPatientName(name);
        getPage().selectGender(gender);
    }

    @Given("I enter patient name {string} and age {string}")
    public void iEnterPatientNameAndAge(String name, String age) {
        getPage().enterPatientName(name);
        getPage().enterAge(age);
    }

    @Given("I try to upload a file without entering any patient details")
    public void iTryToUploadFileWithoutPatientDetails() throws IOException {
        String dummyFile = createDummyFile(".csv");
        getPage().attemptUploadWithoutPatientDetails(dummyFile);
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @Given("a frontend error message is displayed")
    public void aFrontendErrorMessageIsDisplayed() {
        Assert.assertTrue(getPage().isFrontendErrorDisplayed(),
            "Precondition: a frontend error message should already be visible");
    }

    @Given("I have successfully submitted the contact form")
    public void iHaveSuccessfullySubmittedTheContactForm() {
        // Delegate to contact steps — this step appears in contact_form.feature
        // Reuse is handled by Cucumber's shared step registry
    }

    // ================================================================
    //  When
    // ================================================================

    @When("I try to upload a valid EEG file")
    public void iTryToUploadAValidEegFile() throws IOException {
        String dummyFile = createDummyFile(".csv");
        getPage().uploadFile(dummyFile);
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @When("I upload a file with extension {string}")
    public void iUploadAFileWithExtension(String extension) throws IOException {
        String dummyFile = createDummyFile(extension);
        getPage().uploadFile(dummyFile);
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @When("I upload a valid .csv EEG file")
    public void iUploadAValidCsvFile() throws IOException {
        String dummyFile = createDummyFile(".csv");
        getPage().uploadFile(dummyFile);
        WaitUtils.waitForReactRender(DriverManager.getDriver());
    }

    @When("I click the {string} button")
    public void iClickTheButton(String buttonLabel) {
        switch (buttonLabel) {
            case "Clear Error" : getPage().clickClearError();
            default : throw new IllegalArgumentException("Unknown button: " + buttonLabel);
        }
    }

    // ================================================================
    //  Then
    // ================================================================

    @Then("the patient information card should be visible")
    public void thePatientCardShouldBeVisible() {
        Assert.assertTrue(getPage().isPatientCardDisplayed(),
            "Patient information card should be visible");
    }

    @Then("the EEG file upload card should be visible")
    public void theUploadCardShouldBeVisible() {
        Assert.assertTrue(getPage().isUploadCardDisplayed(),
            "EEG file upload card should be visible");
    }

    @Then("the status banner should be visible")
    public void theStatusBannerShouldBeVisible() {
        Assert.assertTrue(getPage().isStatusBannerDisplayed(),
            "Status banner should be visible");
    }

    @Then("a frontend error message should be displayed")
    public void aFrontendErrorMessageShouldBeDisplayed() {
        Assert.assertTrue(getPage().isFrontendErrorDisplayed(),
            "A frontend validation error message should be displayed");
    }

    @Then("the error message should not be visible")
    public void theErrorMessageShouldNotBeVisible() {
        Assert.assertFalse(getPage().isFrontendErrorDisplayed(),
            "Error message should not be visible after clearing");
    }

    @Then("the optional phone field should be present on the form")
    public void theOptionalPhoneFieldShouldBePresent() {
        // Interact with it to confirm it exists — no assertion on value
        getPage().enterPhone("+91 00000 00000");
    }

    @Then("the optional remarks textarea should be present on the form")
    public void theOptionalRemarksTextareaShouldBePresent() {
        getPage().enterRemarks("BDD test remark");
    }
}
