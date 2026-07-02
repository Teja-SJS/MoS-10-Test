package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

/**
 * WindowUtility class provides methods to handle multiple windows/tabs.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class WindowUtility {

    private WebDriver driver;
    private String parentWindowHandle;

    /**
     * Constructor to initialize WindowUtility with WebDriver.
     */
    public WindowUtility(WebDriver driver) {
        this.driver = driver;
        this.parentWindowHandle = driver.getWindowHandle();
    }

    /**
     * Switches to child window.
     */
    public void switchToChildWindow() {
        try {
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(parentWindowHandle)) {
                    driver.switchTo().window(handle);
                    LoggerManager.info("Switched to child window.");
                    return;
                }
            }
        } catch (Exception e) {
            LoggerManager.error("Failed to switch to child window: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Switches to parent window.
     */
    public void switchToParentWindow() {
        try {
            driver.switchTo().window(parentWindowHandle);
            LoggerManager.info("Switched to parent window.");
        } catch (Exception e) {
            LoggerManager.error("Failed to switch to parent window: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Switches to window by title.
     */
    public void switchToWindowByTitle(String windowTitle) {
        try {
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                if (driver.getTitle().equals(windowTitle)) {
                    LoggerManager.info("Switched to window with title: " + windowTitle);
                    return;
                }
            }
            LoggerManager.error("Window with title " + windowTitle + " not found.");
        } catch (Exception e) {
            LoggerManager.error("Failed to switch to window by title: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Gets all window handles.
     */
    public List<String> getAllWindowHandles() {
        try {
            List<String> handles = new ArrayList<>(driver.getWindowHandles());
            LoggerManager.info("Retrieved all window handles. Count: " + handles.size());
            return handles;
        } catch (Exception e) {
            LoggerManager.error("Failed to get window handles: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Closes the current window.
     */
    public void closeCurrentWindow() {
        try {
            driver.close();
            LoggerManager.info("Current window closed.");
        } catch (Exception e) {
            LoggerManager.error("Failed to close current window: " + e.getMessage(), e);
            throw e;
        }
    }
}
