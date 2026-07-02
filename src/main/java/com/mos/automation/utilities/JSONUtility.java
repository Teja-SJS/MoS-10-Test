package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;

/**
 * JSONUtility class provides JSON manipulation and parsing utilities.
 * Uses Jackson library for JSON operations.
 *
 * Features:
 * - Parse JSON string
 * - Convert object to JSON
 * - Read JSON file
 * - Get JSON value by path
 * - Create JSON object
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class JSONUtility {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parses JSON string.
     *
     * @param jsonString JSON string to parse
     * @return JsonNode object
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
     *
     * @param filePath Path to JSON file
     * @return JsonNode object
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
     *
     * @param jsonNode JsonNode to search
     * @param path Path to value (e.g., "user.name")
     * @return Value as string
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
     *
     * @param object Object to convert
     * @return JSON string
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

    /**
     * Creates new JSON object.
     *
     * @return New ObjectNode
     */
    public static ObjectNode createJSONObject() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        LoggerManager.debug("New JSON object created.");
        return objectNode;
    }

    /**
     * Adds property to JSON object.
     *
     * @param jsonObject ObjectNode to modify
     * @param key Property key
     * @param value Property value
     */
    public static void addProperty(ObjectNode jsonObject, String key, String value) {
        try {
            jsonObject.put(key, value);
            LoggerManager.debug("Property added to JSON object: " + key + " = " + value);
        } catch (Exception e) {
            LoggerManager.error("Failed to add property to JSON: " + e.getMessage(), e);
        }
    }
}
