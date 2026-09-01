## ADDED Requirements

### Requirement: Workflow triggers on main branch push
The system SHALL automatically execute the test workflow when code is pushed to the main branch.

#### Scenario: Push to main triggers workflow
- **WHEN** a developer pushes code to the main branch
- **THEN** the GitHub Actions workflow initiates automatically
- **AND** the workflow runs the complete test suite

#### Scenario: Push to other branches does not trigger workflow
- **WHEN** a developer pushes code to any branch other than main
- **THEN** the GitHub Actions workflow does not initiate

### Requirement: Browser matrix testing
The system SHALL execute tests across Chrome and Firefox browsers in parallel.

#### Scenario: Chrome browser execution
- **WHEN** the workflow runs with Chrome in the browser matrix
- **THEN** tests execute using Chrome browser in headless mode
- **AND** test results are collected and reported

#### Scenario: Firefox browser execution
- **WHEN** the workflow runs with Firefox in the browser matrix
- **THEN** tests execute using Firefox browser in headless mode
- **AND** test results are collected and reported

### Requirement: Headless browser execution
The system SHALL configure browsers to run in headless mode for CI environments.

#### Scenario: Headless mode configuration
- **WHEN** the workflow executes tests
- **THEN** browsers run in headless mode without display
- **AND** no GUI rendering occurs during test execution

### Requirement: Parallel execution with reduced thread count
The system SHALL execute tests with 2 parallel threads to avoid resource contention in CI.

#### Scenario: Parallel test execution
- **WHEN** the workflow runs tests
- **THEN** tests execute with 2 parallel threads
- **AND** thread count is configured via Maven property override

### Requirement: Maven dependency caching
The system SHALL cache Maven dependencies to accelerate build times.

#### Scenario: Cache hit on subsequent runs
- **WHEN** the workflow runs and Maven dependencies are cached
- **THEN** the build uses cached dependencies
- **AND** build time is reduced compared to fresh dependency download

#### Scenario: Cache invalidation on dependency changes
- **WHEN** pom.xml changes between workflow runs
- **THEN** the Maven cache is invalidated
- **AND** dependencies are downloaded fresh

### Requirement: Test result reporting
The system SHALL generate and publish test results using both GitHub Actions native reporting and TestNG HTML reports.

#### Scenario: GitHub Actions native test results
- **WHEN** tests complete
- **THEN** test results are published to GitHub Actions native test reporting
- **AND** results appear in the workflow summary

#### Scenario: TestNG HTML report generation
- **WHEN** tests complete
- **THEN** TestNG generates HTML reports
- **AND** reports are uploaded as workflow artifacts

### Requirement: Artifact upload for test reports
The system SHALL upload test reports as workflow artifacts regardless of test outcome.

#### Scenario: Successful test artifact upload
- **WHEN** tests complete successfully
- **THEN** Surefire XML reports are uploaded as artifacts
- **AND** TestNG HTML reports are uploaded as artifacts

#### Scenario: Failed test artifact upload
- **WHEN** tests fail
- **THEN** Surefire XML reports are uploaded as artifacts
- **AND** TestNG HTML reports are uploaded as artifacts

### Requirement: Failure artifact capture
The system SHALL upload screenshots and logs as workflow artifacts only when tests fail.

#### Scenario: Screenshot upload on failure
- **WHEN** tests fail
- **THEN** screenshots captured during failure are uploaded as artifacts
- **AND** screenshots are available for debugging

#### Scenario: Log upload on failure
- **WHEN** tests fail
- **THEN** application logs are uploaded as artifacts
- **AND** logs are available for debugging

#### Scenario: No failure artifacts on success
- **WHEN** all tests pass
- **THEN** screenshots are not uploaded as artifacts
- **AND** logs are not uploaded as artifacts

### Requirement: Complete test execution on failure
The system SHALL continue executing all tests even when individual tests fail to collect complete failure information.

#### Scenario: Continue on test failure
- **WHEN** a test fails during execution
- **THEN** the workflow continues executing remaining tests
- **AND** all test failures are collected in the final report

### Requirement: Configuration override via Maven properties
The system SHALL override framework configuration using Maven system properties for CI-specific settings.

#### Scenario: Headless mode override
- **WHEN** the workflow executes
- **THEN** headless mode is enabled via `-Dheadless=true` Maven property
- **AND** config.properties is not modified

#### Scenario: Browser selection override
- **WHEN** the workflow executes for a specific browser
- **THEN** browser type is set via `-Dbrowser=<browser>` Maven property
- **AND** the matrix browser value is used

#### Scenario: Thread count override
- **WHEN** the workflow executes
- **THEN** thread count is set to 2 via `-DthreadCount=2` Maven property
- **AND** local configuration remains unchanged

### Requirement: Java 21 runtime environment
The system SHALL execute tests using JDK 21 to match framework requirements.

#### Scenario: JDK 21 setup
- **WHEN** the workflow starts
- **THEN** JDK 21 is installed and configured
- **AND** Maven uses JDK 21 for compilation and test execution
