package com.mos.automation.listeners;

import com.mos.automation.logger.LoggerManager;
import com.mos.automation.reports.ExtentReportManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener class implements TestNG ITestListener interface.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class TestListener implements ITestListener {

    /**
     * Called when test starts.
     */
    @Override
    public void onTestStart(ITestResult result) {
        LoggerManager.info("===== Test Started: " + result.getName() + " =====");
        ExtentReportManager.createTest(result.getName(), result.getMethod().getDescription());
    }

    /**
     * Called when test succeeds.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerManager.info("===== Test Passed: " + result.getName() + " =====");
        ExtentReportManager.logPass("Test passed successfully.");
    }

    /**
     * Called when test fails.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        LoggerManager.error("===== Test Failed: " + result.getName() + " =====");
        LoggerManager.error("Failure Reason: " + result.getThrowable().getMessage());
        ExtentReportManager.logFail("Test failed: " + result.getThrowable().getMessage());
    }

    /**
     * Called when test is skipped.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerManager.warn("===== Test Skipped: " + result.getName() + " =====");
    }
}
