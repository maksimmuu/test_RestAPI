package org.requests;

import io.restassured.response.Response;
import org.models.Post;

public class RequestsToPosts extends BaseRequest {
    private static final String URL_POSTS = "/posts";

    public static Response doGetRequest() {
        return getRequest(URL_POSTS);
    }

    public static Response doPostRequest(Post newPost) {
        return postRequestWithBody(URL_POSTS, newPost);
    }
}
