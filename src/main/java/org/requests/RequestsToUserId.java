package org.requests;

import io.restassured.response.Response;

public class RequestsToUserId extends BaseRequest {
    private static final String URL_USER_ID = "/users/";

    public static Response doGetRequest(int userId) {
        return getRequest(URL_USER_ID + userId);
    }
}
