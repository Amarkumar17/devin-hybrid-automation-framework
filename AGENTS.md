# AGENTS.md

## Setup Commands
- Resolve dependencies: `mvn dependency:resolve`
- Clean and compile project: `mvn clean compile`
- Run entire TestNG suite (Default Headed with UI): `mvn test`
- Run entire TestNG suite in Headless mode (no UI): `mvn test -Dhead=false`
- Run a specific test suite (Default Headed with UI): `mvn test -DsuiteXmlFile=testng.xml`
- Run a specific test suite in Headless mode: `mvn test -DsuiteXmlFile=testng.xml -Dhead=false`
- Run a single test class (Default Headed with UI): `mvn test -Dtest=ClassName`
- Run a single test class in Headless mode: `mvn test -Dtest=ClassName -Dhead=false`

## Code Style
- **Page Object Model (POM)**: Strictly separate page actions, elements, and locators from test logic. All UI locators must reside in the corresponding Page Object classes.
- **Locator Strategy Guidelines**: Use descriptive and resilient locators. Prioritize selectors in this order: `id` > `name` > `cssSelector` > `xpath`. Avoid absolute or auto-generated XPaths.
- **Explicit Waits**: Avoid hardcoded pauses like `Thread.sleep()`. Always use Selenium's explicit waits (`WebDriverWait` or `FluentWait`) to handle dynamic element loading.
- **Encapsulation**: Keep locators (`WebElement` or `By` objects) private or protected within Page classes. Expose public action methods to represent user interactions.
- **Thread Safety**: Manage WebDriver instances using `ThreadLocal<WebDriver>` to ensure thread safety during parallel execution in TestNG.

## Testing Guidelines
- Use TestNG annotations (`@Test`, `@BeforeClass`, `@AfterClass`, `@BeforeMethod`, `@AfterMethod`, `@DataProvider`) to control setup, execution flow, and teardown.
- All test scripts must be stored under `/src/test/java`
- **Validation**: Use TestNG `Assert` statements (e.g., `Assert.assertEquals`, `Assert.assertTrue`) for validation rather than manual console printing. Use Soft Assertions (`SoftAssert`) for non-blocking UI checks.
- **Data-Driven Automation**: Separate test data from logic using TestNG `@DataProvider` combined with external data readers (e.g., Apache POI for Excel or Jackson for JSON).

## Project Structure
- `/src/main/java` - Package structure for Page Objects (`pages`), custom WebDriver utilities (`utils`), Excel/data helpers (`helpers`), and BaseTest classes (`base`).
- `/src/test/java` - Test classes containing TestNG scripts.
- `/src/test/resources` - TestNG execution suites (`testng.xml`), environment configurations (`config.properties`), and test data sources.
- `/pom.xml` - Maven POM declaring core dependencies (Selenium Java, TestNG, Apache POI, WebDriverManager, ExtentReports) and the `maven-surefire-plugin` configured with `testng.xml`.
- `/target/reports` - Generated test reports (e.g., ExtentReports, Surefire reports) and runtime screenshots captured on test failures.

## Development Workflow
- **CI/CD Platform**: The continuous integration and delivery pipeline is built on **GitHub Actions** rather than Devin. Ensure all configurations, test run commands, and execution scripts are fully compatible with standard GitHub Actions runners.
- **Headless Mode Toggling**: 
  - **Default Execution**: The framework must default to running **with UI enabled (headed mode)**.
  - **Headless Trigger**: If we explicitly pass `-Dhead=false` (or `-dhead=false`) in the Maven command line, the framework must run **without UI (headless mode)**.
  - **Implementation**: The custom WebDriver utility must read this property using `System.getProperty("head")` (case-insensitively or supporting lower/upper bounds). When this value is explicitly equal to `"false"`, configure the target browser options (e.g., ChromeOptions or FirefoxOptions) with `--headless=new`. Otherwise, default to regular headed mode.
- **Failure Analysis**: If a UI test fails, check the runtime execution screenshots captured automatically by the framework's test listeners in `/target/screenshots/`.
- **Clean Teardown**: Ensure that `driver.quit()` is invoked in an `@AfterMethod` or `@AfterClass` teardown block to prevent orphaned driver processes.
