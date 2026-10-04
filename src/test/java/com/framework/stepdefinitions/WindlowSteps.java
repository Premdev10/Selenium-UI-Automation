package com.framework.stepdefinitions;

import com.framework.factory.DriverFactory;
import com.framework.pages.WindowPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WindlowSteps{

    private WindowPage windowPage;

    @Given("User is on the multiple windows page")
    public void user_is_on_window_page(){
        windowPage=new WindowPage(DriverFactory.getDriver());
        windowPage.user_on_windowhomepage();

    }
    @When("user clicks on the {string}")
    public void user_clicks_button(String button_name) {
        windowPage.click_button(button_name);

    }

    @And("user navigates to the new tab")
    public void navigate_newtab(){
        windowPage.navigate_newtab();

    }
    @Then("verify the content in the new tab")
    public void verify_content(){
        windowPage.verify_content_newpage();

    }
}
