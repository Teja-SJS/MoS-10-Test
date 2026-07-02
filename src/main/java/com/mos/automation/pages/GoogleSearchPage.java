package com.mos.automation.pages;

import com.mos.automation.base.BasePage;
import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * GoogleSearchPage class is a Page Object for Google Search application.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class GoogleSearchPage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchInputField;

    private By searchResults = By.xpath("//div[@id='search']//div[@class='g']");

    /**
     * Enters search text in search field.
     */
    public void enterSearchText(String searchText) {
        try {
            WebElement element = waitUtility.waitForElementToBeVisible(By.name("q"));
            element.clear();
            element.sendKeys(searchText);
            LoggerManager.info("Entered search text: " + searchText);
        } catch (Exception e) {
            LoggerManager.error("Failed to enter search text: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Clicks on search button.
     */
    public void clickSearchButton() {
        try {
            WebElement button = waitUtility.waitForElementToBeClickable(By.name("btnK"));
            jsUtility.clickElementUsingJS(button);
            LoggerManager.info("Clicked on search button.");
        } catch (Exception e) {
            LoggerManager.error("Failed to click search button: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Performs search with given text.
     */
    public void search(String searchText) {
        try {
            enterSearchText(searchText);
            clickSearchButton();
            LoggerManager.info("Search performed for: " + searchText);
        } catch (Exception e) {
            LoggerManager.error("Search operation failed: " + e.getMessage(), e);
            throw e;
        }
    }
}
