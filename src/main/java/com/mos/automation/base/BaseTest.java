package com.mos.automation.base;

import com.mos.automation.config.ConfigReader;
import com.mos.automation.driver.DriverFactory;
import com.mos.automation.driver.DriverManager;
import com.mos.automation.logger.LoggerManager;
import com.mos.automation.reports.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * BaseTest class serves as the parent class for all test classes.
 * It handles WebDriver initialization, teardown, and reporting setup.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader configReader;

    /**
     * BeforeClass method to initialize ExtentReports.
     * Executed once before all tests in the class.
     */
    @BeforeClass
    public void setupReports() {
        ExtentReportManager.initializeReports();
        LoggerManager.info("ExtentReports initialized for the test class.");
    }

    /**
     * BeforeMethod to initialize WebDriver before each test.
     * Executed before every test method.
     */
    @BeforeMethod
    public void setUp() {
        try {
            configReader = new ConfigReader();
            String browser = configReader.getBrowserName();
            driver = DriverFactory.initializeBrowser(browser);
            DriverManager.setDriver(driver);
            driver.manage().window().maximize();
            LoggerManager.info("Browser " + browser + " initialized successfully.");
        } catch (Exception e) {
            LoggerManager.error("Failed to initialize browser: " + e.getMessage(), e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    /**
     * AfterMethod to close WebDriver after each test.
     * Also captures screenshots on test failure.
     *
     * @param result ITestResult containing test execution details
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                LoggerManager.error("Test Failed: " + result.getName());
                ExtentReportManager.captureScreenshot(driver, result.getName());
            }
            DriverManager.quitDriver();
            LoggerManager.info("Browser closed successfully.");
        } catch (Exception e) {
            LoggerManager.error("Error during teardown: " + e.getMessage(), e);
        }
    }

    /**
     * AfterClass method to finalize ExtentReports.
     * Executed once after all tests in the class.
     */
    @AfterClass
    public void tearDownReports() {
        ExtentReportManager.flushReports();
        LoggerManager.info("ExtentReports finalized.");
    }
}
