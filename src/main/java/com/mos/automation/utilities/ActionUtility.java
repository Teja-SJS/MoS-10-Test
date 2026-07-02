package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * ActionUtility class provides common action operations using Selenium Actions class.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class ActionUtility {

    private WebDriver driver;
    private Actions actions;

    /**
     * Constructor to initialize ActionUtility with WebDriver.
     */
    public ActionUtility(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    /**
     * Performs hover action on an element.
     */
    public void hoverOnElement(WebElement element) {
        try {
            actions.moveToElement(element).perform();
            LoggerManager.info("Hovered on element: " + element);
        } catch (Exception e) {
            LoggerManager.error("Failed to hover on element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Performs double click action on an element.
     */
    public void doubleClick(WebElement element) {
        try {
            actions.doubleClick(element).perform();
            LoggerManager.info("Double clicked on element: " + element);
        } catch (Exception e) {
            LoggerManager.error("Failed to double click on element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Performs right click action on an element.
     */
    public void rightClick(WebElement element) {
        try {
            actions.contextClick(element).perform();
            LoggerManager.info("Right clicked on element: " + element);
        } catch (Exception e) {
            LoggerManager.error("Failed to right click on element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Performs drag and drop action.
     */
    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        try {
            actions.dragAndDrop(sourceElement, targetElement).perform();
            LoggerManager.info("Dragged and dropped element.");
        } catch (Exception e) {
            LoggerManager.error("Failed to drag and drop: " + e.getMessage(), e);
            throw e;
        }
    }
}
