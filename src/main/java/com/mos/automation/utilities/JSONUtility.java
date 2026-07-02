package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;

/**
 * JSONUtility class provides JSON manipulation and parsing utilities.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class JSONUtility {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parses JSON string.
     */
    public static JsonNode parseJSON(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            LoggerManager.debug("JSON parsed successfully.");
            return jsonNode;
        } catch (Exception e) {
            LoggerManager.error("Failed to parse JSON: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Reads JSON from file.
     */
    public static JsonNode readJSONFile(String filePath) {
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            LoggerManager.info("JSON file read: " + filePath);
            return jsonNode;
        } catch (Exception e) {
            LoggerManager.error("Failed to read JSON file: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Gets value from JSON by path.
     */
    public static String getValueByPath(JsonNode jsonNode, String path) {
        try {
            String[] keys = path.split("\\.");
            JsonNode current = jsonNode;
            for (String key : keys) {
                current = current.get(key);
                if (current == null) {
                    return null;
                }
            }
            String value = current.asText();
            LoggerManager.debug("JSON value retrieved for path: " + path);
            return value;
        } catch (Exception e) {
            LoggerManager.error("Failed to get JSON value: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Converts object to JSON string.
     */
    public static String convertToJSON(Object object) {
        try {
            String jsonString = objectMapper.writeValueAsString(object);
            LoggerManager.debug("Object converted to JSON.");
            return jsonString;
        } catch (Exception e) {
            LoggerManager.error("Failed to convert to JSON: " + e.getMessage(), e);
            return null;
        }
    }
}
