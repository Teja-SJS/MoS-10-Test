package com.mos.automation.base;

import com.mos.automation.driver.DriverManager;
import com.mos.automation.logger.LoggerManager;
import com.mos.automation.utilities.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * BasePage class contains common utilities and methods for all page classes.
 * All page objects should extend this class to access common functionalities.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class BasePage {

    protected WebDriver driver;
    protected WaitUtility waitUtility;
    protected ActionUtility actionUtility;
    protected JavaScriptUtility jsUtility;
    protected AlertUtility alertUtility;
    protected DropdownUtility dropdownUtility;
    protected WindowUtility windowUtility;
    protected FrameUtility frameUtility;
    protected ScreenshotUtility screenshotUtility;

    /**
     * Constructor to initialize WebDriver and all utility classes.
     */
    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.waitUtility = new WaitUtility(driver);
        this.actionUtility = new ActionUtility(driver);
        this.jsUtility = new JavaScriptUtility(driver);
        this.alertUtility = new AlertUtility(driver);
        this.dropdownUtility = new DropdownUtility(null);
        this.windowUtility = new WindowUtility(driver);
        this.frameUtility = new FrameUtility(driver);
        this.screenshotUtility = new ScreenshotUtility(driver);
        LoggerManager.info("BasePage initialized with all utilities.");
    }
}
