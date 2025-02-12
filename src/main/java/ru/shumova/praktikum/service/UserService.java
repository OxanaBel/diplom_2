package ru.shumova.praktikum.service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.shumova.praktikum.dto.UserCreateRequest;
import ru.shumova.praktikum.dto.UserLoginRequest;
import ru.shumova.praktikum.dto.UserUpdateDataRequest;

import static ru.shumova.praktikum.util.RequestHelper.*;

public class UserService {

    @Step("Создание пользователя")
    public Response createUser(UserCreateRequest request) {
        return getRequestSpec().body(request).when().post(AUTH_REGISTER_API_PATH);
    }

    @Step("Удаление пользователя")
    public void deleteUser(String token) {
        getRequestSpec().header("authorization", token).when().delete(AUTH_USER_API_PATH);
    }

    @Step("Логин пользователя")
    public Response loginUser(UserLoginRequest request) {
        return getRequestSpec().body(request).when().post(AUTH_LOGIN_API_PATH);
    }

    @Step("Изменение данных пользователя")
    public Response updateUser(UserUpdateDataRequest request, String token) {
        return getRequestSpec().header("authorization", token).body(request).when().patch(AUTH_USER_API_PATH);
    }
}
