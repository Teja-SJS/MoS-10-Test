package com.mos.automation.database;

import com.mos.automation.config.ConfigReader;
import com.mos.automation.logger.LoggerManager;
import java.sql.*;

/**
 * DatabaseUtility class provides JDBC operations for MySQL database.
 * Handles database connectivity and query execution.
 *
 * Features:
 * - Database connection management
 * - Execute SELECT queries
 * - Execute INSERT/UPDATE/DELETE queries
 * - ResultSet processing
 * - Connection closing
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class DatabaseUtility {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ConfigReader configReader;

    /**
     * Constructor to initialize DatabaseUtility.
     */
    public DatabaseUtility() {
        this.configReader = new ConfigReader();
    }

    /**
     * Establishes database connection.
     */
    public void connectToDatabase() {
        try {
            String url = "jdbc:mysql://" + configReader.getDatabaseHost() + ":" 
                    + configReader.getDatabasePort() + "/" + configReader.getDatabaseName();
            String user = configReader.getDatabaseUser();
            String password = configReader.getDatabasePassword();

            connection = DriverManager.getConnection(url, user, password);
            LoggerManager.info("Connected to database: " + configReader.getDatabaseName());
        } catch (SQLException e) {
            LoggerManager.error("Failed to connect to database: " + e.getMessage(), e);
            throw new RuntimeException("Database connection failed", e);
        }
    }

    /**
     * Executes SELECT query.
     *
     * @param query SQL SELECT query
     * @return ResultSet with query results
     */
    public ResultSet executeSelectQuery(String query) {
        try {
            if (connection == null) {
                connectToDatabase();
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            LoggerManager.info("SELECT query executed: " + query);
            return resultSet;
        } catch (SQLException e) {
            LoggerManager.error("Failed to execute SELECT query: " + e.getMessage(), e);
            throw new RuntimeException("Query execution failed", e);
        }
    }

    /**
     * Executes UPDATE query.
     *
     * @param query SQL UPDATE query
     * @return Number of rows affected
     */
    public int executeUpdateQuery(String query) {
        try {
            if (connection == null) {
                connectToDatabase();
            }
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
            LoggerManager.info("UPDATE query executed. Rows affected: " + rowsAffected);
            return rowsAffected;
        } catch (SQLException e) {
            LoggerManager.error("Failed to execute UPDATE query: " + e.getMessage(), e);
            throw new RuntimeException("Query execution failed", e);
        }
    }

    /**
     * Executes INSERT query.
     *
     * @param query SQL INSERT query
     * @return Number of rows affected
     */
    public int executeInsertQuery(String query) {
        return executeUpdateQuery(query);
    }

    /**
     * Executes DELETE query.
     *
     * @param query SQL DELETE query
     * @return Number of rows affected
     */
    public int executeDeleteQuery(String query) {
        return executeUpdateQuery(query);
    }

    /**
     * Gets column value from ResultSet.
     *
     * @param resultSet ResultSet object
     * @param columnName Column name
     * @return Column value as String
     */
    public String getColumnValue(ResultSet resultSet, String columnName) {
        try {
            String value = resultSet.getString(columnName);
            LoggerManager.debug("Column value retrieved: " + columnName + " = " + value);
            return value;
        } catch (SQLException e) {
            LoggerManager.error("Failed to get column value: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Closes database connection.
     */
    public void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                LoggerManager.info("Database connection closed.");
            }
        } catch (SQLException e) {
            LoggerManager.error("Failed to close database connection: " + e.getMessage(), e);
        }
    }
}
