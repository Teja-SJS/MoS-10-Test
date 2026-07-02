package com.mos.automation.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LoggerManager class provides centralized logging using Log4j2.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class LoggerManager {

    private static final Logger logger = LogManager.getLogger(LoggerManager.class);

    /**
     * Logs info level message.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs error level message with exception.
     */
    public static void error(String message, Exception exception) {
        logger.error(message, exception);
    }

    /**
     * Logs error level message.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs debug level message.
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Logs warning level message.
     */
    public static void warn(String message) {
        logger.warn(message);
    }
}
