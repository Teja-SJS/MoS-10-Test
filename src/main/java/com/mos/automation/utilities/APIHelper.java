package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

/**
 * APIHelper class provides methods for API testing using REST Assured.
 * Supports GET, POST, PUT, DELETE, PATCH operations.
 *
 * Features:
 * - GET requests
 * - POST requests
 * - PUT requests
 * - DELETE requests
 * - PATCH requests
 * - Header management
 * - Response validation
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class APIHelper {

    /**
     * Performs GET request.
     *
     * @param baseUri Base URI of the API
     * @param endpoint API endpoint
     * @return Response object
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
     *
     * @param baseUri Base URI of the API
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
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
     * Performs PUT request.
     *
     * @param baseUri Base URI of the API
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
     */
    public static Response putRequest(String baseUri, String endpoint, String body) {
        try {
            RestAssured.baseURI = baseUri;
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(body)
                    .when()
                    .put(endpoint)
                    .then()
                    .extract()
                    .response();
            LoggerManager.info("PUT request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            LoggerManager.error("Failed to perform PUT request: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Performs DELETE request.
     *
     * @param baseUri Base URI of the API
     * @param endpoint API endpoint
     * @return Response object
     */
    public static Response deleteRequest(String baseUri, String endpoint) {
        try {
            RestAssured.baseURI = baseUri;
            Response response = RestAssured.given()
                    .when()
                    .delete(endpoint)
                    .then()
                    .extract()
                    .response();
            LoggerManager.info("DELETE request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            LoggerManager.error("Failed to perform DELETE request: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Performs PATCH request.
     *
     * @param baseUri Base URI of the API
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
     */
    public static Response patchRequest(String baseUri, String endpoint, String body) {
        try {
            RestAssured.baseURI = baseUri;
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(body)
                    .when()
                    .patch(endpoint)
                    .then()
                    .extract()
                    .response();
            LoggerManager.info("PATCH request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            LoggerManager.error("Failed to perform PATCH request: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Gets response body as string.
     *
     * @param response Response object
     * @return Response body
     */
    public static String getResponseBody(Response response) {
        try {
            String body = response.getBody().asString();
            LoggerManager.debug("Response body retrieved.");
            return body;
        } catch (Exception e) {
            LoggerManager.error("Failed to get response body: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Gets response status code.
     *
     * @param response Response object
     * @return Status code
     */
    public static int getStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        LoggerManager.debug("Response status code: " + statusCode);
        return statusCode;
    }
}
