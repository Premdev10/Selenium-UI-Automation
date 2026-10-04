package com.framework.stepdefinitions;

import com.framework.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class BaseSteps {
    @Before
    public void setup(Scenario scenario) {
        WebDriver driver = DriverFactory.initDriver();
        driver.get(loadUrl(scenario));
    }

    private String loadUrl(Scenario scenario) {
        Properties config = new Properties();
        try (InputStream input = BaseSteps.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties was not found on the classpath");
            }
            config.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load config.properties", e);
        }

        List<String> urlTags = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith("@url_"))
                .map(tag -> tag.substring(1))
                .collect(Collectors.toList());
        if (urlTags.size() > 1) {
            throw new IllegalStateException("A scenario can specify only one URL tag, but found " + urlTags);
        }
        String propertyName = urlTags.isEmpty() ? "url" : urlTags.get(0);
        String url = config.getProperty(propertyName);
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalStateException(
                    "The '" + propertyName + "' property is missing or empty in config.properties");
        }
        return url.trim();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = DriverFactory.getDriver();
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
        }
        DriverFactory.quitDriver();
    }
}
