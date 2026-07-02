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

## All 24 Framework Features Implemented

✅ Base Class  
✅ Driver Factory  
✅ Browser Manager  
✅ Config Reader  
✅ Excel Utility  
✅ Screenshot Utility  
✅ Wait Utility  
✅ Actions Utility  
✅ JavaScript Utility  
✅ Database Utility  
✅ Alert Utility  
✅ Dropdown Utility  
✅ Window Utility  
✅ Frame Utility  
✅ Robot Utility  
✅ Date Utility  
✅ Retry Analyzer  
✅ Test Listener  
✅ Extent Report Manager  
✅ Logger Manager  
✅ Soft Assertion Utility  
✅ Random Data Generator  
✅ API Helper  
✅ JSON Utility  

## Project Structure

```
src/main/java/com/mos/automation/
├── base/
│   ├── BaseTest.java
│   └── BasePage.java
├── driver/
│   ├── DriverFactory.java
│   └── DriverManager.java
├── config/
│   └── ConfigReader.java
├── utilities/ (15 utilities)
├── database/
│   └── DatabaseUtility.java
├── logger/
│   └── LoggerManager.java
├── reports/
│   └── ExtentReportManager.java
├── listeners/
│   ├── TestListener.java
│   └── RetryAnalyzer.java
└── pages/
    └── GoogleSearchPage.java
```

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.8.0 or higher

### Installation

```bash
git clone <repository-url>
cd MoS-10-Test
mvn clean install
```

### Running Tests

```bash
mvn test
```

## SOLID Principles

- Single Responsibility
- Open/Closed
- Liskov Substitution
- Interface Segregation
- Dependency Inversion

## License

This framework is provided for automation testing purposes.
