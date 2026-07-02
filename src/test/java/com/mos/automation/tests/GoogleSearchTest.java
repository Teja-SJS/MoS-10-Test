package com.mos.automation.tests;

import com.mos.automation.base.BaseTest;
import com.mos.automation.pages.GoogleSearchPage;
import com.mos.automation.utilities.SoftAssertionUtility;
import com.mos.automation.logger.LoggerManager;
import org.testng.annotations.Test;

/**
 * GoogleSearchTest class is a sample test class.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class GoogleSearchTest extends BaseTest {

    /**
     * Test to verify Google Search functionality.
     */
    @Test(description = "Test Google Search Functionality")
    public void testGoogleSearch() {
        try {
            driver.navigate().to("https://www.google.com");
            LoggerManager.info("Navigated to Google home page.");

            GoogleSearchPage searchPage = new GoogleSearchPage();
            searchPage.search("Selenium WebDriver");

            SoftAssertionUtility softAssert = new SoftAssertionUtility();
            softAssert.assertNotNull(searchPage, "Search page object should not be null.");
            softAssert.assertAll();

            LoggerManager.info("Test passed: Google Search test completed successfully.");
        } catch (Exception e) {
            LoggerManager.error("Test failed: " + e.getMessage(), e);
            throw e;
        }
    }
}
