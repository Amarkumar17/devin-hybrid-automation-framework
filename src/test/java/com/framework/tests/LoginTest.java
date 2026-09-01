package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.DashboardPage;
import com.framework.pages.LoginPage;
import com.framework.utils.Log;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        Log.info("Starting test: testSuccessfulLogin");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after successful login");
        assertNotNull(dashboardPage.getWelcomeMessage(), "Welcome message should be displayed");
        
        Log.info("Test completed: testSuccessfulLogin");
    }

    @Test(description = "Verify login with invalid credentials shows error message")
    public void testInvalidCredentials() {
        Log.info("Starting test: testInvalidCredentials");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("invaliduser")
                 .enterPassword("wrongpassword")
                 .clickLoginButton();
        
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid credentials");
        assertTrue(loginPage.isLoginPageDisplayed(), "User should remain on login page");
        
        Log.info("Test completed: testInvalidCredentials");
    }

    @Test(description = "Verify login with empty username shows error")
    public void testEmptyUsername() {
        Log.info("Starting test: testEmptyUsername");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("")
                 .enterPassword("password123")
                 .clickLoginButton();
        
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for empty username");
        
        Log.info("Test completed: testEmptyUsername");
    }

    @Test(description = "Verify login with empty password shows error")
    public void testEmptyPassword() {
        Log.info("Starting test: testEmptyPassword");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("testuser")
                 .enterPassword("")
                 .clickLoginButton();
        
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for empty password");
        
        Log.info("Test completed: testEmptyPassword");
    }

    @Test(description = "Verify remember me checkbox functionality")
    public void testRememberMeFunctionality() {
        Log.info("Starting test: testRememberMeFunctionality");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setRememberMe(true);
        
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        
        Log.info("Test completed: testRememberMeFunctionality");
    }

    @Test(description = "Verify forgot password link is clickable")
    public void testForgotPasswordLink() {
        Log.info("Starting test: testForgotPasswordLink");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();
        
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page should still be displayed");
        
        Log.info("Test completed: testForgotPasswordLink");
    }
}
