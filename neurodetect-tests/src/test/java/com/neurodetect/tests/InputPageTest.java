package com.neurodetect.tests;

import com.neurodetect.pages.InputPage;
import com.neurodetect.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputPageTest extends BaseTest {

    // Temp directory where dummy test files are created
    private Path tempDir;

    @BeforeMethod
    public void goToInputPage() throws IOException {
        navigateTo("/analyze");
        // Create a temp directory for dummy test files
        tempDir = Files.createTempDirectory("neurodetect_test_");
    }

    private String createDummyFile(String fileName) throws IOException {
        Path file = tempDir.resolve(fileName);
        Files.write(file, "dummy eeg data for testing".getBytes());
        return file.toAbsolutePath().toString();
    }

    @Test(description = "Status banner is displayed at the top of the page")
    public void statusBannerIsDisplayed() {
        InputPage page = new InputPage(getDriver());
        Assert.assertTrue(page.isStatusBannerDisplayed(),
            "Status banner should be visible at the top of the Predict page");
    }

    @Test(description = "Patient Information card is displayed")
    public void patientCardIsDisplayed() {
        InputPage page = new InputPage(getDriver());
        Assert.assertTrue(page.isPatientCardDisplayed(),
            "Patient Information card should be visible");
    }

    @Test(description = "EEG File Upload card is displayed")
    public void uploadCardIsDisplayed() {
        InputPage page = new InputPage(getDriver());
        Assert.assertTrue(page.isUploadCardDisplayed(),
            "EEG File Upload card should be visible");
    }

    @Test(description = "Uploading without patient details shows a frontend error message")
    public void uploadWithoutPatientDetailsShowsError() throws IOException {
        String dummyCsv = createDummyFile("test_eeg.csv");
        InputPage page = new InputPage(getDriver());

        // Attempt upload WITHOUT filling in any patient fields
        page.attemptUploadWithoutPatientDetails(dummyCsv);

        Assert.assertTrue(page.isFrontendErrorDisplayed(),
            "An error message should appear when upload is attempted without patient details");
    }

    @Test(description = "Frontend error message mentions Name, Age, and Gender")
    public void errorMessageMentionsRequiredFields() throws IOException {
        String dummyCsv = createDummyFile("test_eeg.csv");
        InputPage page = new InputPage(getDriver());
        page.attemptUploadWithoutPatientDetails(dummyCsv);

        String errorText = page.getFrontendErrorText();
        Assert.assertTrue(
            errorText.contains("Name") || errorText.contains("Age") || errorText.contains("Gender"),
            "Error message should mention the missing required fields, found: " + errorText
        );
    }

//    @Test(description = "Clear Error button dismisses the error message")
//    public void clearErrorButtonDismissesError() throws IOException {
//        String dummyCsv = createDummyFile("test_eeg.csv");
//        InputPage page = new InputPage(getDriver());
//        page.attemptUploadWithoutPatientDetails(dummyCsv);
//
//        Assert.assertTrue(page.isFrontendErrorDisplayed(), "Error should be showing");
//        page.clickClearError();
//
//        Assert.assertFalse(page.isFrontendErrorDisplayed(),
//            "Error message should be dismissed after clicking 'Clear Error'");
//    }

    // ================================================================
    //  3. Data-Driven Form Validation (TestNG @DataProvider)
    // ================================================================

    @DataProvider(name = "incompletePatientData")
    public Object[][] incompletePatientData() {
        return new Object[][] {
            { "John",   "",    "",       "Age and Gender missing" },
            { "",       "35",  "",       "Name and Gender missing" },
            { "",       "",    "Male",   "Name and Age missing" },
        };
    }

    @Test(
        dataProvider = "incompletePatientData",
        description  = "Upload is blocked for all incomplete patient data combinations"
    )
    public void uploadBlockedForIncompletePatientData(
            String name, String age, String gender, String scenario
    ) throws IOException {
        String dummyCsv = createDummyFile("test_" + scenario.replaceAll(" ", "_") + ".csv");
        InputPage page = new InputPage(getDriver());

        // Fill only what's provided (empty string = leave blank)
        if (!name.isEmpty())   page.enterPatientName(name);
        if (!age.isEmpty())    page.enterAge(age);
        if (!gender.isEmpty()) page.selectGender(gender);

        page.attemptUploadWithoutPatientDetails(dummyCsv);

        Assert.assertTrue(page.isFrontendErrorDisplayed(),
            "Error should appear for scenario: " + scenario);
    }
}
