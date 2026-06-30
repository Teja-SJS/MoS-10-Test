import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test Class for LoginPage
 * Tests login functionality including validation, authentication, and error handling
 */
@DisplayName("LoginPage Tests")
public class LoginPageTest {

    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
    }

    // ==================== Email/Username Tests ====================

    @Test
    @DisplayName("Should accept valid email")
    public void testValidEmail() {
        assertTrue(loginPage.isValidEmail("user@example.com"));
    }

    @Test
    @DisplayName("Should reject invalid email format")
    public void testInvalidEmailFormat() {
        assertFalse(loginPage.isValidEmail("invalid-email"));
    }

    @Test
    @DisplayName("Should reject empty email")
    public void testEmptyEmail() {
        assertFalse(loginPage.isValidEmail(""));
    }

    @Test
    @DisplayName("Should reject null email")
    public void testNullEmail() {
        assertFalse(loginPage.isValidEmail(null));
    }

    // ==================== Password Tests ====================

    @Test
    @DisplayName("Should accept valid password")
    public void testValidPassword() {
        assertTrue(loginPage.isValidPassword("SecurePass123!"));
    }

    @Test
    @DisplayName("Should reject short password")
    public void testShortPassword() {
        assertFalse(loginPage.isValidPassword("Pass1!"));
    }

    @Test
    @DisplayName("Should reject empty password")
    public void testEmptyPassword() {
        assertFalse(loginPage.isValidPassword(""));
    }

    @Test
    @DisplayName("Should reject null password")
    public void testNullPassword() {
        assertFalse(loginPage.isValidPassword(null));
    }

    // ==================== Login Tests ====================

    @Test
    @DisplayName("Should successfully login with valid credentials")
    public void testSuccessfulLogin() {
        boolean result = loginPage.login("user@example.com", "SecurePass123!");
        assertTrue(result, "Login should succeed with valid credentials");
    }

    @Test
    @DisplayName("Should fail login with invalid email")
    public void testLoginWithInvalidEmail() {
        boolean result = loginPage.login("invalid-email", "SecurePass123!");
        assertFalse(result, "Login should fail with invalid email");
    }

    @Test
    @DisplayName("Should fail login with invalid password")
    public void testLoginWithInvalidPassword() {
        boolean result = loginPage.login("user@example.com", "weak");
        assertFalse(result, "Login should fail with invalid password");
    }

    @Test
    @DisplayName("Should fail login with empty credentials")
    public void testLoginWithEmptyCredentials() {
        boolean result = loginPage.login("", "");
        assertFalse(result, "Login should fail with empty credentials");
    }

    @Test
    @DisplayName("Should fail login with null credentials")
    public void testLoginWithNullCredentials() {
        boolean result = loginPage.login(null, null);
        assertFalse(result, "Login should fail with null credentials");
    }

    // ==================== Remember Me Tests ====================

    @Test
    @DisplayName("Should handle remember me option")
    public void testRememberMeOption() {
        boolean result = loginPage.login("user@example.com", "SecurePass123!", true);
        assertTrue(result, "Login should succeed with remember me enabled");
    }

    @Test
    @DisplayName("Should handle login without remember me")
    public void testLoginWithoutRememberMe() {
        boolean result = loginPage.login("user@example.com", "SecurePass123!", false);
        assertTrue(result, "Login should succeed without remember me");
    }

    // ==================== Error Handling Tests ====================

    @Test
    @DisplayName("Should return appropriate error message for invalid email")
    public void testErrorMessageForInvalidEmail() {
        String errorMessage = loginPage.getErrorMessage("user", "SecurePass123!");
        assertNotNull(errorMessage);
        assertTrue(errorMessage.contains("email") || errorMessage.contains("Email"));
    }

    @Test
    @DisplayName("Should return appropriate error message for invalid password")
    public void testErrorMessageForInvalidPassword() {
        String errorMessage = loginPage.getErrorMessage("user@example.com", "weak");
        assertNotNull(errorMessage);
        assertTrue(errorMessage.contains("password") || errorMessage.contains("Password"));
    }

    // ==================== Session Tests ====================

    @Test
    @DisplayName("Should create session after successful login")
    public void testSessionCreation() {
        loginPage.login("user@example.com", "SecurePass123!");
        assertTrue(loginPage.isSessionActive(), "Session should be active after successful login");
    }

    @Test
    @DisplayName("Should clear session on logout")
    public void testLogout() {
        loginPage.login("user@example.com", "SecurePass123!");
        loginPage.logout();
        assertFalse(loginPage.isSessionActive(), "Session should be inactive after logout");
    }

    // ==================== Reset Password Tests ====================

    @Test
    @DisplayName("Should allow password reset with valid email")
    public void testPasswordReset() {
        boolean result = loginPage.initiatePasswordReset("user@example.com");
        assertTrue(result, "Password reset should succeed with valid email");
    }

    @Test
    @DisplayName("Should reject password reset with invalid email")
    public void testPasswordResetWithInvalidEmail() {
        boolean result = loginPage.initiatePasswordReset("invalid-email");
        assertFalse(result, "Password reset should fail with invalid email");
    }

    @Test
    @DisplayName("Should reject password reset with unregistered email")
    public void testPasswordResetWithUnregisteredEmail() {
        boolean result = loginPage.initiatePasswordReset("unregistered@example.com");
        assertFalse(result, "Password reset should fail with unregistered email");
    }

    // ==================== Edge Cases ====================

    @Test
    @DisplayName("Should handle SQL injection attempts")
    public void testSQLInjectionPrevention() {
        String maliciousInput = "' OR '1'='1";
        boolean result = loginPage.login(maliciousInput, maliciousInput);
        assertFalse(result, "Login should safely handle SQL injection attempts");
    }

    @Test
    @DisplayName("Should handle special characters in credentials")
    public void testSpecialCharactersHandling() {
        boolean result = loginPage.login("user+test@example.com", "Pass@123#!");
        // Result depends on implementation, but should not throw exception
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should handle XSS attempts")
    public void testXSSPrevention() {
        String xssInput = "<script>alert('xss')</script>";
        boolean result = loginPage.login(xssInput, xssInput);
        assertFalse(result, "Login should safely handle XSS attempts");
    }

    // ==================== Rate Limiting Tests ====================

    @Test
    @DisplayName("Should enforce rate limiting after multiple failed attempts")
    public void testRateLimitingAfterFailedAttempts() {
        // Simulate multiple failed login attempts
        for (int i = 0; i < 5; i++) {
            loginPage.login("user@example.com", "wrongpassword");
        }
        
        // Sixth attempt should be blocked
        boolean result = loginPage.login("user@example.com", "SecurePass123!");
        assertFalse(result, "Login should be blocked after multiple failed attempts");
    }

    // ==================== Two-Factor Authentication Tests ====================

    @Test
    @DisplayName("Should prompt for 2FA when enabled")
    public void test2FAPrompt() {
        boolean requires2FA = loginPage.requiresTwoFactorAuth("user@example.com", "SecurePass123!");
        assertTrue(requires2FA, "2FA should be prompted when enabled for user");
    }

    @Test
    @DisplayName("Should complete login after valid 2FA code")
    public void testValid2FACode() {
        loginPage.login("user@example.com", "SecurePass123!");
        boolean result = loginPage.validate2FACode("123456");
        assertTrue(result, "Login should complete with valid 2FA code");
    }

    @Test
    @DisplayName("Should reject invalid 2FA code")
    public void testInvalid2FACode() {
        loginPage.login("user@example.com", "SecurePass123!");
        boolean result = loginPage.validate2FACode("000000");
        assertFalse(result, "Login should fail with invalid 2FA code");
    }
}
