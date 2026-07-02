package com.mos.automation.listeners;

import com.mos.automation.logger.LoggerManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * RetryAnalyzer class implements TestNG IRetryAnalyzer interface.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    /**
     * Determines if test should be retried.
     */
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            LoggerManager.warn("Retrying test: " + result.getName() + " - Retry attempt: " + retryCount);
            return true;
        }
        return false;
    }
}
