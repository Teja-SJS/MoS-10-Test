package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.testng.asserts.SoftAssert;

/**
 * SoftAssertionUtility class provides soft assertion capabilities.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class SoftAssertionUtility {

    private SoftAssert softAssert;

    /**
     * Constructor to initialize SoftAssertionUtility.
     */
    public SoftAssertionUtility() {
        this.softAssert = new SoftAssert();
        LoggerManager.info("SoftAssertionUtility initialized.");
    }

    /**
     * Asserts that condition is true (soft assertion).
     */
    public void assertTrue(boolean condition, String message) {
        try {
            softAssert.assertTrue(condition, message);
            if (condition) {
                LoggerManager.info("Soft assertion passed: " + message);
            } else {
                LoggerManager.warn("Soft assertion failed: " + message);
            }
        } catch (Exception e) {
            LoggerManager.error("Error in soft assertion: " + e.getMessage(), e);
        }
    }

    /**
     * Asserts that condition is false (soft assertion).
     */
    public void assertFalse(boolean condition, String message) {
        try {
            softAssert.assertFalse(condition, message);
            if (!condition) {
                LoggerManager.info("Soft assertion passed: " + message);
            } else {
                LoggerManager.warn("Soft assertion failed: " + message);
            }
        } catch (Exception e) {
            LoggerManager.error("Error in soft assertion: " + e.getMessage(), e);
        }
    }

    /**
     * Asserts that two objects are equal (soft assertion).
     */
    public void assertEquals(Object actual, Object expected, String message) {
        try {
            softAssert.assertEquals(actual, expected, message);
            LoggerManager.info("Soft assertion - assertEquals: " + message);
        } catch (Exception e) {
            LoggerManager.error("Error in soft assertion: " + e.getMessage(), e);
        }
    }

    /**
     * Asserts that object is not null (soft assertion).
     */
    public void assertNotNull(Object object, String message) {
        try {
            softAssert.assertNotNull(object, message);
            LoggerManager.info("Soft assertion - assertNotNull: " + message);
        } catch (Exception e) {
            LoggerManager.error("Error in soft assertion: " + e.getMessage(), e);
        }
    }

    /**
     * Asserts all accumulated assertions.
     */
    public void assertAll() {
        try {
            softAssert.assertAll();
            LoggerManager.info("All soft assertions verified.");
        } catch (AssertionError e) {
            LoggerManager.error("Soft assertion failure: " + e.getMessage(), e);
            throw e;
        }
    }
}
