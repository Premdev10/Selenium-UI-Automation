package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class WindowPage {

    private final WebDriverWait wait;
    private final WebDriver driver;
    private String parentWindow;
    private Set<String> windowHandlesBeforeClick;
    private static final By WINDOW_HEADER = By.xpath("//h2[text()='Multiple Windows']");
    private static final By PROJECT_DETAILS_HEADER = By.xpath("//h2[text()='Project Details']");
    private static final By NEW_TAB_TABLE = By.xpath("//table[@id='myTable']");

    public WindowPage(WebDriver driver) {
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void user_on_windowhomepage(){
        String header = wait.until(ExpectedConditions.visibilityOfElementLocated(WINDOW_HEADER)).getText();
        if(header.equalsIgnoreCase("Multiple Windows")){
            System.out.println("page loaded and got correct header");
        }
        else {
            System.out.println("page not loaded/got inccorrect header");
        }
    }

    public void click_button(String but_name) {
        parentWindow = driver.getWindowHandle();
        windowHandlesBeforeClick = new HashSet<>(driver.getWindowHandles());
        By button = By.xpath("//button[normalize-space()='" + but_name + "']");
        wait.ignoring(StaleElementReferenceException.class).until(currentDriver -> {
            if (currentDriver.getWindowHandles().size() > windowHandlesBeforeClick.size()) {
                return true;
            }
            WebElement clickableButton = ExpectedConditions.elementToBeClickable(button).apply(currentDriver);
            if (clickableButton == null) {
                return false;
            }
            clickableButton.click();
            return true;
        });
        System.out.println("clicked on new tab button");
    }
    public void navigate_newtab(){
        if (windowHandlesBeforeClick == null) {
            throw new IllegalStateException("Click the new-tab button before navigating to the new tab");
        }
        wait.until(driver -> driver.getWindowHandles().size() > windowHandlesBeforeClick.size());
        Set<String> newWindows = new HashSet<>(driver.getWindowHandles());
        newWindows.removeAll(windowHandlesBeforeClick);
        if (newWindows.isEmpty()) {
            throw new IllegalStateException("Clicking the new-tab button did not open a new window");
        }
        driver.switchTo().window(newWindows.iterator().next());
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECT_DETAILS_HEADER));
        System.out.println("switched to new window");
    }

    public void verify_content_newpage(){
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(NEW_TAB_TABLE)).isDisplayed()){
            System.out.println("table displayed");
        }
        else {
            System.out.println("table not displayed");
        }
        driver.switchTo().window(parentWindow);
    }
}
