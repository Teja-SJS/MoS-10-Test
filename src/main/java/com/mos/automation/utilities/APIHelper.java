package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * APIHelper class provides methods for API testing using REST Assured.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class APIHelper {

    /**
     * Performs GET request.
     */
    public static Response getRequest(String baseUri, String endpoint) {
        try {
            RestAssured.baseURI = baseUri;
            Response response = RestAssured.given()
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
            LoggerManager.info("GET request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            LoggerManager.error("Failed to perform GET request: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Performs POST request.
     */
    public static Response postRequest(String baseUri, String endpoint, String body) {
        try {
            RestAssured.baseURI = baseUri;
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(body)
                    .when()
                    .post(endpoint)
                    .then()
                    .extract()
                    .response();
            LoggerManager.info("POST request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            LoggerManager.error("Failed to perform POST request: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Gets response status code.
     */
    public static int getStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        LoggerManager.debug("Response status code: " + statusCode);
        return statusCode;
    }
}
