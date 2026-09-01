# Hybrid Automation Framework

A production-grade Java Maven Hybrid Test Automation Framework built with TestNG and Selenium WebDriver 4+.

## Features

- **Thread-Safe Design**: Uses ThreadLocal WebDriver for parallel test execution
- **Page Object Model (POM)**: Clean separation of page logic and test logic
- **Configuration Management**: Externalized configuration via properties files
- **Logging**: Log4j2 with multiple appenders (console, file, rolling file)
- **Retry Mechanism**: Configurable retry logic for flaky tests
- **Screenshot Capture**: Automatic screenshot capture on test failure
- **Explicit Waits**: Comprehensive wait utilities for robust synchronization
- **Parallel Execution**: Support for parallel test execution with TestNG
- **Browser Support**: Chrome, Firefox, Edge, Safari
- **Test Data Management**: Support for JSON, CSV, and Properties files

## Project Structure

```
hybrid-automation-framework/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/framework/
│   │   │       ├── base/               # BaseTest and DriverFactory
│   │   │       ├── config/             # ConfigReader
│   │   │       ├── constants/          # Framework constants
│   │   │       ├── listeners/          # TestListener, AnnotationTransformer
│   │   │       ├── pages/              # Page Object Model classes
│   │   │       ├── retry/              # RetryAnalyzer
│   │   │       └── utils/              # BrowserUtils, Log, ScreenshotUtil, ExplicitWaitUtils
│   │   └── resources/
│   │       ├── config.properties       # Framework configuration
│   │       └── log4j2.xml              # Logging configuration
│   └── test/
│       ├── java/
│       │   └── com/framework/tests     # TestNG test classes
│       └── resources/
│           ├── testng.xml              # TestNG suite configuration
│           └── testdata/               # Test data files
└── .gitignore
```

## Prerequisites

- JDK 21 or higher
- Maven 3.6 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Setup Instructions

1. **Clone or navigate to the project directory:**
   ```bash
   cd /Users/amarkumar/devinAI_projects/hybrid-automation-framework
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Configure the framework:**
   Edit `src/main/resources/config.properties` to set:
   - Environment URL
   - Browser type
   - Timeout values
   - Retry configuration
   - Screenshot settings

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=LoginTest
```

### Run with specific browser:
```bash
mvn test -Dbrowser=firefox
```

### Run in parallel:
The framework is configured to run tests in parallel (4 threads by default). You can adjust this in `testng.xml` or `pom.xml`.

## Configuration

### Browser Configuration
- Supported browsers: chrome, firefox, edge, safari
- Set in `config.properties` or via command line: `-Dbrowser=chrome`

### Timeout Configuration
- Implicit wait: 10 seconds (configurable)
- Explicit wait: 30 seconds (configurable)
- Page load timeout: 30 seconds (configurable)

### Retry Configuration
- Default retry count: 2
- Configurable via `config.properties`

## Page Object Model

The framework follows the Page Object Model pattern:

```java
LoginPage loginPage = new LoginPage(driver);
DashboardPage dashboardPage = loginPage.login("username", "password");
```

## Utilities

### Explicit Wait Utils
```java
ExplicitWaitUtils.waitForElementVisible(locator);
ExplicitWaitUtils.waitForElementClickable(element);
ExplicitWaitUtils.waitForPageLoad();
```

### Browser Utils
```java
BrowserUtils.scrollToBottom();
BrowserUtils.switchToNewWindow();
BrowserUtils.refreshPage();
```

### Screenshot Utils
```java
ScreenshotUtil.captureScreenshot(driver, "testName");
```

## Test Data

Test data files are located in `src/test/resources/testdata/`:
- `testdata.properties` - Key-value pairs
- `testdata.json` - JSON format
- `testdata.csv` - CSV format

## Logging

Logs are written to:
- Console (INFO level)
- `target/logs/automation.log`
- `target/logs/automation-rolling.log` (with rotation)

## Parallel Execution

The framework supports parallel execution at method level:
- Configured in `testng.xml`
- Thread-safe WebDriver implementation using ThreadLocal
- Configurable thread count in `pom.xml` and `testng.xml`

## Best Practices

1. Always use explicit waits instead of Thread.sleep()
2. Follow the Page Object Model pattern
3. Keep locators in page classes, not in test classes
4. Use descriptive test method names
5. Leverage the retry mechanism for flaky tests
6. Review logs in `target/logs/` for debugging

## GitHub Actions CI/CD

This project uses GitHub Actions for automated continuous integration testing.

### Workflow Triggers

The test workflow automatically runs on:
- Push to the `main` branch

### How to View Test Results

1. Navigate to the **Actions** tab in your GitHub repository
2. Select the workflow run you want to review
3. View the **Test Results** section in the workflow summary for a detailed breakdown
4. Download artifacts for detailed reports and failure debugging

### Artifacts

Each workflow run generates the following artifacts:

**Always Uploaded:**
- `surefire-reports-{browser}` - Maven Surefire XML test reports
- `testng-html-reports-{browser}` - TestNG HTML reports with detailed test information

**Uploaded on Failure Only:**
- `screenshots-{browser}` - Screenshots captured during test failures
- `logs-{browser}` - Application logs for debugging failures

### Local Testing Before Push

Before pushing to the main branch, run tests locally to catch issues early:

```bash
# Run all tests with headless mode
mvn test -Dheadless=true

# Run with specific browser
mvn test -Dbrowser=chrome -Dheadless=true

# Run with reduced thread count (same as CI)
mvn test -DthreadCount=2 -Dheadless=true
```

### Troubleshooting Common CI Issues

**Issue: Headless mode errors**
- Ensure `-Dheadless=true` is passed to Maven
- Check that browser versions are compatible with Selenium 4.23.0

**Issue: Flaky tests in CI**
- CI runs with 2 parallel threads to avoid resource contention
- Consider reducing thread count to 1 if tests are unstable
- Review screenshots and logs from failed artifacts

**Issue: Maven cache not working**
- Cache is invalidated when `pom.xml` changes
- First run after dependency changes will be slower
- Subsequent runs should be significantly faster

**Issue: Browser compatibility**
- GitHub Actions uses latest Chrome and Firefox on Ubuntu
- If browser updates cause issues, update Selenium version in `pom.xml`

## License

This project is created for educational and demonstration purposes.
