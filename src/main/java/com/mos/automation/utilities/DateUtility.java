package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * DateUtility class provides date and time manipulation utilities.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class DateUtility {

    /**
     * Gets current date as LocalDate.
     */
    public static LocalDate getCurrentDate() {
        LocalDate today = LocalDate.now();
        LoggerManager.debug("Current date: " + today);
        return today;
    }

    /**
     * Gets current date and time as LocalDateTime.
     */
    public static LocalDateTime getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LoggerManager.debug("Current date and time: " + now);
        return now;
    }

    /**
     * Formats date to specific pattern.
     */
    public static String formatDate(LocalDate date, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            String formattedDate = date.format(formatter);
            LoggerManager.debug("Date formatted: " + formattedDate);
            return formattedDate;
        } catch (Exception e) {
            LoggerManager.error("Failed to format date: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Adds days to a date.
     */
    public static LocalDate addDays(LocalDate date, long days) {
        LocalDate newDate = date.plus(days, ChronoUnit.DAYS);
        LoggerManager.debug("Added " + days + " days to date: " + newDate);
        return newDate;
    }

    /**
     * Subtracts days from a date.
     */
    public static LocalDate subtractDays(LocalDate date, long days) {
        LocalDate newDate = date.minus(days, ChronoUnit.DAYS);
        LoggerManager.debug("Subtracted " + days + " days from date: " + newDate);
        return newDate;
    }

    /**
     * Calculates days between two dates.
     */
    public static long daysBetween(LocalDate date1, LocalDate date2) {
        long days = ChronoUnit.DAYS.between(date1, date2);
        LoggerManager.debug("Days between " + date1 + " and " + date2 + ": " + days);
        return days;
    }
}
