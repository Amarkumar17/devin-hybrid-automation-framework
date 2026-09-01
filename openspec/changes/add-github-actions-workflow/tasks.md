## 1. Setup

- [x] 1.1 Create `.github/workflows/` directory structure
- [x] 1.2 Verify no existing GitHub Actions workflows conflict with new setup

## 2. Workflow Implementation

- [x] 2.1 Create `.github/workflows/test.yml` with workflow trigger configuration (push to main only)
- [x] 2.2 Configure job strategy with browser matrix (chrome, firefox)
- [x] 2.3 Add checkout step to clone repository code
- [x] 2.4 Add JDK 21 setup step using actions/setup-java action
- [x] 2.5 Configure Maven dependency caching with actions/cache action
- [x] 2.6 Add Maven test execution step with CI-specific property overrides (-Dheadless=true, -Dbrowser=${{ matrix.browser }}, -DthreadCount=2)
- [x] 2.7 Configure TestNG surefire plugin to continue on failure for complete test execution
- [x] 2.8 Add test result publishing step for GitHub Actions native test results
- [x] 2.9 Add artifact upload step for Surefire XML reports (always upload)
- [x] 2.10 Add artifact upload step for TestNG HTML reports (always upload)
- [x] 2.11 Add conditional artifact upload step for screenshots (upload only on failure)
- [x] 2.12 Add conditional artifact upload step for logs (upload only on failure)

## 3. Documentation

- [x] 3.1 Add "GitHub Actions CI/CD" section to README.md
- [x] 3.2 Document workflow triggers (push to main branch)
- [x] 3.3 Document how to view test results in GitHub Actions
- [x] 3.4 Document artifact locations and access
- [x] 3.5 Document local testing recommendations before pushing to main
- [x] 3.6 Add troubleshooting section for common CI issues

## 4. Validation

- [ ] 4.1 Commit and push changes to trigger first workflow run
- [ ] 4.2 Verify workflow executes successfully for Chrome browser
- [ ] 4.3 Verify workflow executes successfully for Firefox browser
- [ ] 4.4 Confirm test reports are uploaded as artifacts
- [ ] 4.5 Verify GitHub Actions native test results display correctly
- [ ] 4.6 Validate headless mode is working (no display errors)
- [ ] 4.7 Confirm Maven caching reduces build time on subsequent runs
- [ ] 4.8 Test failure artifact capture by intentionally failing a test
- [ ] 4.9 Verify screenshots and logs upload on failure only
- [ ] 4.10 Monitor for browser compatibility issues and adjust if needed
