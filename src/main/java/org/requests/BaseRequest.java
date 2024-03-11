package org.requests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.constants.ConfigConstants;
import static io.restassured.RestAssured.given;

public abstract class BaseRequest {
    private static void installSpecification() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConfigConstants.URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    private static RequestSpecification givenWithAllLog() {
        installSpecification();
        return given()
                .when().log().all();
    }

    protected static Response postRequestWithBody(String url, Object body) {
        return givenWithAllLog()
                .body(body)
                .post(url);
    }

    protected static Response getRequest(String url) {
        return givenWithAllLog()
                .get(url);
    }
}
