package pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.BaseURIConfigJsonReader;
import utility.SetHeaderConfig;
import utility.UserPayloadReader;

import static io.restassured.RestAssured.*;

public class api_chain_page {

    private Response response;
    private RequestSpecification request;
    private Integer createdUserId;
    private String requestBody;
    String deleteUserEndpoint = "";

    public Response createUser() {
        baseURI = BaseURIConfigJsonReader.getBaseURI();
        request = SetHeaderConfig.setHeaders();

        String payload = UserPayloadReader.getCreateUserPayload();
        if (payload == null || payload.isEmpty()) {
            throw new RuntimeException("Failed to read create user payload.");
        }
        requestBody = payload;

        response = request
                .body(requestBody)
                .when()
                .post(BaseURIConfigJsonReader.getCreateUserEndpoint());
        response.then().log().all();

        // attempt to extract id from common locations
        try {
            Object idObj = response.jsonPath().get("data.id");
            if (idObj instanceof Number) {
                createdUserId = ((Number) idObj).intValue();
            } else {
                idObj = response.jsonPath().get("id");
                if (idObj instanceof Number) {
                    createdUserId = ((Number) idObj).intValue();
                }
            }
        } catch (Exception e) {
            // ignore - createdUserId may remain null
        }

        return response;
    }

    public Response retrieveUsers() {
        baseURI = BaseURIConfigJsonReader.getBaseURI();
        request = SetHeaderConfig.setHeadersForGetUserDetails();

        response = request
                .when()
                .get(BaseURIConfigJsonReader.getUserEndpoint());
        response.then().log().all();
        return response;
    }

    public Response updateUser() {
        baseURI = BaseURIConfigJsonReader.getBaseURI();
        request = SetHeaderConfig.setHeadersForUpdate();

        String payload = UserPayloadReader.getUpdateUserPayload();
        if (payload == null || payload.isEmpty()) {
            throw new RuntimeException("Failed to read update user payload.");
        }
        requestBody = payload;

        String updateEndpoint = BaseURIConfigJsonReader.getUpdateUserEndpoint();
        if (createdUserId != null) {
            updateEndpoint = updateEndpoint.replaceAll("\\d+", createdUserId.toString());
            if (!updateEndpoint.startsWith("/")) {
                updateEndpoint = "/" + updateEndpoint;
            }
        }

        response = request
                .body(requestBody)
                .when()
                .put(updateEndpoint);
        response.then().log().all();
        return response;
    }

    public Response deleteUser() {
        baseURI = BaseURIConfigJsonReader.getBaseURI();
        request = given();

        int idToDelete = createdUserId != null ? createdUserId.intValue() : 101;

        String deleteEndpoint = BaseURIConfigJsonReader.getDeleteUserEndpoint();
        if (deleteEndpoint != null) {
            // replace any digits in the endpoint (e.g. .../102/) with the actual id
            deleteEndpoint = deleteEndpoint.replaceAll("\\d+", String.valueOf(idToDelete));
            if (!deleteEndpoint.startsWith("/")) {
                deleteEndpoint = "/" + deleteEndpoint;
            }
        } else {
            // fallback if endpoint not configured

            deleteEndpoint = deleteUserEndpoint + idToDelete + "/";
            
        }

        response = request
                .when()
                .delete(deleteEndpoint);
        response.then().log().all();
        return response;
    }

    public Integer getCreatedUserId() {
        return createdUserId;
    }

    public Response getResponse() {
        return response;
    }
}


