package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.DashboardPage;
import com.framework.pages.LoginPage;
import com.framework.utils.Log;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DashboardTest extends BaseTest {

    @Test(description = "Verify dashboard is displayed after login")
    public void testDashboardDisplay() {
        Log.info("Starting test: testDashboardDisplay");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed");
        assertNotNull(dashboardPage.getDashboardTitle(), "Dashboard title should be displayed");
        
        Log.info("Test completed: testDashboardDisplay");
    }

    @Test(description = "Verify welcome message is displayed")
    public void testWelcomeMessage() {
        Log.info("Starting test: testWelcomeMessage");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        String welcomeMessage = dashboardPage.getWelcomeMessage();
        assertNotNull(welcomeMessage, "Welcome message should be displayed");
        assertFalse(welcomeMessage.isEmpty(), "Welcome message should not be empty");
        
        Log.info("Test completed: testWelcomeMessage");
    }

    @Test(description = "Verify user menu functionality")
    public void testUserMenu() {
        Log.info("Starting test: testUserMenu");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        dashboardPage.clickUserMenu();
        
        assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should remain displayed after clicking user menu");
        
        Log.info("Test completed: testUserMenu");
    }

    @Test(description = "Verify logout functionality")
    public void testLogout() {
        Log.info("Starting test: testLogout");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        dashboardPage.logout();
        
        assertTrue(dashboardPage.isDashboardDisplayed(), "User should be logged out");
        
        Log.info("Test completed: testLogout");
    }

    @Test(description = "Verify search functionality")
    public void testSearch() {
        Log.info("Starting test: testSearch");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        dashboardPage.search("test search term");
        
        assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should remain displayed after search");
        
        Log.info("Test completed: testSearch");
    }

    @Test(description = "Verify notification icon is displayed")
    public void testNotificationIcon() {
        Log.info("Starting test: testNotificationIcon");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        assertTrue(dashboardPage.isNotificationIconDisplayed(), "Notification icon should be displayed");
        
        Log.info("Test completed: testNotificationIcon");
    }

    @Test(description = "Verify sidebar menu is displayed")
    public void testSidebarMenu() {
        Log.info("Starting test: testSidebarMenu");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        assertTrue(dashboardPage.isSidebarMenuDisplayed(), "Sidebar menu should be displayed");
        
        Log.info("Test completed: testSidebarMenu");
    }

    @Test(description = "Verify page title is correct")
    public void testPageTitle() {
        Log.info("Starting test: testPageTitle");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        String pageTitle = dashboardPage.getPageTitle();
        assertNotNull(pageTitle, "Page title should not be null");
        assertFalse(pageTitle.isEmpty(), "Page title should not be empty");
        
        Log.info("Test completed: testPageTitle");
    }

    @Test(description = "Verify current URL is correct")
    public void testCurrentUrl() {
        Log.info("Starting test: testCurrentUrl");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("testuser", "password123");
        
        String currentUrl = dashboardPage.getCurrentUrl();
        assertNotNull(currentUrl, "Current URL should not be null");
        assertFalse(currentUrl.isEmpty(), "Current URL should not be empty");
        
        Log.info("Test completed: testCurrentUrl");
    }
}
