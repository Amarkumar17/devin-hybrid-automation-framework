package com.framework.utils;

import com.framework.base.DriverFactory;
import com.framework.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public final class ExplicitWaitUtils {

    private ExplicitWaitUtils() {
    }

    private static WebDriverWait getWebDriverWait() {
        ConfigReader config = ConfigReader.getInstance();
        return new WebDriverWait(DriverFactory.getDriver(), config.getExplicitWait());
    }

    private static FluentWait<WebDriver> getFluentWait() {
        ConfigReader config = ConfigReader.getInstance();
        return new FluentWait<>(DriverFactory.getDriver())
                .withTimeout(config.getExplicitWait())
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
    }

    public static WebElement waitForElementVisible(By locator) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementVisible(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementPresent(By locator) {
        return getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(By locator) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementClickable(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static Boolean waitForElementInvisible(By locator) {
        return getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static Boolean waitForElementInvisible(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public static Boolean waitForElementSelected(By locator) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeSelected(locator));
    }

    public static Boolean waitForElementSelected(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeSelected(element));
    }

    public static Boolean waitForTextToBePresentInElement(By locator, String text) {
        return getWebDriverWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static Boolean waitForTextToBePresentInElement(WebElement element, String text) {
        return getWebDriverWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static Boolean waitForAttributeToBe(By locator, String attribute, String value) {
        return getWebDriverWait().until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }

    public static Boolean waitForAttributeToBe(WebElement element, String attribute, String value) {
        return getWebDriverWait().until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public static Boolean waitForTitleContains(String title) {
        return getWebDriverWait().until(ExpectedConditions.titleContains(title));
    }

    public static Boolean waitForTitleIs(String title) {
        return getWebDriverWait().until(ExpectedConditions.titleIs(title));
    }

    public static Boolean waitForUrlContains(String fraction) {
        return getWebDriverWait().until(ExpectedConditions.urlContains(fraction));
    }

    public static Boolean waitForUrlToBe(String url) {
        return getWebDriverWait().until(ExpectedConditions.urlToBe(url));
    }

    public static WebDriver waitForFrameToBeAvailableAndSwitchToIt(By locator) {
        return getWebDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public static WebDriver waitForFrameToBeAvailableAndSwitchToIt(int frameIndex) {
        return getWebDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
    }

    public static WebDriver waitForFrameToBeAvailableAndSwitchToIt(String frameNameOrId) {
        return getWebDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
    }

    public static WebDriver waitForFrameToBeAvailableAndSwitchToIt(WebElement frameElement) {
        return getWebDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    public static org.openqa.selenium.Alert waitForAlertIsPresent() {
        return getWebDriverWait().until(ExpectedConditions.alertIsPresent());
    }

    public static java.util.List<WebElement> waitForNumberOfElementsToBe(By locator, int number) {
        return getWebDriverWait().until(ExpectedConditions.numberOfElementsToBe(locator, number));
    }

    public static java.util.List<WebElement> waitForNumberOfElementsToBeLessThan(By locator, int number) {
        return getWebDriverWait().until(ExpectedConditions.numberOfElementsToBeLessThan(locator, number));
    }

    public static java.util.List<WebElement> waitForNumberOfElementsToBeMoreThan(By locator, int number) {
        return getWebDriverWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
    }

    public static Boolean waitForStalenessOf(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.stalenessOf(element));
    }

    public static WebElement waitForElementToBeRefreshed(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public static <T> T waitForCustomCondition(Function<WebDriver, T> condition) {
        return getWebDriverWait().until(condition);
    }

    public static <T> T waitForFluentCondition(Function<WebDriver, T> condition) {
        return getFluentWait().until(condition);
    }

    public static void waitForPageLoad() {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public static void waitForAjaxComplete() {
        WebDriver driver = DriverFactory.getDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(webDriver -> {
                        Object jQueryActive = ((org.openqa.selenium.JavascriptExecutor) webDriver)
                                .executeScript("return typeof jQuery !== 'undefined' ? jQuery.active === 0 : true");
                        return jQueryActive instanceof Boolean && (Boolean) jQueryActive;
                    });
        } catch (Exception e) {
            Log.warn("jQuery wait failed, continuing: " + e.getMessage());
        }
    }

    public static void waitForAngularLoadComplete() {
        WebDriver driver = DriverFactory.getDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(webDriver -> {
                        Object angularComplete = ((org.openqa.selenium.JavascriptExecutor) webDriver)
                                .executeScript("return typeof angular !== 'undefined' ? " +
                                        "(angular.element(document.body).injector() ? " +
                                        "angular.element(document.body).injector().get('$http').pendingRequests.length === 0 : true) : true");
                        return angularComplete instanceof Boolean && (Boolean) angularComplete;
                    });
        } catch (Exception e) {
            Log.warn("Angular wait failed, continuing: " + e.getMessage());
        }
    }
}
