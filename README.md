# Selenium BDD Framework

A Java-based browser automation framework using Selenium WebDriver, Cucumber, and TestNG. Scenarios are written in Gherkin, with page interactions organized in page-object classes.

## Requirements

- Java 11 or later
- Maven
- Google Chrome

## Configuration

Update `src/test/resources/config.properties` with the URLs for your test environments:

```properties
url=https://your-default-test-site.example
url_window=https://your-window-test-site.example
```

The default URL is selected when a scenario has no URL tag. Add a tag such as `@url_window` to a scenario to use the matching property (`url_window`). A tag beginning with `@url_` must have a corresponding property in the config file.

## Run tests

From the project root, run:

```bash
mvn test
```

The current TestNG/Cucumber runner is `com.framework.runners.TestRunner`. Its feature path is configured in `src/test/java/com/framework/runners/TestRunner.java`.

## Project layout

- `src/main/java/com/framework/factory` — WebDriver lifecycle
- `src/main/java/com/framework/pages` — page-object classes
- `src/main/java/com/framework/utils` — shared test utilities, including Excel reading
- `src/test/java/com/framework/stepdefinitions` — Cucumber step definitions and hooks
- `src/test/java/com/framework/runners` — Cucumber/TestNG runner
- `src/test/resources/features` — Gherkin feature files
- `src/test/resources/config.properties` — test URLs
- `src/test/resources/testdata` — test data files

Failed scenarios attach a browser screenshot to the Cucumber report. Extent Reports settings are in `src/test/resources/extent.properties`.
