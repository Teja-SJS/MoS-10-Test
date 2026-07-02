package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.testng.asserts.SoftAssert;

/**
 * SoftAssertionUtility class provides soft assertion capabilities.
 * Soft assertions allow test execution to continue even after assertion failures.
 * All failures are reported at the end.
 *
 * Features:
 * - Soft assertions for test validation
 * - Assertion failure reporting
 * - Multiple assertions in single test
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
     *
     * @param condition Condition to verify
     * @param message Message on failure
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
     *
     * @param condition Condition to verify
     * @param message Message on failure
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
     *
     * @param actual Actual value
     * @param expected Expected value
     * @param message Message on failure
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
     * Asserts that two objects are not equal (soft assertion).
     *
     * @param actual Actual value
     * @param expected Expected value
     * @param message Message on failure
     */
    public void assertNotEquals(Object actual, Object expected, String message) {
        try {
            softAssert.assertNotEquals(actual, expected, message);
            LoggerManager.info("Soft assertion - assertNotEquals: " + message);
        } catch (Exception e) {
            LoggerManager.error("Error in soft assertion: " + e.getMessage(), e);
        }
    }

    /**
     * Asserts that object is null (soft assertion).
     *
     * @param object Object to check
     * @param message Message on failure
     */
    public void assertNull(Object object, String message) {
        try {
            softAssert.assertNull(object, message);
            LoggerManager.info("Soft assertion - assertNull: " + message);
        } catch (Exception e) {
            LoggerManager.error("Error in soft assertion: " + e.getMessage(), e);
        }
    }

    /**
     * Asserts that object is not null (soft assertion).
     *
     * @param object Object to check
     * @param message Message on failure
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
     * Call this at the end of test to verify all soft assertions.
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
