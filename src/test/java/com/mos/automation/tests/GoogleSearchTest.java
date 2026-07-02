package com.mos.automation.tests;

import com.mos.automation.base.BaseTest;
import com.mos.automation.pages.GoogleSearchPage;
import com.mos.automation.utilities.SoftAssertionUtility;
import org.testng.annotations.Test;

/**
 * GoogleSearchTest class is a sample test class.
 * Demonstrates how to write test cases using the framework.
 *
 * Test Cases:
 * 1. Test Search Functionality
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class GoogleSearchTest extends BaseTest {

    /**
     * Test to verify Google Search functionality.
     * 
     * Steps:
     * 1. Open Google home page
     * 2. Enter search text
     * 3. Click search button
     * 4. Verify search results are displayed
     */
    @Test(description = "Test Google Search Functionality")
    public void testGoogleSearch() {
        try {
            // Navigate to Google home page
            driver.navigate().to("https://www.google.com");
            LoggerManager.info("Navigated to Google home page.");

            // Initialize page object
            GoogleSearchPage searchPage = new GoogleSearchPage();

            // Perform search
            searchPage.search("Selenium WebDriver");

            // Verify search results
            SoftAssertionUtility softAssert = new SoftAssertionUtility();
            int resultsCount = searchPage.getSearchResultsCount();
            softAssert.assertTrue(resultsCount > 0, "Search results should be displayed.");
            softAssert.assertNotNull(searchPage, "Search page object should not be null.");

            // Assert all soft assertions
            softAssert.assertAll();

            LoggerManager.info("Test passed: Google Search test completed successfully.");
        } catch (Exception e) {
            LoggerManager.error("Test failed: " + e.getMessage(), e);
            throw e;
        }
    }
}
