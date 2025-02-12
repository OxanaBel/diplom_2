package ru.shumova.praktikum.util;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestHelper {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/";
    public static final String AUTH_REGISTER_API_PATH = "auth/register";
    public static final String AUTH_USER_API_PATH = "auth/user";
    public static final String AUTH_LOGIN_API_PATH = "auth/login";
    public static final String API_ORDERS= "orders";

    public static RequestSpecification getRequestSpec() {
        return given().baseUri(BASE_URL).contentType(ContentType.JSON);
    }
}
