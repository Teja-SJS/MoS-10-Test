package com.mos.automation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ExtentReportManager class manages Extent Reports functionality.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final String REPORT_DIR = "reports/";

    /**
     * Initializes ExtentReports.
     */
    public static void initializeReports() {
        if (extentReports == null) {
            try {
                createReportDirectory();
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                        REPORT_DIR + "ExtentReport_" + generateReportTimestamp() + ".html");
                sparkReporter.config().setReportName("Automation Test Report");
                sparkReporter.config().setDocumentTitle("Test Execution Report");

                extentReports = new ExtentReports();
                extentReports.attachReporter(sparkReporter);
                LoggerManager.info("ExtentReports initialized.");
            } catch (Exception e) {
                LoggerManager.error("Failed to initialize ExtentReports: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Creates a test case in the report.
     */
    public static void createTest(String testName, String description) {
        try {
            if (extentReports == null) {
                initializeReports();
            }
            ExtentTest test = extentReports.createTest(testName, description);
            extentTest.set(test);
            LoggerManager.info("Test case created in report: " + testName);
        } catch (Exception e) {
            LoggerManager.error("Failed to create test in report: " + e.getMessage(), e);
        }
    }

    /**
     * Logs pass status to report.
     */
    public static void logPass(String message) {
        try {
            if (extentTest.get() != null) {
                extentTest.get().pass(message);
                LoggerManager.info("Test passed: " + message);
            }
        } catch (Exception e) {
            LoggerManager.error("Failed to log pass status: " + e.getMessage(), e);
        }
    }

    /**
     * Logs fail status to report.
     */
    public static void logFail(String message) {
        try {
            if (extentTest.get() != null) {
                extentTest.get().fail(message);
                LoggerManager.error("Test failed: " + message);
            }
        } catch (Exception e) {
            LoggerManager.error("Failed to log fail status: " + e.getMessage(), e);
        }
    }

    /**
     * Captures screenshot and attaches to report.
     */
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            File srcFile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(
                    org.openqa.selenium.OutputType.FILE);
            String screenshotPath = REPORT_DIR + screenshotName + ".png";
            FileHandler.copy(srcFile, new File(screenshotPath));
            if (extentTest.get() != null) {
                extentTest.get().addScreenCaptureFromPath(screenshotPath);
            }
            LoggerManager.info("Screenshot attached to report: " + screenshotName);
        } catch (Exception e) {
            LoggerManager.error("Failed to capture screenshot: " + e.getMessage(), e);
        }
    }

    /**
     * Finalizes and flushes the report.
     */
    public static void flushReports() {
        try {
            if (extentReports != null) {
                extentReports.flush();
                LoggerManager.info("ExtentReports flushed successfully.");
            }
        } catch (Exception e) {
            LoggerManager.error("Failed to flush reports: " + e.getMessage(), e);
        }
    }

    /**
     * Generates unique timestamp for report file.
     */
    private static String generateReportTimestamp() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return dateTime.format(formatter);
    }

    /**
     * Creates report directory if it doesn't exist.
     */
    private static void createReportDirectory() {
        try {
            File reportDir = new File(REPORT_DIR);
            if (!reportDir.exists()) {
                reportDir.mkdirs();
                LoggerManager.info("Report directory created.");
            }
        } catch (Exception e) {
            LoggerManager.error("Failed to create report directory: " + e.getMessage(), e);
        }
    }
}
