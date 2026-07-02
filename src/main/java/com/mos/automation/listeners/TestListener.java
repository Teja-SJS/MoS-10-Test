package com.mos.automation.listeners;

import com.mos.automation.logger.LoggerManager;
import com.mos.automation.reports.ExtentReportManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener class implements TestNG ITestListener interface.
 * Provides test lifecycle event handling and reporting.
 *
 * Features:
 * - Test start logging
 * - Test success handling
 * - Test failure handling
 * - Test skip handling
 * - Test finish handling
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class TestListener implements ITestListener {

    /**
     * Called when test starts.
     *
     * @param result ITestResult containing test details
     */
    @Override
    public void onTestStart(ITestResult result) {
        LoggerManager.info("===== Test Started: " + result.getName() + " =====");
        ExtentReportManager.createTest(result.getName(), result.getMethod().getDescription());
    }

    /**
     * Called when test succeeds.
     *
     * @param result ITestResult containing test details
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerManager.info("===== Test Passed: " + result.getName() + " =====");
        ExtentReportManager.logPass("Test passed successfully.");
    }

    /**
     * Called when test fails.
     *
     * @param result ITestResult containing test details
     */
    @Override
    public void onTestFailure(ITestResult result) {
        LoggerManager.error("===== Test Failed: " + result.getName() + " =====");
        LoggerManager.error("Failure Reason: " + result.getThrowable().getMessage());
        ExtentReportManager.logFail("Test failed: " + result.getThrowable().getMessage());
    }

    /**
     * Called when test is skipped.
     *
     * @param result ITestResult containing test details
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerManager.warn("===== Test Skipped: " + result.getName() + " =====");
    }

    /**
     * Called when test finishes (after success, failure, or skip).
     *
     * @param result ITestResult containing test details
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LoggerManager.warn("Test failed but within success percentage: " + result.getName());
    }
}
