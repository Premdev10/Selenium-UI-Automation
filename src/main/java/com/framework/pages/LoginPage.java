package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriverWait wait;

    @FindBy(id = "login-username")
    private WebElement usernameInput;

    @FindBy(id = "login-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@data-testid='login-captcha-toggle']")
    private WebElement disable_captcha;

    @FindBy(xpath="//button[@data-testid='login-submit-btn']")
    private WebElement loginBtn;

    @FindBy(id="otp-input")
    private WebElement otp_field;

    @FindBy(xpath="//button[text()='Verify']")
    private WebElement verify_but;

    @FindBy(xpath = "//button[@data-testid='nav-dashboard']")
    private WebElement nav_dashboard;

    @FindBy(xpath="//div[@data-testid='login-error-banner']")
    private WebElement login_error;



    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
    }
    public void disablecaptcha(){
        wait.until(ExpectedConditions.elementToBeClickable(disable_captcha)).click();
    }

    public void clickLogin(String otp) {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(otp_field)).sendKeys(otp);
        verify_but.click();

    }
    
    public void isDashboardDisplayed() {
         wait.until(ExpectedConditions.visibilityOf(nav_dashboard)).isDisplayed();
         if (nav_dashboard.isDisplayed()){
             System.out.println("dashboard displayed");
         }
         else {
             System.out.println("dashboard not displayed");
         }
    }

    public void verify_error_message(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(login_error));

        if(login_error.getText().contains("Invalid credentials. Hint:")){
            System.out.println("verified error message");
        }
        else{
            System.out.println("error message is incorrect");
        }

    }
}
