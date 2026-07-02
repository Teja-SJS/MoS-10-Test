package com.mos.automation.utilities;

import com.mos.automation.logger.LoggerManager;
import java.util.Random;
import java.util.UUID;

/**
 * RandomDataGenerator class provides methods to generate random test data.
 *
 * @author Senior Automation Architect
 * @version 1.0
 */
public class RandomDataGenerator {

    private static final Random random = new Random();
    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Generates random string of specified length.
     */
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ALPHA_NUMERIC.charAt(random.nextInt(ALPHA_NUMERIC.length())));
        }
        LoggerManager.debug("Generated random string of length: " + length);
        return sb.toString();
    }

    /**
     * Generates random integer within range.
     */
    public static int generateRandomNumber(int min, int max) {
        int randomNum = random.nextInt((max - min) + 1) + min;
        LoggerManager.debug("Generated random number: " + randomNum);
        return randomNum;
    }

    /**
     * Generates random email address.
     */
    public static String generateRandomEmail() {
        String email = generateRandomString(10) + "@testmail.com";
        LoggerManager.debug("Generated random email: " + email);
        return email;
    }

    /**
     * Generates UUID.
     */
    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        LoggerManager.debug("Generated UUID: " + uuid);
        return uuid;
    }
}
