package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * RobotUtility class provides keyboard and mouse simulation capabilities.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class RobotUtility {

    private Robot robot;

    /**
     * Constructor to initialize RobotUtility.
     */
    public RobotUtility() {
        try {
            this.robot = new Robot();
            LoggerManager.info("RobotUtility initialized.");
        } catch (AWTException e) {
            LoggerManager.error("Failed to initialize Robot: " + e.getMessage(), e);
            throw new RuntimeException("Robot initialization failed", e);
        }
    }

    /**
     * Presses a key.
     */
    public void pressKey(int keyCode) {
        try {
            robot.keyPress(keyCode);
            LoggerManager.debug("Key pressed: " + keyCode);
        } catch (Exception e) {
            LoggerManager.error("Failed to press key: " + e.getMessage(), e);
        }
    }

    /**
     * Releases a key.
     */
    public void releaseKey(int keyCode) {
        try {
            robot.keyRelease(keyCode);
            LoggerManager.debug("Key released: " + keyCode);
        } catch (Exception e) {
            LoggerManager.error("Failed to release key: " + e.getMessage(), e);
        }
    }

    /**
     * Presses Enter key.
     */
    public void pressEnter() {
        pressKey(KeyEvent.VK_ENTER);
        releaseKey(KeyEvent.VK_ENTER);
        LoggerManager.info("Enter key pressed.");
    }

    /**
     * Presses Tab key.
     */
    public void pressTab() {
        pressKey(KeyEvent.VK_TAB);
        releaseKey(KeyEvent.VK_TAB);
        LoggerManager.info("Tab key pressed.");
    }
}
