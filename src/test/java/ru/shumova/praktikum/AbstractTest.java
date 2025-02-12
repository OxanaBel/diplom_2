package ru.shumova.praktikum;

import io.qameta.allure.Step;
import ru.shumova.praktikum.dto.UserCreateRequest;
import ru.shumova.praktikum.dto.UserLoginRequest;
import ru.shumova.praktikum.service.UserService;

import static ru.shumova.praktikum.config.TestData.*;

public class AbstractTest {
    UserService userService = new UserService();
    String token;

    @Step("Подготовка данных")
    public void createUser() {
        UserCreateRequest userCreateRequest = new UserCreateRequest(EMAIL, PASSWORD, NAME);
        userService.createUser(userCreateRequest).then().statusCode(200);
    }

    @Step("Удаление данных")
    public void deleteUser() {
        if (token == null) {
            token = userService.loginUser(new UserLoginRequest(EMAIL, PASSWORD)).then().extract().body().path("accessToken");
        }
        userService.deleteUser(token);
    }
}
