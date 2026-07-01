package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * AlertUtility class provides methods to handle JavaScript alerts, confirmations, and prompts.
 *
 * Features:
 * - Wait for alert presence
 * - Accept alert
 * - Dismiss alert
 * - Send text to alert
 * - Get alert text
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class AlertUtility {

    private WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10;

    /**
     * Constructor to initialize AlertUtility with WebDriver.
     *
     * @param driver WebDriver instance
     */
    public AlertUtility(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for alert to be present.
     *
     * @return Alert object
     */
    public Alert waitForAlert() {
        return waitForAlert(DEFAULT_TIMEOUT);
    }

    /**
     * Waits for alert to be present with custom timeout.
     *
     * @param timeoutInSeconds Custom timeout
     * @return Alert object
     */
    public Alert waitForAlert(int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            LoggerManager.info("Alert is present.");
            return alert;
        } catch (Exception e) {
            LoggerManager.error("Alert not found within " + timeoutInSeconds + " seconds.", e);
            throw e;
        }
    }

    /**
     * Accepts an alert.
     */
    public void acceptAlert() {
        try {
            Alert alert = waitForAlert();
            alert.accept();
            LoggerManager.info("Alert accepted.");
        } catch (Exception e) {
            LoggerManager.error("Failed to accept alert: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Dismisses an alert.
     */
    public void dismissAlert() {
        try {
            Alert alert = waitForAlert();
            alert.dismiss();
            LoggerManager.info("Alert dismissed.");
        } catch (Exception e) {
            LoggerManager.error("Failed to dismiss alert: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Gets alert text.
     *
     * @return Alert message text
     */
    public String getAlertText() {
        try {
            Alert alert = waitForAlert();
            String alertText = alert.getText();
            LoggerManager.info("Alert text: " + alertText);
            return alertText;
        } catch (Exception e) {
            LoggerManager.error("Failed to get alert text: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Sends text to prompt alert.
     *
     * @param text Text to send
     */
    public void sendTextToAlert(String text) {
        try {
            Alert alert = waitForAlert();
            alert.sendKeys(text);
            LoggerManager.info("Text sent to alert: " + text);
        } catch (Exception e) {
            LoggerManager.error("Failed to send text to alert: " + e.getMessage(), e);
            throw e;
        }
    }
}
