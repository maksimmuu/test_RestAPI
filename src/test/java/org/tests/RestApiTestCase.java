package org.tests;

import io.restassured.response.Response;
import org.constants.ConfigConstants;
import org.constants.ResponseAttribute;
import org.constants.StatusCode;
import org.constants.TestConstants;
import org.models.Post;
import org.models.User;
import org.requests.RequestsToPostId;
import org.requests.RequestsToPosts;
import org.requests.RequestsToUserId;
import org.requests.RequestsToUsers;
import org.testng.annotations.Test;
import org.utils.ApiUtils;
import org.utils.JsonParserUtils;
import org.utils.StringGenerator;

import java.util.List;

public class RestApiTestCase {

    @Test
    public void testGetAllPosts() {
        Response responseGetPosts = RequestsToPosts.doGetRequest();

        ApiUtils.checkStatusCode(responseGetPosts, StatusCode.SUCCESS_200);
        ApiUtils.checkContentType(responseGetPosts, ResponseAttribute.CONTENT_TYPE_JSON);

        List<Post> allPostsList = ApiUtils.getListObjectsByResponse(responseGetPosts, Post.class);
        List<Integer> postsIdList = allPostsList.stream().map(Post::getId).toList();
        List<Integer> postsIdExpectedSortedList = postsIdList.stream().sorted().toList();

        ApiUtils.checkEqualsLists(postsIdList, postsIdExpectedSortedList);
    }

    @Test
    public void testGetPostId99() {
        Response responseGetPostId99 = RequestsToPostId.doGetRequest(TestConstants.POST_ID_99);
        Post postId99 = ApiUtils.getObjectByResponse(responseGetPostId99, Post.class);

        ApiUtils.checkStatusCode(responseGetPostId99, StatusCode.SUCCESS_200);

        ApiUtils.checkEqualsNumbers(postId99.getUserId(), TestConstants.USER_ID_10);
        ApiUtils.checkEqualsNumbers(postId99.getId(), TestConstants.POST_ID_99);
        ApiUtils.checkStringIsNotEmpty(postId99.getTitle());
        ApiUtils.checkStringIsNotEmpty(postId99.getBody());
    }

    @Test
    public void testGetPostId150() {
        Response responseGetPostId150 = RequestsToPostId.doGetRequest(TestConstants.POST_ID_150);
        Post postId150 = ApiUtils.getObjectByResponse(responseGetPostId150, Post.class);
        ApiUtils.checkStatusCode(responseGetPostId150, StatusCode.NOT_FOUND_404);
        ApiUtils.checkStringIsEmpty(postId150.getBody());
    }

    @Test
    public void testCreatePost() {
        Post newPost = new Post();
        String randomTitle = StringGenerator.generate(TestConstants.STRING_LENGTH);
        String randomBody = StringGenerator.generate(TestConstants.STRING_LENGTH);
        newPost.setUserId(TestConstants.USER_ID_1);
        newPost.setTitle(randomTitle);
        newPost.setBody(randomBody);

        Response responsePost = RequestsToPosts.doPostRequest(newPost);

        ApiUtils.checkStatusCode(responsePost, StatusCode.CREATED_201);
        Post receivedPost = ApiUtils.getObjectByResponse(responsePost, Post.class);

        ApiUtils.checkEqualsValueAttribute(receivedPost.getTitle(), newPost.getTitle(), ResponseAttribute.TITLE);
        ApiUtils.checkEqualsValueAttribute(receivedPost.getBody(), newPost.getBody(), ResponseAttribute.BODY);
        ApiUtils.checkEqualsValueAttribute(receivedPost.getUserId(), newPost.getUserId(), ResponseAttribute.USER_ID);
        ApiUtils.checkValueAttributeNotNull(receivedPost.getId(), ResponseAttribute.ID);
    }

    @Test
    public void testGetAllUsers() {
        Response responseGetUsers = RequestsToUsers.doGetRequest();

        ApiUtils.checkStatusCode(responseGetUsers, StatusCode.SUCCESS_200);
        ApiUtils.checkContentType(responseGetUsers, ResponseAttribute.CONTENT_TYPE_JSON);

        List<User> allUsersList = ApiUtils.getListObjectsByResponse(responseGetUsers, User.class);

        User receivedUserId5 = allUsersList.stream().filter(x -> x.getId().equals(TestConstants.USER_ID_5)).findFirst().orElse(null);
        User testUser = JsonParserUtils.parseToObject(ConfigConstants.PATH_TO_TEST_FILE, User.class);

        ApiUtils.checkEqualsUsers(receivedUserId5, testUser);
    }

    @Test
    public void testGetUserId5() {
        Response responseGetUserId5 = RequestsToUserId.doGetRequest(TestConstants.USER_ID_5);

        ApiUtils.checkStatusCode(responseGetUserId5, StatusCode.SUCCESS_200);

        User userId5 = ApiUtils.getObjectByResponse(responseGetUserId5, User.class);
        Response responseGetUsers = RequestsToUsers.doGetRequest();
        List<User> allUsersList = ApiUtils.getListObjectsByResponse(responseGetUsers, User.class);
        User receivedUserId5 = allUsersList.stream().filter(x -> x.getId().equals(TestConstants.USER_ID_5)).findFirst().orElse(null);

        ApiUtils.checkEqualsUsers(receivedUserId5, userId5);
    }
}
