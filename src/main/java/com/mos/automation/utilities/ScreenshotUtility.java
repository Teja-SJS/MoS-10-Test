package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ScreenshotUtility class provides functionality to capture screenshots.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class ScreenshotUtility {

    private WebDriver driver;
    private static final String SCREENSHOT_DIR = "screenshots/";

    /**
     * Constructor to initialize ScreenshotUtility with WebDriver.
     */
    public ScreenshotUtility(WebDriver driver) {
        this.driver = driver;
        createScreenshotDirectory();
    }

    /**
     * Captures screenshot and saves to file system.
     */
    public String captureScreenshot(String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = generateScreenshotFileName(testName);
            String filePath = SCREENSHOT_DIR + fileName;
            Files.copy(screenshot.toPath(), Paths.get(filePath));
            LoggerManager.info("Screenshot captured: " + filePath);
            return filePath;
        } catch (Exception e) {
            LoggerManager.error("Failed to capture screenshot: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Captures screenshot as Base64 string.
     */
    public String captureScreenshotAsBase64() {
        try {
            String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            LoggerManager.info("Screenshot captured as Base64.");
            return screenshot;
        } catch (Exception e) {
            LoggerManager.error("Failed to capture screenshot as Base64: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Generates unique screenshot filename with timestamp.
     */
    private String generateScreenshotFileName(String testName) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return testName + "_" + dateTime.format(formatter) + ".png";
    }

    /**
     * Creates screenshot directory if it doesn't exist.
     */
    private void createScreenshotDirectory() {
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
        } catch (Exception e) {
            LoggerManager.error("Failed to create screenshot directory: " + e.getMessage(), e);
        }
    }
}
