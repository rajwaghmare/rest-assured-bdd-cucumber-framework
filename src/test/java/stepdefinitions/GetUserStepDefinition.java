package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.BaseURIConfigJsonReader;
import utility.SetHeaderConfig;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class GetUserStepDefinition {
    Response response;
    RequestSpecification request;
    String requestBody;


    @Given("User set the base URI for GET request")
    public void user_set_the_base_uri_for_get_request() {
        baseURI = BaseURIConfigJsonReader.getBaseURI();
        request = given();
    }

    @When("User sets the request header Connection for GET request")
    public void user_sets_the_request_header_connection_for_get_request() {
        request = SetHeaderConfig.setHeadersForGetUserDetails();
    }

    @Then("User sends GET request to collect user details")
    public void user_sends_get_request_to_collect_user_details() {
        response = request
                .when()
                .get(BaseURIConfigJsonReader.getUserEndpoint());
        response.then().log().all();
    }

    @Then("User should receive status code should be {int}")
    public void user_should_receive_status_code_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("User verify the response body contains user details")
    public void user_verify_the_response_body_contains_user_details() {
        List<Map<String, Object>> users = response.jsonPath().getList("");

        Map<String, Object> user = users.get(0);

        assertEquals(101, user.get("userId"));
        assertEquals("Michael Thompson", user.get("name"));
        assertEquals("michael.thompson@company.com", user.get("email"));
        assertEquals("Senior Software Engineer", user.get("job"));
        assertEquals("New York", user.get("city"));
    }
}
