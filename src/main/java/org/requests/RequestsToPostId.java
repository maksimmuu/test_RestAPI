package org.requests;

import io.restassured.response.Response;

public class RequestsToPostId extends BaseRequest {
    private static final String URL_POST_ID = "/posts/";

    public static Response doGetRequest(int postId) {
        return getRequest(URL_POST_ID + postId);
    }
}
