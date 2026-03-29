package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.ObjectUtils;
import utility.BaseURIConfigJsonReader;
import static io.restassured.RestAssured.*;
import utility.SetHeaderConfig;
import utility.UserPayloadReader;

import static org.hamcrest.Matchers.equalTo;


public class add_user_step {
    Response response;
    RequestSpecification request;
    String requestBody;
    String payload;

    @Given("User sets the base URI")
    public void user_sets_the_base_uri() {
        baseURI = BaseURIConfigJsonReader.getBaseURI();
        request = given();
    }
    @Given("User sets the request header Content-Type")
    public void user_sets_the_request_header_content_type() {
        request = SetHeaderConfig.setHeaders();

    }
    @Given("User sets the request body for creating user")
    public void user_sets_the_request_body_for_creating_user() {

        payload  = UserPayloadReader.getCreateUserPayload();
        if (payload == null || payload.isEmpty()) {
            throw new RuntimeException("Failed to read the request body from the JSON file.");
        }
        requestBody = payload;
    }
    @When("User sends POST request to {string}")
    public void user_sends_post_request_to(String resource) {
        response = request
                .body(requestBody)
                .when()
                .post(BaseURIConfigJsonReader.getCreateUserEndpoint());
        response.then().log().all();

    }
    @Then("User should receive status code {int}")
    public void user_should_receive_status_code(Integer statusCode) {

        response.then().statusCode(statusCode);
    }
    @Then("Response message should be {string}")
    public void response_message_should_be(String message) {
        response.then()
                .body("message", equalTo(message));

    }
}
