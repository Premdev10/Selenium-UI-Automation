package com.framework.stepdefinitions;

import com.framework.factory.DriverFactory;
import com.framework.pages.LoginPage;
import com.framework.utils.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class LoginSteps {
    private LoginPage loginPage;
    private String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
    List<Map<String, String>> testData;

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @When("User fills the login form from given sheetname {string} and rownumber {int}")
    public void user_fills_the_login_form_from_given_sheetname_and_rownumber(String sheetName, Integer rowNumber) {
        // Reads Excel into a List of Maps. Adding new columns in Excel requires zero changes to the reader class.
        testData = ExcelReader.getData(excelFilePath, sheetName);
        
        String username = testData.get(rowNumber).get("username");
        String password = testData.get(rowNumber).get("password");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        

    }
    @And("user disables the captcha")
    public void disable_cpatcha(){
        loginPage.disablecaptcha();
    }

    @And("User clicks on login button from given sheetname {string} and rownumber {int}")
    public void user_clicks_on_login_button(String sheetname,Integer rownumber) {
        testData = ExcelReader.getData(excelFilePath, sheetname);
        String otp=testData.get(rownumber).get("otp");
        loginPage.clickLogin(otp);
    }

    @Then("User should be navigated to the dashboard")
    public void user_should_be_navigated_to_the_dashboard() {

        loginPage.isDashboardDisplayed();
    }

    @Then("verify the error message after clicking on the login button")
    public void verify_error_message(){
        loginPage.verify_error_message();

    }
}
