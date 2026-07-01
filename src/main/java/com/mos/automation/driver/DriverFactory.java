package com.mos.automation.driver;

import com.mos.automation.logger.LoggerManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * DriverFactory class is responsible for initializing WebDriver instances
 * for different browsers (Chrome, Firefox, Edge).
 *
 * Features:
 * - Automatic driver setup using WebDriverManager
 * - Browser-specific options configuration
 * - Support for headless mode
 * - Centralized driver initialization
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class DriverFactory {

    /**
     * Initializes WebDriver based on the browser name.
     *
     * @param browserName Name of the browser (Chrome, Firefox, Edge)
     * @return WebDriver instance
     */
    public static WebDriver initializeBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = initializeChrome();
                break;
            case "firefox":
                driver = initializeFirefox();
                break;
            case "edge":
                driver = initializeEdge();
                break;
            default:
                LoggerManager.error("Unsupported browser: " + browserName);
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        return driver;
    }

    /**
     * Initializes Chrome WebDriver.
     *
     * @return Chrome WebDriver instance
     */
    private static WebDriver initializeChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        // Uncomment for headless mode
        // options.addArguments("--headless");
        LoggerManager.info("Chrome WebDriver initialized.");
        return new ChromeDriver(options);
    }

    /**
     * Initializes Firefox WebDriver.
     *
     * @return Firefox WebDriver instance
     */
    private static WebDriver initializeFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        // Uncomment for headless mode
        // options.addArguments("--headless");
        LoggerManager.info("Firefox WebDriver initialized.");
        return new FirefoxDriver(options);
    }

    /**
     * Initializes Edge WebDriver.
     *
     * @return Edge WebDriver instance
     */
    private static WebDriver initializeEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        // Uncomment for headless mode
        // options.addArguments("--headless");
        LoggerManager.info("Edge WebDriver initialized.");
        return new EdgeDriver(options);
    }
}
