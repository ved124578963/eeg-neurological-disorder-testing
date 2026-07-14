package com.neurodetect.pages;

import com.neurodetect.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPage {

    private final WebDriver driver;

    // --- Page Header ---
    private final By pageTitle      = By.xpath("//header/h1");
    private final By pageSubtitle   = By.xpath("//header/p");

    // --- Info Card ---
    private final By infoCard       = By.xpath("//div[@class='contact-grid']/div[1]");
    private final By departmentInfo = By.xpath("//div[@class='contact-info-card glass-panel']/div[1]/div/p");
    private final By githubLink     = By.xpath("//h4[contains(text(), 'Repository')]/following-sibling::a");
    // --- Contact Form ---
    private final By contactFormCard  = By.xpath("//div[@class='contact-form-card glass-panel']");
    private final By nameInput        = By.xpath("//input[@name='name']");
    private final By emailInput       = By.xpath("//input[@name='email']");
    private final By messageTextarea  = By.xpath("//textarea[@name='message']");
    private final By submitButton     = By.xpath("//button[@class='btn-primary']");

    // --- Success State (shown after submission) ---
    private final By successState     = By.xpath("//div[@class='contact-success animate-fade-in']");
    private final By successHeading   = By.xpath("//h3");
    private final By sendAnotherBtn   = By.xpath("//button[@class='btn-outline']");

    // ================================================================

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    // ---- Page ----

    public String getPageTitle() {
        return WaitUtils.waitForVisibility(driver, pageTitle).getText();
    }

    // ---- Info Card ----

    public boolean isInfoCardDisplayed() {
        return driver.findElement(infoCard).isDisplayed();
    }

    public boolean isDepartmentInfoDisplayed() {
        return driver.findElement(departmentInfo).isDisplayed();
    }

    public boolean isGithubLinkPresent() {
        WebElement link = driver.findElement(githubLink);
        String href = link.getAttribute("href");
        return href != null && !href.isEmpty();
    }

    // ---- Contact Form ----

    public boolean isFormDisplayed() {
        return driver.findElement(contactFormCard).isDisplayed();
    }

    public void enterName(String name) {
        WebElement input = WaitUtils.waitForClickability(driver, nameInput);
        input.clear();
        input.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement input = WaitUtils.waitForClickability(driver, emailInput);
        input.clear();
        input.sendKeys(email);
    }

    public void enterMessage(String message) {
        WebElement input = WaitUtils.waitForClickability(driver, messageTextarea);
        input.clear();
        input.sendKeys(message);
    }

    public void clickSubmit() {
        WaitUtils.waitForClickability(driver, submitButton).click();
        WaitUtils.waitForReactRender(driver);
    }

    /** Fill and submit the contact form in one call. */
    public void submitContactForm(String name, String email, String message) {
        enterName(name);
        enterEmail(email);
        enterMessage(message);
        clickSubmit();
    }

    // ---- Success State ----

    public boolean isSuccessStateDisplayed() {
        return WaitUtils.waitForVisibility(driver, successState).isDisplayed();
    }

    public boolean isSubmitBtnDisplayedAfterPreviousResponse(){
        return WaitUtils.waitForVisibility(driver, submitButton).isDisplayed();
    }

    public String getSuccessHeadingText() {
        return driver.findElement(successHeading).getText();
    }

    public void clickSendAnother() {
        WaitUtils.waitForClickability(driver, sendAnotherBtn).click();
        WaitUtils.waitForReactRender(driver);
    }

    // ---- Validation ----

    /** Checks that the browser's native required-field validation fires. */
    public boolean isNameInputRequired() {
        return driver.findElement(nameInput).getAttribute("required") != null;
    }

    public boolean isEmailInputRequired() {
        return driver.findElement(emailInput).getAttribute("required") != null;
    }

    public boolean isMessageRequired() {
        return driver.findElement(messageTextarea).getAttribute("required") != null;
    }
}
