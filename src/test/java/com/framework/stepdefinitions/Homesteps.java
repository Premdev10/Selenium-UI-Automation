package com.framework.stepdefinitions;

import com.framework.factory.DriverFactory;
import com.framework.pages.Homepage;
import com.framework.pages.LoginPage;
import com.framework.utils.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class Homesteps {
    private Homepage homepage;
    private String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
    List<Map<String, String>> testData;

    @And("user clicks on the banking section")
    public void click_banking_section(){
        homepage = new Homepage(DriverFactory.getDriver());
        homepage.click_banking();

    }

    @Then("validate user completes the transfer request with amount {int}")
    public void user_validates_transfer_request(int amount){
        homepage.validate_transfer(amount);


    }

    @And("user clicks on the alert {string} button")
    public void click_alert(String alerttype){
        homepage = new Homepage(DriverFactory.getDriver());
        homepage.click_alert(alerttype);

    }

    @And("user clicks on the beneficiary tab")
    public void click_beneficiary(){
        homepage = new Homepage(DriverFactory.getDriver());
        homepage.click_beneficiary_link();
    }

    @Then("verify beneficiary added successfully")
    public void verify_beneficiary(){
        homepage = new Homepage(DriverFactory.getDriver());
        homepage.add_beneficiary();
    }
}
