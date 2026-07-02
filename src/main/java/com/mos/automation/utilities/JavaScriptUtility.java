package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * JavaScriptUtility class provides methods to execute JavaScript on the webpage.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class JavaScriptUtility {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    /**
     * Constructor to initialize JavaScriptUtility with WebDriver.
     */
    public JavaScriptUtility(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    /**
     * Executes JavaScript code.
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
