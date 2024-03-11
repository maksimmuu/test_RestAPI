package org.utils;

import io.restassured.response.Response;
import org.constants.JsonConstants;
import org.models.User;
import org.testng.Assert;

import java.util.List;

public class ApiUtils {
    public static <T> T getObjectByResponse(Response response, Class<T> objectClass) {
        return response.then().extract().body().as(objectClass);
    }

    public static <T> List<T> getListObjectsByResponse(Response response, Class<T> objectsClass) {
        return response.then().extract().body().jsonPath().getList(JsonConstants.ROOT_JSON, objectsClass);
    }

    public static void checkStatusCode(Response response, int expectedStatusCode) {
        int actualStatus = response.getStatusCode();
        Assert.assertEquals(actualStatus, expectedStatusCode, String.format("Код ответа '%d' не соответствует ожидаемому коду '%d'", actualStatus, expectedStatusCode));
    }

    public static void checkContentType(Response response, String expectedContentType) {
        String actualContentType = response.contentType();
        Assert.assertTrue(actualContentType.contains(expectedContentType), String.format("Content-type полученного ответа '%s' не соответствует ожидаемому '%s'", actualContentType, expectedContentType));
    }

    public static void checkEqualsLists(List<?> actualList, List<?> expectedList) {
        Assert.assertEquals(actualList, expectedList, String.format("Полученный список '%s' не соответствует ожидаемому '%s'", actualList, expectedList));
    }

    public static void checkEqualsValueAttribute(Object actualValue, Object expectedValue, String typeAttribute) {
        Assert.assertEquals(actualValue, expectedValue, String.format("Значение в атрибуте '%s' актуального результата '%s' не соответствует ожидаемому значению '%s'", typeAttribute, actualValue, expectedValue));
    }

    public static void checkValueAttributeNotNull(Object actualValue, String typeAttribute) {
        Assert.assertNotNull(actualValue, String.format("Значение в атрибуте '%s' отсутствует", typeAttribute));
    }

    public static void checkEqualsUsers(User actualUser, User expectedUser) {
        Assert.assertEquals(actualUser, expectedUser, String.format("Пользовательские данные user '%s' не совпадают с ожидаемыми данными user '%s'", actualUser, expectedUser));
    }

    public static void checkStringIsNotEmpty(String stringToBeChecked) {
        Assert.assertTrue(stringToBeChecked != null && !stringToBeChecked.isEmpty(), String.format("Переданная строка '%s' является пустой", stringToBeChecked));
    }

    public static void checkStringIsEmpty(String stringToBeChecked) {
        Assert.assertTrue(stringToBeChecked == null || stringToBeChecked.isEmpty(), String.format("Переданная строка '%s' является непустой", stringToBeChecked));
    }

    public static void checkEqualsNumbers(Number actualNumber, Number expectedNumber) {
        Assert.assertEquals(actualNumber, expectedNumber, String.format("Полученное число '%s' не равно ожидаемому '%s'", actualNumber, expectedNumber));
    }
}
