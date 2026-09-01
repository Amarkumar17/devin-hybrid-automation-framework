package com.framework.base;

import com.framework.config.ConfigReader;
import com.framework.utils.Log;
import com.framework.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected WebDriver driver;
    protected ConfigReader config;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        Log.info("Starting Test Suite");
        config = ConfigReader.getInstance();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browser) {
        try {
            if (browser != null && !browser.trim().isEmpty()) {
                System.setProperty("browser", browser);
            }
            DriverFactory.initDriver();
            driver = DriverFactory.getDriver();
            driver.get(config.getUrl());
            Log.info("Navigated to URL: " + config.getUrl());
        } catch (Exception e) {
            Log.error("Failed to set up test", e);
            throw new RuntimeException("Test setup failed", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                DriverFactory.quitDriver();
            }
        } catch (Exception e) {
            Log.error("Failed to tear down test", e);
        }
    }

    protected void takeScreenshot(String testName) {
        try {
            ScreenshotUtil.captureScreenshot(driver, testName);
        } catch (Exception e) {
            Log.error("Failed to capture screenshot", e);
        }
    }
}
