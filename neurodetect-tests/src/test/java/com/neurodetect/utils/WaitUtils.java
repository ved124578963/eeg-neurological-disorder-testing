package com.neurodetect.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private WaitUtils() {}

    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.EXPLICIT_WAIT_SECONDS));
    }

    private static WebDriverWait getWait(WebDriver driver, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    /** Wait until the element is visible on screen. */
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Wait until the element is visible on screen. */
    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        return getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    /** Wait until the element is visible and clickable. */
    public static WebElement waitForClickability(WebDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    /** Wait until the element is visible and clickable. */
    public static WebElement waitForClickability(WebDriver driver, WebElement element) {
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    /** Wait until the element disappears from the DOM (e.g. loading spinner). */
    public static void waitForInvisibility(WebDriver driver, By locator) {
        getWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /** Wait until the page URL contains the given fragment. */
    public static void waitForUrlContains(WebDriver driver, String urlFragment) {
        getWait(driver).until(ExpectedConditions.urlContains(urlFragment));
    }

    /** Wait until the element's text contains the expected value. */
    public static void waitForTextInElement(WebDriver driver, By locator, String text) {
        getWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** Convenience: standard React render pause from ConfigReader. */
    public static void waitForReactRender(WebDriver driver) {
        pause(ConfigReader.RENDER_WAIT_MS);
    }
}
