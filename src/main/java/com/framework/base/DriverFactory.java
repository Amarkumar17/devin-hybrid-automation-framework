package com.framework.base;

import com.framework.config.ConfigReader;
import com.framework.constants.FrameworkConstants;
import com.framework.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver has not been initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static void initDriver() {
        ConfigReader config = ConfigReader.getInstance();
        String browser = config.getBrowser().toLowerCase();
        
        // Check Maven parameter first: -Dhead=false triggers headless mode
        String headParam = System.getProperty("head");
        boolean headless;
        if (headParam != null && headParam.equalsIgnoreCase("false")) {
            headless = true;
        } else {
            headless = config.isHeadless();
        }
        
        String windowSize = config.getWindowSize();

        try {
            switch (browser) {
                case FrameworkConstants.CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    chromeOptions.addArguments("--window-size=" + windowSize);
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case FrameworkConstants.FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("-headless");
                    }
                    firefoxOptions.addArguments("--width=" + windowSize.split("x")[0]);
                    firefoxOptions.addArguments("--height=" + windowSize.split("x")[1]);
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;

                case FrameworkConstants.EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) {
                        edgeOptions.addArguments("--headless=new");
                    }
                    edgeOptions.addArguments("--window-size=" + windowSize);
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--no-sandbox");
                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                case FrameworkConstants.SAFARI:
                    SafariOptions safariOptions = new SafariOptions();
                    driver.set(new SafariDriver(safariOptions));
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            WebDriver currentDriver = driver.get();
            currentDriver.manage().timeouts().implicitlyWait(config.getImplicitWait());
            currentDriver.manage().timeouts().pageLoadTimeout(config.getPageLoadTimeout());
            currentDriver.manage().timeouts().scriptTimeout(config.getScriptTimeout());
            currentDriver.manage().window().maximize();

            Log.info("WebDriver initialized successfully for browser: " + browser);

        } catch (Exception e) {
            Log.error("Failed to initialize WebDriver for browser: " + browser, e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
                Log.info("WebDriver quit successfully");
            } catch (Exception e) {
                Log.error("Error while quitting WebDriver", e);
            } finally {
                driver.remove();
            }
        }
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            try {
                driver.get().close();
                Log.info("WebDriver closed successfully");
            } catch (Exception e) {
                Log.error("Error while closing WebDriver", e);
            }
        }
    }
}
