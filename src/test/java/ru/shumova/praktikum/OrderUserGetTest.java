package ru.shumova.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.shumova.praktikum.dto.UserLoginRequest;
import ru.shumova.praktikum.service.OrderService;

import static org.hamcrest.Matchers.is;
import static ru.shumova.praktikum.config.TestData.EMAIL;
import static ru.shumova.praktikum.config.TestData.PASSWORD;

public class OrderUserGetTest extends AbstractTest {
    OrderService orderService = new OrderService();

    @Before
    public void init() {
        createUser();
        token = userService.loginUser(new UserLoginRequest(EMAIL, PASSWORD)).then().extract().body().path("accessToken");
    }

    @Test
    @DisplayName("Получение заказов авторизованного пользователя")
    public void orderUserGetTest() {
        orderService.getOrder(token).then().statusCode(200).body("success", is(true));
    }

    @Test
    @DisplayName("Получение заказов неавторизованного пользователя")
    public void orderUserWithoutLoginGetTest() {
        orderService.getOrder(StringUtils.EMPTY).then().statusCode(401).body("success", is(false), "message", is("You should be authorised"));
    }

    @After
    public void after() {
        deleteUser();
    }
}
