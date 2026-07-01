package com.mos.automation.config;

import com.mos.automation.logger.LoggerManager;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * ConfigReader class reads configuration properties from the config.properties file.
 * All configuration values are centralized here for easy management.
 *
 * Features:
 * - Centralized configuration management
 * - Properties file support
 * - Default value handling
 * - Thread-safe implementation
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class ConfigReader {

    private Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    /**
     * Constructor to load properties from config file.
     */
    public ConfigReader() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
            LoggerManager.info("Configuration properties loaded successfully.");
        } catch (Exception e) {
            LoggerManager.error("Failed to load config.properties: " + e.getMessage(), e);
            throw new RuntimeException("Configuration file not found or cannot be read", e);
        }
    }

    /**
     * Gets the browser name from configuration.
     *
     * @return Browser name (chrome, firefox, edge)
     */
    public String getBrowserName() {
        return getProperty("browser.name", "chrome");
    }

    /**
     * Gets the base URL from configuration.
     *
     * @return Application base URL
     */
    public String getBaseUrl() {
        return getProperty("app.baseUrl", "https://www.google.com");
    }

    /**
     * Gets the implicit wait timeout from configuration.
     *
     * @return Timeout in seconds
     */
    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    /**
     * Gets the explicit wait timeout from configuration.
     *
     * @return Timeout in seconds
     */
    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "15"));
    }

    /**
     * Gets the database host from configuration.
     *
     * @return Database host URL
     */
    public String getDatabaseHost() {
        return getProperty("db.host", "localhost");
    }

    /**
     * Gets the database port from configuration.
     *
     * @return Database port
     */
    public int getDatabasePort() {
        return Integer.parseInt(getProperty("db.port", "3306"));
    }

    /**
     * Gets the database name from configuration.
     *
     * @return Database name
     */
    public String getDatabaseName() {
        return getProperty("db.name", "testdb");
    }

    /**
     * Gets the database user from configuration.
     *
     * @return Database username
     */
    public String getDatabaseUser() {
        return getProperty("db.user", "root");
    }

    /**
     * Gets the database password from configuration.
     *
     * @return Database password
     */
    public String getDatabasePassword() {
        return getProperty("db.password", "");
    }

    /**
     * Gets a property value with default fallback.
     *
     * @param propertyName Name of the property
     * @param defaultValue Default value if property not found
     * @return Property value or default value
     */
    private String getProperty(String propertyName, String defaultValue) {
        String value = properties.getProperty(propertyName, defaultValue);
        LoggerManager.debug("Property retrieved: " + propertyName + " = " + value);
        return value;
    }
}
