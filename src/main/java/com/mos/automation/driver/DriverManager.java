package com.mos.automation.driver;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.WebDriver;

/**
 * DriverManager class manages the WebDriver instance using ThreadLocal.
 * This ensures thread safety for parallel test execution.
 *
 * Features:
 * - ThreadLocal driver management
 * - Thread-safe driver operations
 * - Support for parallel execution
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Sets the WebDriver instance in ThreadLocal.
     *
     * @param driver WebDriver instance to be set
     */
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
        LoggerManager.debug("WebDriver set in ThreadLocal for thread: " + Thread.currentThread().getId());
    }

    /**
     * Gets the WebDriver instance from ThreadLocal.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call setDriver() first.");
        }
        return driver;
    }

    /**
     * Quits the WebDriver and removes it from ThreadLocal.
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            LoggerManager.info("WebDriver quit and removed from ThreadLocal.");
        }
    }
}
