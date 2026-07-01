# Enterprise Selenium Automation Framework

## Overview
This is a production-ready, enterprise-level Selenium Automation Framework built with Java 21, Selenium 4, TestNG, and Page Object Model (POM) design pattern.

## Technology Stack

- **Language**: Java 21
- **Automation Tool**: Selenium 4
- **Build Tool**: Maven
- **Testing Framework**: TestNG
- **Design Pattern**: Page Object Model (POM)
- **Reporting**: Extent Reports
- **Logging**: Log4j2
- **Excel Handling**: Apache POI
- **Database**: MySQL with JDBC
- **JSON Processing**: Jackson
- **Browser Support**: Chrome, Firefox, Edge
- **Parallel Execution**: TestNG
- **CI/CD**: Jenkins & GitHub Actions

## Project Structure

```
src/
├── main/
│   └── java/com/mos/automation/
│       ├── base/
│       │   ├── BaseTest.java
│       │   └── BasePage.java
│       ├── driver/
│       │   ├── DriverFactory.java
│       │   └── DriverManager.java
│       ├── config/
│       │   └── ConfigReader.java
│       ├── utilities/
│       │   ├── WaitUtility.java
│       │   ├── ActionUtility.java
│       │   ├── JavaScriptUtility.java
│       │   ├── ScreenshotUtility.java
│       │   ├── AlertUtility.java
│       │   ├── DropdownUtility.java
│       │   ├── WindowUtility.java
│       │   ├── FrameUtility.java
│       │   ├── ExcelUtility.java
│       │   └── RandomDataGenerator.java
│       ├── logger/
│       │   └── LoggerManager.java
│       └── reports/
│           └── ExtentReportManager.java
├── test/
│   ├── java/com/mos/automation/
│   │   ├── tests/
│   │   │   └── YourTestClass.java
│   │   └── pages/
│   │       └── YourPageObject.java
│   └── resources/
│       ├── config.properties
│       └── log4j2.xml
└── pom.xml
```

## Framework Features

### 1. Base Classes
- **BaseTest**: Parent class for all test classes with setup/teardown methods
- **BasePage**: Parent class for all page objects with utility initializations

### 2. Driver Management
- **DriverFactory**: Initializes browsers (Chrome, Firefox, Edge)
- **DriverManager**: ThreadLocal-based driver management for parallel execution

### 3. Configuration Management
- **ConfigReader**: Centralized configuration from properties file

### 4. Utilities
- **WaitUtility**: Explicit waits for various element conditions
- **ActionUtility**: Mouse and keyboard interactions
- **JavaScriptUtility**: JavaScript execution
- **ScreenshotUtility**: Screenshot capture functionality
- **AlertUtility**: JavaScript alert handling
- **DropdownUtility**: Dropdown/Select element handling
- **WindowUtility**: Window/Tab switching
- **FrameUtility**: Frame/iFrame handling
- **ExcelUtility**: Excel file read/write operations
- **RandomDataGenerator**: Random test data generation

### 5. Logging & Reporting
- **LoggerManager**: Centralized logging with Log4j2
- **ExtentReportManager**: HTML report generation with screenshots

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.8.0 or higher
- Chrome, Firefox, or Edge browser
- MySQL (optional, for database operations)

### Installation

1. Clone the repository
2. Navigate to project directory
3. Run `mvn clean install`

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=YourTestClassName

# Run with TestNG XML
mvn test -DsuiteXmlFile=testng.xml
```

### Configuration

Edit `src/test/resources/config.properties` to configure:
- Browser name
- Application URL
- Wait timeouts
- Database credentials

## Creating Test Cases

### 1. Create a Page Object

```java
public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameField;

    public void enterUsername(String username) {
        waitUtility.waitForElementToBeVisible(By.id("username"));
        usernameField.sendKeys(username);
    }
}
```

### 2. Create a Test Class

```java
public class LoginTest extends BaseTest {
    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("testuser");
        // Add assertions
    }
}
```

## SOLID Principles Implementation

- **Single Responsibility**: Each class has a single, well-defined purpose
- **Open/Closed**: Framework is open for extension, closed for modification
- **Liskov Substitution**: Base classes can be substituted by derived classes
- **Interface Segregation**: Specific interfaces for specific utilities
- **Dependency Inversion**: High-level modules depend on abstractions

## Parallel Execution

Tests can be run in parallel using TestNG. Configure in `testng.xml`:

```xml
<suite parallel="tests" thread-count="4">
```

## Reporting

Extent Reports are generated in the `reports/` directory with:
- Test execution summary
- Pass/Fail status
- Screenshots on failure
- Detailed logs

## Logging

Logs are generated in the `logs/` directory using Log4j2 with:
- Console output
- File output
- Rolling file appender

## Best Practices

1. **Use Page Object Model**: Keep page elements and actions separated
2. **Use Explicit Waits**: Avoid implicit waits, prefer explicit waits
3. **Centralized Configuration**: Store all configuration in properties file
4. **Meaningful Logging**: Log important steps for better debugging
5. **Data-Driven Testing**: Use Excel or data providers for test data
6. **Reusable Methods**: Write methods that can be reused across tests
7. **Exception Handling**: Proper exception handling with meaningful messages

## Troubleshooting

### Common Issues

1. **WebDriver not initialized**: Ensure BeforeMethod is running before tests
2. **Element not found**: Verify locators are correct and element is visible
3. **Timeout errors**: Increase wait timeout in config.properties
4. **Screenshot not captured**: Check screenshot directory permissions

## Contributing

Contributions are welcome! Please follow the coding standards and SOLID principles.

## License

This framework is provided as-is for automation testing purposes.

## Support

For issues or questions, please contact the automation team.
