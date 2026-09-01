## Context

The Hybrid Automation Framework is a Java Maven-based test automation framework using TestNG and Selenium WebDriver 4+. It currently has no CI/CD integration, requiring manual test execution. The framework uses Selenium 4.23.0 with built-in Selenium Manager (webdriver-manager was removed in a previous change), supports Chrome, Firefox, Edge, and Safari browsers, and runs tests in parallel with configurable thread count.

GitHub Actions runners (ubuntu-latest) come with Chrome and Firefox pre-installed, making them ideal for browser testing without additional setup overhead.

## Goals / Non-Goals

**Goals:**
- Automate test execution on every push to main branch
- Provide consistent, reproducible test execution in CI environment
- Enable cross-browser testing (Chrome and Firefox) in parallel
- Deliver comprehensive test reporting via both GitHub Actions native results and TestNG HTML reports
- Ensure fast feedback through Maven dependency caching
- Capture failure artifacts (screenshots, logs) for debugging

**Non-Goals:**
- Multi-OS testing (Ubuntu-only to optimize for speed and cost)
- Cross-browser testing for Edge and Safari (limited to Chrome and Firefox)
- Scheduled/nightly test runs
- Deployment or release automation
- Integration test environments or staging deployments

## Decisions

### Workflow Trigger Strategy
**Decision:** Trigger only on push to main branch
**Rationale:** Minimizes CI load and costs while ensuring main branch quality. Pull requests can be validated locally before merging. Alternative considered (push to all branches) would increase CI minutes significantly without proportional value.

### Browser Matrix Configuration
**Decision:** Test on Chrome and Firefox only
**Rationale:** GitHub Actions ubuntu-latest runner has both browsers pre-installed. These represent the two most widely used browsers for web automation. Alternative considered (all 5 browsers) would increase runtime and Edge/Safari have limited Linux support in CI environments.

### Parallel Execution in CI
**Decision:** Reduce thread count to 2 for CI (vs 4 in local config)
**Rationale:** GitHub Actions runners have limited resources (2 cores typical). Running 4 parallel browser instances may cause timeouts or resource contention. 2 threads balance parallelism with stability. Configured via Maven property override: `-DthreadCount=2`.

### Headless Mode Enforcement
**Decision:** Force headless mode in CI via Maven property
**Rationale:** CI environments have no display. Override config.properties via `-Dheadless=true` to ensure headless execution without modifying source config files. Alternative considered (separate config-ci.properties) adds maintenance overhead.

### Failure Handling Strategy
**Decision:** Run all tests and collect all failures
**Rationale:** Provides complete picture of test health rather than stopping at first failure. Configured via TestNG surefire plugin to continue on failure. Alternative (fail-fast) would hide concurrent failures and require multiple CI runs to identify all issues.

### Configuration Override Approach
**Decision:** Use Maven system properties for CI-specific configuration
**Rationale:** Clean separation of concerns - config.properties remains for local development, CI overrides applied at runtime. Properties passed: `-Dheadless=true -Dbrowser=${{ matrix.browser }} -DthreadCount=2`. Alternative (environment variables) would require code changes to read env vars.

### Maven Caching Strategy
**Decision:** Cache ~/.m2/repository directory with Maven dependency cache action
**Rationale:** Reduces build time by 30-60% by avoiding dependency downloads. Cache key includes pom.xml hash to invalidate on dependency changes. Standard practice for Maven projects in CI.

### Artifact Upload Strategy
**Decision:** Upload test reports always, failure artifacts on failure only
**Rationale:** Test reports (Surefire XML, TestNG HTML) provide value regardless of outcome. Screenshots and logs only useful for debugging failures, reducing storage costs. Alternative (upload everything always) would increase artifact storage unnecessarily.

### Test Reporting Approach
**Decision:** Implement both GitHub Actions native test results and TestNG HTML reports
**Rationale:** GitHub Actions native results integrate with PR checks and provide quick status overview. TestNG HTML reports offer detailed per-test information and historical comparison. Complementary strengths justify maintaining both.

### Java Version
**Decision:** Use JDK 21 (matches pom.xml)
**Rationale:** Framework requires JDK 21 per Maven compiler configuration. GitHub Actions setup-java action supports JDK 21. No migration needed.

## Risks / Trade-offs

**Risk:** Browser version incompatibility with Selenium 4.23.0
**Mitigation:** GitHub Actions keeps browsers updated. Selenium 4.23.0 is recent (2024) and compatible with current browser versions. Monitor for compatibility issues and update Selenium version if needed.

**Risk:** Maven cache corruption causing flaky builds
**Mitigation:** Cache key includes pom.xml hash, ensuring cache invalidation on dependency changes. Manual cache clearing action available if needed.

**Risk:** Parallel test execution causing flaky tests in CI
**Mitigation:** Reduced thread count to 2 mitigates resource contention. If flakiness persists, can reduce to 1 (sequential) as fallback.

**Trade-off:** Ubuntu-only testing may miss OS-specific browser behavior
**Acceptance:** Web applications typically render consistently across OS for Chrome/Firefox. Cross-OS testing cost outweighs marginal benefit for this framework.

**Trade-off:** No pull request testing shifts quality burden to developers
**Acceptance:** Developers can run tests locally before pushing. Main branch protection ensures quality gate. Reduced CI costs justify this approach.

## Migration Plan

1. Create `.github/workflows/` directory structure
2. Add `test.yml` workflow file with configured matrix and steps
3. Update README.md with GitHub Actions section documenting:
   - Workflow triggers
   - How to view results
   - Artifact locations
   - Local testing before push
4. Commit and push to main branch to trigger first workflow run
5. Validate workflow execution and artifact uploads
6. Monitor initial runs for browser compatibility or timeout issues

**Rollback Strategy:** Delete `.github/workflows/test.yml` to disable CI. No code changes require rollback.

## Open Questions

None identified. All technical decisions are clear based on user preferences and framework constraints.
