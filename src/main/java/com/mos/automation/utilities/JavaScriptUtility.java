package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * JavaScriptUtility class provides methods to execute JavaScript on the webpage.
 * Useful for handling dynamic elements and executing custom scripts.
 *
 * Features:
 * - Execute JavaScript code
 * - Scroll to element
 * - Scroll to position
 * - Set element value directly
 * - Click element using JavaScript
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class JavaScriptUtility {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    /**
     * Constructor to initialize JavaScriptUtility with WebDriver.
     *
     * @param driver WebDriver instance
     */
    public JavaScriptUtility(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    /**
     * Executes JavaScript code.
     *
     * @param script JavaScript code to execute
     * @return Result of JavaScript execution
     */
    public Object executeScript(String script) {
        try {
            Object result = jsExecutor.executeScript(script);
            LoggerManager.debug("JavaScript executed: " + script);
            return result;
        } catch (Exception e) {
            LoggerManager.error("Failed to execute JavaScript: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Scrolls to a specific element on the page.
     *
     * @param element WebElement to scroll to
     */
    public void scrollToElement(WebElement element) {
        try {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            LoggerManager.info("Scrolled to element: " + element);
        } catch (Exception e) {
            LoggerManager.error("Failed to scroll to element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Scrolls to a specific position on the page.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     */
    public void scrollToPosition(int x, int y) {
        try {
            jsExecutor.executeScript("window.scrollTo(" + x + ", " + y + ");");
            LoggerManager.info("Scrolled to position: (" + x + ", " + y + ")");
        } catch (Exception e) {
            LoggerManager.error("Failed to scroll to position: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Clicks an element using JavaScript.
     *
     * @param element WebElement to click
     */
    public void clickElementUsingJS(WebElement element) {
        try {
            jsExecutor.executeScript("arguments[0].click();", element);
            LoggerManager.info("Clicked element using JavaScript: " + element);
        } catch (Exception e) {
            LoggerManager.error("Failed to click element using JavaScript: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Sets value to an input element using JavaScript.
     *
     * @param element WebElement to set value
     * @param value Value to set
     */
    public void setElementValue(WebElement element, String value) {
        try {
            jsExecutor.executeScript("arguments[0].value = '" + value + "';", element);
            LoggerManager.info("Set value to element using JavaScript: " + value);
        } catch (Exception e) {
            LoggerManager.error("Failed to set element value: " + e.getMessage(), e);
            throw e;
        }
    }
}
