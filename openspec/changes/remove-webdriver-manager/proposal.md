## Why

Selenium 4.6.0+ includes built-in driver management (Selenium Manager) that automatically downloads and manages browser drivers, making the WebDriverManager dependency redundant. Since the project uses Selenium 4.23.0, we can remove this external dependency to reduce project complexity and maintenance overhead.

## What Changes

- Remove WebDriverManager dependency from pom.xml (io.github.bonigarcia:webdrivermanager:5.9.2)
- Remove WebDriverManager import and setup calls from DriverFactory.java
- Remove `WebDriverManager.chromedriver().setup()` for Chrome driver initialization
- Remove `WebDriverManager.firefoxdriver().setup()` for Firefox driver initialization  
- Remove `WebDriverManager.edgedriver().setup()` for Edge driver initialization
- Safari driver remains unchanged (never used WebDriverManager)

## Capabilities

### New Capabilities
- None

### Modified Capabilities
- None

## Impact

- **Dependencies**: Removes one external dependency (webdrivermanager 5.9.2)
- **Code**: DriverFactory.java will have 4 lines removed (1 import + 3 setup calls)
- **Build**: Smaller dependency tree, faster builds
- **Functionality**: No behavioral changes - Selenium Manager will handle driver management automatically
- **Maintenance**: Reduced dependency updates and potential compatibility issues
