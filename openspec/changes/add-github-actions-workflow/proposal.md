## Why

The framework currently lacks automated CI/CD integration, requiring manual test execution and local verification. Adding GitHub Actions workflow enables automated testing on every push to main, providing immediate feedback on code quality, detecting regressions early, and ensuring consistent test execution across environments.

## What Changes

- Create GitHub Actions workflow file at `.github/workflows/test.yml`
- Configure automated test execution on push to main branch
- Set up browser matrix testing for Chrome and Firefox
- Configure headless browser execution for CI environment
- Implement Maven dependency caching for faster builds
- Add test result reporting (both GitHub Actions native and TestNG HTML)
- Configure artifact uploads for test reports, screenshots (on failure), and logs (on failure)
- Update README.md with comprehensive documentation for GitHub Actions usage

## Capabilities

### New Capabilities
- `github-actions-ci`: Automated continuous integration workflow for test execution with browser matrix, artifact management, and comprehensive reporting

### Modified Capabilities
- None

## Impact

- New directory: `.github/workflows/`
- New file: `.github/workflows/test.yml`
- Modified file: `README.md` (adds CI/CD documentation section)
- No changes to existing Java code or Maven configuration
- No new dependencies required
- Workflow triggers only on push to main branch (minimal CI load)
