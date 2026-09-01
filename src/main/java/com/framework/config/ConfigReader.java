package com.framework.config;

import com.framework.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public final class ConfigReader {

    private static Properties properties;
    private static ConfigReader instance;

    private ConfigReader() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.CONFIG_PROPERTIES_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + FrameworkConstants.CONFIG_PROPERTIES_PATH, e);
        }
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getEnvironment() {
        return getProperty(FrameworkConstants.ENVIRONMENT);
    }

    public String getUrl() {
        return getProperty(FrameworkConstants.URL);
    }

    public String getBrowser() {
        return getProperty(FrameworkConstants.BROWSER, FrameworkConstants.DEFAULT_BROWSER);
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty(FrameworkConstants.HEADLESS, String.valueOf(FrameworkConstants.DEFAULT_HEADLESS)));
    }

    public String getWindowSize() {
        return getProperty(FrameworkConstants.WINDOW_SIZE, FrameworkConstants.DEFAULT_WINDOW_SIZE);
    }

    public Duration getImplicitWait() {
        return Duration.ofSeconds(Long.parseLong(getProperty(FrameworkConstants.IMPLICIT_WAIT_KEY, "10")));
    }

    public Duration getExplicitWait() {
        return Duration.ofSeconds(Long.parseLong(getProperty(FrameworkConstants.EXPLICIT_WAIT_KEY, "30")));
    }

    public Duration getPageLoadTimeout() {
        return Duration.ofSeconds(Long.parseLong(getProperty(FrameworkConstants.PAGE_LOAD_TIMEOUT_KEY, "30")));
    }

    public Duration getScriptTimeout() {
        return Duration.ofSeconds(Long.parseLong(getProperty(FrameworkConstants.SCRIPT_TIMEOUT_KEY, "30")));
    }

    public int getRetryCount() {
        return Integer.parseInt(getProperty(FrameworkConstants.RETRY_COUNT, String.valueOf(FrameworkConstants.DEFAULT_RETRY_COUNT)));
    }

    public boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty(FrameworkConstants.SCREENSHOT_ON_FAILURE, String.valueOf(FrameworkConstants.DEFAULT_SCREENSHOT_ON_FAILURE)));
    }

    public String getScreenshotPath() {
        return getProperty(FrameworkConstants.SCREENSHOT_PATH_KEY, FrameworkConstants.SCREENSHOT_PATH);
    }

    public String getLogLevel() {
        return getProperty(FrameworkConstants.LOG_LEVEL, "INFO");
    }

    public String getLogPath() {
        return getProperty(FrameworkConstants.LOG_PATH_KEY, FrameworkConstants.LOG_PATH);
    }

    public String getTestDataPath() {
        return getProperty(FrameworkConstants.TEST_DATA_PATH_KEY, FrameworkConstants.TEST_DATA_PATH);
    }
}
