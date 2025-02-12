package ru.shumova.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import ru.shumova.praktikum.dto.UserCreateRequest;

import static org.hamcrest.Matchers.is;
import static ru.shumova.praktikum.config.TestData.*;

public class UserCreateTest extends AbstractTest {

    @Test
    @DisplayName("Создание уникального пользователя")
    public void newUserCreateTest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest(EMAIL, PASSWORD, NAME);
        userService.createUser(userCreateRequest).then().statusCode(200).body("success", is(true));
        deleteUser();
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    public void userAlreadyExistsCreateTest() {
        createUser();
        UserCreateRequest userCreateRequest = new UserCreateRequest(EMAIL, PASSWORD, NAME);
        userService.createUser(userCreateRequest).then().statusCode(403).body("success", is(false),"message", is("User already exists"));
        deleteUser();
    }

    @Test
    @DisplayName("Создание пользователя с незаполненым одним из обязательных полей")
    public void userWithoutNameCreateTest() {
        UserCreateRequest userCreateRequest = new UserCreateRequest(EMAIL, PASSWORD, StringUtils.EMPTY);
        userService.createUser(userCreateRequest).then().statusCode(403).body("success", is(false),"message", is("Email, password and name are required fields"));
    }
}
