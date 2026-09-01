package com.framework.pages;

import com.framework.utils.ExplicitWaitUtils;
import com.framework.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private final WebDriver driver;

    private static final By USERNAME_INPUT = By.id("username");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("loginBtn");
    private static final By ERROR_MESSAGE = By.cssSelector(".error-message");
    private static final By FORGOT_PASSWORD_LINK = By.linkText("Forgot Password?");
    private static final By REMEMBER_ME_CHECKBOX = By.id("rememberMe");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage enterUsername(String username) {
        Log.info("Entering username: " + username);
        ExplicitWaitUtils.waitForElementVisible(USERNAME_INPUT).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        Log.info("Entering password");
        ExplicitWaitUtils.waitForElementVisible(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        Log.info("Clicking login button");
        ExplicitWaitUtils.waitForElementClickable(LOGIN_BUTTON).click();
        return this;
    }

    public DashboardPage login(String username, String password) {
        Log.info("Attempting login with username: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        Log.info("Getting error message");
        return ExplicitWaitUtils.waitForElementVisible(ERROR_MESSAGE).getText();
    }

    public boolean isErrorMessageDisplayed() {
        Log.info("Checking if error message is displayed");
        try {
            return ExplicitWaitUtils.waitForElementVisible(ERROR_MESSAGE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage clickForgotPassword() {
        Log.info("Clicking forgot password link");
        ExplicitWaitUtils.waitForElementClickable(FORGOT_PASSWORD_LINK).click();
        return this;
    }

    public LoginPage setRememberMe(boolean remember) {
        Log.info("Setting remember me: " + remember);
        WebElement checkbox = ExplicitWaitUtils.waitForElementVisible(REMEMBER_ME_CHECKBOX);
        if (checkbox.isSelected() != remember) {
            checkbox.click();
        }
        return this;
    }

    public boolean isLoginPageDisplayed() {
        Log.info("Checking if login page is displayed");
        try {
            return ExplicitWaitUtils.waitForElementVisible(USERNAME_INPUT).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        Log.info("Getting page title");
        return driver.getTitle();
    }
}
