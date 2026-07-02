package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * FrameUtility class provides methods to handle frames and iframes.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class FrameUtility {

    private WebDriver driver;

    /**
     * Constructor to initialize FrameUtility with WebDriver.
     */
    public FrameUtility(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Switches to frame by index.
     */
    public void switchToFrameByIndex(int frameIndex) {
        try {
            driver.switchTo().frame(frameIndex);
            LoggerManager.info("Switched to frame by index: " + frameIndex);
        } catch (Exception e) {
            LoggerManager.error("Failed to switch to frame by index: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Switches to frame by name or id.
     */
    public void switchToFrameByNameOrId(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
            LoggerManager.info("Switched to frame by name/id: " + nameOrId);
        } catch (Exception e) {
            LoggerManager.error("Failed to switch to frame by name/id: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Switches to frame by locator.
     */
    public void switchToFrameByLocator(By locator) {
        try {
            WebElement frameElement = driver.findElement(locator);
            driver.switchTo().frame(frameElement);
            LoggerManager.info("Switched to frame by locator: " + locator);
        } catch (Exception e) {
            LoggerManager.error("Failed to switch to frame by locator: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Switches to default content (out of frame).
     */
    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            LoggerManager.info("Switched to default content.");
        } catch (Exception e) {
            LoggerManager.error("Failed to switch to default content: " + e.getMessage(), e);
            throw e;
        }
    }
}
