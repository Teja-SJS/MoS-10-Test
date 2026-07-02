package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * RobotUtility class provides keyboard and mouse simulation capabilities.
 * Useful for system-level interactions that Selenium cannot handle directly.
 *
 * Features:
 * - Keyboard key press
 * - Mouse movement
 * - Mouse click
 * - Type text using keyboard
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
     *
     * @param keyCode KeyEvent code for the key
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
     *
     * @param keyCode KeyEvent code for the key
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
     * Types text using keyboard simulation.
     *
     * @param text Text to type
     */
    public void typeText(String text) {
        try {
            for (char c : text.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (keyCode != KeyEvent.VK_UNDEFINED) {
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                }
            }
            LoggerManager.info("Text typed: " + text);
        } catch (Exception e) {
            LoggerManager.error("Failed to type text: " + e.getMessage(), e);
        }
    }

    /**
     * Moves mouse to specific coordinates.
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void moveMouse(int x, int y) {
        try {
            robot.mouseMove(x, y);
            LoggerManager.debug("Mouse moved to: (" + x + ", " + y + ")");
        } catch (Exception e) {
            LoggerManager.error("Failed to move mouse: " + e.getMessage(), e);
        }
    }

    /**
     * Clicks mouse button.
     *
     * @param button Button to click (1 = left, 2 = middle, 3 = right)
     */
    public void clickMouse(int button) {
        try {
            robot.mousePress(button);
            robot.mouseRelease(button);
            LoggerManager.debug("Mouse clicked: " + button);
        } catch (Exception e) {
            LoggerManager.error("Failed to click mouse: " + e.getMessage(), e);
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
