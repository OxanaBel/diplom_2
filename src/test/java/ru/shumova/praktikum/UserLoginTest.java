package ru.shumova.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.shumova.praktikum.dto.UserLoginRequest;

import static org.hamcrest.Matchers.is;
import static ru.shumova.praktikum.config.TestData.*;

public class UserLoginTest extends AbstractTest {

    @Before
    public void init() {
        createUser();
    }

    @Test
    @DisplayName("Логин под существующим пользователем")
    public void existingUserLoginTest() {
        UserLoginRequest userLoginRequest = new UserLoginRequest(EMAIL, PASSWORD);
        userService.loginUser(userLoginRequest).then().statusCode(200).body("success", is(true));
    }

    @Test
    @DisplayName("Логин с неверным логином и паролем")
    public void incorrectUserLoginTest() {
        UserLoginRequest userLoginRequest = new UserLoginRequest(INCORRECT_EMAIL, INCORRECT_PASSWORD);
        userService.loginUser(userLoginRequest).then().statusCode(401).body("success", is(false), "message", is("email or password are incorrect"));
    }

    @After
    public void after() {
        deleteUser();
    }
}
