package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.BaseURIConfigJsonReader;
import static io.restassured.RestAssured.*;
import utility.SetHeaderConfig;
import utility.UserPayloadReader;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class update_user_step {
    Response response;
    RequestSpecification request;
    String requestBody;
    String payload;


    @Given("User sets the base URI for PUT request")
    public void user_sets_the_base_uri_for_put_request() {
        baseURI = BaseURIConfigJsonReader.getBaseURI();
        request = given();

    }

    @Given("User sets the request header Content-Type for PUT request")
    public void user_sets_the_request_header_content_type_for_put_request() {

        request = SetHeaderConfig.setHeadersForUpdate();
    }

    @Given("User sets the request body for updating user details")
    public void user_sets_the_request_body_for_updating_user_details() {

        payload = UserPayloadReader.getUpdateUserPayload();
        if (payload == null || payload.isEmpty()) {
            throw new RuntimeException("Failed to read the request body from the JSON file.");
        }
        requestBody = payload;
    }

    @When("User sends PUT request")
    public void user_sends_put_request() {
        response = request
                .body(requestBody)
                .when()
                .put(BaseURIConfigJsonReader.getUpdateUserEndpoint());
        response.then().log().all();
    }

    @Then("User should receive updated status code {int}")
    public void user_should_receive_updated_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);

    }

    @Then("Update user response message should be {string}")
    public void update_user_response_message_should_be(String message) {
        response.then()
                .body("message", equalTo(message));
    }
}
