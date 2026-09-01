package com.framework.utils;

import com.framework.config.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            Log.error("Driver is null, cannot capture screenshot");
            return null;
        }

        try {
            ConfigReader config = ConfigReader.getInstance();
            String screenshotPath = config.getScreenshotPath();
            
            File screenshotDir = new File(screenshotPath);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = screenshotPath + File.separator + fileName;

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));

            Log.info("Screenshot captured: " + filePath);
            return filePath;

        } catch (IOException e) {
            Log.error("Failed to capture screenshot", e);
            return null;
        }
    }

    public static String captureScreenshot(WebDriver driver) {
        return captureScreenshot(driver, "screenshot");
    }

    public static void cleanupOldScreenshots(int daysToKeep) {
        try {
            ConfigReader config = ConfigReader.getInstance();
            String screenshotPath = config.getScreenshotPath();
            File screenshotDir = new File(screenshotPath);

            if (!screenshotDir.exists() || !screenshotDir.isDirectory()) {
                return;
            }

            long cutoffTime = System.currentTimeMillis() - (daysToKeep * 24L * 60 * 60 * 1000);
            File[] files = screenshotDir.listFiles();

            if (files != null) {
                int deletedCount = 0;
                for (File file : files) {
                    if (file.isFile() && file.lastModified() < cutoffTime) {
                        if (file.delete()) {
                            deletedCount++;
                        }
                    }
                }
                Log.info("Cleaned up " + deletedCount + " old screenshots older than " + daysToKeep + " days");
            }

        } catch (Exception e) {
            Log.error("Failed to cleanup old screenshots", e);
        }
    }
}
