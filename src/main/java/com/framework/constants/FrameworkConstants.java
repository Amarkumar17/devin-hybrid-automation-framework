package com.framework.constants;

import java.time.Duration;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    // Timeout Constants
    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);
    public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(30);
    public static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(30);
    public static final Duration SCRIPT_TIMEOUT = Duration.ofSeconds(30);

    // Path Constants
    public static final String CONFIG_PROPERTIES_PATH = "src/main/resources/config.properties";
    public static final String LOG4J2_XML_PATH = "src/main/resources/log4j2.xml";
    public static final String SCREENSHOT_PATH = "target/screenshots";
    public static final String LOG_PATH = "target/logs";
    public static final String TEST_DATA_PATH = "src/test/resources/testdata";

    // Browser Constants
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String EDGE = "edge";
    public static final String SAFARI = "safari";

    // Retry Constants
    public static final int DEFAULT_RETRY_COUNT = 2;

    // Configuration Keys
    public static final String ENVIRONMENT = "environment";
    public static final String URL = "url";
    public static final String BROWSER = "browser";
    public static final String HEADLESS = "headless";
    public static final String WINDOW_SIZE = "window_size";
    public static final String IMPLICIT_WAIT_KEY = "implicit_wait";
    public static final String EXPLICIT_WAIT_KEY = "explicit_wait";
    public static final String PAGE_LOAD_TIMEOUT_KEY = "page_load_timeout";
    public static final String SCRIPT_TIMEOUT_KEY = "script_timeout";
    public static final String RETRY_COUNT = "retry_count";
    public static final String SCREENSHOT_ON_FAILURE = "screenshot_on_failure";
    public static final String SCREENSHOT_PATH_KEY = "screenshot_path";
    public static final String LOG_LEVEL = "log_level";
    public static final String LOG_PATH_KEY = "log_path";
    public static final String TEST_DATA_PATH_KEY = "test_data_path";

    // Default Values
    public static final String DEFAULT_BROWSER = CHROME;
    public static final boolean DEFAULT_HEADLESS = false;
    public static final String DEFAULT_WINDOW_SIZE = "1920x1080";
    public static final boolean DEFAULT_SCREENSHOT_ON_FAILURE = true;
}
