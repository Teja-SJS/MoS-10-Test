package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * DateUtility class provides date and time manipulation utilities.
 * Useful for date-based test scenarios.
 *
 * Features:
 * - Get current date/time
 * - Date formatting
 * - Date comparison
 * - Add/subtract days, months, years
 * - Calculate date difference
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class DateUtility {

    /**
     * Gets current date as LocalDate.
     *
     * @return Current date
     */
    public static LocalDate getCurrentDate() {
        LocalDate today = LocalDate.now();
        LoggerManager.debug("Current date: " + today);
        return today;
    }

    /**
     * Gets current date and time as LocalDateTime.
     *
     * @return Current date and time
     */
    public static LocalDateTime getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LoggerManager.debug("Current date and time: " + now);
        return now;
    }

    /**
     * Formats date to specific pattern.
     *
     * @param date Date to format
     * @param pattern Date format pattern
     * @return Formatted date string
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
     *
     * @param date Base date
     * @param days Number of days to add
     * @return New date
     */
    public static LocalDate addDays(LocalDate date, long days) {
        LocalDate newDate = date.plus(days, ChronoUnit.DAYS);
        LoggerManager.debug("Added " + days + " days to date: " + newDate);
        return newDate;
    }

    /**
     * Subtracts days from a date.
     *
     * @param date Base date
     * @param days Number of days to subtract
     * @return New date
     */
    public static LocalDate subtractDays(LocalDate date, long days) {
        LocalDate newDate = date.minus(days, ChronoUnit.DAYS);
        LoggerManager.debug("Subtracted " + days + " days from date: " + newDate);
        return newDate;
    }

    /**
     * Calculates days between two dates.
     *
     * @param date1 First date
     * @param date2 Second date
     * @return Number of days between dates
     */
    public static long daysBetween(LocalDate date1, LocalDate date2) {
        long days = ChronoUnit.DAYS.between(date1, date2);
        LoggerManager.debug("Days between " + date1 + " and " + date2 + ": " + days);
        return days;
    }

    /**
     * Checks if date is future.
     *
     * @param date Date to check
     * @return true if future, false otherwise
     */
    public static boolean isFutureDate(LocalDate date) {
        boolean isFuture = date.isAfter(LocalDate.now());
        LoggerManager.debug("Is future date: " + isFuture);
        return isFuture;
    }

    /**
     * Checks if date is past.
     *
     * @param date Date to check
     * @return true if past, false otherwise
     */
    public static boolean isPastDate(LocalDate date) {
        boolean isPast = date.isBefore(LocalDate.now());
        LoggerManager.debug("Is past date: " + isPast);
        return isPast;
    }
}
