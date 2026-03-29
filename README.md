# rest-assured-bdd-cucumber-framework
# REST Assured BDD Cucumber Framework

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Cucumber](https://img.shields.io/badge/Cucumber-7.34.3-brightgreen?style=flat-square&logo=cucumber)
![REST Assured](https://img.shields.io/badge/REST--Assured-5.3.2-blue?style=flat-square)
![Allure](https://img.shields.io/badge/Allure-2.33.0-purple?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3.9+-red?style=flat-square&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

A production-ready **API Test Automation Framework** built with BDD Cucumber, REST Assured, and Allure Reports. Designed for scalable, maintainable API testing using the Gherkin syntax with rich HTML reporting.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [Allure Reports](#allure-reports)
- [Writing Tests](#writing-tests)
- [Configuration](#configuration)
- [CI/CD](#cicd)
- [Author](#author)

---

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 21 | Core language |
| Maven | 3.9+ | Build & dependency management |
| Cucumber | 7.34.3 | BDD framework (Gherkin) |
| REST Assured | 5.3.2 | API request/response handling |
| Allure | 2.33.0 | HTML test reporting |
| JUnit 5 | 5.10.3 | Test runner (JUnit Platform) |
| Jackson Databind | 2.17.2 | JSON serialization/deserialization |
| AspectJ Weaver | 1.9.22 | Allure `@Step` instrumentation |

---

## Project Structure

```
bdd_cucumber_api_automation_framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── api/
│   │               ├── constants/        # API endpoints, base URIs
│   │               ├── model/            # POJO / request-response models
│   │               └── utils/            # Utility helpers
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── api/
│       │           ├── hooks/            # @Before / @After Cucumber hooks
│       │           ├── runner/           # Test runner class
│       │           └── stepDefs/         # Step definition classes
│       └── resources/
│           ├── features/                 # .feature files (Gherkin scenarios)
│           ├── allure.properties         # Allure configuration
│           └── junit-platform.properties # JUnit platform config
├── pom.xml
└── README.md
```

---

## Prerequisites

Ensure the following are installed on your machine:

- **Java 21** — [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.9+** — [Download](https://maven.apache.org/download.cgi)
- **Allure CLI** — Install via npm:

```bash
npm install -g allure-commandline
```

Or via Homebrew (Mac):

```bash
brew install allure
```

Verify installations:

```bash
java -version
mvn -version
allure --version
```

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/rajwaghmare/rest-assured-bdd-cucumber-framework.git
cd rest-assured-bdd-cucumber-framework
```

### 2. Install dependencies

```bash
mvn clean install -U
```

### 3. Verify the setup

```bash
mvn dependency:resolve
```

---

## Running Tests

### Run all tests

```bash
mvn clean test
```

### Run tests by tag

```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="@regression"
mvn clean test -Dcucumber.filter.tags="@smoke and @regression"
```

### Run and generate Allure report in one command

```bash
mvn clean test && allure serve target/allure-results
```

---

## Allure Reports

### Generate and open (recommended)

```bash
# Run tests first
mvn clean test

# Serve report in browser instantly
mvn allure:serve
```

### Generate static HTML report

```bash
allure generate target/allure-results -o target/allure-report --clean
allure open target/allure-report
```

### What the report shows

- **Overview** — pass/fail summary, trend graph, environment info
- **Suites** — each `.feature` file as a suite, each Scenario as a test case
- **Behaviors** — grouped by `@Epic` → `@Feature` → `@Story`
- **API attachments** — full HTTP request + response body logged automatically per step (via `AllureRestAssured` filter)
- **Timeline** — parallel execution view

---

## Writing Tests

### 1. Create a feature file

```gherkin
# src/test/resources/features/users.feature

@smoke
Feature: User API

  @regression
  Scenario: Get user by ID
    Given the API base URI is configured
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should contain field "name"
```

### 2. Create step definitions

```java
// src/test/java/com/api/stepDefs/UserStepDefs.java

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.*;

@Epic("User Management")
@Feature("Get User")
public class UserStepDefs {

    private Response response;

    @Given("the API base URI is configured")
    public void configureBaseUri() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Step("Send GET request to {endpoint}")
    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = RestAssured
            .given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
            .when()
                .get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }
}
```

### 3. Register the Allure plugin in your runner

```java
// src/test/java/com/api/runner/TestRunner.java

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
    value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@smoke")
public class TestRunner {}
```

---

## Configuration

### allure.properties

```properties
# src/test/resources/allure.properties
allure.results.directory=target/allure-results
allure.link.issue.pattern=https://jira.example.com/browse/{}
allure.link.tms.pattern=https://jira.example.com/browse/{}
```

### junit-platform.properties

```properties
# src/test/resources/junit-platform.properties
cucumber.publish.quiet=true
cucumber.plugin=pretty, json:target/cucumber-reports/cucumber.json
```

---

## CI/CD

### GitHub Actions

```yaml
# .github/workflows/rest_assured.yml
name: API Automation Tests

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Set up Java 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Run tests
        run: mvn clean test

      - name: Upload Allure results
        uses: actions/upload-artifact@v4
        if: always()
        with:
          name: allure-results
          path: target/allure-results

      - name: Generate Allure report
        run: mvn allure:report

      - name: Upload Allure report
        uses: actions/upload-artifact@v4
        if: always()
        with:
          name: allure-report
          path: target/site/allure-maven-plugin
```

---

## Author

**Raj G. Waghmare**
Senior Automation Test Engineer | Lead Software Engineer
Virtusa Consulting Services, Pune, India

[![GitHub](https://img.shields.io/badge/GitHub-rajwaghmare-black?style=flat-square&logo=github)](https://github.com/rajwaghmare)

---

> Built with focus on scalability, maintainability, and rich reporting for enterprise API test automation.
