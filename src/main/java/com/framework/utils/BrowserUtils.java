package com.framework.utils;

import com.framework.base.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public final class BrowserUtils {

    private BrowserUtils() {
    }

    public static void waitForPageLoad() {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public static void waitForElementVisible(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementClickable(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementPresent(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.error("Thread sleep interrupted", e);
        }
    }

    public static void switchToWindow(String windowTitle) {
        WebDriver driver = DriverFactory.getDriver();
        Set<String> windowHandles = driver.getWindowHandles();
        
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().contains(windowTitle)) {
                Log.info("Switched to window: " + windowTitle);
                return;
            }
        }
        throw new RuntimeException("Window with title '" + windowTitle + "' not found");
    }

    public static void switchToNewWindow() {
        WebDriver driver = DriverFactory.getDriver();
        String originalWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                Log.info("Switched to new window");
                return;
            }
        }
        throw new RuntimeException("No new window found");
    }

    public static void switchToDefaultContent() {
        WebDriver driver = DriverFactory.getDriver();
        driver.switchTo().defaultContent();
        Log.info("Switched to default content");
    }

    public static void switchToFrame(WebElement frame) {
        WebDriver driver = DriverFactory.getDriver();
        driver.switchTo().frame(frame);
        Log.info("Switched to frame");
    }

    public static void switchToFrameByIndex(int index) {
        WebDriver driver = DriverFactory.getDriver();
        driver.switchTo().frame(index);
        Log.info("Switched to frame by index: " + index);
    }

    public static void switchToFrameByIdOrName(String idOrName) {
        WebDriver driver = DriverFactory.getDriver();
        driver.switchTo().frame(idOrName);
        Log.info("Switched to frame by ID or name: " + idOrName);
    }

    public static void scrollIntoView(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Log.info("Scrolled element into view");
    }

    public static void scrollToBottom() {
        WebDriver driver = DriverFactory.getDriver();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Log.info("Scrolled to bottom of page");
    }

    public static void scrollToTop() {
        WebDriver driver = DriverFactory.getDriver();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Log.info("Scrolled to top of page");
    }

    public static void refreshPage() {
        WebDriver driver = DriverFactory.getDriver();
        driver.navigate().refresh();
        Log.info("Page refreshed");
    }

    public static void navigateBack() {
        WebDriver driver = DriverFactory.getDriver();
        driver.navigate().back();
        Log.info("Navigated back");
    }

    public static void navigateForward() {
        WebDriver driver = DriverFactory.getDriver();
        driver.navigate().forward();
        Log.info("Navigated forward");
    }

    public static String getCurrentUrl() {
        WebDriver driver = DriverFactory.getDriver();
        return driver.getCurrentUrl();
    }

    public static String getPageTitle() {
        WebDriver driver = DriverFactory.getDriver();
        return driver.getTitle();
    }

    public static void maximizeWindow() {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        Log.info("Window maximized");
    }

    public static void fullscreen() {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().window().fullscreen();
        Log.info("Browser switched to fullscreen");
    }
}
