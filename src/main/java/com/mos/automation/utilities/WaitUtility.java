package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * WaitUtility class provides explicit wait mechanisms for handling asynchronous operations.
 * Uses WebDriverWait with various expected conditions.
 *
 * Features:
 * - Explicit waits for element visibility, clickability, presence
 * - Custom timeout configuration
 * - Reduced flakiness in automation
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class WaitUtility {

    private WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 15;

    /**
     * Constructor to initialize WaitUtility with WebDriver.
     *
     * @param driver WebDriver instance
     */
    public WaitUtility(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for an element to be visible.
     *
     * @param locator By locator of the element
     * @return WebElement once visible
     */
    public WebElement waitForElementToBeVisible(By locator) {
        return waitForElementToBeVisible(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Waits for an element to be visible with custom timeout.
     *
     * @param locator By locator of the element
     * @param timeoutInSeconds Custom timeout
     * @return WebElement once visible
     */
    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            LoggerManager.info("Element is visible: " + locator);
            return element;
        } catch (Exception e) {
            LoggerManager.error("Element not visible within " + timeoutInSeconds + " seconds: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param locator By locator of the element
     * @return WebElement once clickable
     */
    public WebElement waitForElementToBeClickable(By locator) {
        return waitForElementToBeClickable(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Waits for an element to be clickable with custom timeout.
     *
     * @param locator By locator of the element
     * @param timeoutInSeconds Custom timeout
     * @return WebElement once clickable
     */
    public WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            LoggerManager.info("Element is clickable: " + locator);
            return element;
        } catch (Exception e) {
            LoggerManager.error("Element not clickable within " + timeoutInSeconds + " seconds: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for an element to be present in DOM.
     *
     * @param locator By locator of the element
     * @return WebElement once present
     */
    public WebElement waitForElementToBePresent(By locator) {
        return waitForElementToBePresent(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Waits for an element to be present in DOM with custom timeout.
     *
     * @param locator By locator of the element
     * @param timeoutInSeconds Custom timeout
     * @return WebElement once present
     */
    public WebElement waitForElementToBePresent(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            LoggerManager.info("Element is present: " + locator);
            return element;
        } catch (Exception e) {
            LoggerManager.error("Element not present within " + timeoutInSeconds + " seconds: " + locator, e);
            throw e;
        }
    }

    /**
     * Waits for an element to be invisible.
     *
     * @param locator By locator of the element
     */
    public void waitForElementToBeInvisible(By locator) {
        waitForElementToBeInvisible(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Waits for an element to be invisible with custom timeout.
     *
     * @param locator By locator of the element
     * @param timeoutInSeconds Custom timeout
     */
    public void waitForElementToBeInvisible(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            LoggerManager.info("Element is invisible: " + locator);
        } catch (Exception e) {
            LoggerManager.error("Element still visible after " + timeoutInSeconds + " seconds: " + locator, e);
            throw e;
        }
    }
}
