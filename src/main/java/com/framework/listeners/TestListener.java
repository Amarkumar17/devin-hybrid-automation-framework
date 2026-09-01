package com.framework.listeners;

import com.framework.base.DriverFactory;
import com.framework.config.ConfigReader;
import com.framework.utils.Log;
import com.framework.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("Test Failed: " + result.getMethod().getMethodName());
        
        if (ConfigReader.getInstance().isScreenshotOnFailure()) {
            try {
                WebDriver driver = DriverFactory.getDriver();
                if (driver != null) {
                    String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
                    Log.info("Screenshot captured for failed test: " + screenshotPath);
                }
            } catch (Exception e) {
                Log.error("Failed to capture screenshot on test failure", e);
            }
        }
        
        Log.error("Test Failure Details", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn("Test Skipped: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            Log.warn("Skip Reason", result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.warn("Test Failed But Within Success Percentage: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        Log.info("Test Suite Started: " + context.getName());
        Log.info("Total Test Count: " + context.getAllTestMethods().length);
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Test Suite Finished: " + context.getName());
        Log.info("Passed Tests: " + context.getPassedTests().size());
        Log.info("Failed Tests: " + context.getFailedTests().size());
        Log.info("Skipped Tests: " + context.getSkippedTests().size());
    }
}
