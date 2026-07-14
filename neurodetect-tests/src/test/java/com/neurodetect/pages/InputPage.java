package com.neurodetect.pages;

import com.neurodetect.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputPage {

    private final WebDriver driver;

    // --- Status Banner ---
    private final By statusBanner    = By.xpath("//span[contains(text(), 'Please')]");

    // --- Patient Information Card ---
    private final By patientCard     = By.xpath("//div[@class='patient-section glass-panel']");
    private final By patientCardTitle = By.xpath("//div[@class='patient-section glass-panel']/h2");

    // Patient form inputs
    private final By nameInput       = By.xpath("//input[@name='name']");
    private final By ageInput        = By.xpath("//input[@name='age']");
    private final By genderSelect    = By.xpath("//select[@name='gender']");
    private final By phoneInput      = By.xpath("//input[@name='phone']");
    private final By remarksTextarea = By.xpath("//textarea[@name='remarks']");

    // --- Upload Card ---
    private final By uploadCard      = By.xpath("//div[@class='upload-section glass-panel']");
    private final By uploadCardTitle = By.xpath("//h2[contains(text(),'EEG')]");
    private final By dropZone        = By.xpath("//label[@class='drop-zone']");
    private final By fileInput       = By.xpath("//input[@type='file']");

    // --- Validation / Error ---
    private final By frontendErrorMsg = By.xpath("//div[@class='error-message animate-fade-in']/p");
    private final By clearErrorBtn   = By.xpath("//button[@class='btn-secondary']");


    // ================================================================

    public InputPage(WebDriver driver) {
        this.driver = driver;
    }

    // ---- Page check ----

    public boolean isStatusBannerDisplayed() {
        return WaitUtils.waitForVisibility(driver, statusBanner).isDisplayed();
    }

    public boolean isPatientCardDisplayed() {
        return driver.findElement(patientCard).isDisplayed();
    }

    public boolean isUploadCardDisplayed() {
        return driver.findElement(uploadCard).isDisplayed();
    }

    // ---- Patient Form ----

    public void enterPatientName(String name) {
        WebElement input = WaitUtils.waitForClickability(driver, nameInput);
        input.clear();
        input.sendKeys(name);
    }

    public void enterAge(String age) {
        WebElement input = WaitUtils.waitForClickability(driver, ageInput);
        input.clear();
        input.sendKeys(age);
    }

    public void selectGender(String gender) {
        // Uses org.openqa.selenium.support.ui.Select for dropdown handling
        new org.openqa.selenium.support.ui.Select(
            driver.findElement(genderSelect)
        ).selectByVisibleText(gender);
    }

    public void enterPhone(String phone) {
        WebElement input = WaitUtils.waitForClickability(driver, phoneInput);
        input.clear();
        input.sendKeys(phone);
    }

    public void enterRemarks(String remarks) {
        WebElement input = WaitUtils.waitForClickability(driver, remarksTextarea);
        input.clear();
        input.sendKeys(remarks);
    }

    /** Fill all required patient fields in one call. */
    public void fillRequiredPatientDetails(String name, String age, String gender) {
        enterPatientName(name);
        enterAge(age);
        selectGender(gender);
    }

    public String getNameInputValue() {
        return driver.findElement(nameInput).getAttribute("value");
    }

    public boolean isNameInputDisabled() {
        return driver.findElement(nameInput).getAttribute("disabled") != null;
    }

    // ---- File Upload ----

    /**
     * Uploads a file by sending the absolute file path directly to the
     * hidden <input type="file"> element.
     *
     * Usage:
     *   inputPage.uploadFile("C:/TestData/sample_epilepsy.csv");
     *   inputPage.uploadFile("/home/user/testdata/sample.edf");
     */
    public void uploadFile(String absoluteFilePath) {
        driver.findElement(fileInput).sendKeys(absoluteFilePath);
        WaitUtils.waitForReactRender(driver);
    }

    // ---- Validation & Error States ----

    /**
     * Attempts to upload a file WITHOUT filling in patient details.
     * Expects the frontend to block this and show an error.
     */
    public void attemptUploadWithoutPatientDetails(String absoluteFilePath) {
        driver.findElement(fileInput).sendKeys(absoluteFilePath);
        WaitUtils.waitForReactRender(driver);
    }

    public boolean isFrontendErrorDisplayed() {
        return WaitUtils.waitForVisibility(driver, frontendErrorMsg).isDisplayed();
    }

    public String getFrontendErrorText() {
        return driver.findElement(frontendErrorMsg).getText();
    }

    public void clickClearError() {
        WaitUtils.waitForClickability(driver, clearErrorBtn).click();
        WaitUtils.waitForReactRender(driver);
    }


    // ---- Drop Zone ----

    public boolean isDropZoneDisplayed() {
        return driver.findElement(dropZone).isDisplayed();
    }
}
