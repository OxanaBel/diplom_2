package ru.shumova.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.shumova.praktikum.dto.UserLoginRequest;
import ru.shumova.praktikum.dto.UserUpdateDataRequest;

import static org.hamcrest.Matchers.is;
import static ru.shumova.praktikum.config.TestData.*;

public class UserUpdateTest extends AbstractTest {

    @Before
    public void init() {
        createUser();
        token = userService.loginUser(new UserLoginRequest(EMAIL, PASSWORD)).then().extract().body().path("accessToken");
    }

    @Test
    @DisplayName("Изменение имени пользователя с авторизацией")
    public void userUpdateNameTest() {
        UserUpdateDataRequest userUpdateDataRequest = new UserUpdateDataRequest(EMAIL, NAME_UPDATE);
        userService.updateUser(userUpdateDataRequest, token).then().statusCode(200).body("success", is(true));
    }

    @Test
    @DisplayName("Изменение емейла пользователя с авторизацией")
    public void userUpdateEmailTest() {
        UserUpdateDataRequest userUpdateDataRequest = new UserUpdateDataRequest(EMAIL_UPDATE, NAME);
        userService.updateUser(userUpdateDataRequest, token).then().statusCode(200).body("success", is(true));
    }

    @Test
    @DisplayName("Изменение имени и емейла пользователя с авторизацией")
    public void userUpdateNameEmailTest() {
        UserUpdateDataRequest userUpdateDataRequest = new UserUpdateDataRequest(EMAIL_UPDATE, NAME_UPDATE);
        userService.updateUser(userUpdateDataRequest, token).then().statusCode(200).body("success", is(true));
    }

    @Test
    @DisplayName("Изменение имя пользователя без авторизаци")
    public void userWithoutTokenUpdateTest() {
        UserUpdateDataRequest userUpdateDataRequest = new UserUpdateDataRequest(EMAIL, NAME);
        userService.updateUser(userUpdateDataRequest, StringUtils.EMPTY).then().statusCode(401).body("success", is(false), "message", is("You should be authorised"));
    }

    @After
    public void after() {
        deleteUser();
    }
}
