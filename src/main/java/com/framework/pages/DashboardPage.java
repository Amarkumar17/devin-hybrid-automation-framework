package com.framework.pages;

import com.framework.utils.ExplicitWaitUtils;
import com.framework.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {

    private final WebDriver driver;

    private static final By WELCOME_MESSAGE = By.cssSelector(".welcome-message");
    private static final By USER_MENU = By.id("userMenu");
    private static final By LOGOUT_BUTTON = By.id("logoutBtn");
    private static final By PROFILE_LINK = By.linkText("Profile");
    private static final By SETTINGS_LINK = By.linkText("Settings");
    private static final By DASHBOARD_TITLE = By.cssSelector("h1.dashboard-title");
    private static final By NOTIFICATION_ICON = By.cssSelector(".notification-icon");
    private static final By SEARCH_BOX = By.id("searchBox");
    private static final By SIDEBAR_MENU = By.cssSelector(".sidebar-menu");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getWelcomeMessage() {
        Log.info("Getting welcome message");
        return ExplicitWaitUtils.waitForElementVisible(WELCOME_MESSAGE).getText();
    }

    public boolean isDashboardDisplayed() {
        Log.info("Checking if dashboard is displayed");
        try {
            return ExplicitWaitUtils.waitForElementVisible(DASHBOARD_TITLE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDashboardTitle() {
        Log.info("Getting dashboard title");
        return ExplicitWaitUtils.waitForElementVisible(DASHBOARD_TITLE).getText();
    }

    public DashboardPage clickUserMenu() {
        Log.info("Clicking user menu");
        ExplicitWaitUtils.waitForElementClickable(USER_MENU).click();
        return this;
    }

    public DashboardPage logout() {
        Log.info("Logging out");
        clickUserMenu();
        ExplicitWaitUtils.waitForElementClickable(LOGOUT_BUTTON).click();
        return this;
    }

    public DashboardPage navigateToProfile() {
        Log.info("Navigating to profile");
        clickUserMenu();
        ExplicitWaitUtils.waitForElementClickable(PROFILE_LINK).click();
        return this;
    }

    public DashboardPage navigateToSettings() {
        Log.info("Navigating to settings");
        clickUserMenu();
        ExplicitWaitUtils.waitForElementClickable(SETTINGS_LINK).click();
        return this;
    }

    public DashboardPage search(String searchTerm) {
        Log.info("Searching for: " + searchTerm);
        WebElement searchBox = ExplicitWaitUtils.waitForElementVisible(SEARCH_BOX);
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
        return this;
    }

    public boolean isNotificationIconDisplayed() {
        Log.info("Checking if notification icon is displayed");
        try {
            return ExplicitWaitUtils.waitForElementVisible(NOTIFICATION_ICON).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public DashboardPage clickNotificationIcon() {
        Log.info("Clicking notification icon");
        ExplicitWaitUtils.waitForElementClickable(NOTIFICATION_ICON).click();
        return this;
    }

    public boolean isSidebarMenuDisplayed() {
        Log.info("Checking if sidebar menu is displayed");
        try {
            return ExplicitWaitUtils.waitForElementVisible(SIDEBAR_MENU).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        Log.info("Getting page title");
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        Log.info("Getting current URL");
        return driver.getCurrentUrl();
    }
}
