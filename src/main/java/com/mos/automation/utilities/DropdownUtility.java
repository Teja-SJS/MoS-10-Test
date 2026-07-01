package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * DropdownUtility class provides methods to interact with dropdown/select elements.
 *
 * Features:
 * - Select by visible text
 * - Select by value
 * - Select by index
 * - Get all options
 * - Get selected option
 * - Deselect options (for multiple select)
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class DropdownUtility {

    private Select select;

    /**
     * Constructor to initialize DropdownUtility with a dropdown element.
     *
     * @param dropdownElement WebElement representing the select/dropdown
     */
    public DropdownUtility(WebElement dropdownElement) {
        try {
            this.select = new Select(dropdownElement);
            LoggerManager.info("DropdownUtility initialized.");
        } catch (Exception e) {
            LoggerManager.error("Failed to initialize dropdown: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Selects option by visible text.
     *
     * @param visibleText Visible text of the option
     */
    public void selectByVisibleText(String visibleText) {
        try {
            select.selectByVisibleText(visibleText);
            LoggerManager.info("Selected option by visible text: " + visibleText);
        } catch (Exception e) {
            LoggerManager.error("Failed to select option by visible text: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Selects option by value.
     *
     * @param value Value attribute of the option
     */
    public void selectByValue(String value) {
        try {
            select.selectByValue(value);
            LoggerManager.info("Selected option by value: " + value);
        } catch (Exception e) {
            LoggerManager.error("Failed to select option by value: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Selects option by index.
     *
     * @param index Index of the option
     */
    public void selectByIndex(int index) {
        try {
            select.selectByIndex(index);
            LoggerManager.info("Selected option by index: " + index);
        } catch (Exception e) {
            LoggerManager.error("Failed to select option by index: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Gets the currently selected option.
     *
     * @return Selected WebElement
     */
    public WebElement getSelectedOption() {
        try {
            WebElement selectedOption = select.getFirstSelectedOption();
            LoggerManager.info("Retrieved selected option: " + selectedOption.getText());
            return selectedOption;
        } catch (Exception e) {
            LoggerManager.error("Failed to get selected option: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Checks if the dropdown supports multiple selection.
     *
     * @return true if multiple, false otherwise
     */
    public boolean isMultiple() {
        return select.isMultiple();
    }
}
