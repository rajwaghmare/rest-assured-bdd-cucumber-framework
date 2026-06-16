package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.api_chain_page;
import io.restassured.response.Response;

public class api_chain_step {

    api_chain_page apiPage = new api_chain_page();
    Response response;

    @Given("User creates a new user")
    public void user_creates_a_new_user() {
        response = apiPage.createUser();
    }

    @When("User retrieves the created user")
    public void user_retrieves_the_created_user() {
        response = apiPage.retrieveUsers();
    }

    @When("User updates the user details")
    public void user_updates_the_user_details() {
        response = apiPage.updateUser();
    }

    @Then("User deletes the user")
    public void user_deletes_the_user() {
        response = apiPage.deleteUser();
    }
}