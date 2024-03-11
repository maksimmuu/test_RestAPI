package org.requests;

import io.restassured.response.Response;

public class RequestsToUsers extends BaseRequest {
    private static final String URL_USERS = "/users";

    public static Response doGetRequest() {
        return getRequest(URL_USERS);
    }
}
