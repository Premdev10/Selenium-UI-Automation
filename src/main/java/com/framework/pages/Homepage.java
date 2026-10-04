package com.framework.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Homepage {
    private final WebDriverWait wait;
    public WebDriver driver;

    @FindBy(xpath="//button[@data-testid='nav-banking']")
    private WebElement banking_field;

    @FindBy(xpath="//p[text()='Available balance']")
    private WebElement balance_field;

    @FindBy(xpath="//button[@data-testid='tab-transfer']")
    private WebElement transfer_tab;

    @FindBy(id="transfer-beneficiary")
    private WebElement beneficiary_dropdown;

    @FindBy(id="transfer-amount")
    private WebElement transfer_amount;

    @FindBy(xpath = "//button[@data-testid='transfer-submit-btn']")
    private WebElement submit_button;

    @FindBy(id="transfer-otp-input")
    private WebElement otp_input;

    @FindBy(xpath = "//button[text()='Confirm transfer']")
    private WebElement confirm_transfer;

    @FindBy(xpath="//button[@data-testid='native-alert-btn']")
    private WebElement nativealert;

    @FindBy(xpath="//button[@data-testid='native-confirm-btn']")
    private WebElement nativeconfirm;

    @FindBy(xpath="//button[@data-testid='native-prompt-btn']")
    private WebElement nativeprompt;

    @FindBy(xpath="//button[@data-testid='tab-beneficiaries']")
    private WebElement beneficiary_tab;

    @FindBy(xpath="//button[@data-testid='add-beneficiary-btn']")
    private WebElement Add_beneficiary;

    @FindBy(xpath="//input[@id='bene-name']")
    private WebElement ben_name;

    @FindBy(xpath="//input[@id='bene-account']")
    private WebElement ben_acc;

    @FindBy(xpath="//input[@id='bene-ifsc']")
    private WebElement ben_ifsc;

    @FindBy(xpath="//input[@id='bene-bank']")
    private WebElement ben_bank;

    @FindBy(xpath = "//button[@data-testid='beneficiary-save-btn']")
    private WebElement ben_save;




    public Homepage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click_banking(){
        banking_field.click();
    }

    public void validate_transfer(int amount){
        wait.until(ExpectedConditions.visibilityOf(balance_field));
        transfer_tab.click();
        wait.until(ExpectedConditions.visibilityOf(beneficiary_dropdown));
        Select select= new Select(beneficiary_dropdown);
        select.selectByValue("B-2");
        transfer_amount.sendKeys(String.valueOf(amount));
        submit_button.click();
        wait.until(ExpectedConditions.visibilityOf(otp_input)).sendKeys("123456");
        confirm_transfer.click();

    }

    public void click_alert(String alerttype){

        if(alerttype.equalsIgnoreCase("native")){
            nativealert.click();
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("native alert content is: "+alert.getText());
            alert.accept();
        }
        else if(alerttype.equalsIgnoreCase("confirm")){
            nativeconfirm.click();
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("confirm alert content is: "+alert.getText());
            alert.dismiss();
        }
        else {
            nativeprompt.click();
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.sendKeys("this is a test");
            alert.accept();
        }



    }

    public void click_beneficiary_link(){
        wait.until(ExpectedConditions.visibilityOf(beneficiary_tab)).click();

    }

    public void add_beneficiary(){
        wait.until(ExpectedConditions.visibilityOf(Add_beneficiary)).click();
        String person="sajeev";
        wait.until(ExpectedConditions.visibilityOf(ben_name)).sendKeys(person);
        ben_acc.sendKeys("savings account");
        ben_ifsc.sendKeys("SBI1123012");
        ben_bank.sendKeys("sbi");
        ben_save.click();
        List<WebElement> emp_name=driver.findElements(By.xpath("//table//tbody//tr//td[1]"));
        for(int i=0;i<emp_name.size();i++){
            String name=emp_name.get(i).getText();
            if(name.equals(person)){
                System.out.println("name added successfully");
                break;
            }

        }
    }


}
