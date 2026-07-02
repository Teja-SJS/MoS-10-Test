package com.mos.automation.pages;

import com.mos.automation.base.BasePage;
import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * GoogleSearchPage class is a Page Object for Google Search application.
 * This is a sample page object demonstrating the POM design pattern.
 *
 * Locators:
 * - Search input field
 * - Search button
 * - Search results
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class GoogleSearchPage extends BasePage {

    // Locators
    @FindBy(name = "q")
    private WebElement searchInputField;

    @FindBy(name = "btnK")
    private WebElement googleSearchButton;

    private By searchResults = By.xpath("//div[@id='search']//div[@class='g']");

    /**
     * Enters search text in search field.
     *
     * @param searchText Text to search
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
     *
     * @param searchText Text to search for
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

    /**
     * Gets number of search results.
     *
     * @return Count of search results
     */
    public int getSearchResultsCount() {
        try {
            int count = driver.findElements(searchResults).size();
            LoggerManager.info("Search results count: " + count);
            return count;
        } catch (Exception e) {
            LoggerManager.error("Failed to get search results count: " + e.getMessage(), e);
            return 0;
        }
    }
}
