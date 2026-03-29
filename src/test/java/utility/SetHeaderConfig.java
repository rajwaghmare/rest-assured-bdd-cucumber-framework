package utility;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SetHeaderConfig {
    public static RequestSpecification setHeaders() {

        return given()
                .header("Content-Type", "application/json");

    }

    public static RequestSpecification setHeadersForUpdate() {

        return given()
                .header("Content-Type", "application/json");

    }

    public static RequestSpecification setHeadersForGetUserDetails() {

        return given()
                .header("Connection", "keep-alive");

    }
}
